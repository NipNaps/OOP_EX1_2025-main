import java.util.ArrayList;
import java.util.List;
public class BombDisc implements Disc {

    private Player player;

    public BombDisc(Player player) {
        this.player = player;
    }
    public Player getOwner() {
        return player;
    }

    public void setOwner(Player player) {
        this.player = player;
    }

    public String getType() {
        return "\uD83D\uDCA3";
    }
    public List<Position> explode(Disc[][] board, Position position) {
        List<Position> destroyedPositions = new ArrayList<>();
        int[][] directions = {
                {-1, -1}, {-1,  0}, {-1,  1},
                { 0, -1}, { 0,  1},
                { 1, -1}, { 1,  0}, { 1,  1}
        };
        for(int[] direction : directions) {
            int newRow = position.row() + direction[0];
            int newCol = position.col() + direction[1];

            if(newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                if(board[newRow][newCol] != null) {
                    destroyedPositions.add(new Position(newRow, newCol));
                    board[newRow][newCol] = null; // Remove the disc from the board
                }
            }
        }
        return destroyedPositions;
    }
}
