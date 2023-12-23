package main.kotlin.oicnanev.org.geocell.domain.entities

import main.kotlin.oicnanev.org.geocell.domain.TokenValidationInfo
import kotlinx.datetime.Instant

data class Token(
        val tokenValidationInfo: TokenValidationInfo,
        val userId: Int,
        val createdAt: Instant,
        val lastUsedAt: Instant
)
