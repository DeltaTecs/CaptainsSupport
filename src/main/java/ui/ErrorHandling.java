package ui;

import javax.swing.*;

public class ErrorHandling {


    private static JFrame referenceFrame;

    /**
     * Opens error popup that suggest to send log files
     * @param e
     */
    public static void popup(Exception e) {
        JFrame errorFrame = new JFrame();
        errorFrame.setLocationRelativeTo(referenceFrame);

        String[] options = new String[]{"Logs an Delta schicken\n(bitte nicht spammen diggi, kuss aufs auge)", "Schließen"};

        int choice = JOptionPane.showOptionDialog(errorFrame, "Bot rip weil: " + e.getMessage(), "oh shit",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
    }

    public static void setReferenceFrame(JFrame frame) {
        referenceFrame = frame;
    }

}
