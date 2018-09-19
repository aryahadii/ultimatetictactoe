package src;

import src.tictactoe.TicTacToeGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Launcher {

    private static void runGame(List<String> botsExecPaths) throws Exception {
        TicTacToeGame game = new TicTacToeGame(botsExecPaths);
        game.run();
    }

    private static List<String> getBotsExecPathsFromArgs(String[] args) throws RuntimeException {
        if (args.length == 0) {
            throw new RuntimeException("No arguments provided.");
        }
        return new ArrayList<>(Arrays.asList(args));
    }

    public static void main(String args[]) throws Exception {
        List<String> botsExecPaths = getBotsExecPathsFromArgs(args);

        runGame(botsExecPaths);
    }

}
