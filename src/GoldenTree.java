import bagel.util.Point;

public class GoldenTree extends Actor {
    public static final String TYPE = "GoldenTree";

    public GoldenTree(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/gold-tree.png");
    }

    @Override
    public void update() {
    }
}
