import bagel.util.Point;

public class Pad extends Actor {
    public static final String TYPE = "Pad";

    public Pad(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/pad.png");
    }

    @Override
    public void update() {
    }
}
