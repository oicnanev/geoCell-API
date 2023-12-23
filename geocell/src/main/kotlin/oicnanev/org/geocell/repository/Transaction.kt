package main.kotlin.oicnanev.org.geocell.repository

interface Transaction {
    val userRepository: UserRepository

    fun rollback()
}
