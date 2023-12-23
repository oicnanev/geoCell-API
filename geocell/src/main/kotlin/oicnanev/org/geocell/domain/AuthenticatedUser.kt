package main.kotlin.oicnanev.org.geocell.domain

import main.kotlin.oicnanev.org.geocell.domain.entities.User

data class AuthenticatedUser(
    val user: User,
    val token: String
)
