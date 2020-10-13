import bagel.util.Point;

import java.util.ArrayList;

/**
 * A Gatherer contains state and performs action every tick
 */
public class Gatherer extends Actor {
    private Point direction = Direction.LEFT;
    private boolean carrying = false;
    private boolean active = true;
    public static final String TYPE = "Gatherer";

    /**
     * Create a gatherer at a specified location in a given game
     */
    public Gatherer(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/gatherer.png");
    }

    /**
     * @return Return current direction of the gatherer
     */
    public Point getDirection() {
        return direction;
    }

    /**
     * @return Return the carrying state of the gatherer
     */
    public boolean isCarrying() {
        return carrying;
    }

    /**
     * @return Return the gatherer's state
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set the direction of the gatherer
     */
    public void setDirection(Point direction) {
        this.direction = direction;
    }

    /**
     * Set the carrying state of the gatherer
     */
    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    /**
     * Set the state of the gatherer
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return Return true if the gatherer is stand on a pool
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
     * @return Return true if the gatherer is stand on a fence
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
     * that the gatherer stand on. If not return -1.
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
     * @return Return the tree index in the main actors list
     * that the gatherer stand on. If not return -1.
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
     * @return Return the storage index in the main actors list
     * that the gatherer stand on. If not return -1
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
     * Gatherer movement update in each tick
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
            // Create one gatherer at its position with its direction
            // rotated 90 degrees counter-clockwise.
            Gatherer newGathererOne = new Gatherer(this.getLocation(), this.game);
            newGathererOne.setDirection(Direction.rotate90CounterClockwise(currDirection));
            Point newGathererOneDirection = newGathererOne.direction;
            // Create one gatherer at its position with its direction
            // rotated 90 degrees clockwise.
            Gatherer newGathererTwo = new Gatherer(this.getLocation(), this.game);
            newGathererTwo.setDirection(Direction.rotate90Clockwise(currDirection));
            Point newGathererTwoDirection = newGathererTwo.direction;
            // Move both gatherers
            newGathererOne.move(newGathererOneDirection.x * ShadowLife.TILE_SIZE,
                    newGathererOneDirection.y * ShadowLife.TILE_SIZE);
            newGathererTwo.move(newGathererTwoDirection.x * ShadowLife.TILE_SIZE,
                    newGathererTwoDirection.y * ShadowLife.TILE_SIZE);
            // Add two newly created gatherers to NewActors List
            game.getNewActors().add(newGathererOne);
            game.getNewActors().add(newGathererTwo);
            // Destroy the original gatherer
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
        int treeIndex = standOnTree();
        if (treeIndex != -1 && !isCarrying()) {
            if (actors.get(treeIndex).type.equals("Tree")) {
                Tree tree = ((Tree) actors.get(treeIndex));
                if (!tree.isEmpty()) {
                    tree.harvest();
                    this.setCarrying(true);
                    this.setDirection(Direction.rotate180Clockwise(this.getDirection()));
                }
            } else {
                this.setCarrying(true);
                this.setDirection(Direction.rotate180Clockwise(this.getDirection()));
            }
        }
        int storageIndex = standOnStorage();
        if (storageIndex != -1) {
            if (isCarrying()) {
                this.setCarrying(false);
                if (actors.get(storageIndex).type.equals("Hoard")) {
                    Hoard hoard = (Hoard) actors.get(storageIndex);
                    hoard.addFruit();
                } else {
                    Stockpile stockpile = (Stockpile) actors.get(storageIndex);
                    stockpile.addFruit();
                }
            }
            this.setDirection(Direction.rotate180Clockwise(this.getDirection()));
        }
    }
}
