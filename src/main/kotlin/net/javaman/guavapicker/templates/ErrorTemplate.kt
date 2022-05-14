package net.javaman.guavapicker.templates

import dev.kord.rest.builder.message.modify.InteractionResponseModifyBuilder
import dev.kord.rest.builder.message.modify.actionRow
import dev.kord.rest.builder.message.modify.embed
import java.util.UUID
import kotlinx.datetime.Clock

object ErrorTemplate {
    operator fun invoke(e: Exception, uuid: UUID): InteractionResponseModifyBuilder.() -> Unit = {
        embed {
            EmbedTemplate("Something went wrong", "```${e.message}```")()
            footer {
                text = uuid.toString()
            }
            timestamp = Clock.System.now()
        }
        actionRow {
            linkButton("https://github.com/TheNewJavaman/guava-picker/issues/new") {
                label = "Report on GitHub"
            }
        }
    }
}