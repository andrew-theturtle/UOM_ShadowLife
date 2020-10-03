import bagel.Font;
import bagel.Image;
import bagel.util.Point;

public abstract class Actor {
    private Image image;
    private Point location;
    private boolean visibility = true;
    protected Font font = new Font("res/VeraMono.ttf", 20);
    protected ShadowLife game;
    public String type;

    public Actor(Point location, ShadowLife game, String type, String imgSrc) {
        this.image = new Image(imgSrc);
        this.location = location;
        this.type = type;
        this.game = game;
    }

    public Point getLocation() {
        return location;
    }

    public Image getImage() {
        return image;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public void draw() {
        if (visibility) {
            image.drawFromTopLeft(location.x, location.y);
        }
    }

    public final void tick() {
        update();
    }

    public void move(double deltaX, double deltaY) {
        // update new location after movement
        Point newLocation = new Point(location.x + deltaX,
                location.y + deltaY);
        setLocation(newLocation);
    }

    public void moveBack(double deltaX, double deltaY) {
        // update new location after movement
        Point newLocation = new Point(location.x - deltaX,
                location.y - deltaY);
        setLocation(newLocation);
    }

    public abstract void update();
}
