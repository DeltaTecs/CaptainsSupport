package ui;

import io.logging.LogHandler;
import main.Launcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class ControlWindow {

    private static final Logger LOGGER = LogManager.getLogger(ControlWindow.class.getName());

    private static JFrame frame = null;


    private ControlWindow() {}

    /**
     * Opens a new JFrame for the Control window with some hello text. Stores JFrame instance to the class
     */
    public static void create() {

        LOGGER.debug("Opening control frame");

        frame = new JFrame("Captain's Engineer Controller " + Launcher.VERSION);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                LOGGER.trace("User closing control frame");
                onClose();
            }
        });
    }

    private static void onClose() {
        LogHandler.renameLogFile();

        LOGGER.info("exiting");
        System.exit(0);
    }

}
