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
import com.theaigames.game.player.AbstractPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Logic interface
 * 
 * DO NOT EDIT THIS FILE
 * 
 * Interface to implement when creating games.
 * 
 * @author Jackie Xu <jackie@starapple.nl>, Jim van Eeden <jim@starapple.nl>
 */
public interface Logic {
    void setupGame(List<IOPlayer> players) throws Exception; // create the players, set up the game
    void playRound(int roundNumber); // play a round
    boolean isGameOver(); // check if the game is over
    void finish() throws Exception; // wrap things up
    void saveGame() throws Exception; // save all the game data
}
