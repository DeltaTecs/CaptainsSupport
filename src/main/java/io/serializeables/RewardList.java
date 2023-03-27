package io.serializeables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RewardList implements Serializable {

    private static final long serialVersionUID = 1002L;

    private static final Logger LOGGER = LogManager.getLogger(RewardList.class.getName());

    public List<RewardMapping> mappedRewards = new ArrayList<>();



}
