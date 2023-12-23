package main.kotlin.oicnanev.org.geocell.repository

import main.kotlin.oicnanev.org.geocell.domain.PasswordValidationInfo
import main.kotlin.oicnanev.org.geocell.domain.TokenValidationInfo
import main.kotlin.oicnanev.org.geocell.domain.entities.Token
import main.kotlin.oicnanev.org.geocell.domain.entities.User
import kotlinx.datetime.Instant

interface UserRepository {
    fun storeUser(
        username: String,
        passwordVAlidation: PasswordValidationInfo
    ) : Int

    fun getUserById(id: Int): User?

    fun getUserUsernameById(id: Int): String?

    fun getUserByUsername(username: String): User?

    fun getTokenByTokenValidationInfo(tokenValidationInfo: TokenValidationInfo): Pair<User, Token>?

    fun isUserStoredByUsername(username: String): Boolean

    fun createToken(token: Token, maxTokens: Int)

    fun updateTokenLastUsed(token: Token, now: Instant)

    fun removeTokenByValidationInfo(tokenValidationInfo: TokenValidationInfo): Int

    fun getUserIDByUsername(username1: String): Int
}