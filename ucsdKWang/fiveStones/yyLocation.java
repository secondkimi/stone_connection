package ucsdKWang.fiveStones;
// create board of game of go
// author kimi wang 
public class yyLocation{
	private int x;
	private int y;
	
	public yyLocation() {
		x = -1;
		y = -1;
	}
	public yyLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public boolean hasStone(int xi, int yi) {
		return ( x == xi && y == yi); 
	}
	
	public int getX(){
		return x;
	}
	public int getY() {
		return y;
	}
}