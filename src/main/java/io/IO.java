package io;

import io.serializeables.Settings;
import main.BotWarning;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public final class IO {

    private static final Logger LOGGER = LogManager.getLogger(IO.class.getName());

    private static final String FOLDER = "saved";
    public static final String PATH_SETTINGS = FOLDER + File.separator + "settings.serial";
    public static Settings settings = new Settings();
    private static Serializer<Settings> serializerSettings  = new Serializer<>(PATH_SETTINGS);

    private IO() {
    }

    public static void loadSettings() {
        try {
            settings = serializerSettings.load();
        } catch (Exception e) {
            // failed loading settings, use default ones. Better than crashing bot
            throw new BotWarning(e, "Failed loading settings, defaulting...");
        }
    }

    public static void saveSettings() {
        try {
            serializerSettings.save(settings);
        } catch (Exception e) {
            // failed save settings, use default ones. Better than crashing bot
            throw new BotWarning(e, "Failed saving settings");
        }
        LOGGER.debug("Settings saved");
    }


}
