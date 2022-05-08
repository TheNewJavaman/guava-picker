package net.javaman.guavapicker

import dev.kord.core.Kord
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.event.interaction.GuildMessageCommandInteractionCreateEvent
import dev.kord.core.on
import org.jetbrains.exposed.sql.Database

suspend fun main() {
    Database.connect(
        "jdbc:mysql://127.0.0.1:3306/guavapicker",
        user = System.getenv("GUAVA_MYSQL_USER"),
        password = System.getenv("GUAVA_MYSQL_PASSWORD")
    )
    val kord = Kord(System.getenv("GUAVA_DISCORD_TOKEN"))
    CreateCommand.register(kord)
    AddRoleCommand.register(kord)
    kord.on<GuildChatInputCommandInteractionCreateEvent> on@{
        if (CreateCommand.handle(this)) return@on
    }
    kord.on<GuildMessageCommandInteractionCreateEvent> on@{
        if (AddRoleCommand.handle(this)) return@on
    }
    kord.login()
}