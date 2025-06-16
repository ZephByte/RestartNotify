package com.zephbyte.restartnotify

import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

object ModCommands {

    fun register() {
        CommandRegistrationCallback.EVENT.register { dispatcher, registryAccess, environment ->
            registerInitiateRestartNotify(dispatcher)
        }
    }

    private fun registerInitiateRestartNotify(
        dispatcher: CommandDispatcher<ServerCommandSource>
    ) {
        dispatcher.register(
            CommandManager.literal(MOD_ID)
                .then(
                    CommandManager.literal("start")
                        .requires { source -> source.hasPermissionLevel(2) }
                        .executes { context ->
                            val countdown = Countdown(context)
                            countdown.initiateCountdown()
                            1
                        }
                )
        )
    }
}