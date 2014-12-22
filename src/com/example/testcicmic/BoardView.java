package com.example.testcicmic;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;


public class BoardView extends SurfaceView implements Runnable{
	
	Thread t = null;
	SurfaceHolder holder;
	boolean isItOK = false;
	Bitmap whitekernel;
	Bitmap blackkernel;
	Bitmap figure;
	Bitmap background;
	//float pieceWidth, pieceHeight;
	// Yzero is the top point for the board, as in board is placed at [0, Yzero]
	private int fromX, fromY, toX, toY, removePieceX, removePieceY, width, height;
	private int whitePlayerScore, blackPlayerScore;
	private Cell board[][];
	public PieceColor turn;
	private boolean pieceSelected;
	private Canvas canvas;
	///////
	Rect src;
	Rect dst;
	SurfaceViewExaple cnxt;
	private GameState gamestate;
	
	//////////
	
	public BoardView(Context context) {
		super(context);
		holder = getHolder();
		
		whitekernel = BitmapFactory.decodeResource(getResources(), R.drawable.whitepiece);
		blackkernel = BitmapFactory.decodeResource(getResources(), R.drawable.blackpiece);
		background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		
		board = new Cell[9][13];
		populateBoard(board);
		turn = PieceColor.white;
		cnxt = (SurfaceViewExaple)context;
		gamestate = GameState.commonState;
		whitePlayerScore = blackPlayerScore = 0;
		
	}

