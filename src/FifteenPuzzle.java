import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class FifteenPuzzle {
    private String[][] board;
    private int emptyRow;
    private int emptyCol;

    public FifteenPuzzle() {
        board = new String[4][4];
        initializeBoard();
        shuffleBoard();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        while (!gameOver) {
            printBoard();
            System.out.print("Enter move (w/a/s/d): ");
            char move = scanner.nextLine().charAt(0);

            if (isValidMove(move)) {
                makeMove(move);
                if (isSolved()) {
                    gameOver = true;
                    System.out.println("Congratulations! You solved the puzzle.");
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    private void initializeBoard() {
        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (count < 10) {
                    board[i][j] = "0" + count;
                } else {
                    board[i][j] = Integer.toString(count);
                }
                count++;
            }
        }
        emptyRow = 3;
        emptyCol = 3;
        board[emptyRow][emptyCol] = "  ";
    }

    private void shuffleBoard() {
        Random random = new Random();
        int moves = 100;

        while (moves > 0) {
            char[] possibleMoves = getPossibleMoves();
            char randomMove = possibleMoves[random.nextInt(possibleMoves.length)];
            makeMove(randomMove);
            moves--;
        }
    }

    private char[] getPossibleMoves() {
        String moves = "";

        if (emptyRow > 0) {
            moves += "w";
        }
        if (emptyRow < 3) {
            moves += "s";
        }
        if (emptyCol > 0) {
            moves += "a";
        }
        if (emptyCol < 3) {
            moves += "d";
        }

        return moves.toCharArray();
    }

    private boolean isValidMove(char move) {
        char[] possibleMoves = getPossibleMoves();
        for (char possibleMove : possibleMoves) {
            if (move == possibleMove) {
                return true;
            }
        }
        return false;
    }

    private void makeMove(char move) {
        switch (move) {
            case 'w':
                swapTiles(emptyRow, emptyCol, emptyRow - 1, emptyCol);
                emptyRow--;
                break;
            case 's':
                swapTiles(emptyRow, emptyCol, emptyRow + 1, emptyCol);
                emptyRow++;
                break;
            case 'a':
                swapTiles(emptyRow, emptyCol, emptyRow, emptyCol - 1);
                emptyCol--;
                break;
            case 'd':
                swapTiles(emptyRow, emptyCol, emptyRow, emptyCol + 1);
                emptyCol++;
                break;
        }
    }

    private void swapTiles(int row1, int col1, int row2, int col2) {
        String temp = board[row1][col1];
        board[row1][col1] = board[row2][col2];
        board[row2][col2] = temp;
    }

    private boolean isSolved() {
        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!board[i][j].equals(String.format("%02d", count))) {
                    return false;
                }
                count++;
                if (count == 16) {
                    return true;
                }
            }
        }
        return true;
    }

    private void printBoard() {
        System.out.println("-----------------");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("| " + board[i][j]);
            }
            System.out.println("|");
            System.out.println("-----------------");
        }
    }

    public static void main(String[] args) {
        FifteenPuzzle game = new FifteenPuzzle();
        game.play();
    }
}
