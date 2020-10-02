import bagel.util.Point;

public class Tree extends Actor {
    private int numFruits = 3;
    public static final String TYPE = "Tree";

    public Tree(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/tree.png");
    }

    public boolean isEmpty() {
        return numFruits == 0;
    }

    public void harvest() {
        numFruits--;
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