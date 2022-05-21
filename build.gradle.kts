import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
    id("com.avast.gradle.docker-compose") version "0.15.2"
}

group = "net.javaman"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("aws.sdk.kotlin:dynamodb:0.15.0")
    implementation("dev.kord:kord-core:0.8.0-M13")
    constraints {
        implementation("io.ktor:ktor-client-okhttp:2.0.0")
    }
    testImplementation(kotlin("test"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}

application {
    this.mainClass.set("net.javaman.guavapicker.MainKt")
}

dockerCompose {
    useComposeFiles.add("docker-compose-test.yml")
}

tasks.test {
    useJUnitPlatform()
}
