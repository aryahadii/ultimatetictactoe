package com.aryaha.ui.main;

import com.aryaha.representers.BoardRepresenter;
import com.aryaha.tictactoe.TicTacToeGame;
import com.aryaha.tictactoe.board.Location;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.*;

public class MainController {
    @FXML
    private GridPane gameGrid;
    private final Image firstPlayerMark, secondPlayerMark, emptyMark;
    private Set<Location> filledGridCells = new HashSet<>();

    public MainController() {
        this.firstPlayerMark = new Image(String.valueOf(getClass().getResource("/images/x.png")));
        this.secondPlayerMark = new Image(String.valueOf(getClass().getResource("/images/o.png")));
        this.emptyMark = new Image(String.valueOf(getClass().getResource("/images/empty.png")));
    }

    void onWindowShownHandler() {
        runGameNonBlocking();
        runFieldUpdater();

        addDefaultMarks(emptyMark);
    }

    private void runFieldUpdater() {
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                updateFieldUI();
            }

        }, 10, 100);
    }

    private void updateFieldUI() {
        BoardRepresenter representer = TicTacToeGame.getInstance().getBoardData();
        List<Location> x = extractBoardCells(representer, 1);
        List<Location> y = extractBoardCells(representer, 2);

        Platform.runLater(() -> updateGridPane(x, y));
    }

    private List<Location> extractBoardCells(BoardRepresenter board, int expectedValue) {
        List<Location> cells = new ArrayList<>();

        for (int y = 0; y < BoardRepresenter.rowsCount; y++) {
            for (int x = 0; x < BoardRepresenter.colsCount; x++) {
                Location loc = new Location(x, y);
                if (board.getMark(loc) == expectedValue) {
                    cells.add(loc);
                }
            }
        }

        return cells;
    }

    private void updateGridPane(List<Location> x, List<Location> o) {
        addMark(x, firstPlayerMark);
        addMark(o, secondPlayerMark);
    }

    private void addMark(List<Location> marksLocation, Image markImage) {
        for (Location loc : marksLocation) {
            if (filledGridCells.contains(loc)) {
                continue;
            }
            addImageView(markImage, loc.getX(), loc.getY());
            filledGridCells.add(loc);
        }
    }

    private void addDefaultMarks(Image defaultImage) {
        for (int y = 0; y < BoardRepresenter.rowsCount; y++) {
            for (int x = 0; x < BoardRepresenter.colsCount; x++) {
                addImageView(defaultImage, x, y);
            }
        }
    }

    private void addImageView(Image img, int x, int y) {
        ImageView view = new ImageView(img);
        view.fitHeightProperty().bind(gameGrid.heightProperty().divide(10));
        view.setPreserveRatio(true);
        gameGrid.add(view, x, y);

        NumberBinding xScale = Bindings.min(1, gameGrid.heightProperty().divide(gameGrid.widthProperty()));
        gameGrid.scaleXProperty().bind(xScale);
    }

    private void runGameNonBlocking() {
        new Thread(() -> {
            try {
                TicTacToeGame.getInstance().run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
