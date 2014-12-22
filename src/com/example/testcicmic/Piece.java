package com.example.testcicmic;

import java.util.ArrayList;

import com.example.testcicmic.AvailableMoves;
//import com.example.testcicmic.cicmicCore.moveStatus;
import com.example.testcicmic.PieceColor;
import com.example.testcicmic.PieceState;

public class Piece {
	
	
	// properties
	private Cell cell_location;
	private PieceState state;
	private int imageResource;
	protected PieceColor color;
	private AvailableMoves availMoves;
	
	public Piece(PieceColor color, Cell cell_location, int imageResource) {
	
				this.color = color;
				this.cell_location = cell_location;
				this.imageResource = imageResource;
			}
			
	//methods
	public Cell getCellLocation() {
		return cell_location;
	}

	
	public PieceState getPieceState() {
		return state;
	}

	public void setPieceState(PieceState newState) {
		state = newState;
		
	}


	public int getImageResource() {
		return imageResource;
	}

	
	public PieceColor getPieceColor() {
		return color;
	}

	
	public boolean isValidMove(Cell moveTo) {
		
		return true;
	}


	
	
	
	// gets a list of available moves 	
	
	public ArrayList<Cell> getAvailableMoves(Cell[][] board) {
		
		ArrayList<Cell> retList = new ArrayList<Cell>();
		int currX = getCellLocation().getX();
		int currY = getCellLocation().getY();
		
		
		
		if (currY == 0 || currY == 12){
			
		   retList.add(board[0][1]); retList.add(board[4][1]); retList.add(board[8][1]);
		   retList.add(board[1][2]); retList.add(board[4][2]); retList.add(board[7][2]);
		   retList.add(board[2][3]); retList.add(board[4][3]); retList.add(board[6][3]);
		   
		   retList.add(board[0][6]); retList.add(board[1][6]); retList.add(board[2][6]);
		   retList.add(board[6][6]); retList.add(board[7][6]); retList.add(board[8][6]);
		   
		   retList.add(board[2][9]); retList.add(board[4][9]); retList.add(board[6][9]);
		   retList.add(board[1][10]); retList.add(board[4][10]); retList.add(board[7][10]);
		   retList.add(board[0][11]); retList.add(board[4][11]); retList.add(board[8][11]);
		}
		
		else{
			if(checkOutsidePieces(board)==false){
				
		if (currX == 0 && currY == 1){retList.add(board[1][2]); retList.add(board[0][6]); retList.add(board[4][1]);}
		if (currX == 4 && currY == 1){retList.add(board[0][1]); retList.add(board[8][1]); retList.add(board[4][2]);}
		if (currX == 8 && currY == 1){retList.add(board[7][2]); retList.add(board[8][6]); retList.add(board[4][1]);}
		
		if (currX == 1 && currY == 2){retList.add(board[0][1]); retList.add(board[1][6]); retList.add(board[4][2]);retList.add(board[2][3]);}
		if (currX == 4 && currY == 2){retList.add(board[1][2]); retList.add(board[4][1]); retList.add(board[4][3]);retList.add(board[7][2]);}
		if (currX == 7 && currY == 2){retList.add(board[4][2]); retList.add(board[7][6]); retList.add(board[6][3]);retList.add(board[8][1]);}
		
		if (currX == 2 && currY == 3){retList.add(board[1][2]); retList.add(board[2][6]); retList.add(board[4][3]);}
		if (currX == 4 && currY == 3){retList.add(board[2][3]); retList.add(board[6][3]); retList.add(board[4][2]);}
		if (currX == 6 && currY == 3){retList.add(board[4][3]); retList.add(board[7][2]); retList.add(board[6][6]);}
		
		if (currX == 0 && currY == 6){retList.add(board[0][1]); retList.add(board[0][11]); retList.add(board[1][6]);}
		if (currX == 1 && currY == 6){retList.add(board[0][6]); retList.add(board[2][6]); retList.add(board[1][2]); retList.add(board[1][10]);}
		if (currX == 2 && currY == 6){retList.add(board[1][6]); retList.add(board[2][3]); retList.add(board[2][9]);}
		
		if (currX == 6 && currY == 6){retList.add(board[6][3]); retList.add(board[7][6]); retList.add(board[6][9]);}
		if (currX == 7 && currY == 6){retList.add(board[6][6]); retList.add(board[8][6]); retList.add(board[7][2]);retList.add(board[7][10]);}
		if (currX == 8 && currY == 6){retList.add(board[7][6]); retList.add(board[8][1]); retList.add(board[8][11]);}
		
		if (currX == 2 && currY == 9){retList.add(board[1][10]); retList.add(board[2][6]); retList.add(board[6][9]);}
		if (currX == 4 && currY == 9){retList.add(board[2][9]); retList.add(board[4][10]); retList.add(board[4][2]);}
		if (currX == 6 && currY == 9){retList.add(board[4][9]); retList.add(board[7][10]); retList.add(board[6][6]);}
		
		if (currX == 1 && currY == 10){retList.add(board[0][11]); retList.add(board[1][6]); retList.add(board[4][10]);retList.add(board[2][9]);}
		if (currX == 4 && currY == 10){retList.add(board[1][10]); retList.add(board[4][9]); retList.add(board[4][11]);retList.add(board[7][10]);}
		if (currX == 7 && currY == 10){retList.add(board[4][10]); retList.add(board[7][6]); retList.add(board[6][9]);retList.add(board[8][11]);}
		
		if (currX == 0 && currY == 11){retList.add(board[1][10]); retList.add(board[0][6]); retList.add(board[4][11]);}
		if (currX == 4 && currY == 11){retList.add(board[0][11]); retList.add(board[8][11]); retList.add(board[4][10]);}
		if (currX == 8 && currY == 11){retList.add(board[7][10]); retList.add(board[8][6]); retList.add(board[4][11]);}
			}
		}
		
		
		return retList;
	}
	
	
	public boolean checkOutsidePieces(Cell[][] board) {

		int k;
		boolean result = false;

		if (getPieceColor() == PieceColor.white)
			k = 0;
		else
			k = 12;

		for (int i = 0; i < 9; i++) {
			if (board[i][k].getPiece() != null) {
				return result = true;
			} else
				return result = false;
		}

		return result;
	}
	
	
	// move takes a valid cell on the board and tries to move the
			// piece to it. if the move is successful, returns true, if not
			// returns false
			public boolean tryMove(Cell moveTo) {

				if (!isValidMove(moveTo))
					return false; //moveStatus.fail;
				// move is valid; apply move
				// empty old location
				this.cell_location.setPiece(null);
				// update new location
				moveTo.setPiece(this);
				// update self location
				this.cell_location = moveTo;

				

				return true;//moveStatus.success;
			}
	
	

}


