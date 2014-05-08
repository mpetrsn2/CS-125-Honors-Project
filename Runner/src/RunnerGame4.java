/* Fixes:
 * maybe more complex color-coding: top of block is landing ability, majority is moving ability
 * improve pause/reset response. Click every time instead of holding allowed? maybe like click trick
 * make type into subclass of obstacle?
 * pass player as a whole to functions instead of x and y
 * use set methods at beginning of every level, update between frames
 * move methods for player to player class, obstacles to obstacles class
 * make symmetry between update player and update obstacles (is calling update on player.update necessary, or just update)
 * put set player in rest if statement with others, take reset checking out of update player
 * clean up push test in updateY
 * caps lock on r?
 * instead of allowed=setupdate just make method void (technique in moveObstacles)
 * abuse ability to change arrays outside main more
 * 
 * make more levels!
 * make it so moving obstacles can't be placed in others and have limited range of where to go (maybe only change allowed region/only change x)
 * more details? Collectable upgrades, flex on jump, trail/change color/bonus points on upgrade collection
 * game over at zero points (maybe make penalty for losing more strict)
 * make coins and end of level obstacles?
 * make coin reseting less ugly
 * maybe not force reset on moving obstacle, but limit where it can go (or make collecting coin worth more)
 * multiple coins per level/variable coin value?
 * make block more than osize possible?
 * glitching on landing
 * make buttons more obvious
 * get game loop clean again
 * earn bonus points on completion of level
 * fix one frame lag on pause (so doesn't jump forward one and score doesn't jump)
 * try to get rid of so many if(!done) tests
 * limit blue block movement by checking x and y of every obstacle
 * make moving blue block less glitchy (wider range of sensitivity, snap back to mouse once new valid location is found so you don't have to drag back over it, make it more likely to touch other block, test against player and coins too, keep it in the level)
 * make it so that you can't cash 250 from beating level then reset and get it again (maybe only award once like coin)
 * get mouse working, simplify oldclicktime, mousedragged, moving
 * fix glitch when jump into corner on right and top (take another look at updateDy fix, kind flying up wall again)
 * have it so returning to game from menu puts you back at level you were
 * maybe make it so that it doesn't reset when you move a block, but can't move one after you've touched it! (get rid of moved?)
 * maybe only allow quantized position of blue block (to dx in x and osize/8 in y)
 * make death animation more interesting
 * push back on next
 * prevent player from reseting if it would mean game over
 * make osize a property of each object, don't all have to be blocks of osize x osize
 * Name???
 * */

public class RunnerGame4 {
	
