package main.kotlin.oicnanev.org.geocell.repository.jdbi

import main.kotlin.oicnanev.org.geocell.repository.jdbi.mappers.InstantMapper
import main.kotlin.oicnanev.org.geocell.repository.jdbi.mappers.PasswordValidationInfoMapper
import main.kotlin.oicnanev.org.geocell.repository.jdbi.mappers.TokenValidationInfoMapper
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.postgres.PostgresPlugin

fun Jdbi.configureWithAppRequirements(): Jdbi {
    installPlugin(KotlinPlugin())
    installPlugin(PostgresPlugin())

    registerColumnMapper(PasswordValidationInfoMapper())
    registerColumnMapper(TokenValidationInfoMapper())
    registerColumnMapper(InstantMapper())

    return this
}
