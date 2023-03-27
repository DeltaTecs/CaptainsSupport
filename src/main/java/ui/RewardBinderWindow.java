package ui;

import features.rewards.RewardHandler;
import features.rewards.RewardType;
import io.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * JFrame for recording reward ids and assigning actions on reward redeem
 */
public class RewardBinderWindow extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 300;

    /**
     * most recent class instance. Used to access ID display text field.
     */
    public static RewardBinderWindow instance;

    private JPanel contentPane = new JPanel();
    private JToggleButton buttonRecord;
    private JLabel labelID;
    private String currentID;

    public RewardBinderWindow() {
        super("Reward Konfigurieren");
        instance = this;

        this.setSize(WIDTH, HEIGHT);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.getContentPane().setSize(WIDTH, HEIGHT);
        contentPane.setSize(WIDTH, HEIGHT);
        contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        contentPane.setLayout(null);
        this.getContentPane().add(contentPane);

        JTextPane description = new JTextPane();
        description.setText("So gehts:\n1. Auswählen was beim Reward passieren soll\n2. Hier Aufnahme drücken\n3. Reward einlösen. Der Reward ist dann auf die gewählte Aktion gemapt");
        description.setBounds(10, 10, WIDTH - 40, 90);

        JComboBox<String> dropdownCategory = new JComboBox<>();
        dropdownCategory.addItem("Sound abspielen");
        dropdownCategory.addItem("Aktion triggern");
        dropdownCategory.addItem("Bot Befehl");
        dropdownCategory.setBounds(10, 110, WIDTH - 40, 20);

        JComboBox<RewardType> dropdownDetail = new JComboBox<>();
        dropdownDetail.setBounds(10, 140, WIDTH - 40, 20);
        dropdownCategory.addActionListener(a -> {
            if (dropdownCategory.getSelectedItem().equals("Sound abspielen")) {
                applyOptions(dropdownDetail, RewardHandler.SOUND_REWARDS);
            } else if (dropdownCategory.getSelectedItem().equals("Aktion triggern")) {
                applyOptions(dropdownDetail, RewardHandler.ACTION_REWARDS);
            } else if (dropdownCategory.getSelectedItem().equals("Bot Befehl")) {
                applyOptions(dropdownDetail, RewardHandler.COMMAND_REWARDS);
            } else
                throw new RuntimeException("reward selection broken amana: " + dropdownCategory.getSelectedItem());
        });

        // default details is first from dropdownDetail so "sounds"
        applyOptions(dropdownDetail, RewardHandler.SOUND_REWARDS);

        buttonRecord = new JToggleButton("Aufnehmen");
        buttonRecord.setBounds(WIDTH - 220, HEIGHT - 95, 200, 50);
        buttonRecord.addActionListener(a -> {
            if (buttonRecord.isSelected()) {
                labelID.setText("recording...");
            } else {
                labelID.setText("");
            }
        });

        labelID = new JLabel("");
        labelID.setOpaque(true);
        labelID.setBounds(10, HEIGHT - 95, 300, 50);
        labelID.setFont(new Font("Arial", Font.BOLD, 25));
        labelID.setForeground(Color.RED);

        contentPane.add(buttonRecord);
        contentPane.add(dropdownCategory);
        contentPane.add(dropdownDetail);
        contentPane.add(description);
        contentPane.add(labelID);
        this.setVisible(true);
        this.pack();
        this.validate();
        this.repaint();
    }

    public void setCurrentID(String id) {
        currentID = id;
        if (buttonRecord.isSelected()) {
            labelID.setText(currentID);
        }
    }


    private void applyOptions(JComboBox<RewardType> dropdown, List<RewardType> rewards) {
        dropdown.removeAllItems();
        for (RewardType option : rewards) {
            dropdown.addItem(option);
        }
    }


}
