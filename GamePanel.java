import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    private final GameLogic logic;
    private final int[] GridPanelDimensions;

    public GamePanel(int[] dimensions, int[] gridPanelDimensions, GameLogic gameLogic) {
        this.setPreferredSize(new Dimension(dimensions[0],dimensions[1]));
        logic = gameLogic;
        GridPanelDimensions = gridPanelDimensions;
    }
    @Override
    protected void paintComponent(Graphics g) {
        // Always call the superclass method first.
        // This clears the panel and prepares it for drawing.
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        for (int i = 0; i < logic.SnakePositions.size(); i++) {
            g.fillRect(logic.SnakePositions.get(i)[0]*GridPanelDimensions[0], logic.SnakePositions.get(i)[1]*GridPanelDimensions[1], GridPanelDimensions[0], GridPanelDimensions[1]);
        }

        g.setColor(Color.RED);
        for (int i = 0; i < logic.ApplePositions.length; i++) {
            g.fillRect(logic.ApplePositions[i][0]*GridPanelDimensions[0], logic.ApplePositions[i][1]*GridPanelDimensions[1], GridPanelDimensions[0], GridPanelDimensions[1]);
        }
    }
}
