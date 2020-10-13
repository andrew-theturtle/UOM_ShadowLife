import bagel.util.Point;

import java.util.ArrayList;

/**
 * A Thief contains state and performs action every tick
 */
public class Thief extends Actor {
    private Point direction = Direction.UP;
    private boolean carrying = false;
    private boolean consuming = false;
    private boolean active = true;
    public static final String TYPE = "Thief";

    /**
     * Create a thief at a specified location in a given game
     */
    public Thief(Point location, ShadowLife game) {
        super(location, game, TYPE, "res/images/thief.png");
    }

    /**
     * @return Return current direction of the thief
     */
    public Point getDirection() {
        return direction;
    }

    /**
     * Set the direction of the thief
     */
    public void setDirection(Point direction) {
        this.direction = direction;
    }

    /**
     * @return Return the carrying state of the thief
     */
    public boolean isCarrying() {
        return carrying;
    }

    /**
     * Set the carrying state of the thief
     */
    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    /**
     * @return Return the consuming state of the thief
     */
    public boolean isConsuming() {
        return consuming;
    }

    /**
     * Set the consuming state of the thief
     */
    public void setConsuming(boolean consuming) {
        this.consuming = consuming;
    }

    /**
     * @return Return the thief's state
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set the state of the thief
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return Return true if the thief is stand on a pool
     */
    public boolean standOnPool() {
        ArrayList<Actor> actors = game.getActors();
        for (int i = 0; i < actors.size(); i++) {
            Point tmp = actors.get(i).getLocation();
            if (tmp.equals(this.getLocation()) &&
                    actors.get(i).type.equals("MitosisPool")) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return Return true if the thief is stand on a fence
     */
    public boolean standOnFence() {
        ArrayList<Actor> actors = game.getActors();
        for (int i = 0; i < actors.size(); i++) {
            Point tmp = actors.get(i).getLocation();
            if (tmp.equals(this.getLocation()) && actors.get(i).type.equals("Fence")) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return Return the sign index in the main actors list
     * that the thief stand on. If not return -1.
     */
    public int standOnSign() {
        ArrayList<Actor> actors = game.getActors();
        for (int i = 0; i < actors.size(); i++) {
            Point tmp = actors.get(i).getLocation();
            if (tmp.equals(this.getLocation())) {
                if (actors.get(i).type.equals("SignUp") ||
                        actors.get(i).type.equals("SignDown") ||
                        actors.get(i).type.equals("SignRight") ||
                        actors.get(i).type.equals("SignLeft")) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * @return Return the storage object index in the main actors list
     * that the thief stand on. If not return -1.
     */
    public int standOnStorage() {
        ArrayList<Actor> actors = game.getActors();
        for (int i = 0; i < actors.size(); i++) {
            Point tmp = actors.get(i).getLocation();
            if (tmp.equals(this.getLocation())) {
                if (actors.get(i).type.equals("Hoard") ||
                        actors.get(i).type.equals("Stockpile")) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * @return Return the tree index in the main actors list
     * that the thief stand on. If not return -1.
     */
    public int standOnTree() {
        ArrayList<Actor> actors = game.getActors();
        for (int i = 0; i < actors.size(); i++) {
            Point tmp = actors.get(i).getLocation();
            if (tmp.equals(this.getLocation())) {
                if (actors.get(i).type.equals("Tree") ||
                        actors.get(i).type.equals("GoldenTree")) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * @return Return the gatherer index in the main actors list
     * that the thief stand on. If not return -1.
     */
    public int standOnGatherer() {
        ArrayList<Actor> actors = game.getActors();
        for (int i = 0; i < actors.size(); i++) {
            Point tmp = actors.get(i).getLocation();
            if (tmp.equals(this.getLocation()) && actors.get(i).type.equals("Gatherer")) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return Return true if the thief is stand on a pad
     */
    public boolean standOnPad() {
        ArrayList<Actor> actors = game.getActors();
        for (int i = 0; i < actors.size(); i++) {
            Point tmp = actors.get(i).getLocation();
            if (tmp.equals(this.getLocation()) && actors.get(i).type.equals("Pad")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Thief movement update in each tick
     */
    @Override
    public void update() {
        ArrayList<Actor> actors = game.getActors();
        if (this.isActive()) {
            this.move(direction.x * ShadowLife.TILE_SIZE,
                    direction.y * ShadowLife.TILE_SIZE);
        }
        if (standOnFence()) {
            setActive(false);
            moveBack(direction.x * ShadowLife.TILE_SIZE,
                    direction.y * ShadowLife.TILE_SIZE);
        }
        if (standOnPool()) {
            Point currDirection = this.getDirection();
            // Create new thief at its position with its direction
            // rotated 90 degrees counter-clockwise.
            Thief newThiefOne = new Thief(this.getLocation(), this.game);
            newThiefOne.setDirection(Direction.rotate90CounterClockwise(currDirection));
            Point newThiefOneDirection = newThiefOne.direction;
            // Create new thief at its position with its direction
            // rotated 90 degrees clockwise.
            Thief newThiefTwo = new Thief(this.getLocation(), this.game);
            newThiefTwo.setDirection(Direction.rotate90Clockwise(currDirection));
            Point newThiefTwoDirection = newThiefTwo.direction;
            // Move both thieves
            newThiefOne.move(newThiefOneDirection.x * ShadowLife.TILE_SIZE,
                    newThiefOneDirection.y * ShadowLife.TILE_SIZE);
            newThiefTwo.move(newThiefTwoDirection.x * ShadowLife.TILE_SIZE,
                    newThiefTwoDirection.y * ShadowLife.TILE_SIZE);
            // Add two newly created thieves to NewActors List
            game.getNewActors().add(newThiefOne);
            game.getNewActors().add(newThiefTwo);
            // Destroy the original thief
            this.setActive(false);
            this.setVisibility(false);
        }
        int signIndex = standOnSign();
        if (signIndex != -1) {
            Point signDirection;
            if (actors.get(signIndex).type.equals("SignRight")) {
                signDirection = Direction.RIGHT;
            } else if (actors.get(signIndex).type.equals("SignLeft")) {
                signDirection = Direction.LEFT;
            } else if (actors.get(signIndex).type.equals("SignUp")) {
                signDirection = Direction.UP;
            } else {
                signDirection = Direction.DOWN;
            }
            this.setDirection(signDirection);
        }
        if (standOnPad()) {
            consuming = true;
        }
        int gathererIndex = standOnGatherer();
        if (gathererIndex != -1) {
            Gatherer gatherer = (Gatherer) actors.get(gathererIndex);
            if (gatherer.isTickUpdate()) {
                this.setDirection(Direction.rotate270Clockwise(direction));
            }
        }
        int treeIndex = standOnTree();
        if (treeIndex != -1 && !carrying) {
            if (actors.get(treeIndex).type.equals("Tree")) {
                Tree tree = ((Tree) actors.get(treeIndex));
                if (!tree.isEmpty()) {
                    tree.harvest();
                    carrying = true;
                }
            } else {
                carrying = true;
            }

        }
        int storageIndex = standOnStorage();
        if (storageIndex != -1) {
            if (actors.get(storageIndex).type.equals("Hoard")) {
                Hoard hoard = (Hoard) actors.get(storageIndex);
                if (consuming) {
                    consuming = false;
                    if (!carrying) {
                        if (!hoard.isEmpty()) {
                            carrying = true;
                            hoard.removeFruit();
                        } else {
                            this.setDirection(Direction.rotate90Clockwise(direction));
                        }
                    }
                } else if (carrying) {
                    carrying = false;
                    hoard.addFruit();
                    this.setDirection(Direction.rotate90Clockwise(direction));
                }
            }
            if (actors.get(storageIndex).type.equals("Stockpile")) {
                Stockpile stockpile = (Stockpile) actors.get(storageIndex);
                if (!carrying) {
                    if (!stockpile.isEmpty()) {
                        carrying = true;
                        consuming = false;
                        stockpile.removeFruit();
                        this.setDirection(Direction.rotate90Clockwise(direction));
                    }
                } else {
                    this.setDirection(Direction.rotate90Clockwise(direction));
                }
            }
        }
    }
}
