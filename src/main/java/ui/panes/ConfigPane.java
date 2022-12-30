package ui.panes;

import io.AppliedChanges;
import io.IO;
import io.serializeables.Settings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.ControlWindow;
import ui.UIException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Panel for settings like xp per chat
 */
public class ConfigPane extends JPanel {

    private static final Logger LOGGER = LogManager.getLogger(ConfigPane.class.getName());

    private HashMap<Field, JTextField> fields = new HashMap<Field, JTextField>();

    public ConfigPane() throws IllegalAccessException {
        super.setLayout(null);
        super.setBorder(new LineBorder(Color.BLACK, 1));


        JLabel labelDescription = new JLabel("Konfiguration, hovern für Beschreibung");
        labelDescription.setBounds(2, 0, 300, 20);
        super.add(labelDescription);


        int y = 20;
        for (Field field : Settings.class.getFields()) {

            if (!(field.getType().equals(int.class) || field.getType().equals(String.class) || field.getType().equals(boolean.class) || field.getType().equals(float.class) || field.getType().equals(double.class)))
                continue;

            final int LABEL_WIDTH = 160;

            JLabel label = new JLabel(field.getName());
            label.setBounds(10, y, LABEL_WIDTH, 20);
            JTextField inputField = new JTextField(field.get(IO.settings).toString());
            inputField.setBounds(LABEL_WIDTH + 20, y, ControlWindow.WINDOW_WIDTH - LABEL_WIDTH - 40, 20);
            label.setToolTipText(Settings.descriptors.get(field));
            inputField.setToolTipText(Settings.descriptors.get(field));

            fields.put(field, inputField);
            this.add(label);
            this.add(inputField);
            y += 20;
        }
        this.setBounds(0, 0, ControlWindow.WINDOW_WIDTH, y);
        this.setPreferredSize(new Dimension(ControlWindow.WINDOW_WIDTH, y));

    }

    /**
     * reloads text field values from config
     */
    public void refresh() {
        for (Field field : fields.keySet()) {
            try {
                fields.get(field).setText(field.get(IO.settings).toString());
            } catch (IllegalAccessException e) {
                LOGGER.error("Failed refreshing config fields", e);
                throw new UIException("Konfig Werte iwie futsch");
            }

        }
    }

    /**
     * Attempts to save all given config values. Returns null on success, Error String on Error
     *
     * @return null on success, error description on error
     */
    public String saveConfig() {

        Settings updated = IO.settings.clone();
        Settings original = IO.settings;

        // check format Strings (%s)
        final String[] formatStrings = new String[]{"msg_welcome", "msg_lurk", "msg_so"};
        for (String fString : formatStrings) {
            try {
                if (!fields.get(Settings.class.getField(fString)).getText().contains("%s")) {
                    return fString + " muss ein %s enthalten (ist Platzhalter)";
                }
            } catch (NoSuchFieldException e) {
                LOGGER.error("saving config failed: format string field not found", e);
                e.printStackTrace();
                throw new RuntimeException("Speichern ging nich");
            }
        }

        try {
            // check integers
            for (Field f : fields.keySet()) {
                if (f.getType().equals(String.class))
                    f.set(updated, fields.get(f).getText());
                else if (f.getType().equals(int.class)) {
                    try {
                        f.set(updated, Integer.parseInt(fields.get(f).getText()));
                    } catch (NumberFormatException e) {
                        return f.getName() + " muss eine ganze Zahl sein, nicht " + fields.get(f).getText();
                    }
                } else if (f.getType().equals(double.class)) {
                    try {
                        f.set(updated, Double.parseDouble(fields.get(f).getText().replace(',', '.')));
                    } catch (NumberFormatException e) {
                        return f.getName() + " muss eine Kommazahl sein, nicht " + fields.get(f).getText();
                    }
                } else if (f.getType().equals(float.class)) {
                    try {
                        f.set(updated, Float.parseFloat(fields.get(f).getText().replace(',', '.')));
                    } catch (NumberFormatException e) {
                        return f.getName() + " muss eine Kommazahl sein, nicht " + fields.get(f).getText();
                    }
                } else if (f.getType().equals(boolean.class)) {
                    if (!fields.get(f).getText().equalsIgnoreCase("true") && !fields.get(f).getText().equalsIgnoreCase("false"))
                        return f.getName() + " muss true (an/ja) oder false (nein/aus) sein, nicht " + fields.get(f).getText();
                    f.set(updated, Boolean.parseBoolean(fields.get(f).getText()));
                } else
                    throw new RuntimeException("Grütze im Kot");
            }

        } catch (IllegalAccessException e) {
            LOGGER.error("failed setting settings values", e);
            e.printStackTrace();
            throw new RuntimeException("Speichern ging nich");
        }

        AppliedChanges<Settings> changes = new AppliedChanges<>(original, updated);
        try {
            changes.detectChanges();
        } catch (IllegalAccessException e) {
            LOGGER.error("Error creating delta", e);
            e.printStackTrace();
        }

        IO.settings = updated;
        IO.saveSettings();
        LOGGER.info("Changes applied: " + changes.getChanges().size());
        return null;
    }

}
