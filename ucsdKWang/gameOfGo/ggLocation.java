package ucsdKWang.gameOfGo;
// create board of game of go
// author kimi wang 
public class ggLocation{
	private int x;
	private int y;
	private boolean stone; // true for black and false for white // not yet used
	private String property; // not yet used
	private boolean isEaten;

	public ggLocation() {
		x = -1;
		y = -1;
		stone = false;
		property = "PARENT";
	}
	public ggLocation(int x, int y, boolean b, boolean isEaten) {
		this.x = x;
		this.y = y;
		this.stone = b;
		this.property = "PARENT";
		this.isEaten = isEaten;
	}
	public ggLocation(int x, int y, boolean b) {
		this.x = x;
		this.y = y;
		this.stone = b;
		this.property = "PARENT"; 
	}
	public ggLocation(int x, int y, boolean b, String property) {
		this.x = x;
		this.y = y;
		this.stone = b;
		this.property = property;
	}
	public boolean hasStone(int xi, int yi) {
		return ( x == xi && y == yi); 
	}
	public boolean isEaten(){
		return isEaten;
	}
	public void setisEaten(boolean b){
		isEaten = b;
	}
	public boolean hasBlackStone(int xi, int yi, boolean b) {
		return ( x == xi && y == yi && b == stone); 
	}
	public boolean hasWhiteStone(int xi, int yi, boolean b) {
		return ( x == xi && y == yi && b != stone); 
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String p) {
		property = p;
	}
	public void setStone(boolean b){
		this.stone = b;

	}
	public int getX(){
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean getStone(){
		return stone;
	}
	public boolean checkCannotPut(int x, int y, boolean color) {
		return ( color==getStone() && x==getX()&& y==getY()&&isEaten() );
	}
}