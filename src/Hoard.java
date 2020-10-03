import bagel.util.Point;

public class Hoard extends Actor implements Storage {
    private int numFruits = 0;
    public static final String TYPE = "Hoard";


    public Hoard(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/hoard.png");
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