	//public static void main(String[] args) {
	public static int gameStart(int difficulty, int initial_level) {
		
		//size of player
		final int PSIZE=20;
		//size of obstacles
		final int OSIZE=40;
		//y position of floor
		final int FLOOR=380;
		//width of level
		final int[] LEVEL_SIZE={2500,2500,2500,2500,2500};
		//x velocity of stage
		final int STAGE_DX=-4;
		//jump velocity
		final int JUMP=-8;
		//gravity
		final double GRAVITY=0.4;
		//range of jump flat
		int range=(int)(STAGE_DX*(2*JUMP/GRAVITY-5));
		final int RANGE=range+range%STAGE_DX;
		//range of jump 1 step up
		int jump_range=(int)(-STAGE_DX*(-JUMP+Math.sqrt(JUMP*JUMP-GRAVITY*OSIZE))/GRAVITY);
		final int JUMP_RANGE=jump_range+jump_range%STAGE_DX;
		//height of jump
		final int JUMP_HEIGHT=(int)(JUMP*JUMP/(2*GRAVITY)+20);
		//initial positions of blocks (quasi final)
		final int[][][] INITIAL={
			//level 1
			{{1200,FLOOR,1},{1500,FLOOR,1},{1500+JUMP_RANGE,FLOOR-OSIZE,1},{1500+3*JUMP_RANGE,FLOOR,2},{1500+2*JUMP_RANGE,FLOOR-2*OSIZE,1},{1500+3*JUMP_RANGE,FLOOR-3*OSIZE,1},{1500+3*JUMP_RANGE+RANGE,FLOOR-3*OSIZE,1},{1500+3*JUMP_RANGE+RANGE,FLOOR,1}},
			//level 2
			{{1240,FLOOR-OSIZE,1},{1200,FLOOR,2},{1240+JUMP_RANGE,FLOOR-2*OSIZE,1},{1240+JUMP_RANGE,FLOOR-OSIZE,2},{1240+JUMP_RANGE,FLOOR,2},{1240+JUMP_RANGE+RANGE,FLOOR-2*OSIZE,2},{1800,FLOOR-OSIZE,1},{1800,FLOOR,2},{1800+RANGE,FLOOR-OSIZE,1},{1800+RANGE,FLOOR-2*OSIZE,2},{1800+2*RANGE,FLOOR-OSIZE,1},{1800+2*RANGE,FLOOR,2}},
			//level 3
			{{1200,FLOOR,1},{1200,FLOOR-4*OSIZE,3},{1200+OSIZE,FLOOR,2},{1200+2*OSIZE,FLOOR,2},{1200+5*OSIZE,FLOOR,1},{1200+6*OSIZE,FLOOR-OSIZE,1},{1200+6*OSIZE+JUMP_RANGE,FLOOR-2*OSIZE,1},{1200+7*OSIZE+JUMP_RANGE,FLOOR-3*OSIZE,2},{1200+7*OSIZE+JUMP_RANGE,FLOOR-4*OSIZE,2},{1800,FLOOR-OSIZE,1},{1800+2*OSIZE,FLOOR,2},{1800+3*OSIZE,FLOOR,2},{1800+5*OSIZE,FLOOR-OSIZE,1},{1800+6*OSIZE,FLOOR-OSIZE,2}},
			//level 4
			{{1200,FLOOR,1},{1200+OSIZE,FLOOR,2},{1200+2*OSIZE,FLOOR,2},{1200+3*OSIZE,FLOOR,2},{1200+4*OSIZE,FLOOR,2},{1200+5*OSIZE,FLOOR,2},{1200+6*OSIZE,FLOOR,3},{1900,FLOOR,1},{1900,FLOOR-OSIZE,1},{1900,FLOOR-2*OSIZE,1},{1900,FLOOR-3*OSIZE,3},{1900+RANGE,FLOOR-2*OSIZE,1},{1900+RANGE,FLOOR-OSIZE,2},{1900+RANGE,FLOOR,2},{1900+2*RANGE,FLOOR-OSIZE,2},{1900+2*RANGE,FLOOR,3}},
			//level 5
			{{1300,FLOOR,1},{1300,FLOOR-OSIZE,1},{1300,FLOOR-2*OSIZE,1},{1300,FLOOR-3*OSIZE,1},{1300,FLOOR-4*OSIZE,1},{1300,FLOOR-5*OSIZE,1},{1300,FLOOR-6*OSIZE,3},{1300,FLOOR-7*OSIZE,3},{1300+3*OSIZE,FLOOR-5*OSIZE,1},{1300+4*OSIZE,FLOOR-5*OSIZE,1},{1300+5*OSIZE,FLOOR-5*OSIZE,1},{1300+6*OSIZE,FLOOR-8*OSIZE,2},{1300+7*OSIZE,FLOOR-7*OSIZE,2},{1300+8*OSIZE,FLOOR-6*OSIZE,2},{1300+9*OSIZE,FLOOR-5*OSIZE,2},{1300+10*OSIZE,FLOOR-5*OSIZE,1},{1300+11*OSIZE,FLOOR-5*OSIZE,1},{1300+12*OSIZE,FLOOR-5*OSIZE,1},{1300+13*OSIZE,FLOOR-5*OSIZE,1},{1300+14*OSIZE,FLOOR-5*OSIZE,3},{1300+15*OSIZE,FLOOR-5*OSIZE,3},{1300+16*OSIZE,FLOOR-5*OSIZE,1},{1300+17*OSIZE,FLOOR-5*OSIZE,1},{1300+17*OSIZE,FLOOR-4*OSIZE,2},{1300+OSIZE,FLOOR-2*OSIZE,2},{1300+2*OSIZE,FLOOR-2*OSIZE,1},{1300+3*OSIZE,FLOOR-2*OSIZE,1},{1300+4*OSIZE,FLOOR-2*OSIZE,2},{1300+5*OSIZE,FLOOR-3*OSIZE,1},{1300+6*OSIZE,FLOOR-3*OSIZE,1},{1300+7*OSIZE,FLOOR-3*OSIZE,1},{1300+8*OSIZE,FLOOR-3*OSIZE,1},{1300+9*OSIZE,FLOOR-3*OSIZE,1},{1300+10*OSIZE,FLOOR-3*OSIZE,1},{1300+11*OSIZE,FLOOR-3*OSIZE,1},{1300+12*OSIZE,FLOOR-3*OSIZE,1},{1300+13*OSIZE,FLOOR-3*OSIZE,1},{1300+14*OSIZE,FLOOR-3*OSIZE,1},{1300+15*OSIZE,FLOOR-3*OSIZE,1},{1300+16*OSIZE,FLOOR-3*OSIZE,1},{1300+17*OSIZE,FLOOR-3*OSIZE,1}}
			};
		//initialize player
		Player player=new Player(Zen.getZenWidth()/2,FLOOR-PSIZE,0,0);
		//holds obstacles
		int max=0;
		for(int i=0;i<INITIAL.length;i++)
			max=INITIAL[i].length>max?INITIAL[i].length:max;
		Obstacles[] obstacles=new Obstacles[max];	
		//level
		int level=initial_level;
		//Collectables
		final int[][] COINS={
			{1500+3*JUMP_RANGE+(int)(1.5*RANGE)+OSIZE,FLOOR-4*OSIZE-JUMP_HEIGHT,1},
			{1800+(int)(2.5*RANGE)+OSIZE,FLOOR-2*OSIZE-JUMP_HEIGHT,1},
			{1400,FLOOR-200,1},
			{2050+RANGE,FLOOR-PSIZE,1},
			{1300+3*OSIZE,FLOOR-3*OSIZE-PSIZE,1}};
		int[] coin=new int[3];
		coin[0]=COINS[level-1][0];
		coin[1]=COINS[level-1][1];
		coin[2]=COINS[level-1][2];
		//holds y status 0 reset, 1 run, 2 push
		int[][] allowed=new int[2][FLOOR];
		//finished all levels
		boolean done=false;
		//score
		int score=-1;
		//high score
		int highScore=0;
		//revert after reset
		int oldScore=-1;
		//horizontal shift
		int shift=0;
		//position of end of level;
		int endOfLevel=LEVEL_SIZE[level-1];
		//game status (reset, running, paused)
		String status="paused";
		//controls toggle
		boolean showControls=false;
		//complete reset
		boolean replay=false;
		//instantaneous click
		int[] click={0,0};
		//time of last click
		long oldClickTime=0;
		//if obstacles currently moving
		boolean moving=false;
		//if mouse is being dragged
		boolean mouseDragged=false;
		//flex animation on jump
		int flex=0;
		//time since trigger started
		long oldTime=0;
		//point penalty of death
		int penalty=(int)(Math.pow(4,difficulty))*250;
		//prize awarded for coins
		int coinPrize=(int)Math.pow(2,difficulty)*500;
		//prize awarded for end of level
		int levelPrize=(int)Math.pow(2,difficulty)*250;
		//prize awarded for running
		int runningPrize=(int)Math.pow(2,difficulty);
		
		obstacles=setObstacles(obstacles,FLOOR,OSIZE,INITIAL[level-1]);
		allowed=setupdateAllowed(allowed,obstacles,player.getX(),FLOOR,PSIZE,OSIZE,STAGE_DX,INITIAL[level-1].length);
		
		//game loop
		while(Zen.isRunning()) {
			//draw all graphics
			draw(player.getX(),player.getY(),obstacles,coin,INITIAL[level-1].length,FLOOR,PSIZE,flex,OSIZE,shift,status,level,score,highScore,endOfLevel,showControls,moving,done,oldTime,penalty,coinPrize,levelPrize);			
			if(!done) {//don't know about this
				//update status
				status=updateStatus(status,player.getX(),allowed[1][player.getY()],PSIZE,player.getDx(),endOfLevel,oldScore-penalty);			
				//update shift
				shift=updateShift(status,shift,click,endOfLevel,LEVEL_SIZE[level-1],moving);
			}
			if(replay) {
				level=1;
				score=-1;
				oldScore=-1;
				done=false;
				replay=false;
				status="reset";
			}
			//moved status
			if(status.equals("next")) {
				if(level+1>INITIAL.length) {
					done=true;
					level=1;
					status="paused";
				}
				else {				
					level++;
					oldScore=score;
				}
			}
			if(status.equals("reset") || status.equals("next")) {
				obstacles=setObstacles(obstacles,FLOOR,OSIZE,INITIAL[level-1]);
				allowed=setupdateAllowed(allowed,obstacles,player.getX(),FLOOR,PSIZE,OSIZE,STAGE_DX,INITIAL[level-1].length);
				endOfLevel=LEVEL_SIZE[level-1];
				coin[0]=COINS[level-1][0];
				coin[1]=COINS[level-1][1];
				coin[2]=COINS[level-1][2];
				if(score>oldScore){
					oldScore-=penalty;
					oldTime=System.currentTimeMillis();
				}
				if(oldScore<0)
					GameOver.endGame((int)highScore);//return score;
			}
			if(!status.equals("paused")) {
				//update player
				player.updatePlayer(allowed,FLOOR-PSIZE,STAGE_DX,status,JUMP,GRAVITY);
				//update obstacles
				updateObstacles(obstacles,STAGE_DX,INITIAL[level-1].length);//took status out?
				allowed=setupdateAllowed(allowed,obstacles,player.getX(),FLOOR,PSIZE,OSIZE,STAGE_DX,INITIAL[level-1].length);
				score=updateScore(score,oldScore,player.getDx(),status,coin,endOfLevel-player.getX(),coinPrize,levelPrize,runningPrize);
				highScore=updateHighScore(score,highScore);
				endOfLevel+=updateEndOfLevel(STAGE_DX);
				flex=updateFlex(player.getDy());
				moving=false;
				updateCoin(coin,player.getX(),player.getY(),STAGE_DX,PSIZE);
			}
			else {
				//update position of click
				click=updateClick(oldClickTime);
				if(!done) {
					mouseDragged=updateMouseDragged(oldClickTime);
					showControls=updateShowControls(showControls,click,moving);
					if(toMenu(click,moving)) {
						//return score;
						GameOver.endGame((int)highScore);
					}
					moving=moveObstacles(obstacles,INITIAL[level-1],shift,FLOOR,moving,mouseDragged,OSIZE,STAGE_DX,player.getX()+PSIZE);
				}
				else
					replay=updateReplay(click);
				//used to test if click has changed
				oldClickTime=updateOldClickTime();
			}
			//prepare for next cycle
			Zen.sleep(10);
			Zen.flipBuffer();
		}
		return 0;
	}
	
