import bagel.AbstractGame;
import bagel.Image;
import bagel.Input;
import bagel.util.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * The base class to initialize and run Shadow Life simulation
 */
public class ShadowLife extends AbstractGame {
    private final Image background = new Image("res/images/background.png");
    public static final int TILE_SIZE = 64;
    private ArrayList<Actor> actors = new ArrayList<>();
    private ArrayList<Actor> newActors = new ArrayList<>();
    private static long tickRate;
    private static int maxTicks;
    private static String worldFile;
    private long lastTime;                      // keep track of last Tick time
    private int numTicks = 0;

    /**
     * Create the game from the loaded world file
     */
    public ShadowLife() {
        // load world file and create corresponding actors
        try (BufferedReader csvReader =
                     new BufferedReader(new FileReader(worldFile))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Point point = new Point(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
                if (data[0].equals("Tree")) {
                    actors.add(new Tree(point, this));
                } else if (data[0].equals("Gatherer")) {
                    actors.add(new Gatherer(point, this));
                } else if (data[0].equals("Thief")) {
                    actors.add(new Thief(point, this));
                } else if (data[0].equals("Fence")) {
                    actors.add(new Fence(point, this));
                } else if (data[0].equals("GoldenTree")) {
                    actors.add(new GoldenTree(point, this));
                } else if (data[0].equals("Hoard")) {
                    actors.add(new Hoard(point, this));
                } else if (data[0].equals("Stockpile")) {
                    actors.add(new Stockpile(point, this));
                } else if (data[0].equals("Pool")) {
                    actors.add(new MitosisPool(point, this));
                } else if (data[0].equals("Pad")) {
                    actors.add(new Pad(point, this));
                } else if (data[0].equals("SignDown")) {
                    actors.add(new SignDown(point, this));
                } else if (data[0].equals("SignUp")) {
                    actors.add(new SignUp(point, this));
                } else if (data[0].equals("SignRight")) {
                    actors.add(new SignRight(point, this));
                } else if (data[0].equals("SignLeft")) {
                    actors.add(new SignLeft(point, this));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // initialize with game's construction time
        lastTime = System.currentTimeMillis();
    }

    /**
     * @return Return the main actors list
     */
    public ArrayList<Actor> getActors() {
        return actors;
    }

    /**
     * @return Return the newly added actors list
     */
    public ArrayList<Actor> getNewActors() {
        return newActors;
    }

    /**
     * @return Return true if all gatherer and thief are not active
     */
    public boolean simulationFinished() {
        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).type.equals("Gatherer")) {
                Gatherer gatherer = (Gatherer) actors.get(i);
                if (gatherer.isActive()) return false;
            } else if (actors.get(i).type.equals("Thief")) {
                Thief thief = (Thief) actors.get(i);
                if (thief.isActive()) return false;
            }
        }
        return true;
    }

    /**
     * Update the state of the game, potentially reading from input
     */
    @Override
    protected void update(Input input) {
        // rendered background image
        background.drawFromTopLeft(0, 0);
        for (int i = 0; i < actors.size(); i++) {
            actors.get(i).draw();
        }
        // tick update
        long newTime = System.currentTimeMillis();
        if (newTime - lastTime >= tickRate) {
            numTicks++;
            lastTime = newTime;
            for (int i = 0; i < actors.size(); i++) {
                actors.get(i).tick();
                actors.get(i).setTickUpdate(true);
            }
        }
        // reset tickUpdate of each actor to false
        for (int i = 0; i < actors.size(); i++) {
            actors.get(i).setTickUpdate(false);
        }
        // add newly created actors to the main list of actors
        if (!newActors.isEmpty()) {
            actors.addAll(newActors);
            newActors.clear();
        }
        // remove invisible actors
        actors.removeIf(cur -> !cur.isVisibility());
        actors.trimToSize();
        // if simulation halts, print the number of ticks have elapsed
        // and amount of fruit at each stockpile and hoard
        if (simulationFinished()) {
            System.out.println(numTicks + " ticks");
            for (int i = 0; i < actors.size(); i++) {
                if (actors.get(i).type.equals("Stockpile")) {
                    Stockpile stockpile = (Stockpile) actors.get(i);
                    System.out.println(stockpile.getNumFruits());
                } else if (actors.get(i).type.equals("Hoard")) {
                    Hoard hoard = (Hoard) actors.get(i);
                    System.out.println(hoard.getNumFruits());
                }
            }
            System.exit(0);
        }
        if (numTicks == maxTicks) {
            System.out.println("Timed out");
            System.exit(-1);
        }
    }

    /**
     * Main method which read in three command-line arguments
     * then created and run the game
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }
        tickRate = Integer.parseInt(args[0]);
        maxTicks = Integer.parseInt(args[1]);
        worldFile = args[2];
        if (tickRate < 0 || maxTicks < 0) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }
        new ShadowLife().run();
    }
}
