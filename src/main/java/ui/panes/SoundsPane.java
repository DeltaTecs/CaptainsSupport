package ui.panes;

import io.IO;
import io.Images;
import io.SoundManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.ControlWindow;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class SoundsPane extends JPanel {

    private static final Logger LOGGER = LogManager.getLogger(ConfigPane.class.getName());


    private HashMap<File, JSlider> sliders = new HashMap<>();
    private HashMap<File, JLabel> labels = new HashMap<>();
    private JSlider sliderMaster;
    private JLabel labelMaster;

    public SoundsPane() {

        super.setLayout(null);
        super.setBorder(new LineBorder(Color.BLACK, 1));


        JLabel labelDescription = new JLabel("Sounds Preview und Lautstärke");
        labelDescription.setBounds(2, 0, 300, 20);
        super.add(labelDescription);

        final int LABEL_WIDTH = 160;


        JLabel descriptionMaster = new JLabel("Master Volume");
        descriptionMaster.setBounds(10, 20, LABEL_WIDTH, 20);

        labelMaster = new JLabel(IO.settings.mastervolume + " %");
        labelMaster.setBounds(LABEL_WIDTH + 20, 20, 50, 20);

        sliderMaster = new JSlider(JSlider.HORIZONTAL);
        sliderMaster.setMinimum(0);
        sliderMaster.setMaximum(200);
        sliderMaster.setValue(IO.settings.mastervolume);
        sliderMaster.setBounds(LABEL_WIDTH + 60, 20, ControlWindow.WINDOW_WIDTH - LABEL_WIDTH - 80, 20);
        sliderMaster.setMajorTickSpacing(10);
        sliderMaster.setMinorTickSpacing(1);
        sliderMaster.setSnapToTicks(true);
        sliderMaster.addChangeListener(e -> labelMaster.setText(sliderMaster.getValue() + " %"));

        this.add(descriptionMaster);
        this.add(labelMaster);
        this.add(sliderMaster);

        int y = 40;
        File[] sounds = SoundManager.getAllSounds();
        for (File file : sounds) {

            final String name = file.getName().replace(SoundManager.FORMAT, "");

            JLabel label = new JLabel(name);
            label.setBounds(10, y, LABEL_WIDTH, 20);

            int value = 100;
            if (IO.settings.volume.containsKey(file.getName()))
                value = IO.settings.volume.get(file.getName());

            JLabel labelValue = new JLabel(value + " %");
            labelValue.setBounds(LABEL_WIDTH + 20, y, 50, 20);

            JSlider slider = new JSlider(JSlider.HORIZONTAL);
            slider.setMinimum(0);
            slider.setMaximum(100);
            slider.setMajorTickSpacing(10);
            slider.setMinorTickSpacing(1);
            slider.setSnapToTicks(true);
            slider.setValue(value);
            slider.setBounds(LABEL_WIDTH + 60, y, ControlWindow.WINDOW_WIDTH - LABEL_WIDTH - 120, 20);
            slider.addChangeListener(e -> labelValue.setText(slider.getValue() + " %"));

            JButton buttonPlay = new JButton("");
            buttonPlay.setBounds(ControlWindow.WINDOW_WIDTH - 55, y, 30, 20);
            buttonPlay.setIcon(Images.play);
            buttonPlay.addActionListener(e -> {
                save();
                SoundManager.stopAll();
                SoundManager.play(name);
            });

            sliders.put(file, slider);
            this.add(label);
            this.add(labelValue);
            this.add(slider);
            this.add(buttonPlay);
            y += 20;
        }
        this.setBounds(0, 0, ControlWindow.WINDOW_WIDTH, y);
        this.setPreferredSize(new Dimension(ControlWindow.WINDOW_WIDTH, y));
    }

    public void save() {

        LOGGER.info("Saving sounds in sounds pane");
        IO.settings.mastervolume = sliderMaster.getValue();
        for (File file : sliders.keySet()) {
            IO.settings.volume.put(file.getName(), sliders.get(file).getValue());
        }
        IO.saveSettings();
    }

    public void refresh() {

        LOGGER.debug("refreshing volumne values");

        sliderMaster.setValue(IO.settings.mastervolume);
        File[] sounds = new File(SoundManager.SOUNDS_DIR).listFiles();
        for (File file : sounds) {

            int value = 100;
            if (IO.settings.volume.containsKey(file.getName()))
                value = IO.settings.volume.get(file.getName());
            if (sliders.containsKey(file))
                sliders.get(file).setValue(value);

        }
    }


}