	//set positions of obstacles
	public static Obstacles[] setObstacles(Obstacles[] obs, int floor, int osize, int[][] initial) {
		for(int i=0;i<initial.length;i++)//numobstacles
			obs[i]=new Obstacles(initial[i][0],initial[i][1]-osize,initial[i][2]);
		return obs;
	}
	
	public static Obstacles[] updateObstacles(Obstacles[] obs, int DX, int numobstacles) {
		for(int i=0;i<numobstacles;i++)
			obs[i].updateX(DX);
		return obs;
	}
	
	public static int[][] setupdateAllowed(int[][] allowed, Obstacles[] obs, int x, int floor, int psize, int osize, int stagedx, int numobstacles) {
		for(int a=0;a<2;a++) {
			for(int i=0;i<allowed[1].length+1-psize;i++)
				allowed[a][i]=1;
			for(int i=allowed[1].length+1-psize;i<allowed[1].length;i++)
				allowed[a][i]=2;
			for(int j=0;j<numobstacles;j++)
				if(obs[j].getX()-psize<=x+stagedx*(1-a) && x+stagedx*(1-a)<obs[j].getX()+osize)//unsure about +/-1
					for(int k=obs[j].getY()+1-psize;k<obs[j].getY()+osize-1;k++) {//
						if(obs[j].getType()==2)
							allowed[a][k]=0;
						else
							allowed[a][k]=2;
					}
		}
		return allowed;
		
	}
	
