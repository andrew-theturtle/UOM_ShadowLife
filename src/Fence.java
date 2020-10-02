import bagel.util.Point;

public class Fence extends Actor {
    public static final String TYPE = "Fence";


    public Fence(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/fence.png");
    }

    @Override
    public void update() {
    }
}
