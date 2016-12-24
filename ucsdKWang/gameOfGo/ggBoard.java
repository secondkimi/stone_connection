package ucsdKWang.gameOfGo;
// create board of game of go
// author kimi wang 

// now the problem lies in the fact that you cannot defitely target the isblack 
// property of pieces  
import java.awt.Font;
public class ggBoard{
	private static final int CANVAS_WIDTH = 866;
	private static final int CANVAS_HEIGHT = 866;
	private static final double SAVE_Y = 14;
	private static final double UNDO_Y = 12;
	private static final double QUIT_Y = 10;
	private static final double RESIGN_Y = 6;
	private static final double RESTART_Y = 8;
	private static final double COUNT_Y = 2;
	private static final double DEAD_Y = 4;
	private ggPiece[][] pieces;
	private int turn = 1;
	private int judge = 1;
	private int whiteWinCounter = 0;
	private int blackWinCounter = 0;
	private boolean dead_mark_flag = false;
	private boolean WWin = false;
	private boolean BWin = false;
	private String Winner = null;
	private boolean GameOver = false;
	private int oldX;
	private int oldY;
	private ggStack<ggLocation> stack;
	private ggStack<ggLocation> removeStoneStack;
	public ggBoard(boolean recap){
		stack = new ggStack<ggLocation>();
		removeStoneStack = new ggStack<ggLocation>();
		if (recap==false){
			pieces = new ggPiece[22][22];
		}
	}
	public ggBoard(){
		stack = new ggStack<ggLocation>();
		removeStoneStack = new ggStack<ggLocation>();
		pieces = new ggPiece[22][22];
	}

	private ggPiece pieceAt(int x, int y){
		if (x<1|| x>19 || y<1 || y>19){
			return null;
		}
		else{
			return pieces[y][x];
		}
	}

	private boolean whiteRealEye(int x, int y) {
		if ( !allSurrounded(x,y) ) return false;
		
		if ( x>1&&x<19&&y>1&&y<19) {
			if(pieceAt(x,y-1).isBlack()==false && pieceAt(x,y+1).isBlack()==false &&pieceAt(x-1,y).isBlack()==false &&pieceAt(x+1,y).isBlack()==false){
				return true;
			} 
		}
		else if (x==1&&y==1){
			// lower left corner
			if(pieceAt(x,y+1).isBlack()==false &&pieceAt(x+1,y).isBlack()==false){
				return true;
			} 
		}
		else if (x==19&&y==1) {
			// lower right corner
			if(pieceAt(x,y+1).isBlack()==false &&pieceAt(x-1,y).isBlack()==false){
				return true;
			} 
		}
		else if (x==19&&y==19){
			// upper right corner
			if(pieceAt(x,y-1).isBlack()==false &&pieceAt(x-1,y).isBlack()==false){
				return true;
			} 
		}
		else if (x==1&&y==19){
			// upper left corner
			if(pieceAt(x,y-1).isBlack()==false &&pieceAt(x+1,y).isBlack()==false){
				return true;
			} 
		}
		else if ( x==1) {
			// left side
			if(pieceAt(x,y+1).isBlack()==false &&pieceAt(x+1,y).isBlack()==false&&pieceAt(x,y-1).isBlack()==false){
				return true;
			} 
		}
		else if (x==19){
			// right side
			if(pieceAt(x,y+1).isBlack()==false &&pieceAt(x-1,y).isBlack()==false&&pieceAt(x,y-1).isBlack()==false){
				return true;
			}
		}
		else if (y==1){
				// lower side
			if(pieceAt(x,y+1).isBlack()==false &&pieceAt(x-1,y).isBlack()==false&&pieceAt(x+1,y).isBlack()==false){
				return true;
			}
		}
		else if (y==19){
			// upper side
			if(pieceAt(x-1,y).isBlack()==false&&pieceAt(x,y-1).isBlack()==false&&pieceAt(x+1,y).isBlack()==false){
				return true;
			}
		}

		return false;
	}
	private boolean blackRealEye(int x, int y) {
		if ( !allSurrounded(x,y) ) return false;
		
		if ( x>1&&x<19&&y>1&&y<19) {
				if(pieceAt(x,y-1).isBlack() && pieceAt(x,y+1).isBlack() &&pieceAt(x-1,y).isBlack() &&pieceAt(x+1,y).isBlack()){
					return true;
				} 
			}
			else if (x==1&&y==1){
				// lower left corner
				if(pieceAt(x,y+1).isBlack() &&pieceAt(x+1,y).isBlack()){
					return true;
				} 
			}
			else if (x==19&&y==1) {
				// lower right corner
				if(pieceAt(x,y+1).isBlack() &&pieceAt(x-1,y).isBlack()){
					return true;
				} 
			}
			else if (x==19&&y==19){
				// upper right corner
				if(pieceAt(x,y-1).isBlack() &&pieceAt(x-1,y).isBlack()){
					return true;
				} 
			}
			else if (x==1&&y==19){
				// upper left corner
				if(pieceAt(x,y-1).isBlack() &&pieceAt(x+1,y).isBlack()){
					return true;
				} 
			}
			else if ( x==1) {
				// left side
				if(pieceAt(x,y+1).isBlack() &&pieceAt(x+1,y).isBlack()&&pieceAt(x,y-1).isBlack()){
					return true;
				} 
			}
			else if (x==19){
				// right side
				if(pieceAt(x,y+1).isBlack() &&pieceAt(x-1,y).isBlack()&&pieceAt(x,y-1).isBlack()){
					return true;
				}
			}
			else if (y==1){
				// lower side
				if(pieceAt(x,y+1).isBlack() &&pieceAt(x-1,y).isBlack()&&pieceAt(x+1,y).isBlack()){
					return true;
				}
			}
			else if (y==19){
				// upper side
				if(pieceAt(x-1,y).isBlack()&&pieceAt(x,y-1).isBlack()&&pieceAt(x+1,y).isBlack()){
					return true;
				}
			}
 			
		return false;
	}


