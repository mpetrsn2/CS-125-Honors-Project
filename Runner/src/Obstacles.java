
public class Obstacles {
	private int x;
	private int y;
	private int type;
	private boolean held=false;
	public Obstacles(int xx, int yy, int otype) {
		x=xx;
		y=yy;
		type=otype;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getType() {
		return type;
	}
	public void updateX(int dx) {
		x+=dx;
	}
	public void setX(int xx) {
		x=xx;
	}
	public void setY(int yy) {
		y=yy;
	}
	public boolean getHeld() {
		return held;
	}
	public void setHeld(boolean isheld) {
		held=isheld;
	}
}
