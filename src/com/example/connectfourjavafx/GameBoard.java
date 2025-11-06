package com.example.connectfourjavafx;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class GameBoard {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final int CELL_SIZE = 80;

    private final GameLogic logic = new GameLogic();
    private final GridPane grid = new GridPane();
    private BorderPane root;

    // 🏆 Scoreboard tracking
    private int xWins = 0;
    private int oWins = 0;
    private Label scoreLabel;
    private Label turnLabel;

    public BorderPane createBoard() {
        root = new BorderPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setStyle("-fx-background-color:#003366;");

        // Create cells
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                StackPane cell = new StackPane();
                Circle circle = new Circle(CELL_SIZE / 2 - 5);
                circle.setFill(Color.WHITE);
                cell.getChildren().add(circle);
                grid.add(cell, col, row);

                int finalCol = col;
                cell.setOnMouseClicked((MouseEvent e) -> handleMove(finalCol));
            }
        }

        // 🪄 New Game button
        Button resetBtn = new Button("New Game 🔁");
        resetBtn.setStyle("-fx-font-size: 16px; -fx-background-color: #FFD700;");
        resetBtn.setOnAction(e -> resetGame());

        // 🏆 Reset scores button
        Button resetScoresBtn = new Button("Reset Scores 🧹");
        resetScoresBtn.setStyle("-fx-font-size: 14px; -fx-background-color: #FF6347;");
        resetScoresBtn.setOnAction(e -> resetScores());

        // 🏆 Score + Turn Labels
        scoreLabel = new Label("Player X: 0 | Player O: 0");
        scoreLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        turnLabel = new Label("Current Turn: Player X 🔴");
        turnLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

        // Top scoreboard layout
        HBox topBox = new HBox(40, scoreLabel, turnLabel, resetScoresBtn);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(15));

        // Bottom control layout
        HBox bottomBox = new HBox(20, resetBtn);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(15));

        root.setTop(topBox);
        root.setCenter(grid);
        root.setBottom(bottomBox);

        return root;
    }

    private void handleMove(int col) {
        int row = logic.dropPiece(col);
        if (row == -1) return; // column full

        refreshBoard(row, col); // only animate new piece

        if (logic.checkWin()) {
            String winner = String.valueOf(logic.getCurrentPlayer());
            if (winner.equals("X")) xWins++;
            else oWins++;
            updateScoreboard();
            showWinnerPopup("🎉 Player " + winner + " wins!");
        } else {
            logic.switchPlayer();
            updateTurnLabel();
        }
    }

    // 🔄 Refresh board — animate only last move
    private void refreshBoard(int lastRow, int lastCol) {
        for (Node node : grid.getChildren()) {
            if (node instanceof StackPane) {
                StackPane cell = (StackPane) node;
                Integer col = GridPane.getColumnIndex(cell);
                Integer row = GridPane.getRowIndex(cell);
                Circle circle = (Circle) cell.getChildren().get(0);
                char val = logic.getCell(row, col);

                switch (val) {
                    case 'X' -> circle.setFill(Color.RED);
                    case 'O' -> circle.setFill(Color.YELLOW);
                    default -> circle.setFill(Color.WHITE);
                }

                // 🎯 Animate ONLY the last placed piece
                if (row == lastRow && col == lastCol && val != ' ') {
                    double distance = (ROWS - row) * (CELL_SIZE + 5);
                    TranslateTransition fall = new TranslateTransition(Duration.millis(600), circle);
                    fall.setFromY(-distance);
                    fall.setToY(0);
                    fall.setInterpolator(Interpolator.EASE_OUT);

                    fall.setOnFinished(e -> {
                        circle.setScaleX(1.1);
                        circle.setScaleY(1.1);
                        ScaleTransition st = new ScaleTransition(Duration.millis(150), circle);
                        st.setToX(1.0);
                        st.setToY(1.0);
                        st.play();
                    });

                    fall.play();
                }
            }
        }
    }

    private void showWinnerPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        resetGame();
    }

    private void resetGame() {
        logic.resetBoard();
        refreshBoard(-1, -1);
        updateTurnLabel();
    }

    private void resetScores() {
        xWins = 0;
        oWins = 0;
        updateScoreboard();
        updateTurnLabel();
    }

    private void updateScoreboard() {
        scoreLabel.setText("Player X: " + xWins + " | Player O: " + oWins);
    }

    private void updateTurnLabel() {
        char player = logic.getCurrentPlayer();
        String emoji = (player == 'X') ? "🔴" : "🟡";
        turnLabel.setText("Current Turn: Player " + player + " " + emoji);
    }

    private Circle findCircleAt(int row, int col) {
        for (Node node : grid.getChildren()) {
            if (node instanceof StackPane) {
                StackPane cell = (StackPane) node;
                Integer c = GridPane.getColumnIndex(cell);
                Integer r = GridPane.getRowIndex(cell);
                if (r == row && c == col)
                    return (Circle) cell.getChildren().get(0);
            }
        }
        return null;
    }
}
