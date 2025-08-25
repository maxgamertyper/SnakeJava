import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/*
For the snake body, use head, turns, and tail
then fill from head to turn, turn to next, and final turn to tail


logic should be
frame start -> get inputs -> do game logic -> update screen
game start, controller knows of model, visualizer knows of model
 */

public class Main {
    public static void main(String[] args) {

        // start window preloading
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter Grid Pixel Width then Grid Pixel Height, will determine all other sizes (width,height)");
        int[] GridSizeDimensions = {scnr.nextInt(),scnr.nextInt()};
        System.out.println("Enter Window Width then Window Height");
        int[] WindowDimensions = {scnr.nextInt(),scnr.nextInt()};
        System.out.println("Enter FPS");
        int fps = scnr.nextInt();
        System.out.println("Enter Apple Count");
        int AppleCount = scnr.nextInt();

        int[] GridLayoutSize = {WindowDimensions[0]/GridSizeDimensions[0],WindowDimensions[1]/GridSizeDimensions[1]};

        GameLogic logic = new GameLogic(GridLayoutSize,AppleCount);

        GameController controller = new GameController(logic);

        // end window preloading, start loading
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        GamePanel panel = new GamePanel(WindowDimensions, GridSizeDimensions, logic);
        panel.setBackground(Color.BLACK);
        panel.addKeyListener(controller);

        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.setFocusable(true);
        panel.requestFocusInWindow();

        // show the window at boot
        frame.setAlwaysOnTop(true);
        frame.toFront();
        frame.setAlwaysOnTop(false);
        // end window setup
        System.out.println("press any key to start");

        Timer gameTimer = new Timer(1000/fps, e -> {
            logic.Update();
            panel.repaint();
        });
        gameTimer.start();
    }
}
