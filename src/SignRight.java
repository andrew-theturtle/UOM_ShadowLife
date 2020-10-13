import bagel.util.Point;

/**
 * A sign is stationary and takes no action upon a tick. It serves to redirect gatherers
 * and thieves.
 */
public class SignRight extends Actor {
    public static final String TYPE = "SignRight";

    /**
     * Create a SignRight at a specified location in a given game
     */
    public SignRight(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/right.png");
    }

    @Override
    public void update() {
    }
}
