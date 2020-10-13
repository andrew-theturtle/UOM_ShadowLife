import bagel.util.Point;

/**
 * A sign is stationary and takes no action upon a tick. It serves to redirect gatherers
 * and thieves.
 */
public class SignUp extends Actor {
    public static final String TYPE = "SignUp";

    /**
     * Create a SignUp at a specified location in a given game
     */
    public SignUp(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/up.png");
    }

    @Override
    public void update() {
    }
}
