

package com.aryaha.tictactoe.moves;

import com.aryaha.core.game.moves.AbstractMove;
import com.aryaha.core.game.player.AbstractPlayer;

public class Move extends AbstractMove {

	private int mColumn = 0, mRow = 0;
	
	public Move(AbstractPlayer player) {
		super(player);
	}
	
	/**
	 * @param column : Sets the column of a move
	 */
	public void setMove(int column, int row) {
		this.mColumn = column;
		this.mRow = row;
	}

	/**
	 * @return : Column of move
	 */
	public int getColumn() {
		return mColumn;
	}

	/**
	 * @return : Row of move
	 */
	public int getRow() {
		return mRow;
	}
}