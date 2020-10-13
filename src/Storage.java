import bagel.util.Point;

public abstract class Storage extends Actor {
    private int numFruits = 0;

    public Storage(Point point, ShadowLife game, String type, String imgSrc) {
        super(point, game, type, imgSrc);
    }

    public void addFruit() {
        numFruits++;
    }

    public void removeFruit() {
        numFruits--;
    }

    public boolean isEmpty() {
        return numFruits == 0;
    }

    public int getNumFruits() {
        return numFruits;
    }

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
