public class SettingsLevel {
	public static String level = "";
	public static boolean set = false;

	// Sets the level to start at
	// This is not implemented with
	// Runner game yet
	public static void setLevel() {
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
			PixelatedText.drawPixelatedText("Enter the level", 360, 110, 4);
			PixelatedText.drawPixelatedText("you want to start", 343, 150, 4);
			PixelatedText.drawPixelatedText("at here 1-9:" + level, 375, 190, 4);

			if(level.length() < 2 && !set) {
				level = Zen.getEditText();
				if(level.length() == 1)
					set = true;
			}

			if (Zen.isVirtualKeyPressed(8)) {
				// Backspace
				if(level.length() == 1) {
					Zen.setEditText("");
					PixelatedText.drawPixelatedText(" ", 603, 190, 4);
					set = false;
				}
				else
					PixelatedText.drawPixelatedText(" ", 603, 190, 4);;
			}
			if (Zen.isVirtualKeyPressed(92)) {
				// Sets the level and goes back
				// I could not make this work with the enter key
				// so you have to press the '\' key to finish enter
				level = level.substring(0,1);
				Settings.level = Integer.parseInt(level);
				HighScoreList.saveHighScores();
				//Settings.settings();
				return;
			}

			Zen.sleep(45);
		}

	}

}