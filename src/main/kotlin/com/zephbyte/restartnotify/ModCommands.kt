package com.zephbyte.restartnotify

import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

object ModCommands {

    fun register() {
        CommandRegistrationCallback.EVENT.register { dispatcher, registryAccess, environment ->
            //registerInitiateRestartNotify(dispatcher)
            registerTimeCommands(dispatcher)
        }
    }

//    private fun registerInitiateRestartNotify(
//        dispatcher: CommandDispatcher<ServerCommandSource>
//    ) {
//        dispatcher.register(
//            CommandManager.literal(MOD_ID)
//                .then(
//                    CommandManager.literal("start")
//                        .requires { source -> source.hasPermissionLevel(2) }
//                        .executes { context ->
//                            val countdown = Countdown(context)
//                            countdown.initiateCountdown()
//                            1
//                        }
//                )
//        )
//    }

    private fun registerTimeCommands(
        dispatcher: CommandDispatcher<ServerCommandSource>
    ) {
        val warningTimes = intArrayOf(20, 15, 10, 5, 1)

        for(time in warningTimes) {
            dispatcher.register(
                CommandManager.literal(MOD_ID)
                    .then(
                        CommandManager.literal(time.toString())
                            .requires { source -> source.hasPermissionLevel(2) }
                            .executes { context ->
                                val countdown = Countdown(context)
                                countdown.broadcastMessage(time)
                                1
                            }
                    )
            )
        }
    }
}