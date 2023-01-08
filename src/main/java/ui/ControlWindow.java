package ui;

import io.IO;
import io.Images;
import io.logging.LogHandler;
import main.Launcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.panes.ControlPane;
import ui.panes.LoadingPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class ControlWindow {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 1000;
    public static final Rectangle WINDOW_BOUNDS = new Rectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    public static final int SCROLL_SPEED = 16;

    private static final Logger LOGGER = LogManager.getLogger(ControlWindow.class.getName());

    private static JFrame frame = null;


    private ControlWindow() {
    }

    /**
     * Opens a new JFrame for the Control window with some hello text. Stores JFrame instance to the class
     */
    public static void create() throws InterruptedException {

        LOGGER.debug("Opening control frame");

        frame = new JFrame("Captain's Engineer " + Launcher.VERSION);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLocation(frame.getLocation().x - WINDOW_WIDTH / 2, frame.getLocation().y - WINDOW_HEIGHT / 2);
        CrashHandler.setReferenceFrame(frame);

        LoadingPane loadingPane = new LoadingPane();
        frame.getContentPane().add(loadingPane);
        frame.getContentPane().setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        frame.validate();
        frame.pack();
        frame.repaint();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                LOGGER.trace("User closing control frame");
                onClose();
            }
        });

        // no init the rest
        Images.loadAll();
        IO.loadSettings();

        ControlPane controlPane = new ControlPane();
        JScrollPane scrollControl = new JScrollPane(controlPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollControl.getVerticalScrollBar().setUnitIncrement(ControlWindow.SCROLL_SPEED);
        scrollControl.setBounds(WINDOW_BOUNDS);
        scrollControl.setVisible(true);
        frame.getContentPane().remove(loadingPane);
        frame.getContentPane().add(scrollControl);
        frame.validate();
        frame.pack();
        frame.repaint();
    }

    private static void onClose() {
        LogHandler.renameLogFile();

        LOGGER.info("exiting");
        System.exit(0);
    }

}
