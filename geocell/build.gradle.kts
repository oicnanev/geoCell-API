import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.spring") version "1.9.21"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
}

group = "oicnanev.org"
version = "0.0.1-SNAPSHOT"

java {
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // for JDBI
    implementation("org.jdbi:jdbi3-core:3.37.1")
    implementation("org.jdbi:jdbi3-kotlin:3.37.1")
    implementation("org.jdbi:jdbi3-postgres:3.37.1")
    implementation("org.postgresql:postgresql:42.5.4")

    // To use Kotlin specific date and time functions
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")

    // To get passwords encode
    implementation("org.springframework.security:spring-security-core:6.0.2")
    implementation("junit:junit:4.13.1")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")

    // To use WebTestClient
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation(kotlin("test"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}