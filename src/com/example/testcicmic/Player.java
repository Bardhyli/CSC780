package com.example.testcicmic;

import com.example.testcicmic.PieceColor;

public class Player {
	
	
	// properties
	private PieceColor colour;
	private Piece[] pieces;
	
	// Constructor
	public Player(PieceColor colour, Piece[] pieces) {
		this.colour = colour;
		this.pieces = pieces;
	}

	
	
	//methods
	public PieceColor getColour() {
		return colour;
	}

	public Piece[] getPieces() {
		return pieces;
	}
	

}
