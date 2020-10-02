import bagel.util.Point;

public class SignLeft extends Actor {
    public static final String TYPE = "SignLeft";

    public SignLeft(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/left.png");
    }

    @Override
    public void update() {
    }
}
