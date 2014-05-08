/* Standalone version gameplay for Honors Project
 * Note: this program utilizes Zen, so make sure to run this in a package that
 * also contains Zen.java.
 * */

/* Fixes:
 * maybe more complex color-coding: top of block is landing ability, majority is moving ability
 * improve pause/reset response. Click every time instead of holding allowed?
 * make type into subclass of obstacle?
 * pass player as a whole to functions instead of x and y
 * use set methods at beginning of every level, update between frames
 * make update methods in player class so don't do set(update())
 * move methods for player to player class, obstacles to obstacles class
 * make symmetry between update player and update obstacles (is calling update on player.update necessary, or just update)
 * put set player in rest if statement with others, take reset checking out of update player
 * clean up push test in updateY
 * climb up wall?
 * caps lock on r?
 * change maxY to not allowed y so that you can go under blocks
 * get spacing of blocks right so holding jump works
 * weaker gravity?
 * continuous level progression - maybe have blank region in between with vertical markers
 * */

public class RunnerGame2 {
	
	public static void main(String[] args) {
		
		//size of player
		final int PSIZE=20;
		//size of obstacles
		final int OSIZE=40;
		//y position of floor
		final int FLOOR=300;
		//width of level
		final int LEVEL_SIZE=2400;
		//x velocity of stage
		final int STAGE_DX=-4;
		//jump velocity
		final int JUMP=-8;
		//gravity
		final double GRAVITY=0.4;
		//range of jump flat
		final int RANGE=(int)(STAGE_DX*2*JUMP/GRAVITY);
		//range of jump 1 step up
		final int JUMP_RANGE=(int)(-STAGE_DX*(-JUMP+Math.sqrt(JUMP*JUMP-GRAVITY*OSIZE))/GRAVITY);
		//max number of obstacles at once
		final int NUM_OBSTACLES=8;
		//initialized array of types of obstacles
		final int[][] TYPE={{1,1,1,2,1,1,1,1}};
		//initial positions of blocks
		final int[][][] INITIAL={{{1200,FLOOR},{1500,FLOOR},{1500+JUMP_RANGE,FLOOR-OSIZE},
			{1500+2*JUMP_RANGE,FLOOR},{1500+2*JUMP_RANGE,FLOOR-2*OSIZE},{1500+3*JUMP_RANGE,FLOOR-3*OSIZE},
			{1500+3*JUMP_RANGE+RANGE,FLOOR-3*OSIZE},{1500+3*JUMP_RANGE+RANGE,FLOOR}}};
		//initialize player
		Player player=new Player(Zen.getZenWidth()/2,FLOOR-PSIZE,0,0);
		//holds obstacles
		Obstacles[] obstacles=new Obstacles[NUM_OBSTACLES];
		//holds maximum allowed y for any x value
		int[] maxY=new int[LEVEL_SIZE];
		//level
		int level=1;
		//horizontal shift
		int shift=0;
		//position of end of level;
		int endOfLevel=LEVEL_SIZE;
		//game status (reset, running, paused)
		String status="paused";
		
		//move this into loop when multiple levels
		//initialize obstacle position based on user
		obstacles=setObstacles(obstacles,TYPE[level-1],FLOOR,OSIZE,INITIAL);
		maxY=setMaxY(obstacles,TYPE[level-1],FLOOR,PSIZE,OSIZE,LEVEL_SIZE);
		
		//game loop
		while(Zen.isRunning()) {
			//draw all graphics
			draw(player.getX(),player.getY(),obstacles,FLOOR,PSIZE,OSIZE,shift,status,level,endOfLevel);
			//update status
			status=updateStatus(status,player.getX(),player.getY(),maxY[player.getX()],PSIZE,player.getDx(),endOfLevel);
			//update shift
			shift=updateShift(status,shift);			
			if(status.equals("reset")) {
				obstacles=setObstacles(obstacles,TYPE[0],FLOOR,OSIZE,INITIAL);
				maxY=setMaxY(obstacles,TYPE[0],FLOOR,PSIZE,OSIZE,LEVEL_SIZE);
				endOfLevel=LEVEL_SIZE;
			}
			if(!status.equals("paused")) {
				//test for completed level
				level+=updateLevel(player.getX(),player.getDx(),endOfLevel);
				//update player
				//player.updatePlayer(maxY[player.getX()],maxY[0],FLOOR-PSIZE,STAGE_DX,status,JUMP,GRAVITY);
				//update obstacles
				updateObstacles(obstacles,STAGE_DX,status);
				updateMaxY(maxY,STAGE_DX);
				endOfLevel+=updateEndOfLevel(status,STAGE_DX);
			}
			//prepare for next cycle
			Zen.sleep(10);
			Zen.flipBuffer();
		}
		
	}
	
