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

    public boolean isEmpty() {
        return numFruits == 0;
    }

    @Override
    public void draw() {
        super.draw();
        font.drawString("" + numFruits,
                getLocation().x - getImage().getWidth() / 2,
                getLocation().y - getImage().getHeight() / 2);
    }

    @Override
    public void update() {
    }
}
