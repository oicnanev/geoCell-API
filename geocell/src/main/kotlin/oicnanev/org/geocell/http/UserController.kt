package oicnanev.org.geocell

import oicnanev.org.geocell.domain.AuthenticatedUser
import oicnanev.org.geocell.http.model.Problem
import oicnanev.org.geocell.http.model.UserCreateInputModel
import oicnanev.org.geocell.http.model.UserCreateTokenInputModel
import oicnanev.org.geocell.http.model.UserHomeOutputModel
import oicnanev.org.geocell.http.model.UserTokenCreateOutputModel
import oicnanev.org.geocell.services.TokenCreationError
import oicnanev.org.geocell.services.UserCreationError
import oicnanev.org.geocell.services.UserHomeOutputModelError
import oicnanev.org.geocell.services.UserHomeOutputModelResult
import oicnanev.org.geocell.services.UserService
import oicnanev.org.geocell.utils.Failure
import oicnanev.org.geocell.utils.Success
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

}
