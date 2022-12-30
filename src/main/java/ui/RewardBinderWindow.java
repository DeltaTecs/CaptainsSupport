package ui;

import javax.swing.*;
import java.awt.*;

/**
 * JFrame for recording reward ids and assigning actions on reward redeem
 */
public class RewardBinderWindow extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;

    JPanel contentPane = new JPanel();

    public RewardBinderWindow() {
        super("Reward Konfigurieren");

        this.setSize(WIDTH, HEIGHT);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
        this.getContentPane().setSize(WIDTH, HEIGHT);
        contentPane.setSize(WIDTH, HEIGHT);
        contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.getContentPane().add(contentPane);
        this.setVisible(true);
        this.pack();
        this.validate();
        this.repaint();

        JTextField description = new JTextField("So gehts: 1. Auswählen was beim Reward passieren soll, 2. Aufnahme drücken, 3. Reward einlösene. Der Reward ist dann auf die gewählte Aktion gemapt");
        description.setBounds(10, 10, WIDTH - 20, 20);

        contentPane.add(description);

    }

}
