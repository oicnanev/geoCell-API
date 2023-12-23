package main.kotlin.oicnanev.org.geocell.domain

interface TokenEncoder {
    fun createValidationInformation(token: String): TokenValidationInfo
}