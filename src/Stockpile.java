import bagel.util.Point;

public class Stockpile extends Actor implements Storage {
    private int numFruits = 0;
    public static final String TYPE = "Stockpile";


    public Stockpile(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/cherries.png");
    }

    public void addFruit() {
        numFruits++;
    }

    public void removeFruit() {
        numFruits--;
    }

    public int getNumFruits() {
        return numFruits;
    }

    public boolean isEmpty() {
        return numFruits == 0;
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