	//update game status
	public static String updateStatus(String status, int x, int allowed, int psize, int dx, int endoflevel, int difference) {
		if(Zen.isKeyPressed('f') && difference>=0)
			return "reset";
		else if(status.equals("paused")) {
			if(Zen.isKeyPressed('e')) {
				Zen.sleep(250);
				if(allowed==0)
					return "reset";
				else if(allowed==2)
					return "push";
				return "running";
			}
			return "paused";
		}
		else if(Zen.isKeyPressed('e')) {
			Zen.sleep(250);
			return "paused";
		}
		else if(endoflevel<-dx)//added else?
			return "next";
		else if(x<-dx || allowed==0) {
			Zen.sleep(500);
			return "reset";
		}
		else if(allowed==2)
			return "push";
		else if((status.equals("reset") || status.equals("next") || status.equals("pushback")) && x<Zen.getZenWidth()/2)
			return "pushback";
		return "running";
	}
	
	public static int updateShift(String status, int shift, int[] click, int endoflevel, int levelsize, boolean moving) {
		if(status.equals("paused")) {
			if(Zen.isKeyPressed('a') && endoflevel+shift<levelsize)
				return shift+5;
			else if(Zen.isKeyPressed('d') && (endoflevel+shift>Zen.getZenWidth() || shift>0))
				return shift-5;
			else if(!moving && Math.abs(click[0]-120)<=100 && Math.abs(click[1]-125)<=15)//!moving
				return 0;
			return shift;
		}
		return 0;
	}
	
