package ucsdKWang.fiveStones;

// created by Kimi Wang

import java.awt.Font;
public class yyBoard{
	private yyPiece[][] pieces;
	private int turn = 1;
	private int judge = 1;
	private boolean WWin = false;
	private boolean BWin = false;
	private String Winner = null;
	private boolean GameOver = false;
	private int oldX;
	private int oldY;
	private int blackWinCounter;
	private int whiteWinCounter;
	private yyStack<yyLocation> stack;

	public yyBoard(boolean recap){
		stack = new yyStack<yyLocation>();
		if (recap==false){
			pieces = new yyPiece[22][22];
		}
	}

	private yyPiece pieceAt(int x, int y){
		if (x<1|| x>19 || y<1 || y>19){
			return null;
		}
		else{
			return pieces[y][x];
		}
	}

	private boolean validPut(int x, int y){
		if (pieceAt(x,y)!=null){
			return false;
		}
		else if (x>19 || x<0 || y<0 || y>19){
			return false;
		}
		return true;
	}

	private void putStone(int x, int y){
		if (validPut(x,y)){
			if (turn%2 ==1){
			pieces[y][x] = new yyPiece(false,true,this,x,y);
		}
		else if (turn%2 == 0){
			pieces[y][x] =  new yyPiece(false,false,this,x,y);
		}
		stack.add(new yyLocation(x,y));
		this.oldX = x;
		this.oldY = y;
		turn = turn +1;
		}
	}

