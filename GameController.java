import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController extends KeyAdapter {
    private final GameLogic gameLogic;
    private static final int[] UP    = {0, -1};
    private static final int[] DOWN  = {0, 1};
    private static final int[] LEFT  = {-1, 0};
    private static final int[] RIGHT = {1, 0};

    public GameController(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        gameLogic.GameStarted = true;

        if (keyCode == KeyEvent.VK_UP && gameLogic.SnakeDirection!=DOWN) {
            gameLogic.SnakeDirection = UP;
        } else if (keyCode == KeyEvent.VK_DOWN && gameLogic.SnakeDirection!=UP) {
            gameLogic.SnakeDirection = DOWN;
        } else if (keyCode == KeyEvent.VK_LEFT && gameLogic.SnakeDirection!=RIGHT) {
            gameLogic.SnakeDirection = LEFT;
        } else if (keyCode == KeyEvent.VK_RIGHT && gameLogic.SnakeDirection!=LEFT) {
            gameLogic.SnakeDirection = RIGHT;
        } else if (keyCode == KeyEvent.VK_R) {
            gameLogic.GameOver=false;
            gameLogic.Restart();
        }
    }
}
