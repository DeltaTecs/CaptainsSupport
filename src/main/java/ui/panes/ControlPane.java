package ui.panes;

import io.Images;
import io.SoundManager;
import main.Launcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.ControlWindow;
import ui.RewardBinderWindow;
import ui.UIException;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel for all content of the Control Window
 */
public class ControlPane extends JPanel {

    private static final Logger LOGGER = LogManager.getLogger(ControlPane.class.getName());

    private JTextPane textFieldWhatsNew;

    private ConfigPane configPane;

    private SoundsPane soundsPane;

    public ControlPane() {
        this.setBounds(0, 0, 10000, ControlWindow.WINDOW_HEIGHT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        // Whats new field
        textFieldWhatsNew = new JTextPane();
        textFieldWhatsNew.setText("Was neu in Version " + Launcher.VERSION + ":\n" + Launcher.LATEST_CHANGES);
        JScrollPane scrollFieldWhatsNew = new JScrollPane(textFieldWhatsNew);
        scrollFieldWhatsNew.setBounds(0, 0, 450, 180);
        scrollFieldWhatsNew.setMaximumSize(new Dimension(ControlWindow.WINDOW_WIDTH, 180));

        // Save changes button
        JButton buttonSave = new JButton("Speichern");
        buttonSave.setPreferredSize(new Dimension(200, 50));
        buttonSave.addActionListener(e -> saveChanges());

        // Stop sounds button
        JButton buttonStopSounds = new JButton(Images.stop);
        buttonStopSounds.setPreferredSize(new Dimension(50, 50));
        buttonStopSounds.addActionListener(e -> SoundManager.stopAll());

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        panelButtons.add(buttonSave);
        panelButtons.add(buttonStopSounds);


        // Config Values editor
        try {
            configPane = new ConfigPane();
        } catch (IllegalAccessException e) {
            LOGGER.error("Failed instantiating config pane", e);
            throw new UIException("Config werte einlesen futschikato");
        }

        JScrollPane scrollConfig = new JScrollPane(configPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollConfig.getVerticalScrollBar().setUnitIncrement(ControlWindow.SCROLL_SPEED);
        scrollConfig.setBounds(0, 300, ControlWindow.WINDOW_WIDTH, 400);
        scrollConfig.setPreferredSize(new Dimension(ControlWindow.WINDOW_WIDTH, 400));

        // Sound volume sliders
        soundsPane = new SoundsPane();
        JScrollPane scrollSounds = new JScrollPane(soundsPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollSounds.getVerticalScrollBar().setUnitIncrement(ControlWindow.SCROLL_SPEED);
        scrollSounds.setBounds(0, 300, ControlWindow.WINDOW_WIDTH, 400);
        scrollSounds.setPreferredSize(new Dimension(ControlWindow.WINDOW_WIDTH, 400));


        this.add(scrollFieldWhatsNew);
        this.add(panelButtons);
        this.add(scrollSounds);
        this.add(scrollConfig);
        this.repaint();

        new RewardBinderWindow();
    }


    private void saveChanges() {
        LOGGER.trace("Saving changes...");
        String error = configPane.saveConfig();

        if (error != null) {
            JOptionPane.showMessageDialog(new JFrame(), error, "Also Config speichern ging jetzt nicht", JOptionPane.ERROR_MESSAGE);
        }

        soundsPane.save();

        configPane.refresh();
        soundsPane.refresh();
    }



}
