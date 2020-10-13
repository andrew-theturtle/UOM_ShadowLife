import bagel.util.Point;

/**
 * Represent an object that has storage's functionality
 */
public abstract class Storage extends Actor {
    private int numFruits = 0;

    /**
     * Create a storage object at a specified location in a given game
     */
    public Storage(Point point, ShadowLife game, String type, String imgSrc) {
        super(point, game, type, imgSrc);
    }

    /**
     * Increase the number of fruits in storage
     */
    public void addFruit() {
        numFruits++;
    }

    /**
     * Decrease the number of fruits in storage
     */
    public void removeFruit() {
        numFruits--;
    }

    /**
     * @return Return true if the storage is empty
     */
    public boolean isEmpty() {
        return numFruits == 0;
    }

    /**
     * @return Return the current number of fruits in storage
     */
    public int getNumFruits() {
        return numFruits;
    }

    /**
     * Draw the storage's current number of fruits at the top-left of the its image
     * and the storage image
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
