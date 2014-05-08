public class two_player_mode {

	public static void twoPlayerStart(){

		start(); //starts the game. Also allows for reset. 


	}
	public static void draw(int xt, int xb, int yt, int yb, int gx, int score1, int score2, int s1x, int s2x) {	
		//draw graphics

		Zen.setColor(0,0,0);
		Zen.setColor(0, 200, 200);										
		Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());							//Background Top
		Zen.setColor(200,100,50);											
		Zen.fillRect(0, Zen.getZenHeight()/2, Zen.getZenWidth(), Zen.getZenHeight());		//Background Bottom

		Zen.fillRect(xt, yt, 26, 26);		//top block
		Zen.setColor(0, 200, 200);
		Zen.fillRect(xb,yb, 26, 26);		//bottom block	

		Zen.setColor(0,0,0);
		Zen.fillRect(0, Zen.getZenHeight()/2, Zen.getZenWidth() - 1, 2); //black center line
		Zen.fillRect(gx, Zen.getZenHeight()/2 - 5, 11, 11);    			//block in center that shows distance

		PixelatedText.drawPixelatedText("Score:"+score1,s1x,Zen.getZenHeight()/2 + 10,3);		//player 1 score
		PixelatedText.drawPixelatedText("Score:"+score2,s2x,Zen.getZenHeight()/2 - 20,3);		//player 2 score
	}
	public static void checkOrbs(OB[] orbs){
		if (orbs[0].getLeft() + 30 < 0) orbs[0].setLeft(Zen.getZenWidth());


		for(int i = 0; i < orbs.length - 1; i ++){															   //Reset
			if (orbs[i].getLeft() + 30 < 0){																  // Obstacles
				if (i != 0 && orbs[i-1].po != 0) orbs[i] = new OB(1,(int)(Math.random() * 5));			  	 //
				else if  (i != 0 && orbs[i-1].po == 0) orbs[i] = new OB(1,((int)(Math.random() * 2))+1); 	//
				if (i > 0) orbs[i].setLeft(orbs[i-1].getLeft() + 50);									   //	
			}									   
		}
	}
	public static void checkOrbs1(OB[] orbs){
		if (orbs[0].getLeft() + 30 < 0) orbs[0].setLeft(Zen.getZenWidth());
		for(int i = 0; i < orbs.length - 1; i ++){															   //Reset
			if (orbs[i].getLeft() + 30 < 0){																  // Obstacles
				if (i != 0 && orbs[i-1].po != 0) orbs[i] = new OB(2,(int)(Math.random() * 5));			  	 //
				else if  (i != 0 && orbs[i-1].po == 0) orbs[i] = new OB(2,((int)(Math.random() * 2))+1); 	//
				if (i > 0) orbs[i].setLeft(orbs[i-1].getLeft() + 50);									   //
			}									   
		}	
	}
	// jump for bottom block
	public static int jump( int yb, int dyb, boolean checkTop, int gx, int xb, OB[] pops){
		if (xb < pops[0].getRight() || xb < pops[8].getRight() || xb < pops[12].getRight()|| xb < pops[2].getRight() || xb < pops[5].getRight()){
			if (Zen.isKeyPressed((char)'/') && (yb == Zen.getZenHeight() -26 || checkTop)) dyb = -17;
			else if (yb < Zen.getZenHeight() - 26 && ! checkTop) dyb += 2;
		}
		else if (yb < Zen.getZenHeight() - 26 && ! checkTop) dyb += 2;
		return dyb;
	}
	// jump for top block
	public static int jumpdown( int yt, int dyt, boolean checkBottom, int gx, int xt, OB[] pops){
		if (xt < pops[0].getRight() || xt < pops[8].getRight() || xt < pops[12].getRight() || xt < pops[2].getRight() || xt < pops[5].getRight() ){
			if (Zen.isKeyPressed((char)'z') && (yt == 0 || checkBottom)) dyt = 17;
			else if (yt > 0 && ! checkBottom) dyt -= 2;
		}
			else if (yt > 0 && ! checkBottom) dyt -= 2;
		return dyt;
	}
	//new y for top block
	public static int newy(int yb, int dyb){
		if(yb+dyb<Zen.getZenHeight() - 26) return yb+dyb;									
		else return Zen.getZenHeight() - 26;
	}
	//new y for bottom block
	public static int newyT(int yb, int dyb){
		if(yb+dyb > 0) return yb+dyb;									
		else return 0;
	}
	//checks for contact with top of obstacles for bottom
	public static boolean checkTop(OB[] pops, int xb, int dyb, int yb, int i){
		if (i > pops.length -1) return false;
		if (i < pops.length -1 && (pops[i].getRight() > xb && pops[i].getLeft() < xb + 26) && (pops[i].getTop() <= yb + 26 && pops[i].getTop() + 30 > yb + 26)) return true;
		return checkTop(pops,xb,dyb,yb,i+1);
	}
	//checks for contact with top of obstacles for top
	public static boolean checkTopT(OB[] orbs, int xt, int dyt, int yt, int i){
		if (i > orbs.length -1) return false;
		if (i < orbs.length -1 && (orbs[i].getRight() > xt && orbs[i].getLeft() < xt + 26) && (orbs[i].getTop() + 30 >= yt && orbs[i].getTop() < yt)) return true;
		return checkTopT(orbs,xt,dyt,yt,i+1);
	}
	//pushes both blocks
	public static int pushT(int xb, int yb, OB pop){
		if (xb + 26 >= pop.getLeft() && xb < pop.getRight() && (yb > pop.getY() && yb < pop.getTop() + 30|| (yb + 26 > pop.getY() && yb+26 < pop.getTop() + 30)))                     
			xb = xb + pop.getDX();
		return xb;
	}
	// updates click
	public static int[] updateClick(long oldClickTime) {
		int[] temp={Zen.getMouseClickX(),Zen.getMouseClickY()};
		int[] temp2={0,0};
		if(Zen.getMouseClickTime()!=oldClickTime)
			return temp;
		return temp2;
	}
	//updates old click value
	public static long updateOldClickTime() {
		return Zen.getMouseClickTime();
	}
	public static void start(){
		int xt = Zen.getZenWidth()/2-13;   		//sets top block in the middle of screen: also used to push 
		int xb = Zen.getZenWidth()/2-13; 	   // sets bottom block in middle: also used to push
		int yt = 0;						 	  // top block y
		int yb = Zen.getZenHeight() - 26;	 // bottom block y
		int dyt = 0;						// top block dy 
		int dyb = 0;					   // bottom block dy
		int gx = 0;						  // little center guide block dx
		int s1x = Zen.getZenWidth() - 150;  // position of score1: used to move   	  
		int s2x = 16;					   //  position of score2: used to move
		int score1 = 0;					  //value of scores
		int score2 = 0;					 //
		boolean checkT = false;			// check if top block can jump
		boolean checkB = false;		   // check if bottom block can jump
		int[] click={0,0};			  // position of clicks
		long oldClickTime=0;		 //	click time
		boolean state = false;		// allow for the ready screen then the game
		boolean ready1 = false;	   // true when player 1 clicks ready
		boolean ready2 = false;	  // true when payer 2 clicks ready
		boolean outB = false;	 // allows the blocks to come back into play
		boolean outT = false;   //
		boolean colorCoin = true;   // allows for the color changing coin to change colors and still disappear on contact
		boolean colorCoinT = true; //
		int colorHold = 0; // holds value of previous color for color changing block

		// sets up the obstacle arrays and the first obstacle is always on the ground
		OB[] orbs = new OB[21]; 
		orbs[0] = new OB(1,0);
		OB[] pops = new OB[21]; 
		pops[0] = new OB(2,0);
		OB coin = new OB();
		OB coin2 = new OB();
		OB coin3 = new OB();
		OB coin4 = new OB();

		OB coinT = new OB();
		OB coinT2 = new OB();
		OB coinT3 = new OB();
		OB coinT4 = new OB();

		// assigns position of obstacles
		for(int i = 0; i < orbs.length - 1; i ++){															   //Sets
			if (i != 0 && orbs[i-1].po != 0) orbs[i] = new OB(1,(int)(Math.random() * 4));			          // up
			else if  (i != 0 && orbs[i-1].po == 0) orbs[i] = new OB(1,((int)(Math.random() * 2))+1); 		 //  top 
			if (i > 0) orbs[i].setLeft(orbs[i-1].getLeft() + 50);										    //   obstacles
		}

		for(int i = 0; i < pops.length - 1; i ++){															   //Sets
			if (i != 0 && pops[i-1].po != 0) pops[i] = new OB(2,(int)(Math.random() * 4));			          // up
			else if  (i != 0 && pops[i-1].po == 0) pops[i] = new OB(2,((int)(Math.random() * 2))+1); 		 //  bottom 
			if (i > 0) pops[i].setLeft(pops[i-1].getLeft() + 50);											//
		}


		//ready screen
		while (Zen.isRunning() && ! state){
		draw(xt,xb,yt,yb, gx, score1, score2 , s1x, s2x);
		Zen.setColor(0, 200, 200);
		Zen.fillRect(Zen.getZenWidth()/2 - 50, Zen.getZenHeight() -140, 100, 40);
		Zen.fillOval(Zen.getZenWidth()/2 + 21, Zen.getZenHeight() - 140, 50, 40);
		Zen.fillOval(Zen.getZenWidth()/2 - 74, Zen.getZenHeight() - 140, 50, 40);
		PixelatedText.drawPixelatedText("Ready",Zen.getZenWidth()/2 - 35, Zen.getZenHeight() - 127,3);

		Zen.setColor(200,100,50);
		Zen.fillRect(Zen.getZenWidth()/2 - 50, 100, 100, 40);
		Zen.fillOval(Zen.getZenWidth()/2 + 21, 100, 50, 40);
		Zen.fillOval(Zen.getZenWidth()/2 - 74, 100, 50, 40);
		PixelatedText.drawPixelatedText("Ready",Zen.getZenWidth()/2 - 35,113,3);
		// when both ready buttons are clicked the game begins
		if(Zen.getMouseClickX() >= Zen.getZenWidth()/2 - 75 && Zen.getMouseClickX() <= Zen.getZenWidth()/2 + 71 && Zen.getMouseClickY() >= 100 && Zen.getMouseClickY() <= 140) { ready1 = true;}                       
		if(Zen.getMouseClickX() >= Zen.getZenWidth()/2 - 75 && Zen.getMouseClickX() <= Zen.getZenWidth()/2 + 71 && Zen.getMouseClickY() >= Zen.getZenHeight() - 140 && Zen.getMouseClickY() <= Zen.getZenHeight() - 100) { ready2 = true;}

		if (ready1) { Zen.setColor(0,200,200);  Zen.fillRect(Zen.getZenWidth()/2 - 100, 100, 200, 40);} //covers up the ready button when clicked on
		if (ready2){Zen.setColor(200,100,50);  Zen.fillRect(Zen.getZenWidth()/2 - 100, Zen.getZenHeight() - 140, 200, 40);}  //covers up the ready button when clicked on                     
		if (ready1 && ready2) state = true;

		Zen.sleep(30);			
		Zen.flipBuffer();
		}
		// main part of the game
		while(Zen.isRunning() && state) { 
			click = updateClick(oldClickTime);
			oldClickTime=updateOldClickTime();
			if (score2 <= 0) score2 = 0;
			if (score1 <= 0) score1 = 0;
			draw(xt,xb,yt,yb, gx, score1, score2 , s1x, s2x);   // draws everything

			//draws coins 
			coin.drawcoin(pops[3]);
			coin2.drawcoin(pops[12]);
			coin3.drawcoin(pops[7]);
			coin4.drawcoin(pops[18]);

			coinT.drawcoinT(orbs[3]);
			coinT2.drawcoinT(orbs[12]);
			coinT3.drawcoinT(orbs[7]);
			coinT4.drawcoinT(orbs[18]);

			if (colorCoin) coin2.setCoinColor((int)(((Math.random() *2) +1)));
			if (colorCoinT) coinT2.setCoinColor((int)(((Math.random() *2) +1)));
			// allows the coins to be replaced by points upon contact and then reappear next cycle
			if(((coin.getRight() > xb && coin.getRight() < xb + 26)|| (coin.getLeft() > xb && coin.getLeft() < xb + 26)) && ((coin.getTop() > yb && coin.getTop() < yb + 26) || (coin.getTop() + 12  > yb && coin.getTop() + 12 < yb + 26))) {coin.setCoinColor(0); score1 += 20;}
			if (coin.getRight() <= 20 && pops[3].getRight() <= 15) coin.setCoinColor(1);   
			if (coin.getCoinColor() == 0 && pops[3].getX() > xb - 100) {PixelatedText.drawPixelatedText("20",pops[3].getX()+5,pops[3].getY() - 20,2);}

			if(((coin2.getRight() > xb && coin2.getRight() < xb + 26)|| (coin2.getLeft() > xb && coin2.getLeft() < xb + 26)) && ((coin2.getTop() > yb && coin2.getTop() < yb + 26) || (coin2.getTop() + 12  > yb && coin2.getTop() + 12 < yb + 26))) {colorHold = coin2.getCoinColor();colorCoin = false;coin2.setCoinColor(0); if (colorHold == 1) score1 += 100; if(colorHold == 2) score1 -= 50;}
			if (coin2.getRight() <= 20 && pops[12].getRight() <= 15) colorCoin = true;   
			if (coin2.getCoinColor() == 0 && colorHold == 1 && pops[12].getX() > xb - 100) {PixelatedText.drawPixelatedText("100",pops[12].getX()+2,pops[12].getY() - 20,2);}
			if (coin2.getCoinColor() == 0 && colorHold == 2 && pops[12].getX() > xb - 100) {PixelatedText.drawPixelatedText("-50",pops[12].getX(),pops[12].getY() - 20,2);}

			if(((coin3.getRight() > xb && coin3.getRight() < xb + 26)|| (coin3.getLeft() > xb && coin3.getLeft() < xb + 26)) && ((coin3.getTop() > yb && coin3.getTop() < yb + 26) || (coin3.getTop() + 12  > yb && coin3.getTop() + 12 < yb + 26))) {coin3.setCoinColor(0); score1 += 20;}
			if (coin3.getRight() <= 20 && pops[7].getRight() <= 15) coin3.setCoinColor(1);   
			if (coin3.getCoinColor() == 0 && pops[7].getX() > xb - 100) {PixelatedText.drawPixelatedText("20",pops[7].getX()+5,pops[7].getY() - 20,2);}

			if(((coin4.getRight() > xb && coin4.getRight() < xb + 26)|| (coin4.getLeft() > xb && coin4.getLeft() < xb + 26)) && ((coin4.getTop() > yb && coin4.getTop() < yb + 26) || (coin4.getTop() + 12  > yb && coin4.getTop() + 12 < yb + 26))) {coin4.setCoinColor(0); score1 += 20;}
			if (coin4.getRight() <= 20 && pops[18].getRight() <= 15) coin4.setCoinColor(1);   
			if (coin4.getCoinColor() == 0 && pops[18].getX() > xb - 100) {PixelatedText.drawPixelatedText("20",pops[18].getX()+5,pops[18].getY() - 20,2);}



			if(((coinT.getRight() > xt && coinT.getRight() < xt + 26)|| (coinT.getLeft() > xt && coinT.getLeft() < xt + 26)) && ((coinT.getTop() > yt && coinT.getTop() < yt + 26) || (coinT.getTop() + 12  > yt && coinT.getTop() + 12 < yt + 26))) {coinT.setCoinColor(0); score2 += 20;}
			if (coinT.getRight() <= 20 && orbs[3].getRight() <= 15) coinT.setCoinColor(1);   
			if (coinT.getCoinColor() == 0 && orbs[3].getX() > xt - 100) {PixelatedText.drawPixelatedText("20",orbs[3].getX() +5,orbs[3].getY() +35,2);}

			if(((coinT2.getRight() > xt && coinT2.getRight() < xt + 26)|| (coinT2.getLeft() > xt && coinT2.getLeft() < xt + 26)) && ((coinT2.getTop() > yt && coinT2.getTop() < yt + 26) || (coinT2.getTop() + 12  > yt && coinT2.getTop() + 12 < yt + 26))) {colorHold = coinT2.getCoinColor();colorCoinT = false;coinT2.setCoinColor(0); if (colorHold == 1) score2 += 100; if(colorHold == 2) score2 -= 50;}
			if (coinT2.getRight() <= 20 && orbs[12].getRight() <= 15) colorCoinT = true;   
			if (coinT2.getCoinColor() == 0 && colorHold == 1 && orbs[12].getX() > xt - 100) {PixelatedText.drawPixelatedText("100",orbs[12].getX()+2,orbs[12].getY() + 35,2);}
			if (coinT2.getCoinColor() == 0 && colorHold == 2 && orbs[12].getX() > xt - 100) {PixelatedText.drawPixelatedText("-50",orbs[12].getX(),orbs[12].getY() + 35,2);}

			if(((coinT3.getRight() > xt && coinT3.getRight() < xt + 26)|| (coinT3.getLeft() > xt && coinT3.getLeft() < xt + 26)) && ((coinT3.getTop() > yt && coinT3.getTop() < yt + 26) || (coinT3.getTop() + 12  > yt && coinT3.getTop() + 12 < yt + 26))) {coinT3.setCoinColor(0); score2 += 20;}
			if (coinT3.getRight() <= 20 && orbs[7].getRight() <= 15) coinT3.setCoinColor(1);   
			if (coinT3.getCoinColor() == 0 && orbs[7].getX() > xt - 100) {PixelatedText.drawPixelatedText("20",orbs[7].getX()+5,orbs[7].getY() +35,2);}

			if(((coinT4.getRight() > xt && coinT4.getRight() < xt + 26)|| (coinT4.getLeft() > xt && coinT4.getLeft() < xt + 26)) && ((coinT4.getTop() > yt && coinT4.getTop() < yt + 26) || (coinT4.getTop() + 12  > yt && coinT4.getTop() + 12 < yt + 26))) {coinT4.setCoinColor(0); score2 += 20;}
			if (coinT4.getRight() <= 20 && orbs[18].getRight() <= 15) coinT4.setCoinColor(1);   
			if (coinT4.getCoinColor() == 0 && orbs[18].getX() > xt - 100) {PixelatedText.drawPixelatedText("20",orbs[18].getX()+5,orbs[18].getY() + 35,2);}






			if (gx > Zen.getZenWidth()/4) {							//
				for(int i = 0; i < orbs.length - 1; i ++){			//
					orbs[i].dx = -5;								//
					pops[i].dx = -5;								//
				}													//
			}														//
			if (gx > Zen.getZenWidth()/2) {							//
				for(int i = 0; i < orbs.length - 1; i ++){			// changes speed of obstacles depending
					orbs[i].dx = -6;								// on position of center block
					pops[i].dx = -6;								//
				}													//
			}														//
			if (gx > (Zen.getZenWidth()/4)*3) {						//
				for(int i = 0; i < orbs.length - 1; i ++){			//
					orbs[i].dx = -7;								//
					pops[i].dx = -7;								//
				}													//
			}														//


			checkT = checkTop(pops,xb,dyb, yb,0);     //check to see if the blocks are
			checkB = checkTopT(orbs,xt,dyt,yt,0);    // standing on an obstacle or the ground
			if (checkT) dyb = 0;					//
			if (checkB) dyt = 0;				   // downward velocity is stopped if on an obstacle or the ground

			dyb = jump(yb,dyb, checkT, gx,xb,pops);		// checks if the bottom block can jump
			dyt = jumpdown(yt,dyt,checkB,gx,xt,pops);  // checks if the top block can jump
			yt = newyT(yt,dyt);					// new y
			yb = newy(yb,dyb);				   // new y
			if (gx + 11 != Zen.getZenWidth())gx += 1; // moves the center block
			for(int i = 0; i < pops.length - 1; i ++){
				orbs[i].draw();     //
				orbs[i].newX();		// draws and moves obstacles
				pops[i].draw();		//
				pops[i].newX();		//
				xb = pushT(xb,yb,pops[i]);		// pushes blocks
				xt = pushT(xt,yt,orbs[i]);		//


				// these make sure the blocks are always above the obstacles
				if ((pops[i].getRight() > xb && pops[i].getLeft() < xb + 26) && (pops[i].getTop() < yb + 26 && pops[i].getTop() + 30 > yb + 26)){
					yb  = pops[i].getTop() - 26;	
				}
				if ((orbs[i].getRight() > xt && orbs[i].getLeft() < xt + 26) && (orbs[i].getTop() + 30 > yt && orbs[i].getTop() < yt)){
					yt  = orbs[i].getTop() + 30;	
				}

				// make sure the blocks fall after they hit the bottom of obstacles
				if ((pops[i].getRight() > xb && pops[i].getLeft() < xb + 26) && (pops[i].getTop() + 30 > yb && pops[i].getTop() < yb)){
					yb = pops[i].getTop() + 30;
					dyb = 0;
				}
				if ((orbs[i].getRight() > xt && orbs[i].getLeft() < xt + 26) && (orbs[i].getTop() < yt + 26 && orbs[i].getTop() + 30 > yt + 26)){
					yt = orbs[i].getTop() - 26;
					dyt = 0;			
				}


				// makes sure the blocks are always above the ground
				if (yb <= Zen.getZenHeight()/2){ yb = Zen.getZenHeight()/2; dyb =0;}
				if (yt + 26 >= Zen.getZenHeight()/2) {yt = Zen.getZenHeight()/2 - 26; dyt =0;}
			}
			// builds score and resets the obstacles while the center block is still going
			if (gx + 11 < Zen.getZenWidth()){
				checkOrbs(orbs);
				checkOrbs1(pops);
				score1 ++;
				score2 ++;
			}
			// makes sure the center block stops at the end
			if (gx + 11 == Zen.getZenWidth()) gx = Zen.getZenWidth() - 11;


			// when the blocks go off the screen the players loose points
			if ( xb + 20 < 0) {outB = true; score1 -= 50;}
			if ( xt + 20 < 0) {outT = true; score2 -= 50;}
			//gets the blocks back into play
			if (outB) {
				yb = Zen.getZenHeight()/2 + 4;
				xb += 5;
				dyb = 0;
				score1 --;
			}
			if (outT) {
				yt =Zen.getZenHeight()/2 - 28;
				xt += 5;
				dyt = 0;
				score2 --;
			}
			//blocks from re-entry when all of the blocks are off of the screen at the end
			if (xb > pops[5].getRight() && gx + 11 == Zen.getZenWidth() && yb < Zen.getZenHeight() - 26){outB = false;}
			if (xt > pops[5].getRight() && gx + 11 == Zen.getZenWidth() && yt < Zen.getZenHeight() - 26){outT = false;}
			//when the blocks reach the middle on re-entry they fall back into play
			if (xb >= Zen.getZenWidth()/2-20) outB = false;
			if (xt >= Zen.getZenWidth()/2-20) outT = false;
			// at the end the blocks move to the middle and loose points for however far they are behind the middle
			if (xb > pops[5].getRight() && gx + 11 == Zen.getZenWidth() && xb < Zen.getZenWidth()/2 - 13 && yb == Zen.getZenHeight() - 26)  {xb += 4; score1 --;}
			if (xt > pops[5].getRight() && gx + 11 == Zen.getZenWidth() && xt < Zen.getZenWidth()/2 - 13 && yt == 0 )  {xt += 4; score2 --;}

			// moves the scores to the middle after the last obstacle is off screen
			if (0 > pops[2].getRight() && s1x > Zen.getZenWidth()/2 - 60)  {s1x -= 6;}
			if (0 > pops[2].getRight() && s2x < Zen.getZenWidth()/2 - 64)  {s2x += 6;}



			//draws the winners as well as the replay and menu buttons. Also makes them clickable
			if (xb >= Zen.getZenWidth()/2 - 13 && xt >= Zen.getZenWidth()/2 - 13 && score2 > score1 && s1x <= Zen.getZenWidth()/2 - 60 && s2x >= Zen.getZenWidth()/2 - 64 && score1!=score2) {
				Zen.setColor(0,0,0);
				PixelatedText.drawPixelatedText("Player 1 Wins!",Zen.getZenWidth() /2 - 200,120, 6); 
				Zen.setColor(0, 200, 200);
				Zen.fillRect(Zen.getZenWidth()/2 - 200, Zen.getZenHeight() - 140, 100, 40);
				Zen.fillRect(Zen.getZenWidth()/2 + 100, Zen.getZenHeight() - 140, 100, 40);
				Zen.fillOval(Zen.getZenWidth()/2 + 171, Zen.getZenHeight() - 140, 50, 40);
				Zen.fillOval(Zen.getZenWidth()/2 + 76, Zen.getZenHeight() - 140, 50, 40);
				Zen.fillOval(Zen.getZenWidth()/2 - 222, Zen.getZenHeight() - 140, 50, 40);
				Zen.fillOval(Zen.getZenWidth()/2 - 129, Zen.getZenHeight() - 140, 50, 40);
				PixelatedText.drawPixelatedText("Replay",Zen.getZenWidth()/2 - 194,Zen.getZenHeight() - 127,3);
				PixelatedText.drawPixelatedText("Menu",Zen.getZenWidth()/2 + 115,Zen.getZenHeight() - 127,3);
				if(click[0] >= Zen.getZenWidth()/2 -220 && click[0] <= Zen.getZenWidth()/2 - 80 && click[1] >= Zen.getZenHeight() - 140 && click[1] <= Zen.getZenHeight() - 100) {start();}
				if(click[0] >= Zen.getZenWidth()/2 + 75 && click[0] <= Zen.getZenWidth()/2 + 222 && click[1] >= Zen.getZenHeight() - 140 && click[1] <= Zen.getZenHeight() - 100) {Interface.start();}

			}
			if (xb >= Zen.getZenWidth()/2 - 13 && xt >= Zen.getZenWidth()/2 - 13 && score2 < score1 && s1x <= Zen.getZenWidth()/2 - 60 && s2x >= Zen.getZenWidth()/2 - 64) {
				Zen.setColor(0,0,0);
				PixelatedText.drawPixelatedText("Player 2 Wins!",Zen.getZenWidth() /2 - 200,Zen.getZenHeight() - 120,6); 
				Zen.setColor(200,100,50);
				Zen.fillRect(Zen.getZenWidth()/2 - 200, 100, 100, 40);
				Zen.fillRect(Zen.getZenWidth()/2 + 100, 100, 100, 40);
				Zen.fillOval(Zen.getZenWidth()/2 + 171, 100, 50, 40);
				Zen.fillOval(Zen.getZenWidth()/2 + 76, 100, 50, 40);
				Zen.fillOval(Zen.getZenWidth()/2 - 222, 100, 50, 40);
				Zen.fillOval(Zen.getZenWidth()/2 - 129, 100, 50, 40);
				PixelatedText.drawPixelatedText("Replay",Zen.getZenWidth()/2 - 194,113,3);
				PixelatedText.drawPixelatedText("Menu",Zen.getZenWidth()/2 + 115,113,3);
				if(click[0] >= Zen.getZenWidth()/2 - 220 &&click[0] <= Zen.getZenWidth()/2 - 80 &&  click[1] >= 100 && click[1] <= 140) { start();}
				if(click[0] >= Zen.getZenWidth()/2 + 75 &&click[0] <= Zen.getZenWidth()/2 + 222 &&  click[1] >= 100 && click[1] <= 140) { Interface.start();}
				//
			}
			if (xb >= Zen.getZenWidth()/2 - 13 && xt >= Zen.getZenWidth()/2 - 13 && score2 == score1 && s1x <= Zen.getZenWidth()/2 - 60 && s2x >= Zen.getZenWidth()/2 - 64) {
				Zen.setColor(0,0,0);
				PixelatedText.drawPixelatedText("It is a Tie!",350,120,6); 
				PixelatedText.drawPixelatedText("It is a Tie!",350,Zen.getZenHeight() - 160,6); 
				Zen.setColor(0, 200, 200);
				Zen.fillRect(Zen.getZenWidth()/2 - 50, Zen.getZenHeight() - 80, 100, 40);
				Zen.fillOval(Zen.getZenWidth()/2 + 21, Zen.getZenHeight() - 80, 50, 40);
				Zen.fillOval(Zen.getZenWidth()/2 - 74, Zen.getZenHeight() - 80, 50, 40);
				PixelatedText.drawPixelatedText("Menu",Zen.getZenWidth()/2 - 35,Zen.getZenHeight() - 68,3);
				if(click[0] >= Zen.getZenWidth()/2 - 75 &&click[0] <= Zen.getZenWidth()/2 + 71 && click[1] >= Zen.getZenHeight() - 80 && click[1] <= Zen.getZenHeight() - 40) { Interface.start();}                       


				Zen.setColor(200,100,50);
				Zen.fillRect(Zen.getZenWidth()/2 - 50, 40, 100, 40);
				Zen.fillOval(Zen.getZenWidth()/2 + 21, 40, 50, 40);
				Zen.fillOval(Zen.getZenWidth()/2 - 74, 40, 50, 40);
				PixelatedText.drawPixelatedText("Replay",Zen.getZenWidth()/2 - 42,53,3);
				if(click[0] >= Zen.getZenWidth()/2 - 75 && click[0] <= Zen.getZenWidth()/2 + 71 && click[1] >= 40 && click[1] <= 80) { Interface.start();}                       

			}

			Zen.sleep(30);			
			Zen.flipBuffer();
		}
	}




}