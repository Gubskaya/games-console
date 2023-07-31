import java.util.Scanner;

public class BattleshipGame {
    private static final int BOARD_SIZE = 10;
    private static final char WATER = '~';
    private static final char SHIP = 'S';
    private static final char HIT = 'X';
    private static final char MISS = 'O';

    private char[][] board;
    private int shipsRemaining;

    public BattleshipGame() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
        shipsRemaining = 5;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (shipsRemaining > 0) {
            printBoard();

            System.out.print("Enter target coordinates (row column): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (isValidMove(row, col)) {
                if (board[row][col] == HIT || board[row][col] == MISS) {
                    System.out.println("You've already tried that position. Try again.");
                } else if (board[row][col] == WATER) {
                    board[row][col] = MISS;
                    System.out.println("Miss!");
                } else {
                    board[row][col] = HIT;
                    shipsRemaining--;
                    System.out.println("Hit!");
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        System.out.println("Congratulations! You sank all the battleships!");
        scanner.close();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = WATER;
            }
        }

        placeShip(2);
        placeShip(3);
        placeShip(3);
        placeShip(4);
        placeShip(5);
    }

    private void placeShip(int shipSize) {
        boolean horizontal = Math.random() < 0.5;
        int row, col;

        do {
            row = (int) (Math.random() * BOARD_SIZE);
            col = (int) (Math.random() * BOARD_SIZE);
        } while (!isValidPlacement(row, col, shipSize, horizontal));

        for (int i = 0; i < shipSize; i++) {
            if (horizontal) {
                board[row][col + i] = SHIP;
            } else {
                board[row + i][col] = SHIP;
            }
        }
    }

    private boolean isValidPlacement(int row, int col, int shipSize, boolean horizontal) {
        if (horizontal) {
            if (col + shipSize > BOARD_SIZE) {
                return false;
            }
            for (int i = 0; i < shipSize; i++) {
                if (board[row][col + i] != WATER) {
                    return false;
                }
            }
        } else {
            if (row + shipSize > BOARD_SIZE) {
                return false;
            }
            for (int i = 0; i < shipSize; i++) {
                if (board[row + i][col] != WATER) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }

    private void printBoard() {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == HIT || board[i][j] == MISS) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print(WATER + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BattleshipGame game = new BattleshipGame();
        game.play();
    }
}