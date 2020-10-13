import bagel.util.Point;

/**
 * A Hoard is stationary and takes no action upon a tick. It begins with 0 fruit.
 */
public class Hoard extends Storage {
    public static final String TYPE = "Hoard";

    /**
     * Create a hoard at a specified location in a given game
     */
    public Hoard(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/hoard.png");
    }
}
