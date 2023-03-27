package features.rewards;

public class RewardType {

    private String name;
    private String description;
    private Reward bind;
    private boolean inputRequired;

    public RewardType(String name, String description, Reward bind, boolean inputRequired) {
        this.name = name;
        this.description = description;
        this.bind = bind;
        this.inputRequired = inputRequired;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Reward getBind() {
        return bind;
    }

    public boolean isInputRequired() {
        return inputRequired;
    }

    @Override
    public String toString() {
        return name;
    }
}
