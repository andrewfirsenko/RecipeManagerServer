package data;

public class Step {
    private String action;
    private String image;

    Step(String action, String image) {
        this.action = action;
        this.image = image;
    }

    public String getAction() {
        return action;
    }

    public String getImage() {
        return image;
    }
}
