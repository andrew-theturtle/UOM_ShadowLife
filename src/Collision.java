import bagel.util.Point;

public interface Collision {
    boolean standOnPool(Point location);

    boolean standOnFence(Point location);

    int standOnSign(Point location);

    int standOnTree(Point location);

    int standOnStorage(Point location);
}
