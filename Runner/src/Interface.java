/*
 * Changes that I made:
 * Start game calls RunnerGame4 with index as difficulty parameter
 * put in click and oldClickTIme variable like in runnergame class
 * (maybe make a mouse class that handles all of this instead of rewriting it every)
 */
public class Interface {
	
	public static void start() {
		
		int[] click={0,0};
		long oldClickTime=0;//System.currentTimeMillis();
		
		while(Zen.isRunning()) {
			HighScoreList.setHighScores();

			Zen.setColor(200, 100, 50);
			// Zen.getZenWidth() = 1000
			// Zen.getZenHeight() = 400
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());
			// Ignore this, just for measurement to center things: 27.5
			PixelatedText.drawPixelatedText("Block Runner",332,20,6);
			
			Zen.setColor(0, 200, 200);
			// Start game button design
			Zen.fillRect(180, 130, 640, 60);
			Zen.fillOval(150, 130, 60, 60);
			Zen.fillOval(790, 130, 60, 60);
			
			// Credit button design
			Zen.fillRect(180, 210, 200, 60);
			Zen.fillOval(150, 210, 60, 60);
			
			// Settings button design
			Zen.fillRect(400, 210, 200, 60);
			
			// Highscore list button design
			Zen.fillRect(620, 210, 200, 60);
			Zen.fillOval(790, 210, 60, 60);
			
			//2P Game button design
			Zen.fillRect(180,290,640,60);
			Zen.fillOval(150,290,60,60);
			Zen.fillOval(790,290,60,60);
			
			PixelatedText.drawPixelatedText("Start Game",405,150,4);
			PixelatedText.drawPixelatedText("Credits",210,230,4);
			PixelatedText.drawPixelatedText("Settings",425,230,4);
			PixelatedText.drawPixelatedText("Highscores",633,230,4);
			PixelatedText.drawPixelatedText("2P Game",430,310,4);
			
			click=RunnerGame4.updateClick(oldClickTime);
			
			if(click[0] >= 165 && click[0] <= 835 && click[1] >= 130 && click[1] <= 190) {
				// Goto the game menu
				//PixelatedText.drawPixelatedText("Game works",0,0,4);
				RunnerGame4.gameStart(Settings.index,1/*Settings.leve*/);
			}
			else if(click[0] >= 165 && click[0] <= 380 && click[1] >= 210 && click[1] <= 270) {
				// Goto the credits menu
				// PixelatedText.drawPixelatedText("Credits works",0,0,4);
				// The above was to test the buttons
				Credits.displayCredits();
			}
			else if(click[0] >= 400 && click[0] <= 600 && click[1] >= 210 && click[1] <= 270) {
				// Goto the settings menu
				// PixelatedText.drawPixelatedText("Settings works",0,0,4);
				// The above was to test the buttons
				Settings.settings();
			}
			else if(click[0] >= 620 && click[0] <= 835 && click[1] >= 210 && click[1] <= 270) {
				// Goto the highscore menu
				// PixelatedText.drawPixelatedText("Highscores works",0,0,4);
				// The above was to test the buttons
				HighScoreList.highscoreList();
			}
			else if(click[0]>=165 && click[0]<=835 && click[1]>=290 && click[1]<=350) {
				// Goto 2P Game
				// PixelatedText.drawPixelatedText("2P Game works",0,0,4);
				two_player_mode.start();
			}
			
			oldClickTime=RunnerGame4.updateOldClickTime();
			
			Zen.sleep(45);
			Zen.flipBuffer();
		}
	}
	
	public static void main(String[] args) {
		Interface.start();
	}
}
