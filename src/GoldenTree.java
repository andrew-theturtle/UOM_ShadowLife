import bagel.util.Point;

/**
 * A golden tree is stationary and takes no action upon a tick. It has an infinite reserve of fruit.
 */
public class GoldenTree extends Actor {
    public static final String TYPE = "GoldenTree";

    /**
     * Create the Golden Tree at a specified location in a given game
     */
    public GoldenTree(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/gold-tree.png");
    }

    @Override
    public void update() {
    }
}
