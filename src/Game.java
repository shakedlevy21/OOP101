import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Class game to execute all the functions for the game to work.(initialize and run).
 */
public class Game {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private final int screenwidth = 800;
    private final int screenheight = 600;
    private final GUI gui = new GUI("Arkanoid", screenwidth, screenheight);

    /**
     * Adds a new collidable to the array list.
     * @param c - collidable to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * Adds a new Sprite to the array list.
     * @param s - new sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        int width = 800;
        int height = 600;
        int walldepth = 20;
        // screen block for safety
        Block screen = new Block(0, 0, width, height);
        screen.setColor(Color.BLUE);
        screen.addToGame(this);
        // blocks
        int startHeight = 100;
        int blockHeight = 30;
        int blockWidth = 50;
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};
        int[] blocksInRow = {12, 11, 10, 9, 8, 7};

        for (int row = 0; row < colors.length; row++) {
            for (int i = 1; i <= blocksInRow[row]; i++) {
                Block b = new Block(width - walldepth - (i * blockWidth), startHeight + (row * blockHeight),
                        blockWidth, blockHeight);
                b.setColor(colors[row]);
                b.addToGame(this);
            }
        }
        // balls
        Ball ball = new Ball(100, 100, 5, Color.white);
        ball.setVelocity(5, 5);
        ball.addToGame(this);
        ball.setEnvironment(environment);
        Ball ball2 = new Ball(100, 200, 5, Color.white);
        ball2.setVelocity(9, 4);
        ball2.addToGame(this);
        ball2.setEnvironment(environment);
        // paddle
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Paddle paddle = new Paddle((width / 2) - (walldepth / 2), height - walldepth - 20,
                120, 20, keyboard, this.screenwidth, this.screenheight);
        paddle.setColor(Color.ORANGE);
        paddle.addToGame(this);
        // walls
        Block[] walls = new Block[4];
        walls[0] = new Block(0, 0, width, walldepth); // top wall
        walls[1] = new Block(0, height - walldepth, width, walldepth); // bottom wall
        walls[2] = new Block(0, walldepth, walldepth, height - (2 * walldepth)); // left wall
        walls[3] = new Block(width - walldepth, walldepth, walldepth, height - (2 * walldepth)); // right wall
        for (Block b : walls) {
            b.setColor(Color.GRAY);
            b.addToGame(this);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();
            gui.show(d);
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
