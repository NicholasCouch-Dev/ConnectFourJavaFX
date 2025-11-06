package com.example.connectfourjavafx;

public class GameLogic {
    private static final int ROWS = 6;
    private static final int COLS = 7;

    private final char[][] board = new char[ROWS][COLS];
    private char currentPlayer = 'X';

    public GameLogic() {
        resetBoard();
    }

    public int dropPiece(int col) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                return row;
            }
        }
        return -1; // Column full
    }

    public boolean checkWin() {
        // Horizontal
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLS - 4; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row][col + 1] == currentPlayer &&
                    board[row][col + 2] == currentPlayer &&
                    board[row][col + 3] == currentPlayer) {
                    return true;
                }
            }
        }

        // Vertical
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row <= ROWS - 4; row++) {
                if (board[row][col] == currentPlayer &&
                    board[row + 1][col] == currentPlayer &&
                    board[row + 2][col] == currentPlayer &&
                    board[row + 3][col] == currentPlayer) {
                    return true;
                }
            }
        }

        // Diagonal Down-Right
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col <= COLS - 4; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row + 1][col + 1] == currentPlayer &&
                    board[row + 2][col + 2] == currentPlayer &&
                    board[row + 3][col + 3] == currentPlayer) {
                    return true;
                }
            }
        }

        // Diagonal Up-Right
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col <= COLS - 4; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row - 1][col + 1] == currentPlayer &&
                    board[row - 2][col + 2] == currentPlayer &&
                    board[row - 3][col + 3] == currentPlayer) {
                    return true;
                }
            }
        }

        return false;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void resetBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = ' ';
            }
        }
        currentPlayer = 'X';
    }

    public char getCell(int row, int col) {
        return board[row][col];
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
}
