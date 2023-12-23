package oicnanev.org.geocell

import oicnanev.org.geocell.services.UserService
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

}
