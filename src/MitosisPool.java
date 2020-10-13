import bagel.util.Point;

/**
 * A mitosis pool is stationary and takes no action upon a tick.
 */
public class MitosisPool extends Actor {
    public static final String TYPE = "MitosisPool";

    /**
     * Create a mitosis pool at a specified location in a given game
     */
    public MitosisPool(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/pool.png");
    }

    @Override
    public void update() {
    }
}
