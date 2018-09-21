/*
 * Copyright 2016 riddles.io (developers@riddles.io)
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *     For the full copyright and license information, please view the LICENSE
 *     file that was distributed with this source code.
 */

package src.tictactoe.logic;

import src.core.game.commands.ICommand;
import src.core.game.logic.ILogicHandler;
import src.core.game.player.AbstractPlayer;
import src.tictactoe.commands.PlaceCommand;
import src.tictactoe.commands.RoundUpdateCommand;
import src.tictactoe.board.Board;
import src.tictactoe.messages.PlaceMessage;
import src.tictactoe.messages.RoundUpdateMessage;
import src.tictactoe.moves.Move;
import src.tictactoe.player.Player;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeLogic implements ILogicHandler {

    private int mMoveNumber = 1;
    private List<Player> mPlayers;
    private List<Move> mMoves;
    //    private List<MoveResult> mMoveResults;
    private Board mBoard;
    private int mGameOverByPlayerErrorPlayerId = 0;

    public TicTacToeLogic(List<Player> players, Board board) {
        mPlayers = players;
        mBoard = board;
        mMoves = new ArrayList<>();
//        mMoveResults = new ArrayList<>();
    }

    @Override
    public void playRound(int roundNumber) {
        for (Player player : mPlayers) {
            if (isGameOver()) {
                return;
            }
            playRoundForPlayer(player, roundNumber);
            mMoveNumber++;
        }
    }

    private void playRoundForPlayer(Player player, int roundNumber) {
        sendUpdateToPlayer(player, roundNumber);

        ICommand command = player.requestMove();
        processPlayerMove(player, command);
    }

    private void processPlayerMove(Player player, ICommand command) {
        if (command instanceof PlaceCommand) {
            doPlayerCommand((PlaceCommand) command, player);
        }
    }

    private void sendUpdateToPlayer(Player player, int roundNumber) {
        RoundUpdateMessage roundUpdateMsg = createRoundUpdateMsg(mBoard, mMoveNumber, roundNumber);
        player.sendCommand(new RoundUpdateCommand(roundUpdateMsg));
    }

    private RoundUpdateMessage createRoundUpdateMsg(Board board, int moveNumber, int roundNumber) {
        RoundUpdateMessage msg = new RoundUpdateMessage();
        msg.setRoundNumber(roundNumber);
        msg.setMoveNumber(moveNumber);
        msg.setBoard(board);
        return msg;
    }

    private void doPlayerCommand(PlaceCommand command, Player player) {
//        String oldFieldPresentation = mBoard.toPresentationString(player.getId(), true);

        try {
            PlaceMessage placeMessage = (PlaceMessage) command.getMessage();
            mBoard.placeMark(placeMessage.toLocation(), player.getId());
        } catch (Exception e) {
            player.getBot().outputEngineWarning(e.getMessage());
        }

//        recordMove(player, oldFieldPresentation);
    }

//    private void recordMove(Player player, String oldFieldPresentationString) {
//        Move move = new Move(player);
//        move.setMove(mBoard.getLastX(), mBoard.getLastY());
////        move.setIllegalMove(mBoard.getLastError());
//        mMoves.add(move);
//
//        MoveResult moveResult = new MoveResult(player, move, oldFieldPresentationString, mBoard);
//        moveResult.setMoveNumber(mMoveNumber);
//        mMoveResults.add(moveResult);
//    }

    @Override
    public AbstractPlayer getWinner() {
        int winner = mBoard.getWinnerPlayerId();
        if (mGameOverByPlayerErrorPlayerId > 0) { /* Game over due to too many player errors. Look up the other player, which became the winner */
            for (Player player : mPlayers) {
                if (player.getId() != mGameOverByPlayerErrorPlayerId) {
                    return player;
                }
            }
        }
        if (winner != Board.EMPTY_SPACE) {
            for (Player player : mPlayers) {
                if (player.getId() == winner) {
                    return player;
                }
            }
        }
        return null;
    }

    @Override
    public String getPlayedGame() {
        return "";
    }

    @Override
    public boolean isGameOver() {
        return (!mBoard.hasPossibleMove() || getWinner() != null);
    }

    public Board getBoard() {
        return mBoard;
    }
}