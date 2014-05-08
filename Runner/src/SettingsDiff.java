public class SettingsDiff {
	public static void set() {
		while (Zen.isRunning()) {
			Zen.flipBuffer();

			Zen.setColor(200, 100, 50);
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());

			PixelatedText.drawPixelatedText("Settings", 390, 20, 6);

			PixelatedText.drawPixelatedText("Difficulty: " + Settings.difficulties[Settings.index],
					100, 100, 4);
			PixelatedText
			.drawPixelatedText("Clear highscore list", 100, 150, 4);
			PixelatedText
			.drawPixelatedText("Clear highscore list", 100, 150, 4);
			PixelatedText.drawPixelatedText("Set start level:" + Integer.toString(Settings.level), 100, 200, 4);

			Zen.setColor(0, 200, 200);
			Zen.fillRect(700, 270, 200, 60);

			PixelatedText.drawPixelatedText("Back", 762, 290, 4);

			Zen.setColor(0, 200, 200);
			Zen.fillRect(315, 90, 375, 200);
			Zen.setColor(255, 255, 255);
			Zen.drawLine(315,90,690,90);
			Zen.drawLine(315,89,690,89);
			Zen.drawLine(315,88,690,88);
			Zen.drawLine(315,290,690,290);
			Zen.drawLine(315,291,690,291);
			Zen.drawLine(315,292,690,292);
			Zen.drawLine(315,88,315,292);
			Zen.drawLine(314,88,314,292);
			Zen.drawLine(313,88,313,292);
			Zen.drawLine(690,88,690,292);
			Zen.drawLine(691,88,691,292);
			Zen.drawLine(692,88,692,292);
			PixelatedText.drawPixelatedText("Easy", 350, 120, 4);
			PixelatedText.drawPixelatedText("Medium", 350, 180, 4);
			PixelatedText.drawPixelatedText("Hard", 350, 240, 4);
			Zen.fillRect(630, 112, 5, 35);
			Zen.fillRect(660, 112, 5, 35);
			Zen.fillRect(630, 112, 35, 5);
			Zen.fillRect(630, 142, 35, 5);
			Zen.fillRect(630, 172, 5, 35);
			Zen.fillRect(630, 172, 35, 5);
			Zen.fillRect(630, 202, 35, 5);
			Zen.fillRect(660, 172, 5, 35);
			Zen.fillRect(630, 232, 5, 35);
			Zen.fillRect(630, 232, 35, 5);
			Zen.fillRect(630, 262, 35, 5);
			Zen.fillRect(660, 232, 5, 35);
			Zen.setColor(200, 100, 50);
			if(Settings.index == 0) {
				Zen.fillRect(635, 117, 25, 25);
			} else if(Settings.index == 1) {
				Zen.fillRect(635, 177, 25, 25);
			} else {
				Zen.fillRect(635, 237, 25, 25);
			}
			if (Zen.getMouseClickX() >= 315 && Zen.getMouseClickX() <= 690
					&& Zen.getMouseClickY() >= 110
					&& Zen.getMouseClickY() <= 150) {
				// change to easy
				//PixelatedText.drawPixelatedText("Button" + Integer.toString(Settings.index) + "works",0,0,4);
				Settings.index = 0;
				HighScoreList.saveHighScores();
				//Settings.settings();
				return;
			} else if (Zen.getMouseClickX() >= 315 && Zen.getMouseClickX() <= 690
					&& Zen.getMouseClickY() >= 170
					&& Zen.getMouseClickY() <= 210) {
				// change to medium
				//PixelatedText.drawPixelatedText("Button" + Integer.toString(Settings.index) + "works",0,0,4);
				Settings.index = 1;
				HighScoreList.saveHighScores();
				//Settings.settings();
				return;
			} else if (Zen.getMouseClickX() >= 315 && Zen.getMouseClickX() <= 690
					&& Zen.getMouseClickY() >= 230
					&& Zen.getMouseClickY() <= 270) {
				// change to hard
				//PixelatedText.drawPixelatedText("Button" + Integer.toString(Settings.index) + "works",0,0,4);
				Settings.index = 2;
				HighScoreList.saveHighScores();
				//Settings.settings();
				return;
			} 

			Zen.sleep(45);
		}

	}
}