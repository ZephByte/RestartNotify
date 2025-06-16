package com.zephbyte.restartnotify

import net.fabricmc.api.ModInitializer

class RestartNotify : ModInitializer {

    override fun onInitialize() {
        LOGGER.info("Restart Notify mod initializing...")

        ConfigManager.loadConfig()
        ModCommands.register()

        LOGGER.info("Restart Notify mod initialized.")
    }
}
