import bagel.util.Point;

public class SignDown extends Actor {
    public static final String TYPE = "SignDown";


    public SignDown(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/down.png");
    }

    @Override
    public void update() {
    }
}
