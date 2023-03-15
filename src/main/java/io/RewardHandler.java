package io;

import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RewardHandler {

    private static final Logger LOGGER = LogManager.getLogger(RewardHandler.class.getName());


    public static List<String> ACTION_REWARDS = new ArrayList<>();
    public static List<String> COMMAND_REWARDS = new ArrayList<>();


    static  {
        ACTION_REWARDS.add("Hunt Challenge");
        ACTION_REWARDS.add("IRL Challenge");
        ACTION_REWARDS.add("Random Sound");
        ACTION_REWARDS.add("Random Strech/Sportübung");
        COMMAND_REWARDS.add("TTS (premium)");
        COMMAND_REWARDS.add("TTS (legacy)");
        COMMAND_REWARDS.add("TTS sprache reroll + preview");
        COMMAND_REWARDS.add("Slots");
        COMMAND_REWARDS.add("Slots 20");
        COMMAND_REWARDS.add("Slots 50");
    }

    public static void handle(RewardRedeemedEvent redeemedEvent) {
        LOGGER.debug("Redeem Event captured: id=" + redeemedEvent.getEventId() + ", redemption-id=" + redeemedEvent.getRedemption().getId() + ", userId=" + redeemedEvent.getRedemption().getUser().getId() + ", userName=" + redeemedEvent.getRedemption().getUser().getDisplayName() + ", userLogin=" + redeemedEvent.getRedemption().getUser().getLogin() + ", userInput=" + redeemedEvent.getRedemption().getUserInput() + ", rewardTitle=" + redeemedEvent.getRedemption().getReward().getTitle());
    }
}
