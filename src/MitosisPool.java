import bagel.util.Point;

public class MitosisPool extends Actor {
    public static final String TYPE = "MitosisPool";

    public MitosisPool(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/pool.png");
    }

    @Override
    public void update() {
    }
}
