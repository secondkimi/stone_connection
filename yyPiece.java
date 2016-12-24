// created by kimi Wang

public class yyPiece{
	public boolean isBlack;
	public boolean isempty;
	public yyBoard board;
	public int x, y;

	public yyPiece(boolean empty, boolean isblack, yyBoard board, int x, int y){
		this.isempty = empty;
		this.isBlack = isblack;
		this.board = board;
		this.x = x;
		this.y = y;
	} 
	public boolean isBlack(){
		return this.isBlack;
	}
}