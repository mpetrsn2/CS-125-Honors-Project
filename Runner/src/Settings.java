public class Settings {
	public final static String[] difficulties = { "Easy", "Medium", "Hard" };
	public static int index;
	public static int level;
	// index is the difficulty corresponding to the array above

	public static void settings() {
		while (Zen.isRunning()) {
			findInfo();
			// There is an error here that 
			// I cannot recreate

			Zen.setColor(200, 100, 50);
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());

			PixelatedText.drawPixelatedText("Settings", 390, 20, 6);

			PixelatedText.drawPixelatedText("Difficulty: " + difficulties[index],
					100, 100, 4);
			PixelatedText
			.drawPixelatedText("Clear highscore list", 100, 150, 4);
			PixelatedText.drawPixelatedText("Set start level:" + Integer.toString(level), 100, 200, 4);

			/**
			 * Testing where the button was 
			 * Zen.setColor(0, 200, 200);
			 * Zen.fillRect(93, 93, 200, 40);
			 * 
			 * Zen.setColor(0, 200, 200);
			 * Zen.fillRect(93, 140, 400, 40);
			 * 
			 * Zen.setColor(0, 200, 200);
			 * Zen.fillRect(93, 189, 300, 40);
			 */


			if (Zen.getMouseClickX() >= 93 && Zen.getMouseClickX() <= 298
					&& Zen.getMouseClickY() >= 93
					&& Zen.getMouseClickY() <= 133) {
				// Pop up difficulty menu
				SettingsDiff.set();
			}
			if (Zen.getMouseClickX() >= 93 && Zen.getMouseClickX() <= 493
					&& Zen.getMouseClickY() >= 140
					&& Zen.getMouseClickY() <= 180) {
				// Pop up clear high scores warning
				SettingsClear.clear();
			}
			if (Zen.getMouseClickX() >= 93 && Zen.getMouseClickX() <= 393
					&& Zen.getMouseClickY() >= 189
					&& Zen.getMouseClickY() <= 229) {
				// Pop up clear high scores warning
				SettingsLevel.setLevel();
			}

			Zen.setColor(0, 200, 200);
			// Back button design
			Zen.fillRect(700, 270, 200, 60);

			PixelatedText.drawPixelatedText("Back", 762, 290, 4);

			if (Zen.getMouseClickX() >= 700 && Zen.getMouseClickX() <= 900
					&& Zen.getMouseClickY() >= 270
					&& Zen.getMouseClickY() <= 330) {
				// Goto the interface menu
				//Interface.start();
				return;
			}

			Zen.sleep(45);
			Zen.flipBuffer();
		}
	}

	// Finds the difficulty and start level from the saved file
	public static void findInfo() {
		TextIO.readFile("Highscores file");
		int lineNum = 1;
		while (lineNum < 13) {
			String line = TextIO.getln();
			if(lineNum == 11)
				index = Integer.parseInt(line);
			if(lineNum == 12)
				level = Integer.parseInt(line);
			lineNum++;
		}
	}

}