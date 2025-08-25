import java.util.ArrayList;
import java.util.Arrays;

public class GameLogic {
    public ArrayList<int[]> SnakePositions = new ArrayList<>();
    public int[][] ApplePositions;
    final int[] GridLayoutSize;
    int[] SnakeDirection = new int[]{0,1};
    boolean GameStarted = false;
    boolean GameOver = false;

    public GameLogic(int[] gridLayoutSize, int AppleCount) {
        GridLayoutSize = gridLayoutSize;
        SnakePositions.add(new int[]{Math.floorDiv(GridLayoutSize[0], 2), Math.floorDiv(GridLayoutSize[1], 2)});

        ApplePositions = new int[AppleCount][2];
        for (int i=0; i<AppleCount;i++) {
            ApplePositions[i] = GenerateApplePos();
        }
    }

    public void Restart() {
        SnakePositions = new ArrayList<>();
        SnakePositions.add(new int[]{Math.floorDiv(GridLayoutSize[0], 2), Math.floorDiv(GridLayoutSize[1], 2)});

        for (int i=0; i<ApplePositions.length;i++) {
            ApplePositions[i] = GenerateApplePos();
        }
        GameStarted = false;

        System.out.println("press any key to start!");
    }

    public int[] GenerateApplePos() {
        int[] ApplePosition;
        boolean onSnake;
        boolean onApple;
        do {
            ApplePosition = new int[]{(int) (Math.random() * GridLayoutSize[0]), (int) (Math.random() * GridLayoutSize[1])};
            onSnake = false;
            onApple = false;

            for (int[] segment : SnakePositions) {
                if (Arrays.equals(segment, ApplePosition)) {
                    onSnake = true;
                    break;
                }
            }

            for (int[] apple : ApplePositions) {
                if (Arrays.equals(apple, ApplePosition)) {
                    onApple = true;
                    break;
                }
            }
        } while (onSnake || onApple);
        return ApplePosition;
    }

    public void Update() {
        if (!GameStarted || GameOver) {
            return;
        }
        // update snake head
        int[] last = SnakePositions.getLast();
        int[] newHead = new int[]{last[0] + SnakeDirection[0], last[1] + SnakeDirection[1]};

        newHead[0] = (newHead[0] + GridLayoutSize[0]) % GridLayoutSize[0];
        newHead[1] = (newHead[1] + GridLayoutSize[1]) % GridLayoutSize[1];

        boolean BadCollision = false;
        for (int[] segment : SnakePositions) {
            if (Arrays.equals(segment, newHead)) {
                BadCollision = true;
                break;
            }
        }

        int AppleHit = -1;
        for (int i=0;i<ApplePositions.length;i++) {
            if (Arrays.equals(ApplePositions[i], newHead)) {
                AppleHit = i;
                break;
            }
        }

        if (BadCollision) {
            GameOver = true;

            System.out.println("Game Over! Score: "+(SnakePositions.size()-1));
            System.out.println("press r key to restart!");
        } else {
            SnakePositions.add(newHead);
        }

        if (AppleHit!=-1) {
            ApplePositions[AppleHit] = GenerateApplePos();
        } else {
            SnakePositions.removeFirst();
        }
    }
}
