package net.javaman.guavapicker.sql

import mu.KotlinLogging
import org.jetbrains.exposed.sql.Database

private val logger = KotlinLogging.logger {}

fun startDatabase() {
    logger.info { "Starting database client" }
    Database.connect(
        "jdbc:mysql://127.0.0.1:3306/guavapicker",
        user = System.getenv("GUAVA_MYSQL_USER"),
        password = System.getenv("GUAVA_MYSQL_PASSWORD")
    )
    logger.info { "Started database client" }
}