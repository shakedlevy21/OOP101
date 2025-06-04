package game;
/**
 * Arkanoid game executable.
 * Shaked Levy
 */


public class Ass5Game {
    /**
     * Main execute.
     * @param args - arguments from command line.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
