package io;

import io.serializeables.Settings;
import main.BotWarning;

import java.io.File;

public final class IO {


    private static final String FOLDER = "saved";
    private static final String FILENAME_SETTINGS = FOLDER + File.separator + "settings.serial";
    public static Settings settings = new Settings();
    private static Serializer<Settings> serializerSettings  = new Serializer<>(FILENAME_SETTINGS);

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
    }


}
