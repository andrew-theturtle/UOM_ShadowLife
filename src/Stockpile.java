import bagel.util.Point;

/**
 * A Stockpile is stationary and takes no action upon a tick. It begins with 0 fruit.
 */
public class Stockpile extends Storage {
    public static final String TYPE = "Stockpile";

    /**
     * Create a stockpile at a specified location in a given game
     */
    public Stockpile(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/cherries.png");
    }

}
