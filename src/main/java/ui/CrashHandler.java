package ui;

import io.IO;
import io.MailSender;
import io.logging.LogHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.File;
import java.nio.file.Files;

public class CrashHandler {

    private static final Logger LOGGER = LogManager.getLogger(CrashHandler.class.getName());


    private static JFrame referenceFrame;

    /**
     * Opens error popup that suggest to send log files
     * @param e
     */
    public static void popup(Exception e) {
        JFrame errorFrame = new JFrame();
        errorFrame.setLocationRelativeTo(referenceFrame);

        String[] options = new String[]{"Logs an Matin schicken\n(bitte nicht spammen diggi, kuss aufs auge)", "Schließen"};

        int choice = JOptionPane.showOptionDialog(errorFrame, "Bot rip weil: " + e.getMessage(), "oh shit",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            // send logs per mail
            sendReport(true);
        }
    }

    public static void sendReport(boolean crash) {

        File logLatest = new File(LogHandler.LOG_DIR + File.separator + "latest.txt");
        File logOld = new File(LogHandler.LOG_DIR + File.separator + "old.txt");
        File serialSettings = new File(IO.PATH_SETTINGS);

        if (!logLatest.exists())
            LOGGER.error("Can not append " + LogHandler.LOG_DIR + File.separator + "latest.txt" + " to crash report: File does not exist");
        if (!logOld.exists())
            LOGGER.error("Can not append " + LogHandler.LOG_DIR + File.separator + "old.txt" + " to crash report: File does not exist");
        if (!serialSettings.exists())
            LOGGER.error("Can not append " + IO.PATH_SETTINGS + " to crash report: File does not exist");

        String msg = "";
        try {
            msg = new String(Files.readAllBytes(logLatest.toPath()));
        } catch (Exception e) {
            // ja shit happens
        }

        // try to send error
        String error = MailSender.sendMail("bach.martin@aol.com", crash ? "[BOTCRASH]" : "[Infos]", msg, new File[]{logLatest, logOld, serialSettings});

        String frameName = "oh shit";

        while (error != null) {
            // ask user if they want to keep trying to send the report in case of error

            LOGGER.error("Crash report sending failed: " + error);
            frameName += ", oh shit";

            JFrame errorFrame = new JFrame();
            errorFrame.setLocationRelativeTo(referenceFrame);
            String[] options = new String[]{"Nochmal versuchen", "Aufgeben"};
            int choice = JOptionPane.showOptionDialog(errorFrame, "Crash report konnte nicht geschickt werden: " + error, frameName,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);

            if (choice == 1)
                break;

            error = MailSender.sendMail("bach.martin@aol.com", crash ? "[BOTCRASH]" : "[Infos]", "rip", new File[]{logLatest, logOld, serialSettings});
        }

        if (error == null) {
            JFrame infoFrame = new JFrame();
            infoFrame.setLocationRelativeTo(referenceFrame);
            JOptionPane.showMessageDialog(infoFrame, crash ? "Crash report mail is raus, kannst neustarten :p" : "Hat geklappt");
        }

    }

    public static void setReferenceFrame(JFrame frame) {
        referenceFrame = frame;
    }

}
