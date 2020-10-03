import bagel.util.Point;

import java.util.ArrayList;

public class Thief extends Actor implements Collision {
    private Point direction = Direction.UP;
    private boolean carrying = false;
    private boolean consuming = false;
    private boolean active = true;
    public static final String TYPE = "Thief";

    public Thief(Point location, ShadowLife game) {
        super(location, game, TYPE, "res/images/thief.png");
    }

    public Point getDirection() {
        return direction;
    }

    public void setDirection(Point direction) {
        this.direction = direction;
    }

    public boolean isCarrying() {
        return carrying;
    }

    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    public boolean isConsuming() {
        return consuming;
    }

    public void setConsuming(boolean consuming) {
        this.consuming = consuming;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean standOnPool(Point location) {
        ArrayList<Actor> actors = game.getActors();
        for (int i = 0; i < actors.size(); i++) {
            Point tmp = actors.get(i).getLocation();
            if (tmp.equals(location) &&
                    actors.get(i).type.equals("MitosisPool")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean standOnFence(Point location) {
        ArrayList<Actor> actors = game.getActors();
        for (int i = 0; i < actors.size(); i++) {
            Point tmp = actors.get(i).getLocation();
            if (tmp.equals(location) && actors.get(i).type.equals("Fence")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int standOnSign(Point location) {
        ArrayList<Actor> actors = game.getActors();
        for (int i = 0; i < actors.size(); i++) {
            Point tmp = actors.get(i).getLocation();
            if (tmp.equals(location)) {
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

    @Override
    public int standOnStorage(Point location) {
        ArrayList<Actor> actors = game.getActors();
        for (int i = 0; i < actors.size(); i++) {
            Point tmp = actors.get(i).getLocation();
            if (tmp.equals(location)) {
                if (actors.get(i).type.equals("Hoard") ||
                        actors.get(i).type.equals("Stockpile")) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int standOnTree(Point location) {
        ArrayList<Actor> actors = game.getActors();
        for (int i = 0; i < actors.size(); i++) {
            Point tmp = actors.get(i).getLocation();
            if (tmp.equals(location)) {
                if (actors.get(i).type.equals("Tree") ||
                        actors.get(i).type.equals("GoldenTree")) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int standOnGatherer(Point location) {
        ArrayList<Actor> actors = game.getActors();
        for (int i = 0; i < actors.size(); i++) {
            Point tmp = actors.get(i).getLocation();
            if (tmp.equals(location) && actors.get(i).type.equals("Gatherer")) {
                return i;
            }
        }
        return -1;
    }

    public boolean standOnPad(Point location) {
        ArrayList<Actor> actors = game.getActors();
        for (int i = 0; i < actors.size(); i++) {
            Point tmp = actors.get(i).getLocation();
            if (tmp.equals(location) && actors.get(i).type.equals("Pad")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update() {
        ArrayList<Actor> actors = game.getActors();
        if (this.isActive()) {
            this.move(direction.x * ShadowLife.TILE_SIZE,
                    direction.y * ShadowLife.TILE_SIZE);
        }
        Point location = this.getLocation();
        if (standOnFence(location)) {
            setActive(false);
            moveBack(direction.x * ShadowLife.TILE_SIZE,
                    direction.y * ShadowLife.TILE_SIZE);
        }
        if (standOnPool(location)) {
            Point currDirection = this.getDirection();
            // Create new thief at its position with its direction
            // rotated 90 degrees counter-clockwise.
            Thief newThiefOne = new Thief(location, this.game);
            newThiefOne.setDirection(Direction.rotate90CounterClockwise(currDirection));
            Point newThiefOneDirection = newThiefOne.direction;
            // Create new thief at its position with its direction
            // rotated 90 degrees clockwise.
            Thief newThiefTwo = new Thief(location, this.game);
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
        int signIndex = standOnSign(location);
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
        if (standOnPad(location)) {
            consuming = true;
        }
        int gathererIndex = standOnGatherer(location);
        if (gathererIndex != -1) {
            Gatherer gatherer = (Gatherer) actors.get(gathererIndex);
            if (gatherer.isTickUpdate()) {
                this.setDirection(Direction.rotate270Clockwise(direction));
            }
        }
        int treeIndex = standOnTree(location);
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
        int storageIndex = standOnStorage(location);
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
