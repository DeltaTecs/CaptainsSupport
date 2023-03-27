package ui.panes;

import features.rewards.RewardHandler;
import features.rewards.RewardType;
import io.AppliedChanges;
import io.IO;
import io.Util;
import io.serializeables.RewardMapping;
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

public class RewardListPane extends JPanel {

    private static final Logger LOGGER = LogManager.getLogger(RewardListPane.class.getName());

    public RewardListPane() throws IllegalAccessException {
        super.setLayout(null);
        super.setBorder(new LineBorder(Color.BLACK, 1));

        // update current rewards
        IO.loadRewards();

        JLabel labelDescription = new JLabel("Alle registrierte Rewards");
        labelDescription.setBounds(2, 0, 300, 20);
        super.add(labelDescription);


        int y = 20;
        for (RewardMapping mapped : IO.rewards.mappedRewards) {


            final int LABEL_WIDTH = 300;

            // look up saved information for enum in saving
            RewardType rewardType = RewardHandler.getRewardType(mapped.link);

            JLabel labelName = new JLabel(rewardType.getName());
            labelName.setBounds(10, y, LABEL_WIDTH, 20);
            JLabel labelRewardDescription = new JLabel(rewardType.getDescription());
            labelRewardDescription.setBounds(10, y + 25, LABEL_WIDTH, 20);
            JLabel labelTwitchName = new JLabel(mapped.rewardName);
            labelTwitchName.setBounds(10, y + 50, LABEL_WIDTH, 20);
            JLabel labelTwitchID = new JLabel(mapped.id);
            labelTwitchID.setBounds(10, y + 75, LABEL_WIDTH, 20);
            JLabel labelCreatedAt = new JLabel(Util.getDate(mapped.created));
            labelCreatedAt.setBounds(10, y + 100, LABEL_WIDTH, 20);

            JButton buttonDelete = new JButton("löschen");
            buttonDelete.setBounds(LABEL_WIDTH + 20, y, ControlWindow.WINDOW_WIDTH - LABEL_WIDTH - 40, 20);

            /*
            fields.put(field, inputField);
            this.add(labelName);
            this.add(inputField);*/
            y += 20;
        }
        this.setBounds(0, 0, ControlWindow.WINDOW_WIDTH, y);
        this.setPreferredSize(new Dimension(ControlWindow.WINDOW_WIDTH, y));

    }



}
