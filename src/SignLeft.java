import bagel.util.Point;

/**
 * A sign is stationary and takes no action upon a tick. It serves to redirect gatherers
 * and thieves.
 */
public class SignLeft extends Actor {
    public static final String TYPE = "SignLeft";

    /**
     * Create a SignLeft at a specified location in a given game
     */
    public SignLeft(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/left.png");
    }

    @Override
    public void update() {
    }
}
