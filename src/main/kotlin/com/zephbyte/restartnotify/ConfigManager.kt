package com.zephbyte.restartnotify

import com.electronwill.nightconfig.core.file.CommentedFileConfig
import com.electronwill.nightconfig.core.io.WritingMode
import net.fabricmc.loader.api.FabricLoader

object ConfigManager {

    // Default values
    private val CONFIG_PATH = FabricLoader.getInstance().configDir.resolve("$MOD_ID.toml")

    private const val DEFAULT_RESTART_MESSAGE = "Server restarting in"
    private const val DEFAULT_FIRST_WARNING_TIME = 15
    private const val DEFAULT_COUNTDOWN_LAST_MINUTE = true

    // Config properties
    var restartMessage: String = DEFAULT_RESTART_MESSAGE
    var firstWarningTime: Int = DEFAULT_FIRST_WARNING_TIME
    var countdownLastMinute: Boolean = DEFAULT_COUNTDOWN_LAST_MINUTE

    fun loadConfig() {
        val builder = CommentedFileConfig.builder(CONFIG_PATH)
            .autosave()
            .writingMode(WritingMode.REPLACE)

        val config = builder.build()
        config.load()

        restartMessage = config.getOptional<String>("general.restartMessage").orElse(DEFAULT_RESTART_MESSAGE)
        firstWarningTime = config.getOptional<Int>("firstWarningTime").orElse(DEFAULT_FIRST_WARNING_TIME)
        countdownLastMinute = config.getOptional<Boolean>("countdownLastMinute").orElse(DEFAULT_COUNTDOWN_LAST_MINUTE)

        if (!config.contains("general.restartMessage")) {
            config.set<String>("general.restartMessage", DEFAULT_RESTART_MESSAGE)
            config.setComment("general.restartMessage", " The restart message to display to players when restart is upcoming.")
        }

        if (!config.contains("firstWarningTime")) {
            config.set<Int>("general.firstWarningTime", DEFAULT_FIRST_WARNING_TIME)
            config.setComment(
                "general.firstWarningTime",
                "When should the warnings start? (ie. 15 = 15 minutes before restart.)"
            )
        }

        if (!config.contains("countdownLastMinute")) {
            config.set<Boolean>("general.countdownLastMinute", DEFAULT_COUNTDOWN_LAST_MINUTE)
            config.setComment(
                "general.countdownLastMinute",
                "Should the last minute have a 60 second countdown?"
            )
        }

        config.save() // Save any changes or defaults
        config.close() // Close the file

        LOGGER.info("Config loaded.")
    }
}