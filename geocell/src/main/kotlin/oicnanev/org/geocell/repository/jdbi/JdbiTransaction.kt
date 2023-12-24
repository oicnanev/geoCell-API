package main.kotlin.oicnanev.org.geocell.repository.jdbi

import main.kotlin.oicnanev.org.geocell.repository.Transaction
import main.kotlin.oicnanev.org.geocell.repository.UserRepository
import org.jdbi.v3.core.Handle

class JdbiTransaction (private val handle: Handle): Transaction {
    override val userRepository: UserRepository = JdbiUserRespository(handle)

    override fun rollback() {
        handle.rollback()
    }
}