	public static int updateEndOfLevel(int dx) {
		return dx;
	}
	
	public static void updateCoin(int[] coin, int x, int y, int dx, int psize) {
		if(Math.abs(x+psize/2-coin[0])<psize && Math.abs(y+psize/2-coin[1])<psize)
			coin[2]--;
		coin[0]+=dx;
	}
	
	//draw graphics
	public static void draw(int x, int y, Obstacles[] obs, int[] coin, int numobstacles, int floor, int psize, int flex, int osize, int shift, String status, int level, int score, int highscore, int endOfLevel, boolean showControls, boolean moving, boolean done, long oldtime, int penalty, int coinprize, int levelprize) {
		//background
		Zen.setColor(200, 100, 50);
		Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());
		//floor
		Zen.setColor(0, 0, 0);
		Zen.fillRect(0, floor, Zen.getZenWidth(), Zen.getZenHeight());
		//end of level
		if(endOfLevel>x)
			Zen.setColor(100,100,100);
		else {
			PixelatedText.drawPixelatedText("+"+levelprize,endOfLevel+shift-80,80,3);
			Zen.setColor(0,200,0);
		}
		Zen.fillRect(endOfLevel+shift,0,3,floor);
		//obstacles
		for(int i=0;i<numobstacles;i++) {
			if(obs[i].getType()==1)
				Zen.setColor(0,0,0);
			else if(obs[i].getType()==2)
				Zen.setColor(200,0,0);
			else
				Zen.setColor(0,0,200);
			Zen.fillRect(obs[i].getX()+shift, obs[i].getY(), osize, osize);
		}
		//coin
		Zen.setColor(200,200,0);
		if(coin[2]==1)
			Zen.fillRect(coin[0]+shift,coin[1],10,10);
		else
			PixelatedText.drawPixelatedText("+"+coinprize,coin[0]+shift,coin[1],3);
		//penalty		
		if(System.currentTimeMillis()-oldtime<2500)
			PixelatedText.drawPixelatedText("Reset, -"+penalty,390,50,3);
		//player
		Zen.setColor(0, 200, 200);
		Zen.fillRect(x+shift+flex/2, y, psize-flex, psize+flex);
		//text
		PixelatedText.drawPixelatedText("Level:"+level,20,20,4);
		PixelatedText.drawPixelatedText("Score:"+(score>=0?score:0),360,20,4);
		PixelatedText.drawPixelatedText("High Score:"+(highscore>=0?highscore:0),670,20,4);
		//pause
		if(status.equals("paused") && !moving) {
			if(!done) {
				Zen.setColor(0, 0, 0);
				Zen.fillRect(Zen.getZenWidth()-80, 60, 20, 60);
				Zen.fillRect(Zen.getZenWidth()-40, 60, 20, 60);
				if(!showControls) {
					PixelatedText.drawPixelatedText("+Controls",20,60,6);
					PixelatedText.drawPixelatedText("+Center",20,110,6);
					PixelatedText.drawPixelatedText("+Menu",380,60,6);
				}
				else {
					PixelatedText.drawPixelatedText("-Controls",20,60,6);
					PixelatedText.drawPixelatedText("Hit space to jump, e to pause, and f to reset.",20,110,4);
					PixelatedText.drawPixelatedText("Use a and d to scroll while paused.",20,140,4);
					PixelatedText.drawPixelatedText("Click and drag blue blocks to move them.",20,170,4);
				}
			}
			else {
				PixelatedText.drawPixelatedText("Congratulations!",210,100,8);
				PixelatedText.drawPixelatedText("You finished the game!",300,160,4);
				PixelatedText.drawPixelatedText("Play again",410,220,4);
			}
		}
	}

	//toggle controls display
	public static boolean updateShowControls(boolean showControls, int[] click, boolean moving) {
		if(!moving && Math.abs(click[0]-150)<=130 && Math.abs(click[1]-75)<=15)
			return !showControls;
		return showControls;
	}
	
	public static boolean toMenu(int[] click, boolean moving) {
		return !moving && Math.abs(click[0]-460)<=80 && Math.abs(click[1]-75)<=15;
	}
	
	public static int[] updateClick(long oldClickTime) {
		int[] temp={Zen.getMouseClickX(),Zen.getMouseClickY()};
		int[] temp2={0,0};
		if(Zen.getMouseClickTime()!=oldClickTime)
			return temp;
		return temp2;
	}
	
	public static long updateOldClickTime() {
		return Zen.getMouseClickTime();
	}
	
	public static int updateScore(int score, int oldscore, int dx, String status, int[] coin, int distance, int coinprize, int levelprize, int runningprize) {
		if(status.equals("reset"))
			return oldscore;
		if(distance==0)
			return score+levelprize;
		int temp=0;
		if(coin[2]==0) {
			temp=coinprize;
			coin[2]=-1;
		}
		return score+(dx>=0?runningprize:0)+temp;
	}
	
	public static int updateHighScore(int score, int highscore) {
		return score>highscore?score:highscore;
	}
	
	public static boolean moveObstacles(Obstacles[] obstacles, int[][] initial, int shift, int floor, boolean moving, boolean mousedragged, int osize, int dx, int xpsize) {
		for(int i=0;i<initial.length;i++) {
			int[] temp={Zen.getMouseX()-osize/2-shift,Zen.getMouseY()-osize/2};
			temp[0]=temp[0]-temp[0]%(-dx);
			temp[0]=temp[0]>xpsize?temp[0]:xpsize;
			temp[1]=temp[1]-temp[1]%(osize/10);
			if(obstacles[i].getType()==3 && obstacles[i].getX()>=xpsize && (obstacles[i].getHeld() || (Math.abs(temp[0]-obstacles[i].getX())<=osize/2 && Math.abs(temp[1]-obstacles[i].getY())<=osize/2)) && mousedragged) {
				int[] old={obstacles[i].getX(),obstacles[i].getY()};
				boolean test=true;
				for(int j=0;j<initial.length;j++)
					if(i!=j && Math.abs(temp[0]-obstacles[j].getX())<osize && Math.abs(temp[1]-obstacles[j].getY())<osize)
						test=false;
				if(temp[1]+osize>floor)
					test=false;
				if(test) {
					obstacles[i].setX(temp[0]);
					obstacles[i].setY(temp[1]);
					initial[i][0]+=obstacles[i].getX()-old[0];
					initial[i][1]+=obstacles[i].getY()-old[1];
				}
				obstacles[i].setHeld(true);
				return true;
			}
			else
				obstacles[i].setHeld(false);
		}
		return false;
	}
	
	public static boolean updateReplay(int[] click) {
		return Math.abs(click[0]-510)<=100 && Math.abs(click[1]-230)<=10;
	}
	
	public static int updateFlex(int dy) {
		if(dy<0)
			return 2;
		return 0;
	}
	
	public static boolean updateMouseDragged(long oldclicktime) {
		return System.currentTimeMillis()-oldclicktime<20 || (Zen.getMouseX()==Zen.getMouseClickX() && Zen.getMouseY()==Zen.getMouseClickY());
	}
}
