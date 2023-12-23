package main.kotlin.oicnanev.org.geocell.services

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import main.kotlin.oicnanev.org.geocell.domain.UserDomain
import main.kotlin.oicnanev.org.geocell.domain.entities.Token
import main.kotlin.oicnanev.org.geocell.domain.entities.User
import main.kotlin.oicnanev.org.geocell.http.model.UserHomeOutputModel
import main.kotlin.oicnanev.org.geocell.repository.TransactionManager
import main.kotlin.oicnanev.org.geocell.utils.Either
import main.kotlin.oicnanev.org.geocell.utils.failure
import main.kotlin.oicnanev.org.geocell.utils.success
import org.springframework.stereotype.Component

data class TokenExternalInfo(
    val tokenValue: String,
    val tokenExpiration: Instant
)

sealed class UserCreationError {
    object UserAlreadyExists : UserCreationError()
    object InsecurePassword : UserCreationError()
}
typealias UserCreationResult = Either<UserCreationError, Int>

sealed class TokenCreationError {
    object UserOrPasswordAreInvalid : TokenCreationError()
}
typealias TokenCreationResult = Either<TokenCreationError, TokenExternalInfo>

sealed class UserHomeOutputModelError {
    object InvalidUser : UserHomeOutputModelError()
}
typealias UserHomeOutputModelResult = Either<UserHomeOutputModelError, UserHomeOutputModel>

@Component
class UserService(
    private val transactionManager: TransactionManager,
    private val userDomain: UserDomain,
    private val clock: Clock
) {
    fun createUser(username: String, password: String): UserCreationResult {
        if (userDomain.isSafePassword(password)) {
            return failure(UserCreationError.InsecurePassword)
        }

        val passwordValidationInfo = userDomain.createPasswordValidationInformation(password)

        return transactionManager.run {
            val userRepository = it.userRepository
            if (userRepository.isUserStoredByUsername(username)) {
                failure(UserCreationError.UserAlreadyExists)
            } else {
                val id = userRepository.storeUser(username, passwordValidationInfo)
                success(id)
            }
        }
    }

    fun createToken(username: String, password: String): TokenCreationResult {
        if (username.isBlank() || password.isBlank()) {
            failure(TokenCreationError.UserOrPasswordAreInvalid)
        }
        return transactionManager.run {
            val userRepository = it.userRepository
            val user: User = userRepository.getUserByUsername(username)
                ?: return@run failure(TokenCreationError.UserOrPasswordAreInvalid)
            if (!userDomain.validatePassword(password, user.passwordValidation)) {
                return@run failure(TokenCreationError.UserOrPasswordAreInvalid)
            }
            val tokenValue = userDomain.generateTokenValue()
            val now = clock.now()
            val newToken = Token(
                userDomain.createTokenValidationInformation(tokenValue),
                user.id,
                createdAt = now,
                lastUsedAt = now
            )
            userRepository.createToken(newToken, userDomain.maxNumberOfTokensPerUser)
            Either.Right(TokenExternalInfo(tokenValue, userDomain.getTokenExpiration(newToken)))
        }
    }

    fun getUserByToken(token: String): User? {
        if (!userDomain.canBeToken(token)) {
            return null
        }
        return transactionManager.run {
            val usersRepository = it.userRepository
            val tokenValidationInfo = userDomain.createTokenValidationInformation(token)
            val userAndToken = usersRepository.getTokenByTokenValidationInfo(tokenValidationInfo)
            if (userAndToken != null && userDomain.isTokenTimeValid(clock, userAndToken.second)) {
                usersRepository.updateTokenLastUsed(userAndToken.second, clock.now())
                userAndToken.first
            } else {
                null
            }
        }
    }

    fun getUserById(id: String): UserHomeOutputModelResult {
        return transactionManager.run {
            val userRepository = it.userRepository
            val user = userRepository.getUserById(id.toInt())
            if (user == null) {
                failure(UserHomeOutputModelError.InvalidUser)
            } else {
                success(UserHomeOutputModel(id = user.id, username = user.username))
            }
        }
    }
}
