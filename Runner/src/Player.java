
public class Player {
	private int x;
	private int y;
	private int dx;
	private double dy;
	public Player(int xx, int yy, int dxx, double dyy) {
		x=xx;
		y=yy;
		dx=dxx;
		dy=dyy;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getDx() {
		return dx;
	}
	public int getDy() {
		return (int)dy;
	}
	public void updatePlayer(int[][] allowed, int floor, int stagedx, String status, int jump, double gravity) {
		updateDx(status,stagedx);
		updateDy(allowed,floor,status,jump,gravity);
		updateX(status);
		updateY(allowed[1],floor,status);
	}
	private void updateX(String status) {
		if(x>Zen.getZenWidth()/2)//status.equals("reset"))
			x=Zen.getZenWidth()/2;
		else
			x+=dx;
	}
	private void updateY(int[] allowed, int floor, String status) {
		if(status.equals("reset"))
			y=floor;
		else if(y+dy>floor)
			y=floor;
		else if(allowed[y+(int)dy]==2 && !status.equals("push"))
			while(allowed[y+(int)dy]==2) {
				if(dy>=0)
					y--;
				else
					y++;
			}
		else
			y+=dy;
	}
	private void updateDx(String status, int stagedx) {
		if(status.equals("push"))
			dx=stagedx;
		else if(status.equals("pushback"))
			dx=-stagedx;
		else
			dx=0;
	}
	private void updateDy(int[][] allowed, int floor, String status, int jump, double gravity) {
		if(status.equals("reset"))
			dy=0;
		else if (Zen.isKeyPressed((char)32) && allowed[1][y+(int)(dy>1?dy:1)]==2 && allowed[0][y+(int)(dy>1?dy:1)]==2)
			dy=jump;
		else
			dy+=gravity;
		if(allowed[1][y+(int)(dy<0?dy:1)]==2 && allowed[0][y+(int)(dy<0?dy:1)]==2)
			dy=0;
	}
	
}
