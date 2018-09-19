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

package src.core;

import src.core.game.logic.ILogicHandler;
import src.core.io.IOPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Engine class
 * <p>
 * A general engine to implement IO for bot classes
 * All logic is handled by implemented logic interfaces.
 *
 * @author Jackie Xu <jackie@starapple.nl>, Jim van Eeden <jim@starapple.nl>
 */
public class Engine {

    private List<IOPlayer> players;
    private ILogicHandler logic;
    private boolean isRunning;

    public Engine() {
        this.isRunning = false;
        this.players = new ArrayList<>();
    }

    /**
     * Start up the bot process and add the player to the logic
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
     * Sets the engine's logic
     *
     * @param logic
     */
    public void setLogic(ILogicHandler logic) {
        this.logic = logic;
    }

    /**
     * Determines whether the logic has ended
     *
     * @return : true if the logic has ended
     */
    public boolean hasEnded() {
        return this.logic.isGameOver();
    }

    /**
     * @return : A list of all the players in this logic
     */
    public List<IOPlayer> getPlayers() {
        return this.players;
    }

    /**
     * Starts the logic
     */
    public void start() {
        this.isRunning = true;

        for (int round = 0; this.isRunning; round++) {
            for (IOPlayer ioPlayer : this.getPlayers())
                ioPlayer.addToDump(String.format("Round %d", round));

            this.logic.playRound(round);

            if (this.hasEnded()) {
                System.out.println("stopping...");

                this.isRunning = false;
                return;
            }
        }
    }

}
