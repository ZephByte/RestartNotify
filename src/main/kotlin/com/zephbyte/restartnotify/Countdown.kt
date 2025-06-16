package com.zephbyte.restartnotify

import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import net.minecraft.util.Formatting

class Countdown(private val context: CommandContext<ServerCommandSource>) { // context is an instance property

    fun initiateCountdown() {
        broadcastMessage() // Uses the instance's context
    }

    private fun broadcastMessage() {
        // 'context' is available directly from the class instance
        context.source.server.playerManager.playerList.forEach { player ->
            player.sendMessage(
                Text.literal("Daily restart in 15 minutes!").formatted(
                    Formatting.DARK_PURPLE
                ), true
            )
        }
    }
}
    