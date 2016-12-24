package ucsdKWang.gameOfGo;
// create pieces of game of go
// author kimi wang 

public class ggPiece{
	public boolean isBlack;
	public boolean isEaten;
	public ggBoard board;
	public int x, y;

	public ggPiece(boolean eaten, boolean isblack, ggBoard board, int x, int y){
		this.isEaten = eaten;
		this.isBlack = isblack;
		this.board = board;
		this.x = x;
		this.y = y;
	} 
	public boolean isBlack(){
		return this.isBlack;
	}

	public boolean isEaten(){
		return this.isEaten;
	}

	public int getX(){
		return x;
	}
	public int getY() {
		return y;
	}

}