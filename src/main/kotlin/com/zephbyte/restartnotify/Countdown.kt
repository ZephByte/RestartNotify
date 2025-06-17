package com.zephbyte.restartnotify

import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import net.minecraft.util.Formatting

class Countdown(private val context: CommandContext<ServerCommandSource>) { // context is an instance property

//    fun initiateCountdown() {
//        broadcastMessage() // Uses the instance's context
//    }

    fun broadcastMessage(timeLeft: Int) {
        // 'context' is available directly from the class instance
        context.source.server.playerManager.playerList.forEach { player ->
            player.sendMessage(
                Text.literal("Daily restart in $timeLeft minute${if (timeLeft > 1) "s" else ""}!").formatted(
                    Formatting.LIGHT_PURPLE, Formatting.BOLD
                ), true
            )
        }
    }
}
    