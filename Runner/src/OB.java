public class OB {
	private int x = Zen.getZenWidth() - 50;
	private int y;
	public int dx = -4;
	public int po;
	private int color =1;


	public int setLeft(int xx){
		this.x = xx;
		return this.x;
	}
	public void setY(int yy){
		this.y = yy;
	}
	public void setX(int xx){
		this.x = xx;
	}
	public int getLeft(){
		return this.x;
	}
	public int getRight(){
		int r = this.x + 30;
		return r; 
	}
	public int getY(){
		return this.y;
	}
	public int getX(){
		return this.x;
	}
	public int getTop(){
		int b = this.y;
		return b;
	}
	public int getDX(){
		return this.dx;
	}
	public OB(){
		y = 0;
		x = 0;
	}
	public OB(int tob,int yy) {
		if (tob == 1 && yy == 0) y = 0;
		if (tob == 1 && yy == 1) y = 36;
		if (tob == 1 && yy == 2) y = 70;
		if (tob == 1 && yy == 3) y = 106;
		if (tob == 1 && yy == 4) y = 140;
		if (tob == 1 && yy == 5) y = 176;


		if (tob == 2 && yy == 0) y = Zen.getZenHeight() - 30;
		if (tob == 2 && yy == 1) y = Zen.getZenHeight() - 70;
		if (tob == 2 && yy == 2) y = Zen.getZenHeight() - 100;
		if (tob == 2 && yy == 3) y = Zen.getZenHeight() - 140;
		if (tob == 2 && yy == 4) y = Zen.getZenHeight() - 168;

		po = yy;
	}
	public void draw() {	
		Zen.setColor(0,0,0);
		Zen.fillRect(x, y, 30, 30);
	}
	public void drawcoin(OB obj){
		if (color == 1) {x = obj.getX() + 9; y = obj.getY() - 20;Zen.setColor(200,200,0);Zen.fillRect(x, y, 12, 12);}
		if (color == 2) {x = obj.getX() + 9; y = obj.getY() - 20;Zen.setColor(200,0,0);Zen.fillRect(x,y,12,12);}
		if (color == 0) {x = -12; y = -12;Zen.setColor(200,100,50);Zen.fillRect(x,y, 12, 12);}
	}
	public void drawcoinT(OB obj){
		if (color == 1) {x = obj.getX() + 9; y = obj.getY() + 35;Zen.setColor(200,200,0);Zen.fillRect(x, y, 12, 12);}
		if (color == 2) {x = obj.getX() + 9; y = obj.getY() + 35;Zen.setColor(200,0,0);Zen.fillRect(x,y,12,12);}
		if (color == 0) {x = -12; y = -12;Zen.setColor(200,100,50);Zen.fillRect(x,y, 12, 12);}
	}
	public void setCoinColor(int i){
		color = i;
	}
	public int getCoinColor(){
		return color;
	}

	public void newX() {				
			x = (x+dx);		
	}
}