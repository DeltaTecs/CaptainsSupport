package io.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Handles copying and sending logs
 */
public final class LogHandler {

    private static final Logger LOGGER = LogManager.getLogger(LogHandler.class.getName());

    public static String LOG_DIR = "logs";


    private LogHandler() {
    }

    /**
     * Copies the latest.log to the old.log while overwriting it
     */
    public static void renameLogFile() {

        Path copy = Paths.get(LOG_DIR + File.separator + "old.txt");
        Path original = Paths.get(LOG_DIR + File.separator + "latest.txt");
        try {
            Files.copy(original, copy, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            LOGGER.error("Failed copying log files", e);
        }
    }

}