	public Cell[][] getBoard() {
		return board;
	}
	public PieceColor getTurn() {
		return turn;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isItOK ==true){
			//perform canvas drawing
			if(!holder.getSurface().isValid()){
				continue;
			}
			
			Paint p = new Paint();
            p.setAntiAlias(false);
			
			
			//NOTE: I HARDCODED THE DRAWING POSITIONS BELOW IN THE BEGINNING OF PROJECT
            //THOSE COORDINATES SHOULD BE CALCULATED BY GETTING THE CELL POSITIONS WITH INT NUMBERS
            //AND WIDTH AND HEIGHT OF SCREEN
            //BECAUSE OF LIMITTED TIME FIXING LAST MINUTE WOULD INCREASE THE PROBABILITY OF MISTAKES
            //I'M LEAVING LIKE THIS FOR NOW BUT I KNOW HOW DO A BETTER JOB
			canvas =holder.lockCanvas();
			
			//canvas.drawARGB(255, 150, 150, 10);
			src = new Rect(0, 0, background.getWidth(), background.getHeight());
			dst = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
			canvas.drawBitmap(background, src, dst, null);			
			
			//float linewidth = p.getStrokeWidth();
			//p.setStrokeWidth(3);
			p.setStrokeWidth(6);
			
			//VERTICAL LINES
            //left
            canvas.drawLine(60, 196.269f, 60, 1504.729f, p);
            canvas.drawLine(180, 327.115f, 180, 1373.882f, p);
            canvas.drawLine(300, 457.961f, 300, 1243.037f, p);
            //middle
            canvas.drawLine(540, 196.269f, 540, 457.961f, p);
            canvas.drawLine(540, 1243.037f, 540, 1504.729f, p);
            //right
            canvas.drawLine(780, 457.961f, 780, 1243.037f, p);
            canvas.drawLine(900, 327.115f, 900, 1373.882f, p);
            canvas.drawLine(1020, 196.269f, 1020, 1504.729f, p);
            
            //HORIZONTAL LINES
            //up
            canvas.drawLine(60, 196.269f, 1020, 196.269f, p);
            canvas.drawLine(180, 327.115f, 900, 327.115f, p);
            canvas.drawLine(300, 457.961f, 780, 457.961f, p);
            //middle
            canvas.drawLine(60, 850.499f, 300, 850.499f, p);
            canvas.drawLine(780, 850.499f, 1020, 850.499f, p);
            //bottom
            canvas.drawLine(300, 1243.037f, 780, 1243.037f, p);
            canvas.drawLine(180, 1373.882f, 900, 1373.882f, p);
            canvas.drawLine(60, 1504.729f, 1020, 1504.729f, p);
            
          //OTHER LINES
            canvas.drawLine(60, 196.269f, 300, 457.961f, p);
            canvas.drawLine(780, 1243.037f, 1020, 1504.729f, p);
            
            canvas.drawLine(60, 1504.729f, 300, 1243.037f, p);
            canvas.drawLine(780, 457.961f, 1020, 196.269f, p);
            
            ////////////////////////
            //p.setColor(Color.argb(102, 0, 255, 0));original
            //p.setColor(Color.rgb(107, 83, 71));
            //p.setColor(Color.rgb(182, 147, 128));
            //p.setColor(Color.rgb(122, 5, 5));
            p.setColor(Color.rgb(199, 146, 62));
            
            //canvas.drawCircle(100, 100, 25, p);
            //canvas.drawCircle((width/9 - width/18),2*(height/13 - height/26),25,p);
            canvas.drawCircle(60, 196.269f, 25, p);
            canvas.drawCircle(540, 196.269f, 25, p);
            canvas.drawCircle(1020, 196.269f, 25, p);
            
            canvas.drawCircle(180, 327.115f, 25, p);
            canvas.drawCircle(540, 327.115f, 25, p);
            canvas.drawCircle(900, 327.115f, 25, p);
            
            canvas.drawCircle(300, 457.961f, 25, p);
            canvas.drawCircle(540, 457.961f, 25, p);
            canvas.drawCircle(780, 457.961f, 25, p);
            ////////////////////
            canvas.drawCircle(60, 850.499f, 25, p);
            canvas.drawCircle(180, 850.499f, 25, p);
            canvas.drawCircle(300, 850.499f, 25, p);
            
            canvas.drawCircle(780, 850.499f, 25, p);
            canvas.drawCircle(900, 850.499f, 25, p);
            canvas.drawCircle(1020, 850.499f, 25, p);
            
            //////////////////////////////
            
            canvas.drawCircle(60, 1504.729f, 25, p);
            canvas.drawCircle(540, 1504.729f, 25, p);
            canvas.drawCircle(1020, 1504.729f, 25, p);
            
            canvas.drawCircle(180, 1373.883f, 25, p);
            canvas.drawCircle(540, 1373.883f, 25, p);
            canvas.drawCircle(900, 1373.883f, 25, p);
            
            canvas.drawCircle(300, 1243.037f, 25, p);
            canvas.drawCircle(540, 1243.037f, 25, p);
            canvas.drawCircle(780, 1243.037f, 25, p);
            ///////////////////////////////////
            
            this.drawBoard(getBoard() , canvas);
			if(pieceSelected)
				drawAvailableMoves(getBoard(), canvas, fromX, fromY);


			holder.unlockCanvasAndPost(canvas);
		}
				
		
	}
	
	public void pause(){
		isItOK = false;
		while(true){
			try{
				t.join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			break;
		}
		t=null;
	}
	
	public void resume(){
		isItOK = true;
		t =new Thread(this);
		t.start();
	}
	
	
	private void drawBoard(Cell[][] board, Canvas canvas)
	{	
		

		width =  canvas.getWidth();
		height = canvas.getHeight();
		
		for(int x = 0; x < 9; x++)
		{
			for(int y = 0; y < 13; y++)
			{
				Piece piece = board[x][y].getPiece();
				if(piece != null)
				{
					figure = BitmapFactory.decodeResource(getResources(), piece.getImageResource());
					canvas.drawBitmap(figure, x*width/9 + (width/9-figure.getWidth())/2, y*height/13 + (height/13-figure.getHeight())/2,null);
				}
			}
		}
		
	}
	
	private void drawAvailableMoves(Cell[][] board, Canvas canvas, int x, int y)
	{
		//x=fromX;y=fromY
		Piece piece = board[x][y].getPiece();
		if(piece != null && piece.getPieceColor() == getTurn())
		{
			/*Drawable selection = res.getDrawable(R.drawable.selected);
			selection.setBounds(getBoardX(x), getBoardY(y), getBoardX(x) + width/8, getBoardY(y) + width /8);
			selection.draw(canvas);*/
			
			ArrayList<Cell> availMoves = piece.getAvailableMoves(board);
			for(int i = 0; i < availMoves.size(); i ++)
			{
				if(piece.isValidMove(availMoves.get(i)))
				{
					Cell availMove = availMoves.get(i);
					//Drawable circle = res.getDrawable(R.drawable.selectioncircle);
					//circle.setBounds(getBoardX(availMove.getX()), getBoardY(availMove.getY()), getBoardX(availMove.getX()) + width/8, getBoardY(availMove.getY()) + width /8);
					//circle.draw(canvas);
				}
			}
		}
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
		
		

		// White is on the top, black is on bottom
		// pawns
		for (int i = 0; i < 9; i++) {
			//white
			Piece whitePiece = new Piece(PieceColor.white, board[i][0], R.drawable.whitepiece);
			whitePieces[i] = whitePiece;
			board[i][0].setPiece(whitePiece);
			
			//black
			Piece blackPiece = new Piece(PieceColor.black, board[i][12], R.drawable.blackpiece);
			blackPieces[i] = blackPiece;
			board[i][12].setPiece(blackPiece);
		}
		
		
		
		
	}
	
	
	// TODO: maybe move this into a game class
	public void move(int fromX, int fromY, int toX, int toY, Cell[][] board)
			throws Exception {
		Piece piece = board[fromX][fromY].getPiece();
		
		// TODO: cache this so that we're not constantly re-populating
		ArrayList<Cell> availableMoves = piece.getAvailableMoves(board);
		
		if (availableMoves.contains(board[toX][toY])) {
			piece.tryMove(board[toX][toY]);//moveStatus status
			
		if(checkForBam(toX, toY, piece.color, board)==true){
			gamestate=GameState.bamState;
			Toast toast =Toast.makeText(cnxt, "BAAAM!!!",Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
			
			
		}
		//return false;
		
	}
	
	public boolean checkForBam(int toX, int toY, PieceColor color, Cell[][] board){
		
		boolean result = false;
		
		if (toX == 0 && toY == 1){
			if((board[1][2].getPiece()!=null && board[2][3].getPiece()!=null) && (board[1][2].getPiece().color==color && board[2][3].getPiece().color==color) ||
			   (board[0][6].getPiece()!=null && board[0][11].getPiece()!=null) && (board[0][6].getPiece().color==color && board[0][11].getPiece().color==color) ||
			   (board[4][1].getPiece()!=null && board[8][1].getPiece()!=null) && (board[4][1].getPiece().color==color && board[8][1].getPiece().color==color))
				result = true;
		}
		
		if (toX == 4 && toY == 1){
			if((board[0][1].getPiece()!=null && board[8][1].getPiece()!=null) && (board[0][1].getPiece().color==color && board[8][1].getPiece().color==color) ||
			   (board[4][2].getPiece()!=null && board[4][3].getPiece()!=null) && (board[4][2].getPiece().color==color && board[4][3].getPiece().color==color))
				result = true;
		}
		
		if (toX == 8 && toY == 1){
			if((board[0][1].getPiece()!=null && board[4][1].getPiece()!=null) && (board[0][1].getPiece().color==color && board[4][1].getPiece().color==color) ||
			   (board[8][6].getPiece()!=null && board[8][11].getPiece()!=null) && (board[8][6].getPiece().color==color && board[8][11].getPiece().color==color) ||
			   (board[7][2].getPiece()!=null && board[6][3].getPiece()!=null) && (board[7][2].getPiece().color==color && board[6][3].getPiece().color==color))
				result = true;
		}
		
		if (toX == 1 && toY == 2){
			if((board[0][1].getPiece()!=null && board[2][3].getPiece()!=null) && (board[0][1].getPiece().color==color && board[2][3].getPiece().color==color) ||
			   (board[4][2].getPiece()!=null && board[7][2].getPiece()!=null) && (board[4][2].getPiece().color==color && board[7][2].getPiece().color==color) ||
			   (board[1][6].getPiece()!=null && board[1][10].getPiece()!=null) && (board[1][6].getPiece().color==color && board[1][10].getPiece().color==color))
				result = true;
		}
		
		if (toX == 4 && toY == 2){
			if((board[1][2].getPiece()!=null && board[7][2].getPiece()!=null) && (board[1][2].getPiece().color==color && board[7][2].getPiece().color==color) ||
			   (board[4][1].getPiece()!=null && board[4][3].getPiece()!=null) && (board[4][1].getPiece().color==color && board[4][3].getPiece().color==color))
				result = true;
		}
		
		if (toX == 7 && toY == 2){
			if((board[1][2].getPiece()!=null && board[4][2].getPiece()!=null) && (board[1][2].getPiece().color==color && board[4][2].getPiece().color==color) ||
			   (board[7][6].getPiece()!=null && board[7][10].getPiece()!=null) && (board[7][6].getPiece().color==color && board[7][10].getPiece().color==color) ||
			   (board[8][1].getPiece()!=null && board[6][3].getPiece()!=null) && (board[8][1].getPiece().color==color && board[6][3].getPiece().color==color))
				result = true;
		}
		
		if (toX == 2 && toY == 3){
			if((board[0][1].getPiece()!=null && board[1][2].getPiece()!=null) && (board[0][1].getPiece().color==color && board[1][2].getPiece().color==color) ||
			   (board[4][3].getPiece()!=null && board[6][3].getPiece()!=null) && (board[4][3].getPiece().color==color && board[6][3].getPiece().color==color) ||
			   (board[2][6].getPiece()!=null && board[2][9].getPiece()!=null) && (board[2][6].getPiece().color==color && board[2][9].getPiece().color==color))
				result = true;
		}
		
		if (toX == 4 && toY == 3){
			if((board[2][3].getPiece()!=null && board[6][3].getPiece()!=null) && (board[2][3].getPiece().color==color && board[6][3].getPiece().color==color) ||
			   (board[4][1].getPiece()!=null && board[4][2].getPiece()!=null) && (board[4][1].getPiece().color==color && board[4][2].getPiece().color==color))
				result = true;
		}
		
		if (toX == 6 && toY == 3){
			if((board[2][3].getPiece()!=null && board[4][3].getPiece()!=null) && (board[2][3].getPiece().color==color && board[4][3].getPiece().color==color) ||
			   (board[6][6].getPiece()!=null && board[6][9].getPiece()!=null) && (board[6][6].getPiece().color==color && board[6][9].getPiece().color==color) ||
			   (board[7][2].getPiece()!=null && board[8][1].getPiece()!=null) && (board[7][2].getPiece().color==color && board[8][1].getPiece().color==color))
				result = true;
		}
		
		if (toX == 0 && toY == 6){
			if((board[0][1].getPiece()!=null && board[0][11].getPiece()!=null) && (board[0][1].getPiece().color==color && board[0][11].getPiece().color==color) ||
			   (board[1][6].getPiece()!=null && board[2][6].getPiece()!=null) && (board[1][6].getPiece().color==color && board[2][6].getPiece().color==color))
				result = true;
		}
		
		if (toX == 1 && toY == 6){
			if((board[1][2].getPiece()!=null && board[1][10].getPiece()!=null) && (board[1][2].getPiece().color==color && board[1][10].getPiece().color==color) ||
			   (board[0][6].getPiece()!=null && board[2][6].getPiece()!=null) && (board[0][6].getPiece().color==color && board[2][6].getPiece().color==color))
				result = true;
		}
		
		if (toX == 2 && toY == 6){
			if((board[2][3].getPiece()!=null && board[2][9].getPiece()!=null) && (board[2][3].getPiece().color==color && board[2][9].getPiece().color==color) ||
			   (board[0][6].getPiece()!=null && board[1][6].getPiece()!=null) && (board[0][6].getPiece().color==color && board[1][6].getPiece().color==color))
				result = true;
		}
		
		if (toX == 6 && toY == 6){
			if((board[6][3].getPiece()!=null && board[6][9].getPiece()!=null) && (board[6][3].getPiece().color==color && board[6][9].getPiece().color==color) ||
			   (board[7][6].getPiece()!=null && board[8][6].getPiece()!=null) && (board[7][6].getPiece().color==color && board[8][6].getPiece().color==color))
				result = true;
		}
		
		if (toX == 7 && toY == 6){
			if((board[7][2].getPiece()!=null && board[7][10].getPiece()!=null) && (board[7][2].getPiece().color==color && board[7][10].getPiece().color==color) ||
			   (board[6][6].getPiece()!=null && board[8][6].getPiece()!=null) && (board[6][6].getPiece().color==color && board[8][6].getPiece().color==color))
				result = true;
		}
		
		if (toX == 8 && toY == 6){
			if((board[8][1].getPiece()!=null && board[8][11].getPiece()!=null) && (board[8][1].getPiece().color==color && board[8][11].getPiece().color==color) ||
			   (board[6][6].getPiece()!=null && board[7][6].getPiece()!=null) && (board[6][6].getPiece().color==color && board[7][6].getPiece().color==color))
				result = true;
		}
		
		if (toX == 2 && toY == 9){
			if((board[2][3].getPiece()!=null && board[2][6].getPiece()!=null) && (board[2][3].getPiece().color==color && board[2][6].getPiece().color==color) ||
			   (board[1][10].getPiece()!=null && board[0][11].getPiece()!=null) && (board[1][10].getPiece().color==color && board[0][11].getPiece().color==color) ||
			   (board[4][9].getPiece()!=null && board[6][9].getPiece()!=null) && (board[4][9].getPiece().color==color && board[6][9].getPiece().color==color))
				result = true;
		}
		
		if (toX == 4 && toY == 9){
			if((board[2][9].getPiece()!=null && board[6][9].getPiece()!=null) && (board[2][9].getPiece().color==color && board[6][9].getPiece().color==color) ||
			   (board[4][10].getPiece()!=null && board[4][11].getPiece()!=null) && (board[4][10].getPiece().color==color && board[4][11].getPiece().color==color))
				result = true;
		}
		
		if (toX == 6 && toY == 9){
			if((board[2][9].getPiece()!=null && board[4][9].getPiece()!=null) && (board[2][9].getPiece().color==color && board[4][9].getPiece().color==color) ||
			   (board[6][3].getPiece()!=null && board[6][6].getPiece()!=null) && (board[6][3].getPiece().color==color && board[6][6].getPiece().color==color) ||
			   (board[7][10].getPiece()!=null && board[8][11].getPiece()!=null) && (board[7][10].getPiece().color==color && board[8][11].getPiece().color==color))
				result = true;
		}
		
		if (toX == 1 && toY == 10){
			if((board[1][2].getPiece()!=null && board[1][6].getPiece()!=null) && (board[1][2].getPiece().color==color && board[1][6].getPiece().color==color) ||
			   (board[0][11].getPiece()!=null && board[2][9].getPiece()!=null) && (board[0][11].getPiece().color==color && board[2][9].getPiece().color==color) ||
			   (board[4][10].getPiece()!=null && board[7][10].getPiece()!=null) && (board[4][10].getPiece().color==color && board[7][10].getPiece().color==color))
				result = true;
		}
		
		if (toX == 4 && toY == 10){
			if((board[1][10].getPiece()!=null && board[7][10].getPiece()!=null) && (board[2][9].getPiece().color==color && board[6][9].getPiece().color==color) ||
			   (board[4][9].getPiece()!=null && board[4][11].getPiece()!=null) && (board[4][10].getPiece().color==color && board[4][11].getPiece().color==color))
				result = true;
		}
		
		if (toX == 7 && toY == 10){
			if((board[1][10].getPiece()!=null && board[4][10].getPiece()!=null) && (board[1][10].getPiece().color==color && board[4][10].getPiece().color==color) ||
			   (board[7][2].getPiece()!=null && board[7][6].getPiece()!=null) && (board[7][2].getPiece().color==color && board[7][6].getPiece().color==color) ||
			   (board[6][9].getPiece()!=null && board[8][11].getPiece()!=null) && (board[6][9].getPiece().color==color && board[8][11].getPiece().color==color))
				result = true;
		}
		
		if (toX == 0 && toY == 11){
			if((board[0][1].getPiece()!=null && board[0][6].getPiece()!=null) && (board[0][1].getPiece().color==color && board[0][6].getPiece().color==color) ||
			   (board[4][11].getPiece()!=null && board[8][11].getPiece()!=null) && (board[4][11].getPiece().color==color && board[8][11].getPiece().color==color) ||
			   (board[1][10].getPiece()!=null && board[2][9].getPiece()!=null) && (board[1][10].getPiece().color==color && board[2][9].getPiece().color==color))
				result = true;
		}
		
		if (toX == 4 && toY == 11){
			if((board[0][11].getPiece()!=null && board[8][11].getPiece()!=null) && (board[0][11].getPiece().color==color && board[8][11].getPiece().color==color) ||
			   (board[4][9].getPiece()!=null && board[4][10].getPiece()!=null) && (board[4][9].getPiece().color==color && board[4][10].getPiece().color==color))
				result = true;
		}
		
		if (toX == 8 && toY == 11){
			if((board[0][11].getPiece()!=null && board[4][11].getPiece()!=null) && (board[0][11].getPiece().color==color && board[4][11].getPiece().color==color) ||
			   (board[8][1].getPiece()!=null && board[8][6].getPiece()!=null) && (board[8][1].getPiece().color==color && board[8][6].getPiece().color==color) ||
			   (board[6][9].getPiece()!=null && board[7][10].getPiece()!=null) && (board[6][9].getPiece().color==color && board[7][10].getPiece().color==color))
				result = true;
		}
		
		return result;
	}
	
	public void removePiece(int removePieceX, int removePieceY,  Cell[][] board) {
		
		//turn == (PieceColor.white) ? white : black;
		board[removePieceX][removePieceY].getPiece().getCellLocation().setPiece(null);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent me) {
		
		
			//kernelX= me.getX();
			//kernelY=me.getY();
			
			//return false;
			
			//////////////////////////////

			if (me.getAction() != MotionEvent.ACTION_DOWN)
				return super.onTouchEvent(me);
			else{
			// X and Y have to be integers 0-7
			// so what we need to do for x is x / (full width / 8)
			// y is similar but y - top space / (width of board / 8)
				if(gamestate==GameState.commonState){
			if(!pieceSelected)
			{	
				
				Log.d("onTouch", "first touch");
				fromX = (int)(me.getX() / (width / 9.0));
				fromY = (int)(me.getY() / (height / 13.0));	
				
				if( !(fromX < 0 || fromX > 8 || fromY < 0 || fromY > 12) && board[fromX][fromY].getPiece()!=null){
				
			    pieceSelected = true;			    
			    
				}
				return true;
				
			}
			else
			{
				Log.d("onTouch", "second touch");
				toX = (int)(me.getX() / (width / 9.0));
				toY = (int)(me.getY() / (height / 13.0));//toY = (int)((me.getY() - Yzero) / (width / 8.0));
				// moving to the same place shouldn't be an issue, i don't think
				// TODO: verify this
				//Piece piece = board[fromX][fromY].getPiece();
				//ArrayList<Cell> availableMoves = piece.getAvailableMoves(board);
				
				
				//if(!( toX < 0 || toX > 8 || toY < 0 || toY > 12 || (toX == fromX && toY == fromY))||!availableMoves.contains(board[toX][toY]))
				if(( toX >= 0 || toX < 9 || toY >= 0 || toY < 13 || (toX != fromX && toY != fromY)) 
					&& board[fromX][fromY].getPiece().getAvailableMoves(board).contains(board[toX][toY]) && board[toX][toY].getPiece()==null)
				{
					
					if(board[fromX][fromY].getPiece() != null && board[fromX][fromY].getPiece().getPieceColor() == getTurn())
					{
						try
						{
							
								move(fromX, fromY, toX,toY, getBoard());
								
								pieceSelected = false;
								if(turn == PieceColor.white)
									turn = PieceColor.black;
								else
									turn = PieceColor.white;
								//return true;
							
						}catch( Exception ex)
						{
							pieceSelected = false;
							//return true;
						}
						
						
					}
					
					
					
					
				}
				pieceSelected = false;
				return true;
			}
				}else{
					
					removePieceX = (int)(me.getX() / (width / 9.0));
					removePieceY = (int)(me.getY() / (height / 13.0));
					//Piece piece = board[removePieceX][removePieceY].getPiece();
					
					if( removePieceX >= 0 || removePieceX < 9 || removePieceY > 0 || removePieceY < 12)
					{
						
						if(board[removePieceX][removePieceY].getPiece()!=null && 
						   board[removePieceX][removePieceY].getPiece().getPieceColor() == getTurn() &&
						   !checkForBam(removePieceX, removePieceY, board[removePieceX][removePieceY].getPiece().getPieceColor(), getBoard()))
						{
							try{
								
								if(board[removePieceX][removePieceY].getPiece().getPieceColor()==PieceColor.white)
									blackPlayerScore++;
								else
									whitePlayerScore++;
								
								removePiece(removePieceX, removePieceY, getBoard());
								gamestate=GameState.commonState;
																
								
								if(whitePlayerScore==9){
									gamestate=GameState.whiteWins;
									
									 AlertDialog.Builder builder = new AlertDialog.Builder(cnxt).setTitle("White Wins!");
								                //.setMessage("Start a new game?");
								        AlertDialog dlg = builder.create();
								        dlg.setButton(DialogInterface.BUTTON_POSITIVE, "Play Again", new DialogInterface.OnClickListener() {

								            @Override
								            public void onClick(DialogInterface dialog, int which) {
								            	
								            	 Intent intent = new Intent(cnxt, SurfaceViewExaple.class);
								     	        /*
								     	         * Restart BoardActivity
								     	         */
								            	 cnxt.startActivity(intent);
								            	 cnxt.finish();
								            }
								        });

								        dlg.setButton(DialogInterface.BUTTON_NEGATIVE, "Exit",
								                new DialogInterface.OnClickListener() {

								                    @Override
								                    public void onClick(DialogInterface dialog, int which) {
								                        //textView.setText("Cancel button pressed");
								                    	cnxt.finish();
								                    }
								                });

								        dlg.show();
								}
								else if(blackPlayerScore==9){
									gamestate=GameState.blackWins;
									
									AlertDialog.Builder builder = new AlertDialog.Builder(cnxt).setTitle("Black Wins!");
					                //.setMessage("Start a new game?");
					        AlertDialog dlg = builder.create();
					        dlg.setButton(DialogInterface.BUTTON_POSITIVE, "Play Again", new DialogInterface.OnClickListener() {

					            @Override
					            public void onClick(DialogInterface dialog, int which) {
					            	
					            	 Intent intent = new Intent(cnxt, SurfaceViewExaple.class);
					     	        /*
					     	         * Restart BoardActivity
					     	         */
					            	 cnxt.startActivity(intent);
					            	 cnxt.finish();
					            }
					        });

					        dlg.setButton(DialogInterface.BUTTON_NEGATIVE, "Exit",
					                new DialogInterface.OnClickListener() {

					                    @Override
					                    public void onClick(DialogInterface dialog, int which) {
					                        //textView.setText("Cancel button pressed");
					                    	cnxt.finish();
					                    }
					                });

					        dlg.show();
					        
								}
									
								
									
								
								
							}catch( Exception ex)
							{
								
							}
						}
					}
					
					return true;
				}
			
			}
			
			//return super.onTouchEvent(me);
		
		
		
		////
	}
	
//END OF OurView CLASS	
}