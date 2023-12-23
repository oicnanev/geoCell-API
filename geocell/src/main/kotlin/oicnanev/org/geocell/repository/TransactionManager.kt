package main.kotlin.oicnanev.org.geocell.repository

interface TransactionManager {
    fun <R> run(block: (Transaction) -> R): R
}
