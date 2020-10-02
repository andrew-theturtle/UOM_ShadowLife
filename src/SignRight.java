import bagel.util.Point;

public class SignRight extends Actor {
    public static final String TYPE = "SignRight";

    public SignRight(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/right.png");
    }

    @Override
    public void update() {
    }
}
