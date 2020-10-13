import bagel.util.Point;

public class Hoard extends Storage {
    public static final String TYPE = "Hoard";

    public Hoard(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/hoard.png");
    }

}
