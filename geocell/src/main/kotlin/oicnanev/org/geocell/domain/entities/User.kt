package main.kotlin.oicnanev.org.geocell.domain.entities

import main.kotlin.oicnanev.org.geocell.domain.PasswordValidationInfo

data class User(
        val id: Int,
        val username: String,
        val passwordValidation: PasswordValidationInfo
)
