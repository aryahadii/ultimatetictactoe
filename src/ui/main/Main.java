package src.ui.main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import src.tictactoe.TicTacToeGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) throws Exception {
        List<String> bots = getBotsExecPathsFromArgs(args);
        initializeTicTacToeGame(bots);

        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = fxLoader.load();

        addWindowShownEventHandler(fxLoader, stage);

        stage.setTitle("Ultimate TicTacToe");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    private void addWindowShownEventHandler(FXMLLoader fxLoader, Stage stage) {
        final MainController controller = fxLoader.getController();
        stage.addEventHandler(WindowEvent.WINDOW_SHOWN, window -> controller.onWindowShownHandler());
    }

    private static void initializeTicTacToeGame(List<String> botsExecPaths) throws Exception {
        TicTacToeGame.getInstance().addBots(botsExecPaths);
    }

    private static List<String> getBotsExecPathsFromArgs(String[] args) throws RuntimeException {
        if (args.length == 0) {
            throw new RuntimeException("No arguments provided.");
        }
        return new ArrayList<>(Arrays.asList(args));
    }
}
