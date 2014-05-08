public class GameOver {
	public static boolean isClick = true;

	public static void endGame(int score) {
		while (Zen.isRunning()) {
			// Put this line in for the command that happens
			// when the menu button is pressed in game:
			// GameOver.endGame((int)highScore);

			// Put this line in the last pixeledtest
			// else 
			//   index = 0;

			HighScoreList.setHighScores();

			if(HighScoreList.isHighScore(score)) {
				GameOverHighScore.setName(score);
			}

			Zen.setColor(200, 100, 50);
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());

			PixelatedText.drawPixelatedText("Game Over", 365, 20, 6);


			// Play again and menu button
			Zen.setColor(0, 200, 200);
			Zen.fillRect(375, 140, 250, 60);
			Zen.fillRect(375, 380, 250, 60);
			PixelatedText.drawPixelatedText("Play Again", 405, 160, 4);
			PixelatedText.drawPixelatedText("Menu", 460, 400, 4);
			
			Settings.findInfo();

			if (Zen.getMouseClickX() >= 375 && Zen.getMouseClickX() <= 625
					&& Zen.getMouseClickY() >= 140
					&& Zen.getMouseClickY() <= 200) {
				// Start game
				RunnerGame4.gameStart(Settings.index,1/*Settings.leve*/);
			}
			if (Zen.getMouseClickX() >= 375 && Zen.getMouseClickX() <= 625
					&& Zen.getMouseClickY() >= 380
					&& Zen.getMouseClickY() <= 440) {
				// Go to menu
				Interface.start();
			}

			Zen.sleep(45);
			Zen.flipBuffer();
		}
	}
	
	/* // Testing purposes
	  
	 public static void main(String[] args) {
		GameOver.endGame(1);
	}
*/
	 

}