	private boolean allSurrounded(int x, int y){
		if ( x>1 && x < 19 && y>1 && y<19) {
			if (pieceAt(x,y-1)!=null && pieceAt(x,y+1)!=null &&pieceAt(x-1,y)!=null && pieceAt(x+1,y)!=null){
				return true;
			}
		}
		else if (x==1&&y==1) {
			// lower left corner
			if (pieceAt(1,2)!=null && pieceAt(2,1)!=null){
				return true;
			}
		}
		else if (x==19&&y==1) {
			// lower right corner
			if (pieceAt(19,2)!=null && pieceAt(18,1)!=null){
				return true;
			}
		}
		else if (x==1&&y==19) {
			// upper left corner
			if (pieceAt(1,18)!=null && pieceAt(2,19)!=null){
				return true;
			}
		}
		else if (x==19&&y==19) {
			// upper right corner
			if (pieceAt(19,18)!=null && pieceAt(18,19)!=null){
				return true;
			}
		}
		else if (x==19) {
			//right side
			if (pieceAt(x,y-1)!=null && pieceAt(x,y+1)!=null &&pieceAt(x-1,y)!=null){
				return true;
			}
		}
		else if (x==1) {
			// left side
			if (pieceAt(x,y-1)!=null && pieceAt(x,y+1)!=null && pieceAt(x+1,y)!=null){
				return true;
			}
		}
		else if(y==1){
			// lower side
			if (pieceAt(x,y+1)!=null && pieceAt(x-1,y)!=null && pieceAt(x+1,y)!=null){
				return true;
			}
		}
		else if (y==19){
			// upper side
			if (pieceAt(x,y-1)!=null && pieceAt(x-1,y)!=null && pieceAt(x+1,y)!=null){
				return true;
			}
		}
		return false;
	}
	
