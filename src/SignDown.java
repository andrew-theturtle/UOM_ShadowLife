import bagel.util.Point;

/**
 * A sign is stationary and takes no action upon a tick. It serves to redirect gatherers
 * and thieves.
 */
public class SignDown extends Actor {
    public static final String TYPE = "SignDown";

    /**
     * Create a SignDown at a specified location in a given game
     */
    public SignDown(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/down.png");
    }

    @Override
    public void update() {
    }
}
