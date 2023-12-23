package oicnanev.org.geocell.domain

import oicnanev.org.geocell.domain.entities.User

data class AuthenticatedUser(
        val user: User,
        val token: String
)