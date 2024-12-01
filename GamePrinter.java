public class GamePrinter {
    public void printGameStart(int boardSize, Player firstPlayer, Player secondPlayer) {
        System.out.println("Welcome to Reversi");
        System.out.println("Board size: " + boardSize + "x" + boardSize);
        System.out.println("First player " + (firstPlayer.isHuman() ? "Human Player" : "AI Player") +
                " (Player One)");
        System.out.println("Second Player " + (secondPlayer.isHuman() ? "Human Player" : "AI Player") +
                " (Player Two)");
    }

    public void printGameOver(int firstPlayerScore, int secondPlayerScore) {
        System.out.println("GamePrinter.printGameOver called!");
        System.out.println("Game Over!");
        System.out.println("Final Scores:");
        System.out.println("Player One: " + firstPlayerScore);
        System.out.println("Player Two: " + secondPlayerScore);
        if (firstPlayerScore > secondPlayerScore) {
            System.out.println("Player One wins!");
        } else if (firstPlayerScore < secondPlayerScore) {
            System.out.println("Player Two wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    public void printBoard(Disc[][] board) {
        System.out.println(" 0 1 2 3 4 5 6 7"); // Column headers
        for (int row = 0; row < board.length; row ++) {
            System.out.println(row + " "); // Row header
            for (int col = 0; col < board[row].length; col++) {
                Disc disc = board[row][col];
                if (disc == null) {
                    System.out.println(". "); // Empty cell
                } else {
                    System.out.println(disc.getType() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printMove(Player player, Position position, int flippedCount) {
        System.out.println((player.isHuman() ? "Human Player" : "AI Player") +
        " (" + (player.isPlayerOne() ? "Player One" : "Player Two") + ")" +
        " places a disc at (" + position.row() + ", " + position.col() + ").");
        System.out.println("Flipped " + flippedCount + " disc.");

    }
    public void printGameStatus(Player firstPlayer, Player secondPlayer, int firstPlayerScore, int secondPlayerScore, boolean isFirstPlayerTurn) {
        System.out.println("Game Over!");
        System.out.println("Final Scores:");
        System.out.println((firstPlayer.isHuman() ? "Human Player" : "AI Player") + " (Player One): " + firstPlayerScore);
        System.out.println((secondPlayer.isHuman() ? "Human Player" : "AI Player") + " (Player Two): " + secondPlayerScore);
        if (firstPlayerScore > secondPlayerScore) {
            System.out.println((firstPlayer.isHuman() ? "Human Player" : "AI Player") + " (Player One) wins!");
        } else if (firstPlayerScore < secondPlayerScore) {
            System.out.println((secondPlayer.isHuman() ? "Human Player" : "AI Player") + " (Player Two) wins!");
        } else {
            System.out.println("It's a draw!");
        }
        System.out.println();

    }
    public void printUndoMessage(Player player) {
        System.out.println((player.isHuman() ? "Human Player" : "AI Player") + " (" + (player.isPlayerOne() ? "Player One" : "Player Two") + ")" +
                " undone the last move.");
    }
}
