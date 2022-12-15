package ui.panes;

import main.Constants;
import ui.ControlWindow;

import javax.swing.*;

/**
 * Pane for displaying the loading text
 */
public class LoadingPane extends JPanel {

    public LoadingPane() {


        JTextPane textField = new JTextPane();

        textField.setText("Bot läd maschallaaaaah\n\n\n" + Constants.RANDOM_FACTS[Constants.RANDOM.nextInt(Constants.RANDOM_FACTS.length)]);
        textField.setVisible(true);
        textField.setBounds(0, 0, ControlWindow.WINDOW_WIDTH, ControlWindow.WINDOW_HEIGHT);

        this.setBounds(0, 0, ControlWindow.WINDOW_WIDTH, ControlWindow.WINDOW_HEIGHT);
        this.add(textField);
        this.setVisible(true);
        this.setLayout(null);
    }

}
