package ui.panes;

import main.Launcher;
import ui.ControlWindow;

import javax.swing.*;

public class ControlPane extends JPanel {

    private JTextPane textFieldWhatsNew;


    public ControlPane() {
        this.setBounds(0, 0, 10000, ControlWindow.WINDOW_HEIGHT);
        this.setLayout(null);

        textFieldWhatsNew = new JTextPane();
        textFieldWhatsNew.setText("Was neu in Version " + Launcher.VERSION + ":\n" + Launcher.LATEST_CHANGES);
        JScrollPane scrollFieldWhatsNew = new JScrollPane(textFieldWhatsNew);
        scrollFieldWhatsNew.setBounds(0, 0, 450, 180);

        this.add(scrollFieldWhatsNew);
    }

}
