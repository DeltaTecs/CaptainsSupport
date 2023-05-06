package features;

import com.github.twitch4j.chat.events.channel.FollowEvent;
import com.github.twitch4j.chat.events.channel.RaidEvent;
import io.SoundManager;
import io.Util;
import main.TwitchConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RaidHandler {

    private static final Logger LOGGER = LogManager.getLogger();


    public static void handle(RaidEvent e) {

        LOGGER.info("Raid Event triggered! " +  e.getRaider().getName() + " is raiding with " + e.getViewers().intValue() + " viewers");

        // simply play a sound for now
        SoundManager.play(Util.fiftyfifty() ? "das-boot-alarm" : "pirate-battle");
        TwitchConnector.chat("\uD83C\uDFF4\u200D☠️ " + e.getRaider().getName() + " and their " + e.getViewers().intValue() + " henchmen are attempting to hijack the ship! \uD83C\uDFF4\u200D☠️");

    }


}