	//set positions of obstacles
	public static Obstacles[] setObstacles(Obstacles[] obs, int[] type, int floor, int osize, int[][][] initial) {
		for(int i=0;i<obs.length;i++)
			obs[i]=new Obstacles(initial[0][i][0],initial[0][i][1]-osize,type[i]);
		return obs;
	}
	
	public static Obstacles[] updateObstacles(Obstacles[] obs, int DX, String status) {
		for(int i=0;i<obs.length;i++)
			obs[i].updateX(DX);
		return obs;
	}
	
	//set minimum y for every x
	public static int[] setMaxY(Obstacles[] obs, int[] type, int floor, int psize, int osize, int levelsize) {
		int[] maxY=new int[levelsize];
		for(int i=0;i<maxY.length;i++)
			maxY[i]=floor-psize;
		for(int j=0;j<obs.length;j++)
			for(int k=obs[j].getX()-psize;k<=obs[j].getX()+osize;k++)
				if(obs[j].getY()-psize+type[j]-1<maxY[k])
					maxY[k]=obs[j].getY()-psize+type[j]-1;
		return maxY;	
	}
	
	public static int[] updateMaxY(int[] maxY, int DX) {
		for(int i=0;i<maxY.length+DX;i++)
			maxY[i]=maxY[i-DX];
		return maxY;
	}
	
	//update game status
	public static String updateStatus(String status, int x, int y, int maxY, int psize, int dx, int endoflevel) {
		if(Zen.isKeyPressed('f'))
			return "reset";
		else if(status.equals("paused")) {
			if(Zen.isKeyPressed('e')) {
				Zen.sleep(250);
				if(y>maxY && maxY%10==0)
					return "push";
				return "running";
			}
			return "paused";
		}
		else if(Zen.isKeyPressed('e')) {
			Zen.sleep(250);
			return "paused";
		}
		//type of block encoded in maxY
		else if(x<-dx || (y==maxY && maxY%10!=0) || endoflevel<-dx) {
			Zen.sleep(500);
			return "reset";
		}
		else if(y>maxY && maxY%10==0)
			return "push";
		return "running";
	}
	
	public static int updateShift(String status, int shift) {
		if(status.equals("paused")) {
			if(Zen.isKeyPressed('a'))
				return shift+5;
			else if(Zen.isKeyPressed('d'))
				return shift-5;
			return shift;
		}
		return 0;
	}
	
	public static int updateEndOfLevel(String status, int dx) {
		if(status.equals("paused"))
			return 0;
		return dx;
	}
	
	//draw graphics
	public static void draw(int x, int y, Obstacles[] obs, int floor, int psize, int osize, int shift, String status, int level, int endOfLevel) {
		//background
		Zen.setColor(200, 100, 50);
		Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());
		//floor
		Zen.setColor(0, 0, 0);
		Zen.fillRect(0, floor, Zen.getZenWidth(), Zen.getZenHeight());
		PixelatedText.drawPixelatedText("Level "+level,20,20,4);
		//end of level
		Zen.setColor(100,100,100);
		Zen.fillRect(endOfLevel,0,3,floor);
		//obstacles
		for(int i=0;i<obs.length;i++) {
			if(obs[i].getType()==1)
				Zen.setColor(0, 0, 0);
			else
				Zen.setColor(200, 0, 0);
			Zen.fillRect(obs[i].getX()+shift, obs[i].getY(), osize, osize);
		}
		//player
		Zen.setColor(0, 200, 200);
		Zen.fillRect(x+shift, y, psize, psize);
		//pause
		if(status.equals("paused")) {
			Zen.setColor(0, 0, 0);
			Zen.fillRect(Zen.getZenWidth()-80, 20, 20, 60);
			Zen.fillRect(Zen.getZenWidth()-40, 20, 20, 60);
			PixelatedText.drawPixelatedText("Hit e to pause",20,100,6);
			PixelatedText.drawPixelatedText("and space to jump",20,140,6);
		}
	}
	
	//update level number
	public static int updateLevel(int x, int dx, int endOfLevel) {
		if(endOfLevel<=-dx)
			return 1;
		return 0;
	}
}
