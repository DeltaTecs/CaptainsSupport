package features.rewards;

import com.github.twitch4j.pubsub.events.ChannelPointsRedemptionEvent;
import io.SoundManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RewardHandler {

    private static final Logger LOGGER = LogManager.getLogger(RewardHandler.class.getName());

    public static List<RewardType> SOUND_REWARDS = new ArrayList<>();
    public static List<RewardType> ACTION_REWARDS = new ArrayList<>();
    public static List<RewardType> COMMAND_REWARDS = new ArrayList<>();

    static  {

        for (Reward r : Reward.class.getEnumConstants())
            SOUND_REWARDS.add(new RewardType(r.name().replace("SOUND_", ""), "", r, false));

        ACTION_REWARDS.add(new RewardType("Hunt Challenge", "Zieht eine zufällige Hunt challenge mit sound", Reward.ACTION_HUNT_CHALLENGE, false));
        ACTION_REWARDS.add(new RewardType("IRL Challenge", "Zieht eine zufällige IRL challenge mit sound", Reward.ACTION_IRL_CHALLENGE, false));
        ACTION_REWARDS.add(new RewardType("Random Sport/Strechübung", "Zieht eine zufällige Übung/Strech", Reward.ACTION_RANDOM_SPORT, false));
        ACTION_REWARDS.add(new RewardType("Random Sound", "Spielt zufälligen sound", Reward.ACTION_RANDOM_SOUND, false));
        COMMAND_REWARDS.add(new RewardType("TTS", "führt TTS mit eingabe aus", Reward.COMMAND_TTS, true));
        COMMAND_REWARDS.add(new RewardType("TTS legacy", "führt TTS mit alter Stimme (legacy) aus", Reward.COMMAND_TTS_LEGACY, true));
        COMMAND_REWARDS.add(new RewardType("TTS reroll", "zieht neue zufällige TTS Stimme für Nutzer und gibt kurze preview", Reward.COMMAND_TTS_REROLL, false));
        COMMAND_REWARDS.add(new RewardType("slots", "rollt slots", Reward.COMMAND_SLOTS, false));
    }

    public static RewardType getRewardType(Reward link) {
        for (RewardType rt : SOUND_REWARDS)
            if (rt.getBind() == link)
                return rt;
        for (RewardType rt : ACTION_REWARDS)
            if (rt.getBind() == link)
                return rt;
        for (RewardType rt : COMMAND_REWARDS)
            if (rt.getBind() == link)
                return rt;
        throw new RuntimeException("Reward lookup gescheitert: " + link.name());
    }

    public static void handle(ChannelPointsRedemptionEvent redeemedEvent) {
        // ### DEBUG
        SoundManager.play("brawl");
        LOGGER.debug("Redeem Event captured: id=" + redeemedEvent.getEventId() + ", redemption-id=" + redeemedEvent.getRedemption().getId() + ", userId=" + redeemedEvent.getRedemption().getUser().getId() + ", userName=" + redeemedEvent.getRedemption().getUser().getDisplayName() + ", userLogin=" + redeemedEvent.getRedemption().getUser().getLogin() + ", userInput=" + redeemedEvent.getRedemption().getUserInput() + ", rewardTitle=" + redeemedEvent.getRedemption().getReward().getTitle());
    }
}
