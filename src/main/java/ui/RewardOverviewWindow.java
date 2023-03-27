package ui;

import javax.swing.*;
import java.awt.*;

/**
 * JFrame for recording reward ids and assigning actions on reward redeem
 */
public class RewardOverviewWindow extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 300;

    private JPanel contentPane = new JPanel();

    public RewardOverviewWindow() {
        super("Rewards");

        this.setSize(WIDTH, HEIGHT);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.getContentPane().setSize(WIDTH, HEIGHT);
        contentPane.setSize(WIDTH, HEIGHT);
        contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        contentPane.setLayout(null);
        this.getContentPane().add(contentPane);

        JScrollPane scrollRewards = new JScrollPane(null, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollRewards.getVerticalScrollBar().setUnitIncrement(ControlWindow.SCROLL_SPEED);
        scrollRewards.setBounds(0, 300, ControlWindow.WINDOW_WIDTH, 400);
        scrollRewards.setPreferredSize(new Dimension(ControlWindow.WINDOW_WIDTH, 400));

        this.setVisible(true);
        this.pack();
        this.validate();
        this.repaint();
    }

}
