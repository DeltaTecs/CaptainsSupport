package ui.panes;

import main.Constants;
import ui.ControlWindow;

import javax.swing.*;

/**
 * Pane for displaying the loading text
 */
public class LoadingPane extends JPanel {

    private JTextPane textField = new JTextPane();

    public LoadingPane() {

        applyRandomText();
        textField.setVisible(true);
        textField.setBounds(0, 0, ControlWindow.WINDOW_WIDTH, ControlWindow.WINDOW_HEIGHT);

        this.setBounds(0, 0, ControlWindow.WINDOW_WIDTH, ControlWindow.WINDOW_HEIGHT);
        this.add(textField);
        this.setVisible(true);
        this.setLayout(null);
    }

    /**
     * Sets the pane text to a custom one
     */
    public void setText(String text) {
        textField.setText(text);
    }

    /**
     * Displays the random fact on the pane
     */
    public void applyRandomText() {
        textField.setText("Bot läd maschallaaaaah\n\n\n" + Constants.RANDOM_FACTS[Constants.RANDOM.nextInt(Constants.RANDOM_FACTS.length)]);
    }

}
