package features;

import com.github.twitch4j.chat.events.channel.FollowEvent;
import features.rewards.RewardHandler;
import io.SoundManager;
import main.TwitchConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FollowHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    public static void handle(FollowEvent e) {

        LOGGER.info("Follow Event triggered! " +  e.getUser().getName() + " is following");

        // simply play a sound for now
        SoundManager.play("ship-horn");
        TwitchConnector.chat("Welcome to the crew " + e.getUser().getName() + "! \uD83D\uDEA2⚓");

    }


}
