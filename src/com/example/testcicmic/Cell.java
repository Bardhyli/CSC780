package com.example.testcicmic;

import com.example.testcicmic.Piece;

public class Cell {
	
	
	//members
	private int x;
	private int y;
	private Piece piece;

	//constructor
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.piece = null;
	}

	//methods
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}		

	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece piece) {
		//if (this.piece != null)
			//this.piece.setPieceState(pieceState.dead);
		this.piece = piece;
	}
	
}


