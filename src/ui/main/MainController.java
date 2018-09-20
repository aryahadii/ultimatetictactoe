package src.ui.main;

import src.representers.FieldRepresenter;
import src.representers.Space;
import src.tictactoe.TicTacToeGame;

import java.util.Timer;
import java.util.TimerTask;

public class MainController {
    void onWindowShownHandler() {
        runGameNonBlocking();
        runFieldUpdater();
    }

    private void runFieldUpdater() {
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                updateFieldUI();
            }

        }, 1000, 1000);
    }

    private void updateFieldUI() {
        FieldRepresenter representer = TicTacToeGame.getInstance().getFieldData();
        // TODO
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
