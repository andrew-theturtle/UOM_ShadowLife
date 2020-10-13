import bagel.Font;
import bagel.Image;
import bagel.util.Point;

/**
 * Represent an object with an associated image that is located at a particular tile,
 * and may perform an action every tick.
 */
public abstract class Actor {
    private Image image;
    private Point location;
    private boolean visibility = true;
    private boolean tickUpdate = false;
    protected Font font = new Font("res/VeraMono.ttf", 20);
    protected ShadowLife game;
    public String type;

    /**
     * Create new actor at the specified location,
     * given the game instance, actor type and image source
     *
     * @param location Location of the actor
     * @param game     Game that the actor belongs to
     * @param type     Type of the actor
     * @param imgSrc   Img source of the actor
     */
    public Actor(Point location, ShadowLife game, String type, String imgSrc) {
        this.image = new Image(imgSrc);
        this.location = location;
        this.type = type;
        this.game = game;
    }

    /**
     * @return Return the location of the actor
     */
    public Point getLocation() {
        return location;
    }

    /**
     * @return Return the image used to represent the actor
     */
    public Image getImage() {
        return image;
    }

    /**
     * Set the location of the actor
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * @return Return the visibility of actor
     */
    public boolean isVisibility() {
        return visibility;
    }

    /**
     * Set the visibility of actor
     */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    /**
     * @return Return whether the actor has been updated in the current tick
     */
    public boolean isTickUpdate() {
        return tickUpdate;
    }

    /**
     * Set the tick update status of the actor
     */
    public void setTickUpdate(boolean tickUpdate) {
        this.tickUpdate = tickUpdate;
    }

    /**
     * Draw the actor image with its top left at the actor location
     */
    public void draw() {
        if (visibility) {
            image.drawFromTopLeft(location.x, location.y);
        }
    }

    /**
     * Update actor's action each tick
     */
    public final void tick() {
        update();
    }

    /**
     * Move the actor forward deltaX distance along x-axis
     * and deltaY distance along y-axis
     */
    public void move(double deltaX, double deltaY) {
        // update new location after movement
        Point newLocation = new Point(location.x + deltaX,
                location.y + deltaY);
        setLocation(newLocation);
    }

    /**
     * Move the actor backward deltaX distance along x-axis
     * and deltaY distance along y-axis
     */
    public void moveBack(double deltaX, double deltaY) {
        // update new location after movement
        Point newLocation = new Point(location.x - deltaX,
                location.y - deltaY);
        setLocation(newLocation);
    }

    public abstract void update();
}
