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

package src.tictactoe.board;

public class Board {
    public static final int EMPTY_SPACE = LocalBoard.EMPTY_SPACE;

    private LocalBoard[][] localBoards;
    private Location lastMarkLoc;
    private static final int colsCount = 3, rowsCount = 3;

    public Board() {
        reinitializeMiniBoards();
    }

    private void reinitializeMiniBoards() {
        localBoards = new LocalBoard[rowsCount][colsCount];
        for (int y = 0; y < rowsCount; y++) {
            for (int x = 0; x < colsCount; x++) {
                localBoards[y][x] = new LocalBoard();
            }
        }
    }

    public void placeMark(Location markLoc, int playerId) throws Exception {
        if (!isMarkValid(markLoc)) {
            throw new Exception("mark place is not valid");
        }

        int boardX = markLoc.getX() / colsCount;
        int boardY = markLoc.getY() / rowsCount;
        Location localBoardMark = new Location(markLoc.getX() % colsCount, markLoc.getY() % rowsCount);
        localBoards[boardY][boardX].placeMark(localBoardMark, playerId);
        lastMarkLoc = markLoc;
    }

    private boolean isMarkValid(Location markLoc) {
        if (markLoc.getX() >= colsCount || markLoc.getX() < 0) {
            return false;
        }
        if (markLoc.getY() >= rowsCount || markLoc.getY() < 0) {
            return false;
        }
        if (!isInActiveLocalBoard(markLoc)) {
            return false;
        }
        return true;
    }

    private boolean isInActiveLocalBoard(Location markLoc) {
        if (lastMarkLoc == null) {
            return true;
        }

        Location lastLocalBoardLoc = getLocalBoardLocation(lastMarkLoc);
        return isMarkInLocalBoard(markLoc, lastLocalBoardLoc);

    }

    private boolean isMarkInLocalBoard(Location markLoc, Location localBoardLoc) {
        if (markLoc.getX() < localBoardLoc.getX() * colsCount) {
            return false;
        }
        if (markLoc.getX() >= (localBoardLoc.getX() + 1) * colsCount) {
            return false;
        }
        if (markLoc.getY() < localBoardLoc.getY() * rowsCount) {
            return false;
        }
        if (markLoc.getY() >= (localBoardLoc.getY() + 1) * rowsCount) {
            return false;
        }
        return true;
    }

    private Location getLocalBoardLocation(Location mark) {
        int x = (mark.getX() / colsCount) * colsCount;
        int y = (mark.getY() / rowsCount) * rowsCount;
        return new Location(x, y);
    }


    public int getWinnerPlayerId() {
        int[][] winnerPlayers = new int[rowsCount][colsCount];
        for (int y = 0; y < rowsCount; y++) {
            for (int x = 0; x < colsCount; x++) {
                winnerPlayers[y][x] = localBoards[y][x].getWinnerPlayerId();
            }
        }

        return Utils.getWinnerPlayerId(winnerPlayers, EMPTY_SPACE);
    }

    public boolean hasPossibleMove() {
        for (int y = 0; y < rowsCount; y++) {
            for (int x = 0; x < colsCount; x++) {
                if (localBoards[y][x].hasPossibleMove()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder representer = new StringBuilder();
        for (int y = 0; y < rowsCount; y++) {
            for (int x = 0; x < colsCount; x++) {
                representer.append(localBoards[y][x].toString());
                representer.append(';');
            }
        }
        return representer.toString();
    }
}
