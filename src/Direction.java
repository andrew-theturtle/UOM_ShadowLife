import bagel.util.Point;

public class Direction {
    public static final Point UP = new Point(0, -1);
    public static final Point DOWN = new Point(0, 1);
    public static final Point RIGHT = new Point(1, 0);
    public static final Point LEFT = new Point(-1, 0);

    public static Point rotate90Clockwise(Point direction) {
        if (direction.equals(UP)) {
            return RIGHT;
        } else if (direction.equals(RIGHT)) {
            return DOWN;
        } else if (direction.equals(DOWN)) {
            return LEFT;
        } else {
            return UP;
        }
    }

    public static Point rotate90CounterClockwise(Point direction) {
        if (direction.equals(UP)) {
            return LEFT;
        } else if (direction.equals(RIGHT)) {
            return UP;
        } else if (direction.equals(DOWN)) {
            return RIGHT;
        } else {
            return DOWN;
        }
    }

    public static Point rotate180Clockwise(Point direction) {
        Point tmp = rotate90Clockwise(direction);
        return rotate90Clockwise(tmp);
    }

    public static Point rotate270Clockwise(Point direction) {
        Point tmp = rotate180Clockwise(direction);
        return rotate90Clockwise(tmp);
    }
}
