package main.kotlin.oicnanev.org.geocell.domain.entities

import kotlinx.datetime.Instant
import main.kotlin.oicnanev.org.geocell.domain.TokenValidationInfo

data class Token(
    val tokenValidationInfo: TokenValidationInfo,
    val userId: Int,
    val createdAt: Instant,
    val lastUsedAt: Instant
)
