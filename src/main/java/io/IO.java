package io;

import io.serializeables.RewardList;
import io.serializeables.Settings;
import main.BotWarning;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public final class IO {

    private static final Logger LOGGER = LogManager.getLogger(IO.class.getName());

    private static final String FOLDER = "saved";
    public static final String PATH_SETTINGS = FOLDER + File.separator + "settings.serial";
    public static final String PATH_REWARDS = FOLDER + File.separator + "rewards.serial";
    public static Settings settings = new Settings();
    public static RewardList rewards = new RewardList();
    private static Serializer<Settings> serializerSettings  = new Serializer<>(PATH_SETTINGS);
    private static Serializer<RewardList> serializerRewards  = new Serializer<>(PATH_REWARDS);

    private IO() {
    }

    public static void loadSettings() {
        try {
            settings = serializerSettings.load();
        } catch (Exception e) {
            throw new BotWarning(e, "Failed loading settings, defaulting...");
        }
    }

    public static void saveSettings() {
        try {
            serializerSettings.save(settings);
        } catch (Exception e) {
            throw new BotWarning(e, "Failed saving settings");
        }
        LOGGER.debug("Settings saved");
    }

    public static void loadRewards() {
        try {
            rewards = serializerRewards.load();
        } catch (Exception e) {
            throw new BotWarning(e, "Failed loading reward mappings");
        }
    }

    public static void saveRewards() {
        try {
            serializerRewards.save(rewards);
        } catch (Exception e) {
            throw new BotWarning(e, "Failed saving settings");
        }
        LOGGER.debug("Reward Mapping saved");
    }


}
