package game;

import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import game.gui.shapes.Paddle;
import game.gui.shapes.Ball;
import game.gui.shapes.Block;
import game.gui.sprites.Sprite;
import game.gui.sprites.Collidable;
import game.gui.sprites.SpriteCollection;
import game.listener.HitListener;
import game.listener.Counter;
import game.listener.ScoreTrackingListener;
import game.listener.BallRemover;
import game.listener.BlockRemover;

/**
 * Class game to execute all the functions for the game to work.(initialize and run).
 */
public class Game {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Counter blockcounter = new Counter();
    private Counter ballcounter = new Counter();
    private Counter scorecounter = new Counter();
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
     * removes a collidable object.
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove a sprite.
     * @param s to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
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
        screen.setColor(Color.WHITE);
        screen.addToGame(this);
        // blocks
        int startHeight = 100;
        int blockHeight = 30;
        int blockWidth = 50;
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};
        int[] blocksInRow = {12, 11, 10, 9, 8, 7};

        //block remover and counter
        HitListener blockremover = new BlockRemover(this, blockcounter);
        //ball remover and counter
        HitListener ballremover = new BallRemover(this, ballcounter);
        //score listener
        HitListener score = new ScoreTrackingListener(this.scorecounter);

        // balls
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            double direction = rand.nextDouble(6) + 1;
            double direction2 = rand.nextDouble(6) + 1;
            Ball ball = new Ball(100, 100, 7, Color.white);
            ball.setVelocity(direction, direction2);
            ball.addToGame(this);
            ball.setEnvironment(environment);
            ballcounter.increase(1);
        }
        // blocks
        for (int row = 0; row < colors.length; row++) {
            for (int i = 1; i <= blocksInRow[row]; i++) {
                Block b = new Block(width - walldepth - (i * blockWidth), startHeight + (row * blockHeight),
                        blockWidth, blockHeight);
                b.setColor(colors[row]);
                b.addToGame(this);
                //HitListener h = new PrintingHitListener();
                //b.addHitListener(h);
                blockcounter.increase(1);
                b.addHitListener(blockremover);
                b.addHitListener(score);
            }
        }

        // paddle
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Paddle paddle = new Paddle((width / 2) - (walldepth / 2), height - walldepth - 10,
                150, 10, keyboard, this.screenwidth, this.screenheight);
        paddle.setColor(Color.ORANGE);
        paddle.addToGame(this);
        // walls
        Block[] walls = new Block[4];
        walls[0] = new Block(0, 0, width, walldepth); // top wall
        walls[1] = new Block(0, height - walldepth, width, walldepth); // bottom wall
        walls[2] = new Block(0, walldepth, walldepth, height - (2 * walldepth)); // left wall
        walls[3] = new Block(width - walldepth, walldepth, walldepth, height - (2 * walldepth)); // right wall
        walls[1].addHitListener(ballremover); // add the listener to the bottom wall
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
            if (this.blockcounter.getValue() == 0) {
                this.scorecounter.increase(100);
                this.scorecounter.draw(d, this.screenwidth / 2 - 40, 20);
                gui.show(d);
                return;
            } else if (this.ballcounter.getValue() == 0) {
                return;
            }
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();
            this.scorecounter.draw(d, this.screenwidth / 2 - 40, 20);
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
