import bagel.util.Point;

/**
 * A fence is stationary and takes no action upon a tick.
 */
public class Fence extends Actor {
    public static final String TYPE = "Fence";

    /**
     * Create a fence at a specified location in a given game
     */
    public Fence(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/fence.png");
    }

    @Override
    public void update() {
    }
}
