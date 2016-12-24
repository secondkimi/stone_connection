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

	public yyBoard(boolean recap){
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
		if (judge%2==1){
		if (pieceAt(x,y-1)!=null && pieceAt(x,y)!= null && pieceAt(x,y-2)!=null && pieceAt(x,y-3)!= null && pieceAt(x,y-4)!=null){ 
			if (pieceAt(x,y).isBlack==true && pieceAt(x,y-1).isBlack==true &&
			pieceAt(x,y-2).isBlack==true && pieceAt(x,y-3).isBlack==true
			&& pieceAt(x,y-4).isBlack==true){
			this.BWin = true;
		    this.Winner = "Black";
			GameOver = true;
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x,y-4);
			printWinner();
			StdDraw.show(100);
		}
	}
		else if (pieceAt(x,y+1)!=null && pieceAt(x,y+2)!= null && pieceAt(x,y)!=null && pieceAt(x,y+3)!= null && pieceAt(x,y+4)!=null){
		if (pieceAt(x,y+1).isBlack==true && pieceAt(x,y).isBlack==true &&
			pieceAt(x,y+2).isBlack==true && pieceAt(x,y+3).isBlack==true
			&& pieceAt(x,y+4).isBlack==true){
			this.BWin = true;
			GameOver = true;
			this.Winner = "Black";
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x,y+4);
			printWinner();
			StdDraw.show(100);
		}
		}

		else if(pieceAt(x-1,y)!=null && pieceAt(x,y)!= null && pieceAt(x-2,y)!=null && pieceAt(x-3,y)!= null && pieceAt(x-4,y)!=null){
		 if (pieceAt(x-1,y).isBlack==true && pieceAt(x,y).isBlack==true &&
			pieceAt(x-3,y).isBlack==true && pieceAt(x-2,y).isBlack==true
			&& pieceAt(x-4,y).isBlack==true){
			this.BWin = true;
			GameOver = true;
			this.Winner = "Black";
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x-4,y);
			printWinner();
			StdDraw.show(100);
		}
		}

		else if (pieceAt(x+1,y)!=null && pieceAt(x,y)!= null && pieceAt(x+2,y)!=null && pieceAt(x+3,y)!= null && pieceAt(x+4,y)!=null){ 
			if (pieceAt(x+1,y).isBlack==true && pieceAt(x,y).isBlack==true
			&& pieceAt(x+3,y).isBlack==true && pieceAt(x+2,y).isBlack==true
			&& pieceAt(x+4,y).isBlack==true){
			this.BWin = true;
			this.Winner = "Black";
			GameOver = true;
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x+4,y,x,y);
			printWinner();
			StdDraw.show(100);

		}
}        
		// diagnose for black
		else if (pieceAt(x+1,y+1)!=null && pieceAt(x+2,y+2)!= null && pieceAt(x,y)!=null && pieceAt(x+3,y+3)!= null && pieceAt(x+4,y+4)!=null){
		if (pieceAt(x+1,y+1).isBlack==true && pieceAt(x,y).isBlack==true &&
			pieceAt(x+2,y+2).isBlack==true && pieceAt(x+3,y+3).isBlack==true
			&& pieceAt(x+4,y+4).isBlack==true){
			this.BWin = true;
			GameOver = true;
			this.Winner = "Black";
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x+4,y+4);
			printWinner();
			StdDraw.show(100);
		}
		}
		else if (pieceAt(x-1,y-1)!=null && pieceAt(x-2,y-2)!= null && pieceAt(x,y)!=null && pieceAt(x-3,y-3)!= null && pieceAt(x-4,y-4)!=null){
		if (pieceAt(x-1,y-1).isBlack==true && pieceAt(x,y).isBlack==true &&
			pieceAt(x-2,y-2).isBlack==true && pieceAt(x-3,y-3).isBlack==true
			&& pieceAt(x-4,y-4).isBlack==true){
			this.BWin = true;
			GameOver = true;
			this.Winner = "Black";
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x-4,y-4);
			printWinner();
			StdDraw.show(100);
		}
		}
		else if (pieceAt(x-1,y+1)!=null && pieceAt(x-2,y+2)!= null && pieceAt(x,y)!=null && pieceAt(x-3,y+3)!= null && pieceAt(x-4,y+4)!=null){
		if (pieceAt(x-1,y+1).isBlack==true && pieceAt(x,y).isBlack==true &&
			pieceAt(x-2,y+2).isBlack==true && pieceAt(x-3,y+3).isBlack==true
			&& pieceAt(x-4,y+4).isBlack==true){
			this.BWin = true;
			GameOver = true;
			this.Winner = "Black";
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x-4,y+4);
			printWinner();
			StdDraw.show(100);
		}
		}
		else if (pieceAt(x+1,y-1)!=null && pieceAt(x+2,y-2)!= null && pieceAt(x,y)!=null && pieceAt(x+3,y-3)!= null && pieceAt(x+4,y-4)!=null){
		if (pieceAt(x+1,y-1).isBlack==true && pieceAt(x,y).isBlack==true &&
			pieceAt(x+2,y-2).isBlack==true && pieceAt(x+3,y-3).isBlack==true
			&& pieceAt(x+4,y-4).isBlack==true){
			this.BWin = true;
			GameOver = true;
			this.Winner = "Black";
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x+4,y-4);
			printWinner();
			StdDraw.show(100);
		}
		}
	}

		// white wins the game
			else if (judge%2==0){
			if (pieceAt(x,y+1)!=null && pieceAt(x,y)!= null && pieceAt(x,y+2)!=null && pieceAt(x,y+3)!= null && pieceAt(x,y+4)!=null){
			if(pieceAt(x,y).isBlack!=true && pieceAt(x,y+1).isBlack!=true
			&& pieceAt(x,y+3).isBlack!=true && pieceAt(x,y+2).isBlack!=true
			&& pieceAt(x,y+4).isBlack!=true){
			this.WWin = true;
			GameOver = true;
			this.Winner = "White";
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x,y+4);
			printWinner();
			StdDraw.show(100);
		}
	}
		else if (pieceAt(x,y-1)!=null && pieceAt(x,y)!= null && pieceAt(x,y-2)!=null && pieceAt(x,y-3)!= null && pieceAt(x,y-4)!=null){
			if(pieceAt(x,y).isBlack==false && pieceAt(x,y-2).isBlack==false
			&& pieceAt(x,y-1).isBlack==false && pieceAt(x,y-3).isBlack==false
			&& pieceAt(x,y-4).isBlack==false){
			this.WWin = true;
			GameOver = true;
			this.Winner = "White";
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x,y-4);
			/*
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 80));
			StdDraw.text(10,17,"The game is over!");
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 60));
			StdDraw.text(10,3,"The White won the game!");
			*/ 
			printWinner();
			StdDraw.show(100);
		}
	}
		else if (pieceAt(x,y)!=null && pieceAt(x-1,y)!=null && pieceAt(x-2,y)!= null && pieceAt(x-3,y)!=null && pieceAt(x-4,y)!= null){
			if(pieceAt(x,y).isBlack==false && pieceAt(x-1,y).isBlack==false
			&& pieceAt(x-3,y).isBlack==false && pieceAt(x-2,y).isBlack==false
			&& pieceAt(x-4,y).isBlack==false){
			this.WWin = true;
			GameOver = true;
			this.Winner = "White";
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x-4,y);
			printWinner();
			StdDraw.show(100);
		}
	}
		else if (pieceAt(x+1,y)!=null && pieceAt(x+2,y)!= null && pieceAt(x,y)!=null && pieceAt(x+3,y)!= null && pieceAt(x+4,y)!=null){ 
		 if(pieceAt(x,y).isBlack==false && pieceAt(x+3,y).isBlack==false
			&& pieceAt(x+1,y).isBlack==false && pieceAt(x+4,y).isBlack==false
			&& pieceAt(x+2,y).isBlack==false){
			this.WWin = true;
			GameOver = true;
			this.Winner = "White";
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x+4,y);
			printWinner();
			StdDraw.show(100);
		}
	}
				// diagnose for white
		else if (pieceAt(x+1,y+1)!=null && pieceAt(x+2,y+2)!= null && pieceAt(x,y)!=null && pieceAt(x+3,y+3)!= null && pieceAt(x+4,y+4)!=null){
		if (pieceAt(x+1,y+1).isBlack==false && pieceAt(x,y).isBlack==false &&
			pieceAt(x+2,y+2).isBlack==false && pieceAt(x+3,y+3).isBlack==false
			&& pieceAt(x+4,y+4).isBlack==false){
			this.WWin = true;
			GameOver = true;
			this.Winner = "White";
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x+4,y+4);
			printWinner();
			StdDraw.show(100);
		}
		}
		else if (pieceAt(x-1,y-1)!=null && pieceAt(x-2,y-2)!= null && pieceAt(x,y)!=null && pieceAt(x-3,y-3)!= null && pieceAt(x-4,y-4)!=null){
		if (pieceAt(x-1,y-1).isBlack==false && pieceAt(x,y).isBlack==false &&
			pieceAt(x-2,y-2).isBlack==false && pieceAt(x-3,y-3).isBlack==false
			&& pieceAt(x-4,y-4).isBlack==false){
			this.WWin = true;
			GameOver = true;
			this.Winner = "White";
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x-4,y-4);
			printWinner();
			StdDraw.show(100);
		}
		}
		else if (pieceAt(x-1,y+1)!=null && pieceAt(x-2,y+2)!= null && pieceAt(x,y)!=null && pieceAt(x-3,y+3)!= null && pieceAt(x-4,y+4)!=null){
		if (pieceAt(x-1,y+1).isBlack==false && pieceAt(x,y).isBlack==false &&
			pieceAt(x-2,y+2).isBlack==false && pieceAt(x-3,y+3).isBlack==false
			&& pieceAt(x-4,y+4).isBlack==false){
			this.WWin = true;
			GameOver = true;
			this.Winner = "White";
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x-4,y+4);
			printWinner();
			StdDraw.show(100);
		}
		}
		else if (pieceAt(x+1,y-1)!=null && pieceAt(x+2,y-2)!= null && pieceAt(x,y)!=null && pieceAt(x+3,y-3)!= null && pieceAt(x+4,y-4)!=null){
		if (pieceAt(x+1,y-1).isBlack==false && pieceAt(x,y).isBlack==false &&
			pieceAt(x+2,y-2).isBlack==false && pieceAt(x+3,y-3).isBlack==false
			&& pieceAt(x+4,y-4).isBlack==false){
			this.WWin = true;
			GameOver = true;
			this.Winner = "White";
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.007);
			StdDraw.line(x,y,x+4,y-4);
			printWinner();
			StdDraw.show(100);
		}
		}
	}

		else{
			GameOver = false;
		}
		judge = judge + 1;
	}

	private void printWinner(){
		if (GameOver){
		if (Winner.equals("White")){
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
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledRectangle(20.8,15,1.2,1);
			StdDraw.filledRectangle(20.8,3,1.2,1);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 65));
			StdDraw.text(10.4,18,"The game is over!");
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 55));
			StdDraw.text(10.4,3,"The Black won the game!");
		}
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
		StdDraw.text(16.8,20.7,"This game created by Kimi Wang");

		}


		private void drawEmpty(){
		int x = oldX;
		int y = oldY;
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

		private void undo(){
			drawEmpty();
			remove();
			turn = turn -1;
			judge = judge -1;
			drawIndicator();
		}

		private void reStart(){
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
		while (true){

		while (newboard.GameOver == false){
			
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
				break;
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
		newboard.printWinner();
		System.out.println("the game is over!");
		System.out.println("the winner is "+ newboard.Winner);
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