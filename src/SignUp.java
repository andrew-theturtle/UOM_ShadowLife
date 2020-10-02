import bagel.util.Point;

public class SignUp extends Actor {
    public static final String TYPE = "SignUp";


    public SignUp(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/up.png");
    }

    @Override
    public void update() {
    }
}
