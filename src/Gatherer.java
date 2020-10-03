import bagel.util.Point;

import java.util.ArrayList;

public class Gatherer extends Actor implements Collision {
    private Point direction = Direction.UP;
    private boolean carrying = false;
    private boolean active = true;
    public static final String TYPE = "Gatherer";

    public Gatherer(Point point, ShadowLife game) {
        super(point, game, TYPE, "res/images/gatherer.png");
    }

    public Point getDirection() {
        return direction;
    }

    public boolean isCarrying() {
        return carrying;
    }

    public boolean isActive() {
        return active;
    }

    public void setDirection(Point direction) {
        this.direction = direction;
    }

    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
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
            // Create one gatherer at its position with its direction
            // rotated 90 degrees counter-clockwise.
            Gatherer newGathererOne = new Gatherer(location, this.game);
            newGathererOne.setDirection(Direction.rotate90CounterClockwise(currDirection));
            Point newGathererOneDirection = newGathererOne.direction;
            // Create one gatherer at its position with its direction
            // rotated 90 degrees clockwise.
            Gatherer newGathererTwo = new Gatherer(location, this.game);
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
        int treeIndex = standOnTree(location);
        if (treeIndex != -1 && !isCarrying()) {
            if (actors.get(treeIndex).type.equals("Tree")) {
                Tree tree = ((Tree) actors.get(treeIndex));
                if (!tree.isEmpty()) {
                    tree.harvest();
                }
            }
            this.setCarrying(true);
            this.setDirection(Direction.rotate180Clockwise(this.getDirection()));
        }
        int storageIndex = standOnStorage(location);
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
