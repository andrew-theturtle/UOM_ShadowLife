import bagel.util.Point;

/**
 * A tree is stationary and takes no action upon a tick. It begins with 3 fruit.
 */
public class Tree extends Actor {
    private int numFruits = 3;
    public static final String TYPE = "Tree";

    /**
     * Create a tree at a specified location in a given game
     */
    public Tree(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/tree.png");
    }

    /**
     * @return Return true if the tree has no fruit
     */
    public boolean isEmpty() {
        return numFruits == 0;
    }

    /**
     * Decrease the number of fruits available on the tree
     */
    public void harvest() {
        numFruits--;
    }

    /**
     * Draw the tree's current number of fruits at the top-left of the tree image
     * and the tree image
     */
    @Override
    public void draw() {
        if (isVisibility()) {
            super.draw();
            font.drawString("" + numFruits,
                    getLocation().x,
                    getLocation().y);
        }
    }

    @Override
    public void update() {
    }
}
