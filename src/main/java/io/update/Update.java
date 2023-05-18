package io.update;

import features.rewards.RewardHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Update {

    private String version;
    private String url;

    public Update(String version, String url) {
        this.version = version;
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public String getUrl() {
        return url;
    }
}