	private void drawStone(int x, int y){
		if (pieceAt(x,y)!=null){
			if (pieceAt(x,y).isBlack){
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledRectangle(4,20.5,4,0.9);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle((double) x, (double) y, 0.5);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 30));
			StdDraw.text(4,20,"White's turn");

		}
		else if (pieceAt(x,y).isBlack==false){
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledRectangle(4,20.5,4,0.9);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledCircle((double) x, (double) y, 0.5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 30));
			StdDraw.text(4,20,"Black's turn");
		}
		StdDraw.show(100);
		}
	}

	private void drawIndicator(){
		if (turn%2==0){
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledRectangle(4,20.5,4,0.9);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 30));
			StdDraw.text(4,20,"White's turn");

		}
		else if (turn%2==1){
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledRectangle(4,20.5,4,0.9);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 30));
			StdDraw.text(4,20,"Black's turn");
		}
		StdDraw.show(100);

	}



	private void WinningDrive(int x, int y){
		// black is the winner
		int i, count, maxY, maxX, minY, minX;
		boolean hasWinner;
		if (judge%2==1){

			// check verticaltally
			i = 0;
			count = 0;
			maxY = y -1;
			minY = y;
			hasWinner = false;
			while (pieceAt(x,y+i) != null) {
				if (!pieceAt(x,y+i).isBlack()) {
					break;
				}

				count++;
				maxY++;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "Black";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(x,minY,x,maxY);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else {
					i++;
				}
			}
			i = -1;
			while (!hasWinner && pieceAt(x,y+i) != null) {
				if (!pieceAt(x,y+i).isBlack()) {
					break;
				}
				minY --;
				count++;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "Black";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(x,minY,x,maxY);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else 
					i--;
			}

			//check horizontally 
			i = 0;
			count = 0;
			maxX = x -1;
			minX = x;
			hasWinner = false;
			while (pieceAt(x+i,y) != null) {
				if (!pieceAt(x+i,y).isBlack()) {
					break;
				}

				count++;
				maxX++;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "Black";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(minX,y,maxX,y);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else {
					i++;
				}
			}
			i = -1;
			while (!hasWinner && pieceAt(x+i,y) != null) {
				if (!pieceAt(x+i,y).isBlack()) {
					break;
				}
				minX --;
				count++;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "Black";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(minX,y,maxX,y);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else 
					i--;
			}
       
			// lower right to upper left diagnose for black
			i = 0;
			count = 0;
			maxX = x -1;
			minX = x;
			maxY = y;
			minY = y+1;
			hasWinner = false;
			while (pieceAt(x+i,y-i) != null) {
				if (!pieceAt(x+i,y-i).isBlack()) {
					break;
				}

				count++;
				maxX++;
				minY--;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "Black";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(minX,maxY,maxX,minY);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else {
					i++;
				}
			}
			i = -1;
			while (!hasWinner && pieceAt(x+i,y-i) != null) {
				if (!pieceAt(x+i,y-i).isBlack()) {
					break;
				}
				minX --;
				maxY ++;
				count++;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "Black";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(minX,maxY,maxX,minY);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else 
					i--;
			}
			// // lower left to upper right diagnose for black
			i = 0;
			count = 0;
			maxX = x -1;
			minX = x;
			maxY = y-1;
			minY = y;
			hasWinner = false;
			while (pieceAt(x+i,y+i) != null) {
				if (!pieceAt(x+i,y+i).isBlack()) {
					break;
				}

				count++;
				maxX++;
				maxY++;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "Black";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(minX,minY,maxX,maxY);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else {
					i++;
				}
			}
			i = -1;
			while (!hasWinner && pieceAt(x+i,y+i) != null) {
				if (!pieceAt(x+i,y+i).isBlack()) {
					break;
				}
				minX --;
				minY --;
				count++;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "Black";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(minX,minY,maxX,maxY);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else 
					i--;
			}
		}

		// white wins the game

		else if (judge%2==0){

			// check verticaltally
			i = 0;
			count = 0;
			maxY = y -1;
			minY = y;
			hasWinner = false;
			while (pieceAt(x,y+i) != null) {
				if (pieceAt(x,y+i).isBlack()) {
					break;
				}

				count++;
				maxY++;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "White";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(x,minY,x,maxY);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else {
					i++;
				}
			}
			i = -1;
			while (!hasWinner && pieceAt(x,y+i) != null) {
				if (pieceAt(x,y+i).isBlack()) {
					break;
				}
				minY --;
				count++;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "White";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(x,minY,x,maxY);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else 
					i--;
			}

			//check horizontally 
			i = 0;
			count = 0;
			maxX = x -1;
			minX = x;
			hasWinner = false;
			while (pieceAt(x+i,y) != null) {
				if (pieceAt(x+i,y).isBlack()) {
					break;
				}

				count++;
				maxX++;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "White";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(minX,y,maxX,y);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else {
					i++;
				}
			}
			i = -1;
			while (!hasWinner && pieceAt(x+i,y) != null) {
				if (pieceAt(x+i,y).isBlack()) {
					break;
				}
				minX --;
				count++;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "White";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(minX,y,maxX,y);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else 
					i--;
			}
       
			// lower right to upper left diagnose for black
			i = 0;
			count = 0;
			maxX = x -1;
			minX = x;
			maxY = y;
			minY = y+1;
			hasWinner = false;
			while (pieceAt(x+i,y-i) != null) {
				if (pieceAt(x+i,y-i).isBlack()) {
					break;
				}

				count++;
				maxX++;
				minY--;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "White";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(minX,maxY,maxX,minY);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else {
					i++;
				}
			}
			i = -1;
			while (!hasWinner && pieceAt(x+i,y-i) != null) {
				if (pieceAt(x+i,y-i).isBlack()) {
					break;
				}
				minX --;
				maxY ++;
				count++;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "White";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(minX,maxY,maxX,minY);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else 
					i--;
			}
			// // lower left to upper right diagnose for black
			i = 0;
			count = 0;
			maxX = x -1;
			minX = x;
			maxY = y-1;
			minY = y;
			hasWinner = false;
			while (pieceAt(x+i,y+i) != null) {
				if (pieceAt(x+i,y+i).isBlack()) {
					break;
				}

				count++;
				maxX++;
				maxY++;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "White";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(minX,minY,maxX,maxY);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else {
					i++;
				}
			}
			i = -1;
			while (!hasWinner && pieceAt(x+i,y+i) != null) {
				if (pieceAt(x+i,y+i).isBlack()) {
					break;
				}
				minX --;
				minY --;
				count++;
				if (count == 5) {
					this.BWin = true;
			    	this.Winner = "White";
					GameOver = true;
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(.007);
					StdDraw.line(minX,minY,maxX,maxY);
					printWinner();
					StdDraw.show(100);
					hasWinner = true;
					break;	
				}
				else 
					i--;
			}
		}
/*
		else{
			GameOver = false;
		}
*/
		judge = judge + 1;
	}

	private void printWinner(){
        if (GameOver){
		    if (Winner.equals("White")){
				whiteWinCounter++;
				StdDraw.setPenColor(StdDraw.ORANGE);
				StdDraw.filledRectangle(20.8,15,1.2,1);
				StdDraw.filledRectangle(20.8,3,1.2,1);
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.setFont(new Font("SansSerif", Font.BOLD, 65));
				StdDraw.text(10.4,18,"The game is over!");
				StdDraw.setFont(new Font("SansSerif", Font.BOLD, 55));
				StdDraw.text(10.4,3,"The White won the game!");

			}
			else if (Winner.equals("Black")){
			    blackWinCounter++;
			    StdDraw.setPenColor(StdDraw.ORANGE);
			    StdDraw.filledRectangle(20.8,15,1.2,1);
			    StdDraw.filledRectangle(20.8,3,1.2,1);
			    StdDraw.setPenColor(StdDraw.RED);
				StdDraw.setFont(new Font("SansSerif", Font.BOLD, 65));
				StdDraw.text(10.4,18,"The game is over!");
				StdDraw.setFont(new Font("SansSerif", Font.BOLD, 55));
				StdDraw.text(10.4,3,"The Black won the game!");
		    }
		    StdDraw.setFont(new Font("SansSerif",Font.BOLD,25));
			StdDraw.setPenColor(StdDraw.GREEN);
		    StdDraw.text(5,4.5,"The white wins "+whiteWinCounter+" games");
			StdDraw.text(5,1.5,"The black wins "+blackWinCounter+" games");
	    }
	}

	private void drawboard(){
		StdDraw.setCanvasSize(728,728);
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
		

		for (double i = 1; i<20; i++){
	
			StdDraw.line(1,i,19,i);
			StdDraw.line(i,1,i,19);
		}
	    StdDraw.setPenColor(StdDraw.BLUE);
	    StdDraw.setFont(new Font("SansSerif", Font.BOLD, 22));
		StdDraw.text(20.8,15,"Undo");
		StdDraw.text(20.8,11,"Quit");
		StdDraw.text(20.8,3,"Resign");
		StdDraw.text(20.8,7,"Restart");
		StdDraw.text(20.8,19,"Save");
		StdDraw.setPenColor(StdDraw.MAGENTA);
		StdDraw.text(17.8,20.7,"Created by Kimi Wang");

		}

		private void drawEmpty() {
			drawEmpty(oldX,oldY);
		}

		private void drawEmpty(int x, int y){
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.filledSquare(x,y,0.5);
		StdDraw.setPenColor(StdDraw.BLACK);
		if (!(x==1 || x==19 || y==1 || y==19)){
		StdDraw.line(x-0.5,y,x+0.5,y);
		StdDraw.line(x,y-0.5,x,y+0.5);
	}
	    else if (x==1 && y!=1 && y!=19){
		StdDraw.line(x,y,x+0.5,y);
		StdDraw.line(x,y-0.5,x,y+0.5);
	}
		else if (x==19 && y!=1 && y!=19){
		StdDraw.line(x-0.5,y,x,y);
		StdDraw.line(x,y-0.5,x,y+0.5);
	}
		else if (x!=1 && x!=19 && y==19){
		StdDraw.line(x-0.5,y,x+0.5,y);
		StdDraw.line(x,y-0.5,x,y);
	}
		else if (x!=1 && x!=19 && y==19){
		StdDraw.line(x-0.5,y,x+0.5,y);
		StdDraw.line(x,y,x,y+0.5);
	}
		else if (x==1 && y==1){
		StdDraw.line(x,y,x+0.5,y);
		StdDraw.line(x,y,x,y+0.5);
	}
		else if (x==1 && y==19){
		StdDraw.line(x,y,x+0.5,y);
		StdDraw.line(x,y-0.5,x,y);
	}
		else if (x==19 && y==1){
		StdDraw.line(x-0.5,y,x,y);
		StdDraw.line(x,y,x,y+0.5);
	}
		else if (x==19 && y==19){
		StdDraw.line(x-0.5,y,x,y);
		StdDraw.line(x,y-0.5,x,y);
	}


		if ((x==4 && y == 4) || (x==4 && y==10) || (x==4 && y == 16) || (x==10 && y==10) || (x==10 && y == 4) || (x==10 && y==16)
			|| (x==16 && y == 4) || (x==16 && y==10) || (x==16 && y == 16)){
			StdDraw.filledCircle(x,y,0.2);
		}
		StdDraw.show(200);

	}


		private void letGo(){
			turn = turn-1;
			judge = judge -1;

			drawIndicator();
		}
		private yyPiece remove(){
			int removeX = oldX;
			int removeY = oldY;
			
			pieces[removeY][removeX] = null;
			yyPiece removed = pieceAt(removeX,removeY);
			return removed;
		}
		private yyPiece remove(int removeX, int removeY){
			
			pieces[removeY][removeX] = null;
			yyPiece removed = pieceAt(removeX,removeY);
			return removed;
		}

		private void undo(){
			int x = stack.get().getX();
			int y = stack.get().getY();
			drawEmpty(x,y);
			remove(x,y);
			stack.remove();
			turn = turn -1;
			judge = judge -1;
			drawIndicator();
		}

		private void reStart(){
			stack = new yyStack<yyLocation>();
			turn = 1;
			judge = 1;
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


	public static void main(String[] args){
		yyBoard newboard = new yyBoard(false);
		newboard.drawboard();
		boolean hasResultupdated = false;
		while (true){

		while (newboard.GameOver == false){
			hasResultupdated = false;
			if (StdDraw.mousePressed()){
				double ix = StdDraw.mouseX();
				double iy = StdDraw.mouseY();
				int x = Integer.parseInt(new java.text.DecimalFormat("0").format(ix));
				int y = Integer.parseInt(new java.text.DecimalFormat("0").format(iy));
				StdDraw.show(200);

			if ((x==20 || x==22 || x==21)&& y==15){
				newboard.undo();

			}

			else if ((x==20 ||x==22 || x==21)&& y==19){
				StdDraw.save("plot.jpg");

			}

			else if ((x==20 || x==22 || x==21)&& y==7){
				newboard.reStart();

			}

			else if ((x==20 || x==22 || x==21)&& y==11){
				// when the player click quit, end the program 
				System.exit(0);
			}
			else if ((x==20 || x==22 || x==21)&& y==3){
				newboard.letGo();
			}

			else if (newboard.validPut(x,y)){
				newboard.putStone(x,y);
				System.out.println(newboard.oldX+" , "+newboard.oldY);
				newboard.drawStone(x,y);
				newboard.drawIndicator();
				newboard.WinningDrive(x,y);
                
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

			if ((x==20 || x==22 || x==21)&& y==7){
				newboard.GameOver = false;

			}

			else if ((x==20 || x==22 || x==21)&& y==19){
				StdDraw.save("GameManual.jpg");

			}

			else if ((x==20 || x==22 || x==21) && y==11){
				break;
			}
			else {
				break;
			}
		}
	} // end the first while loop

	} // end the main method 

} // end the class