	private ggQueue beCaptured(int x, int y, boolean b) {
		// return number of stones being captured from the point (x,y)
		if (pieceAt(x,y)==null )
			return null;
		ggQueue queue = new ggQueue();
		ggQueue newQueue = new ggQueue();
		ggPiece piece;
		int currX = x;
		int currY = y;
		//int totalCaptured = 0;
		if (pieceAt(x,y).isBlack() && b){
			// check the black stones
			if ( !allSurrounded(x,y) ) return null;

			queue.add(new ggPiece(false,true,this,currX,currY));
			while (queue.size() > 0){
				piece = queue.remove();
				currX = piece.getX();
				currY = piece.getY();
				if( !allSurrounded(currX, currY) ) return null;
				newQueue.add(piece);
				// check the four sides 
				if (pieceAt(currX+1,currY)!=null && pieceAt(currX+1,currY).isBlack() && !newQueue.hasItem(pieceAt(currX+1,currY))) {
					queue.add(pieceAt(currX+1,currY));					
				}
				if (pieceAt(currX-1,currY)!=null &&pieceAt(currX-1,currY).isBlack() && !newQueue.hasItem(pieceAt(currX-1,currY))) {
					queue.add(pieceAt(currX-1,currY));
				}
				if (pieceAt(currX,currY+1)!=null &&pieceAt(currX,currY+1).isBlack() && !newQueue.hasItem(pieceAt(currX,currY+1))) {
					queue.add(pieceAt(currX,currY+1));		
				}
				if (pieceAt(currX,currY-1)!=null &&pieceAt(currX,currY-1).isBlack() && !newQueue.hasItem(pieceAt(currX,currY-1))) {
					queue.add(pieceAt(currX,currY-1));					
				}
			}

		}
		else if (!pieceAt(x,y).isBlack()&& !b) {
			
			if ( !allSurrounded(x,y) ) return null;

			queue.add(new ggPiece(false,false,this,currX,currY));
			while (queue.size() > 0){
				piece = queue.remove();
				currX = piece.getX();
				currY = piece.getY();
				if( !allSurrounded(currX, currY) ) return null;
				newQueue.add(piece);
				// check the four sides 
				if (pieceAt(currX+1,currY)!=null && !pieceAt(currX+1,currY).isBlack() && !newQueue.hasItem(pieceAt(currX+1,currY))) {
					queue.add(pieceAt(currX+1,currY));					
				}
				if (pieceAt(currX-1,currY)!=null && !pieceAt(currX-1,currY).isBlack() && !newQueue.hasItem(pieceAt(currX-1,currY))) {
					queue.add(pieceAt(currX-1,currY));
				}
				if (pieceAt(currX,currY+1)!=null && !pieceAt(currX,currY+1).isBlack() && !newQueue.hasItem(pieceAt(currX,currY+1))) {
					queue.add(pieceAt(currX,currY+1));		
				}
				if (pieceAt(currX,currY-1)!=null && !pieceAt(currX,currY-1).isBlack() && !newQueue.hasItem(pieceAt(currX,currY-1))) {
					queue.add(pieceAt(currX,currY-1));					
				}
			}
			
		}
		return newQueue;
		
	}
	private int removeStoneQueue(ggQueue newQueue){
		if (newQueue==null) return 0;
		int totalCaptured = newQueue.size();
		int currX, currY;
		ggPiece piece;
		while( newQueue!=null && newQueue.size()>0 ) {
				piece = newQueue.remove();
				currX = piece.getX();
				currY = piece.getY();
				stack.add(new ggLocation(currX,currY,piece.isBlack(),true));
				remove(currX,currY);
				drawEmpty(currX,currY);
		}
		return totalCaptured;
	}
/* 
	private int beCaptured(int x, int y, boolean b) {
		// return number of stones being captured from the point (x,y)
		if (pieceAt(x,y)==null )
			return 0;
		ggQueue queue = new ggQueue();
		ggQueue newQueue = new ggQueue();
		ggPiece piece;
		int currX = x;
		int currY = y;
		int totalCaptured = 0;
		if (pieceAt(x,y).isBlack() && b){
			// check the black stones
			if ( !allSurrounded(x,y) ) return 0;

			queue.add(new ggPiece(false,true,this,currX,currY));
			while (queue.size() > 0){
				System.out.println(queue.size());
				piece = queue.remove();
				currX = piece.getX();
				currY = piece.getY();
				if( !allSurrounded(currX, currY) ) return 0;
				newQueue.add(piece);
				// check the four sides 
				if (pieceAt(currX+1,currY)!=null && pieceAt(currX+1,currY).isBlack() && !newQueue.hasItem(pieceAt(currX+1,currY))) {
					queue.add(pieceAt(currX+1,currY));					
				}
				if (pieceAt(currX-1,currY)!=null &&pieceAt(currX-1,currY).isBlack() && !newQueue.hasItem(pieceAt(currX-1,currY))) {
					queue.add(pieceAt(currX-1,currY));
				}
				if (pieceAt(currX,currY+1)!=null &&pieceAt(currX,currY+1).isBlack() && !newQueue.hasItem(pieceAt(currX,currY+1))) {
					queue.add(pieceAt(currX,currY+1));		
				}
				if (pieceAt(currX,currY-1)!=null &&pieceAt(currX,currY-1).isBlack() && !newQueue.hasItem(pieceAt(currX,currY-1))) {
					queue.add(pieceAt(currX,currY-1));					
				}
			}

		}
		else if (!b) {
			
			if ( !allSurrounded(x,y) ) return 0;

			queue.add(new ggPiece(false,false,this,currX,currY));
			while (queue.size() > 0){
				piece = queue.remove();
				currX = piece.getX();
				currY = piece.getY();
				if( !allSurrounded(currX, currY) ) return 0;
				newQueue.add(piece);
				// check the four sides 
				if (pieceAt(currX+1,currY)!=null && !pieceAt(currX+1,currY).isBlack() && !newQueue.hasItem(pieceAt(currX+1,currY))) {
					queue.add(pieceAt(currX+1,currY));					
				}
				if (pieceAt(currX-1,currY)!=null && !pieceAt(currX-1,currY).isBlack() && !newQueue.hasItem(pieceAt(currX-1,currY))) {
					queue.add(pieceAt(currX-1,currY));
				}
				if (pieceAt(currX,currY+1)!=null && !pieceAt(currX,currY+1).isBlack() && !newQueue.hasItem(pieceAt(currX,currY+1))) {
					queue.add(pieceAt(currX,currY+1));		
				}
				if (pieceAt(currX,currY-1)!=null && !pieceAt(currX,currY-1).isBlack() && !newQueue.hasItem(pieceAt(currX,currY-1))) {
					queue.add(pieceAt(currX,currY-1));					
				}
			}
			
		}
		totalCaptured = newQueue.size();
		while( newQueue!=null && newQueue.size()>0 ) {
				piece = newQueue.remove();
				currX = piece.getX();
				currY = piece.getY();
				stack.add(new ggLocation(currX,currY,piece.isBlack(),true));
				remove(currX,currY);
				drawEmpty(currX,currY);
		}
		return totalCaptured;
	}*/
	
