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

package com.theaigames.engine;

import com.theaigames.engine.io.IOPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Engine class
 * <p>
 * A general engine to implement IO for bot classes
 * All game game is handled by implemented game interfaces.
 *
 * @author Jackie Xu <jackie@starapple.nl>, Jim van Eeden <jim@starapple.nl>
 */
public class Engine {

    private List<IOPlayer> players;
    private boolean isRunning;
    private IGame game;

    public Engine() {
        this.isRunning = false;
        this.players = new ArrayList<>();
    }

    /**
     * Start up the bot process and add the player to the game
     *
     * @param command : command to start a bot process
     */
    public void addPlayer(String command, String botId) throws IOException {
        Process process = Runtime.getRuntime().exec(command);

        IOPlayer player = new IOPlayer(process, botId);
        this.players.add(player);
        player.run();
    }

    /**
     * Sets the game's game
     *
     * @param game
     */
    public void setGame(IGame game) {
        this.game = game;
    }

    /**
     * Determines whether the game has ended
     *
     * @return : true if the game has ended
     */
    public boolean hasEnded() {
        return this.game.isGameOver();
    }

    /**
     * @return : A list of all the players in this game
     */
    public List<IOPlayer> getPlayers() {
        return this.players;
    }

    /**
     * Starts the game
     */
    public void start() throws Exception {
        this.isRunning = true;

        this.game.setupGame(this.players);

        for (int round = 0; this.isRunning; round++) {
            this.game.playRound(round);

            if (this.hasEnded()) {
                System.out.println("stopping...");

                this.isRunning = false;
                try {
                    this.game.finish();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
