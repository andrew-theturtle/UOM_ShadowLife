import bagel.util.Point;

/**
 * A pad is stationary and takes no action upon a tick
 */
public class Pad extends Actor {
    public static final String TYPE = "Pad";

    /**
     * Create a Pad at a specified location in a given game
     */
    public Pad(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/pad.png");
    }

    @Override
    public void update() {
    }
}