	/* Here are the rules for valid put.
	 * First, you cannot put a stone out of the range of the board
	 * Second, if the move does not capture any stones, you cannot put a stone into the eye of the others
	 */
	private boolean validPut(int x, int y){
		if (pieceAt(x,y)!=null){
			return false;
		}
		else if (x>19 || x<0 || y<0 || y>19){
			return false;
		}
		/*
		else if ( whiteRealEye(x,y) && turn%2==1 && stack.get().checkCannotPut(x,y,true) ) {
			// try to put stone at (x,y) and check whether the last stone from the stack is in real eye
			return false;
		}*/
		else if ( whiteRealEye(x,y) && turn%2==1 && stack.get().checkCannotPut(x,y,true)) {

			ggLocation loc = stack.remove();
			int newX = stack.get().getX();
			int newY = stack.get().getY();
			
			pieces[y][x] = new ggPiece(false,true,this,x,y);
			
			if (blackRealEye(newX,newY) && !stack.get().getStone()){
				
				pieces[y][x] = null;
				stack.add(loc);
				return false;
			}
			pieces[y][x] = null;
			stack.add(loc);
		}

		else if ( blackRealEye(x,y) && turn%2==0 &&  stack.get().checkCannotPut(x,y,false)) {
			ggLocation loc = stack.remove();
			int newX = stack.get().getX();
			int newY = stack.get().getY();
			
			pieces[y][x] = new ggPiece(false,false,this,x,y);
			
			if (whiteRealEye(newX,newY)&&stack.get().getStone()){
				
				pieces[y][x] = null;
				stack.add(loc);
				return false;
			}
			pieces[y][x] = null;
			stack.add(loc);
		}
		return true;
	}

	private void putStone(int x, int y){
		if (validPut(x,y)){
			boolean color = (turn%2==1) ? true:false;
			stack.add(new ggLocation(x,y,color));
			if (turn%2 ==1){
				// black's turn
				pieces[y][x] = new ggPiece(false,true,this,x,y);
				drawStone(x,y);
		    	int captured = capFrom(x,y,false);
		    	if (captured ==0 && checkSuicide(x,y,true)) {
					remove(x,y);
					stack.remove();
					drawEmpty(x,y);
					turn-=1;
				}
		    	
			}
			else {
				// white's turn
				pieces[y][x] =  new ggPiece(false,false,this,x,y);
				drawStone(x,y);
				int captured = capFrom(x,y,true);
				if (captured == 0 && checkSuicide(x,y,false)) {
					remove(x,y);
					stack.remove();
					drawEmpty(x,y);
					turn-=1;
				}
			}
			
			this.oldX = x;
			this.oldY = y;
			turn = turn +1;
		}
	}

