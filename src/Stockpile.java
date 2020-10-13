import bagel.util.Point;

public class Stockpile extends Storage {
    public static final String TYPE = "Stockpile";

    public Stockpile(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/cherries.png");
    }

}
