package main.kotlin.oicnanev.org.geocell.http

import main.kotlin.oicnanev.org.geocell.services.UserService
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

}
