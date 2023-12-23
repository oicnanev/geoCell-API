package main.kotlin.oicnanev.org.geocell.http

import main.kotlin.oicnanev.org.geocell.domain.AuthenticatedUser
import main.kotlin.oicnanev.org.geocell.http.model.Problem
import main.kotlin.oicnanev.org.geocell.http.model.UserCreateInputModel
import main.kotlin.oicnanev.org.geocell.http.model.UserCreateTokenInputModel
import main.kotlin.oicnanev.org.geocell.http.model.UserHomeOutputModel
import main.kotlin.oicnanev.org.geocell.http.model.UserTokenCreateOutputModel
import main.kotlin.oicnanev.org.geocell.services.TokenCreationError
import main.kotlin.oicnanev.org.geocell.services.UserCreationError
import main.kotlin.oicnanev.org.geocell.services.UserHomeOutputModelError
import main.kotlin.oicnanev.org.geocell.services.UserHomeOutputModelResult
import main.kotlin.oicnanev.org.geocell.services.UserService
import main.kotlin.oicnanev.org.geocell.utils.Failure
import main.kotlin.oicnanev.org.geocell.utils.Success
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {
    @PostMapping(Uris.Users.CREATE)
    fun create(@RequestBody input: UserCreateInputModel): ResponseEntity<*> {
        logger.info("createUser $input.username")
        val res = userService.createUser(input.username, input.password)
        return when (res) {
            is Success -> ResponseEntity
                .status(201)
                .header("Location", Uris.Users.byId(res.value).toASCIIString()).build<Unit>()
            is Failure -> when (res.value) {
                UserCreationError.InsecurePassword -> Problem.response(400, Problem.insecurePassword)
                UserCreationError.UserAlreadyExists -> Problem.response(400, Problem.userAlreadyExists)
            }
        }
    }

    @PostMapping(Uris.Users.TOKEN)
    fun token(@RequestBody input: UserCreateTokenInputModel, response: HttpServletResponse): ResponseEntity<*> {
        logger.info("login $input.username")
        val res = userService.createToken(input.username, input.password)
        return when (res) {
            is Success -> {
                response.addCookie(createCookie("t", res.value.tokenValue, true))
                response.addCookie(createCookie("u", input.username, false))
                ResponseEntity
                    .status(200)
                    .body(UserTokenCreateOutputModel(res.value.tokenValue))
            }
            is Failure -> when (res.value) {
                TokenCreationError.UserOrPasswordAreInvalid ->
                    Problem.response(400, Problem.userOrPasswordAreInvalid)
            }
        }
    }

    @PostMapping(Uris.Users.LOGOUT)
    fun logout(user: AuthenticatedUser): ResponseEntity<*> {
        logger.info("logout $user.username")
        userService.revokeToken(user.token)
        return ResponseEntity
            .status(200)
            .headers {
                it.add("Set-Cookie", "t=; Max-Age=0; Path=/; HttpOnly; Secure; SameSite=Strict")
                it.add("Set-Cookie", "u=; Max-Age=0; Path=/; Secure; SameSite=Strict")
            }
            .build<Unit>()
    }

    @GetMapping(Uris.Users.GET_BY_ID)
    fun getById(@PathVariable id: String): ResponseEntity<*> =
        getResponseForUserIdRequest(userService.getUserById(id))

    @GetMapping(Uris.Users.Home)
    fun getUserHome(authenticatedUser: AuthenticatedUser): ResponseEntity<*> =
        getResponseForUserIdRequest(userService.getUserHome(authenticatedUser.user.id))

    private fun createCookie(name: String, value: String?, httpOnly: Boolean): Cookie {
        logger.info("createCookie $name: $value")
        val tokenValue = value?.trim()
        val cookie = Cookie(name, tokenValue)
        cookie.path = "/"
        cookie.isHttpOnly = httpOnly
        cookie.secure = true
        cookie.setAttribute("SameSite", "Strict")
        cookie.maxAge = 3600

        return cookie
    }

    private fun getResponseForUserIdRequest(outputModel: UserHomeOutputModelResult): ResponseEntity<*> {
        logger.info("getUserHome $outputModel")
        return when (outputModel) {
            is Success -> ResponseEntity
                .status(200)
                .body(UserHomeOutputModel(userHomeOutputModel.value.id, userHomeOutputModel.value.username))
            is Failure -> when (outputModel.value) {
                UserHomeOutputModelError.InvalidUser ->
                    Problem.response(400, Problem.userNotFound)
            }
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(UserController::class.java)
    }
}
