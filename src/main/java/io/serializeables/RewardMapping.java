package io.serializeables;

import features.rewards.Reward;

import java.io.Serializable;

public class RewardMapping implements Serializable {

    private static final long serialVersionUID = 1003L;

    public String id;
    public String rewardName;
    public long created;
    public Reward link;

}
