import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Paddle class for the movable object to play with in the game.
 */
public class Paddle extends Block implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private final int velocity = 4;
    private int screenwidth;
    private int screenheight;

    /**
     * Constructor.
     * @param x - coordinates for upper left corner.
     * @param y - coordinates for upper left corner.
     * @param width - width of the paddle.
     * @param height - height of the paddle.
     * @param keyboard - keyboard sensor to check for keystrokes.
     * @param screenwidth - to check for out of bounds.
     * @param screenheight - to check for out of bounds.
     */
    public Paddle(int x, int y, int width, int height,
                  biuoop.KeyboardSensor keyboard, int screenwidth, int screenheight) {
        super(x, y, width, height);
        this.keyboard = keyboard;
        this.screenwidth = screenwidth;
        this.screenheight = screenheight;
    }

    /**
     * moves the paddle left if the left arrow in the keyboard is pressed and checks for edges and moves accordingly.
     */
    public void moveLeft() {
        if (this.getX() + this.getWidth() < 0) {
            this.setX(screenwidth);
        }
        this.setX(this.getX() - velocity);
    }
    /**
     * moves the paddle right if the left arrow in the keyboard is pressed and checks for edges and moves accordingly.
     */
    public void moveRight() {
        if (this.getX() > screenwidth) {
            this.setX(-this.getWidth());
        }
        this.setX(this.getX() + velocity);
    }
    //Sprite.
    /**
     * Time passed moves the paddle according to what we do.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
    }

    // Collidable.

    /**
     * Right now just returns this as a Rectangle.
     * @return (Rectangle) this
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return (Rectangle) this;
    }

    /**
     * Hit function to let the paddle know it has been it and returns a new velocity of the hitting object.
     * @param collisionPoint to check with
     * @param currentVelocity current velocity
     * @return the new velcity to change for the hitting object.
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity posthit = super.hit(collisionPoint, currentVelocity);
        if (posthit != null) {
            if (currentVelocity.getDy() == -posthit.getDy()) { // than top or bottom hit
                //check for area by the collision point in relation to the paddle
                double startofpaddle = this.getX();
                double endofpaddle = this.getX() + this.getWidth();
                int relationpoint = (int) collisionPoint.getX() - (int) startofpaddle;
                int dividerpoint = (int) this.getWidth() / 5;
                double angle = currentVelocity.getAngle();
                double speed = currentVelocity.getSpeed();
                Velocity newvelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
                if (relationpoint <= dividerpoint) {
                    return Velocity.fromAngleAndSpeed(210, speed);
                } else if (relationpoint <= dividerpoint * 2) {
                    return Velocity.fromAngleAndSpeed(240, speed);
                } else if (relationpoint <= dividerpoint * 3) {
                    return Velocity.fromAngleAndSpeed(-90, speed);
                 } else if (relationpoint <= dividerpoint * 4) {
                    return Velocity.fromAngleAndSpeed(-60, speed);
                } else if (relationpoint <= this.getWidth()) {
                    return Velocity.fromAngleAndSpeed(-30, speed);
                }
            }
        }
        return posthit;
    }

    /**
     * Add this paddle to the game.
     * @param g - game to add to.
     */
    public void addToGame(Game g) {
        super.addToGame(g);
    }
}
