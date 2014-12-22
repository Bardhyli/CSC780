package com.example.testcicmic;

import java.util.ArrayList;
import com.example.testcicmic.PieceColor;

public class cicmicCore {
	
	// MEMBER VARIABLES

	private Player white, black;
	public PieceColor turn;
	private gameState currentGameState;
	///////////
	// MEMBER VARIABLES
	// this gets used for discarded pieces
	private Cell deadCell;
	private Cell board[][];
	//private userInterfaceBoard UIboard;


	
			// Constructor
		
		
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////
		// 
	
	
	// TODO: maybe move this into a game class
		public void move(int fromX, int fromY, int toX, int toY, Cell[][] board)
				throws Exception {
			//turn = (PieceColor.white) ? white : black;
			Piece piece = board[fromX][fromY].getPiece();
			/*boolean success = currentPlayer.move(piece, board[toX][toY]);
			if (success)
				turn = turn == PieceColor.white ? PieceColor.black
						: PieceColor.white;
			return true; //success;
			
			*/
			////
			////
			////
			//if (piece == null || piece.getPieceColor() != this.color ||  board[fromX][fromY] == null ||  board[fromX][fromY] == deadCell)
				//return false;
			// TODO: cache this so that we're not constantly re-populating
			ArrayList<Cell> availableMoves = piece.getAvailableMoves(board);
			if (availableMoves.contains(board[toX][toY])) {
				boolean status = piece.tryMove(board[toX][toY]);//moveStatus status
				
				/*if (status != moveStatus.promote) {
					if (status == moveStatus.success) {
						return true;
					} else
						return false;
				} else {*/
					// TODO: decide: do we want to explode on incorrect
					// promotion, or do we want
					// to eat the exception and loop until a valid selection is
					// made.
					// For now, it'll explode under the assumption that the
					// input is correct and
					// the code isn't complex enough to really have surprising
					// results.
					
					/*try {
						pieceType type = pieceType.bishop;
						// TODO: promotion logic
						// promotion code: UI, etc.
						piece.setPieceType(type);
						return true;
					} catch (incompatiblePieceTypeConversionException ex) {
						throw new Exception(
								"MAN, WHAT THE FUCK!? Promotion code is seriously jacked up.");
					}*/
				//}
			}
			//return false;
			
		}
		
		void populateBoard(Cell[][] board) {
			
			// create player piece arrays
			Piece blackPieces[] = new Piece[9];
			Piece whitePieces[] = new Piece[9];
			
			// init the cells
			for (int x = 0; x < 9; x++) {
				for (int y = 0; y < 13; y++) {
					board[x][y] = new Cell(x, y);
				}
			}
			
			

			// White is on the bottom, black is on top
			// pawns
			for (int i = 0; i < 9; i++) {
				//white
				Piece whitePiece = new Piece(PieceColor.white, board[i][0], R.drawable.kernel);
				whitePieces[i] = whitePiece;
				board[i][1].setPiece(whitePiece);
				
				//black
				Piece blackPiece = new Piece(PieceColor.black, board[i][12], R.drawable.kernel);
				blackPieces[i] = blackPiece;
				board[i][12].setPiece(blackPiece);
			}
			
			
			//white = new Player(objectColour.white, whitePieces);
			//black = new Player(objectColour.black, blackPieces);
			
			
		}

		public cicmicCore() {
		super();
		// if new game
					//deadCell = new Cell(-1, -1);
					board = new Cell[9][13];
					populateBoard(board);
					turn = PieceColor.white;
					currentGameState = gameState.allClear;
	}
		
		

		///////////////////
		
		public Cell[][] getBoard() {
			return board;
		}
		
		private enum objectColour {
			black, white
		};
		
		private enum pieceState {
			inBoard, outBoard
		};
		
		private enum gameState {
			allClear, whiteWins, blackWins, whiteBam, blackBam
		};
		
		private enum moveStatus {
			success, fail
		};
		
		
		public PieceColor getTurn() {
			return turn;
		}	
		
		
		



}