	private boolean checkSuicide(int x, int y, boolean b){
		if (beCaptured(x,y,b)!=null) {
			return true;
		}
		return false;
		// if the user put a stone in a location that caused suicide, remove all the relevant stones from the board
	}

	private int capFrom(int x, int y, boolean b){
		
		int totalCaptured =  removeStoneQueue(beCaptured(x-1,y,b));
		totalCaptured +=  removeStoneQueue(beCaptured(x+1,y,b));
		totalCaptured +=  removeStoneQueue(beCaptured(x,y-1,b));
		totalCaptured +=  removeStoneQueue(beCaptured(x,y+1,b));
		return totalCaptured;
	}

	private void drawStone(int x, int y){
		// given that there is a stone at (x,y)
		if (pieceAt(x,y)!=null){
			if (pieceAt(x,y).isBlack){
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledCircle((double) x, (double) y, 0.5);

			}
			else {
				// draw white stone
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledCircle((double) x, (double) y, 0.5);
			}
			StdDraw.show(6);
		}
	}

	private void drawStone(int x, int y, boolean black){
		
		if (black){
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledCircle((double) x, (double) y, 0.5);

		}
		else {
			// draw white stone
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledCircle((double) x, (double) y, 0.5);
		}
		StdDraw.show(6);
	}

