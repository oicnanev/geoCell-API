package main.kotlin.oicnanev.org.geocell

import main.kotlin.oicnanev.org.geocell.domain.Sha256TokenEncoder
import main.kotlin.oicnanev.org.geocell.domain.UserDomainConfig
import main.kotlin.oicnanev.org.geocell.http.pipeline.AuthenticatedUserArgumentResolver
import main.kotlin.oicnanev.org.geocell.http.pipeline.AuthenticationInterceptor
import main.kotlin.oicnanev.org.geocell.repository.jdbi.configureWithAppRequirements
import kotlinx.datetime.Clock
import org.jdbi.v3.core.Jdbi
import org.postgresql.ds.PGSimpleDataSource
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import kotlin.time.Duration.Companion.hours

@SpringBootApplication
class GeocellApplication

fun main(args: Array<String>) { runApplication<GeocellApplication>(*args) }
