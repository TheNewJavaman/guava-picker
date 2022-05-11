import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath("mysql:mysql-connector-java:8.0.29")
        classpath("org.flywaydb:flyway-mysql:8.5.10")
    }
}

plugins {
    application
    kotlin("jvm") version "1.6.21"
    id("org.flywaydb.flyway") version "8.5.10"
    id("com.avast.gradle.docker-compose") version "0.15.2"
}

group = "net.javaman"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("mysql:mysql-connector-java:8.0.29")
    implementation("org.jetbrains.exposed:exposed-core:0.38.2")
    implementation("org.jetbrains.exposed:exposed-dao:0.38.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.38.2")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.38.2")
    implementation("dev.kord:kord-core:0.8.0-M13")
    testImplementation(kotlin("test"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}

application {
    this.mainClass.set("net.javaman.guavapicker.MainKt")
}

flyway {
    url = "jdbc:mysql://127.0.0.1:3306/guavapicker"
    user = System.getenv("GUAVA_MYSQL_USER")
    password = System.getenv("GUAVA_MYSQL_PASSWORD")
}

dockerCompose {
    useComposeFiles.add("docker-compose.yml")
}

tasks.test {
    useJUnitPlatform()
}