	private void drawIndicator(){
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.filledRectangle(4,21,4,0.9);
		if (turn%2==0){
			
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 30));
			StdDraw.text(4,21,"White's turn");

		}
		else if (turn%2==1){
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 30));
			StdDraw.text(4,21,"Black's turn");
		}
		StdDraw.show(8);

	}


	private void printWinner(){
			
		if (Winner.equals("White")){
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 80));
			StdDraw.text(10,17,"The game is over!");
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 55));
			StdDraw.text(10,3,"The White won the game!");
		}
		else if (Winner.equals("Black")){
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 80));
			StdDraw.text(10,17,"The game is over!");
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 57));
			StdDraw.text(10,3,"The Black won the game!");
		}
	}

	private void drawboard(){
		StdDraw.setCanvasSize(CANVAS_WIDTH,CANVAS_HEIGHT);
		StdDraw.setXscale(0,22);
		StdDraw.setYscale(0,22);

		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.filledSquare(11,11,11);
		StdDraw.setPenRadius(.004);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(4,4,0.2);
		StdDraw.filledCircle(16,4,0.2);
		StdDraw.filledCircle(4,16,0.2);
		StdDraw.filledCircle(16,10,0.2);
		StdDraw.filledCircle(10,16,0.2);
		StdDraw.filledCircle(16,16,0.2);
		StdDraw.filledCircle(4,10,0.2);
		StdDraw.filledCircle(10,4,0.2);
		StdDraw.filledCircle(10,10,0.2);
		StdDraw.setFont(new Font("SansSerif", Font.BOLD, 36));
		StdDraw.text(15,20,"Game of Go");
		

		for (double i = 1; i<20; i++){
	
			StdDraw.line(1,i,19,i);
			StdDraw.line(i,1,i,19);
	}
	    StdDraw.setPenColor(StdDraw.BLUE);
	    StdDraw.setFont(new Font("SansSerif", Font.BOLD, 22));
		StdDraw.text(20.8,UNDO_Y,"Undo");
		StdDraw.text(20.8,QUIT_Y,"Quit");
		StdDraw.text(20.8,RESIGN_Y,"Resign");
		StdDraw.text(20.8,DEAD_Y,"Dead");
		StdDraw.text(20.8,RESTART_Y,"Restart");
		StdDraw.text(20.8,SAVE_Y,"Save");
		StdDraw.text(20.8,COUNT_Y,"Count");
		}

		private void drawCurrentStone(){

		}

	private void drawEmpty(){
		drawEmpty(oldX,oldY);

	}
			

	private void drawEmpty(int x, int y){
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.filledSquare(x,y,0.5);
		StdDraw.setPenColor(StdDraw.BLACK);
		if (x>1 && x<19 && y>1 && y<19){
			StdDraw.line(x-0.5,y,x+0.5,y);
			StdDraw.line(x,y-0.5,x,y+0.5);
			if ((x==4 && y == 4) || (x==4 && y==10) || (x==4 && y == 16) || (x==10 && y==10) || (x==10 && y == 4) || (x==10 && y==16)
				|| (x==16 && y == 4) || (x==16 && y==10) || (x==16 && y == 16)){
				StdDraw.filledCircle(x,y,0.2);
			}
		}
		else if (x==1&&y==1) {
			// lower left corner
			StdDraw.line(x,y,x+0.5,y);
			StdDraw.line(x,y,x,y+0.5);
		}
		else if (x==19&&y==1) {
			// lower right corner
			StdDraw.line(x-0.5,y,x,y);
			StdDraw.line(x,y,x,y+0.5);
			
		}
		else if (x==1&&y==19) {
			// upper left corner
			StdDraw.line(x,y,x+0.5,y);
			StdDraw.line(x,y-0.5,x,y);
		}
		else if (x==19&&y==19) {
			// upper right corner
			StdDraw.line(x-0.5,y,x,y);
			StdDraw.line(x,y-0.5,x,y);
		}
		else if (x==19) {
			//right side
			StdDraw.line(x-0.5,y,x,y);
			StdDraw.line(x,y-0.5,x,y+0.5);
		}
		else if (x==1) {
			// left side
			StdDraw.line(x,y,x+0.5,y);
			StdDraw.line(x,y-0.5,x,y+0.5);
		}
		else if(y==1){
			// lower side
			StdDraw.line(x-0.5,y,x+0.5,y);
			StdDraw.line(x,y,x,y+0.5);
		}
		else if (y==19){
			// upper side
			StdDraw.line(x-0.5,y,x+0.5,y);
			StdDraw.line(x,y-0.5,x,y);
		}
		
		StdDraw.show(6);

	}




		private void letGo(){
			turn = (turn==0) ? 0 : turn-1;
			judge = (turn==0) ? 0 : judge-1;

			drawIndicator();
		}
		private ggPiece remove(){
			int removeX = oldX;
			int removeY = oldY;
			
			pieces[removeY][removeX] = null;
			ggPiece removed = pieceAt(removeX,removeY);
			return removed;
		}

		private ggPiece remove(int x, int y){
			ggPiece removed = pieceAt(x,y);
			pieces[y][x] =null;
			return removed;
		}

		private void undoRemoveStone() {

		}

		private void undo(){
			if ( stack.size() == 0) return;
			ggLocation loc = stack.remove();
			int currX = oldX;
			int currY = oldY;
			boolean black;
			while (loc.isEaten()){
				currX = loc.getX();
				currY = loc.getY();
				black = loc.getStone();
				drawStone(currX,currY,black);
				pieces[currY][currX] = new ggPiece(false,black,this,currX,currY);
				loc = stack.remove();
			}
			currX = loc.getX();
			currY = loc.getY();
			drawEmpty(currX,currY);
			remove(currX,currY);
			turn = turn -1;
			judge = judge -1;
			drawIndicator();
		}

		private void reStart(){
			turn = 1;
			judge = 1;
			stack = new ggStack<ggLocation>();
			WWin = false;
			BWin = false;
			Winner = null;
			GameOver = false;
			for (int i=0; i<22;i++){
				for (int j=0;j<22;j++){
					pieces[j][i] = null;
				}
			} 
			drawboard();
			StdDraw.show(200);
		}

		private void count(){
			int blackCount = 0;
			int whiteCount = 0;
			// explore every single points of the board
			for (int i=1; i<20; i++){
				for(int j=1; j<20; j++){
					if (pieceAt(i,j)==null) {
						String s = explorePoint(i,j,1);
						if ( s != null ) {
							
							blackCount = (s.equals("Black")) ? (blackCount+1) : blackCount;
							whiteCount = (s.equals("White")) ? (whiteCount+1) : whiteCount;
						}
					}
					else if ( pieceAt(i,j).isBlack() ){
						blackCount++;
						
					}
					else {
						whiteCount++;
						
					}
				}
			}
			if (blackCount+whiteCount==361){
				if ( blackCount - whiteCount >= 8) {
					BWin = true;
					WWin = false;
					Winner = "Black";
					blackWinCounter ++;
				}
				else {
					WWin = true;
					BWin = false;
					Winner = "White";
					whiteWinCounter ++;
				}
				System.out.println("Black count: "+blackCount);
				System.out.println("White count: "+whiteCount);
				printWinner();
				System.out.println(Winner+" wins the game!");
			}
			else {
				System.out.println("Black count: "+blackCount);
				System.out.println("White count: "+whiteCount);
			}

		}
		
		private String explorePoint(int x, int y, int distance) {
			// recursively explore all the points in some distance
			if (distance < 1) return null;
			int loopCounter = (distance / 2) + 1;
			int a = 0; int b = 0;
			boolean hasWhite = false; boolean hasBlack = false;
			for ( int i = 0; i <loopCounter; i++) {
				a = i;
				b = distance - a;
				if ( a==b || a==0 ) {
					if ( pieceAt(x-a,y-b) != null ) {
						hasBlack = (pieceAt(x-a,y-b).isBlack()) ? true : false;
						hasWhite = (pieceAt(x-a,y-b).isBlack()) ? false : true;
					}
					if ( pieceAt(x-a,y+b) != null ) {
						hasBlack = (pieceAt(x-a,y+b).isBlack()) ? true : false;
						hasWhite = (pieceAt(x-a,y+b).isBlack()) ? false : true;
					}
					if ( pieceAt(x+b,y-a) != null ) {
						hasBlack = (pieceAt(x+b,y-a).isBlack()) ? true : false;
						hasWhite = (pieceAt(x+b,y-a).isBlack()) ? false : true;
					}
					if ( pieceAt(x-b,y+a) != null ) {
						hasBlack = (pieceAt(x-b,y+a).isBlack()) ? true : false;
						hasWhite = (pieceAt(x-b,y+a).isBlack()) ? false : true;
					}
				}
				else {
					if ( pieceAt(x-a,y-b) != null ) {
						hasBlack = (pieceAt(x-a,y-b).isBlack()) ? true : false;
						hasWhite = (pieceAt(x-a,y-b).isBlack()) ? false : true;
					}
					if ( pieceAt(x-a,y+b) != null ) {
						hasBlack = (pieceAt(x-a,y+b).isBlack()) ? true : false;
						hasWhite = (pieceAt(x-a,y+b).isBlack()) ? false : true;
					}
					if ( pieceAt(x+a,y-b) != null ) {
						hasBlack = (pieceAt(x+a,y-b).isBlack()) ? true : false;
						hasWhite = (pieceAt(x+a,y-b).isBlack()) ? false : true;
					}
					if ( pieceAt(x+a,y+b) != null ) {
						hasBlack = (pieceAt(x+a,y+b).isBlack()) ? true : false;
						hasWhite = (pieceAt(x+a,y+b).isBlack()) ? false : true;
					}

					if ( pieceAt(x-b,y-a) != null ) {
						hasBlack = (pieceAt(x-b,y-a).isBlack()) ? true : false;
						hasWhite = (pieceAt(x-b,y-a).isBlack()) ? false : true;
					}
					if ( pieceAt(x-b,y+a) != null ) {
						hasBlack = (pieceAt(x-b,y+a).isBlack()) ? true : false;
						hasWhite = (pieceAt(x-b,y+a).isBlack()) ? false : true;
					}
					if ( pieceAt(x+b,y-a) != null ) {
						hasBlack = (pieceAt(x+b,y-a).isBlack()) ? true : false;
						hasWhite = (pieceAt(x+b,y-a).isBlack()) ? false : true;
					}
					if ( pieceAt(x+b,y+a) != null ) {
						hasBlack = (pieceAt(x+b,y+a).isBlack()) ? true : false;
						hasWhite = (pieceAt(x+b,y+a).isBlack()) ? false : true;
					}
				}
			} // end for loop
			if ( hasWhite && !hasBlack ) {
				return "White";
			}
			else if ( !hasWhite && hasBlack ) {
				return "Black";
			}
			else if ( hasWhite && hasBlack ) {
				return null;
			}
			//return null;
			return explorePoint(x,y,distance+1);
		}

		private void setDeadMark(boolean b) {
			dead_mark_flag = b;
		}

		private void deadMarkIndicator() {
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledRectangle(20.8,DEAD_Y,0.9,0.6);

			dead_mark_flag = !dead_mark_flag;
			if ( dead_mark_flag ) {
				// red text
				StdDraw.setPenColor(StdDraw.RED);
			}
			else {
				// blue text 
				StdDraw.setPenColor(StdDraw.BLUE);
			}
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 22));
			StdDraw.text(20.8,DEAD_Y,"Dead");
			StdDraw.show(6);
		}

		private void addToRemovedStack(ggLocation loc) {
			removeStoneStack.add(loc);
		}

		private void recoverRemovedStack() {
			if ( removeStoneStack.size()>0) {
				ggLocation loc = removeStoneStack.remove();
				pieces[loc.getY()][loc.getX()] = new ggPiece(false,loc.getStone(),this,loc.getX(),loc.getY());
				drawStone(loc.getX(),loc.getY());
			}
		}

		private void deadMarkIndicator(boolean b) {
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledRectangle(20.8,DEAD_Y,0.9,0.6);

			if ( b ) {
				// red text
				StdDraw.setPenColor(StdDraw.RED);
			}
			else {
				// blue text 
				StdDraw.setPenColor(StdDraw.BLUE);
			}
			StdDraw.text(20.8,DEAD_Y,"Dead");
			StdDraw.show(6);
		}


	public static void main(String[] args){
		ggBoard newboard = new ggBoard(false);
		newboard.drawboard();
		boolean hasResultupdated = false;
		boolean remove_flag = false;
		while (true) {
			while (newboard.GameOver == false){
				if (StdDraw.mousePressed()){
					double ix = StdDraw.mouseX();
					double iy = StdDraw.mouseY();
					int x = Integer.parseInt(new java.text.DecimalFormat("0").format(ix));
					int y = Integer.parseInt(new java.text.DecimalFormat("0").format(iy));
					StdDraw.show(200);
					if (!remove_flag) {
						if ((x==20 || x==22 || x==21)&& y==UNDO_Y){
							newboard.undo();

						}

						else if ((x==20 ||x==22 || x==21)&& y==SAVE_Y){
							StdDraw.save("plot.jpg");

						}

						else if ((x==20 || x==22 || x==21)&& y==RESTART_Y){
							newboard.reStart();

						}

						else if ((x==20 || x==22 || x==21)&& y==QUIT_Y){
							// when the player click quit, end the program 
							System.exit(0);
						}
						else if ((x==20 || x==22 || x==21)&& y==RESIGN_Y){
							newboard.letGo();
						}
						else if ((x==20 || x==22 || x==21)&& y==COUNT_Y){
							newboard.count();
						}
						else if ((x==20 || x==22 || x==21)&& y==DEAD_Y){
							remove_flag = !remove_flag;
							newboard.deadMarkIndicator();
						}

						else //if (newboard.validPut(x,y)){
						{
							newboard.putStone(x,y);
							System.out.println(newboard.oldX+" , "+newboard.oldY);
							newboard.drawIndicator();
			                
						}
					} 
					else{
						// remove flag is on
						if ((x==20 || x==22 || x==21)&& y==DEAD_Y){
							remove_flag = !remove_flag;
							newboard.deadMarkIndicator();
						}
						else if ((x==20 || x==22 || x==21)&& y==UNDO_Y){
							newboard.recoverRemovedStack();
						}

						else if (x>0&&x<20&&y<20&&y>0){
							
							ggPiece p = newboard.remove(x,y);
							if ( p!=null ) {
								newboard.addToRemovedStack(new ggLocation(x,y,p.isBlack(),false));
								newboard.drawEmpty(x,y);
							}			
							
						}
					}

				}
			} // end the inner while loop

			// do the following printing stuff once
			if ( !hasResultupdated ) {
				hasResultupdated = true;
				System.out.println("the game is over!");
				System.out.println("the winner is "+ newboard.Winner);
			}
			if (StdDraw.mousePressed()){
					
				double ix = StdDraw.mouseX();
				double iy = StdDraw.mouseY();
				int x = Integer.parseInt(new java.text.DecimalFormat("0").format(ix));
				int y = Integer.parseInt(new java.text.DecimalFormat("0").format(iy));

				if ((x==20 || x==22 || x==21)&& y==RESTART_Y){
					newboard.GameOver = false;
					remove_flag = false;
					newboard.setDeadMark(false);
					newboard.deadMarkIndicator();
				}

				else if ((x==20 || x==22 || x==21)&& y==SAVE_Y){
					StdDraw.save("GameManual.jpg");

				}

				else if ((x==20 || x==22 || x==21) && y==QUIT_Y){
					System.exit(0);
				}
				else {
					break;
				}
				
			}
		} // end the outer while loop
		newboard.printWinner();
		System.out.println("the game is over!");
		System.out.println("the winner is "+ newboard.Winner);
	} // end the main method 

} // end the class