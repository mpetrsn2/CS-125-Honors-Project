public class HighScoreList {
	// The "Highscores file" stores the high scores in a text file to
	// permanently save them

	private static String[] names = new String[5];
	private static String[] scores = new String[5];

	// Reads the highscores from the saved file
	public static void setHighScores() {
		TextIO.readFile("Highscores file");
		int lineNum = 1;
		while (lineNum < 11) {
			String line = TextIO.getln();
			if (lineNum <= 5)
				names[lineNum - 1] = line;
			else
				scores[lineNum - 6] = line;
			lineNum++;
		}
		
	}

	// Saves the highscores and difficulty to the saved file
	public static void saveHighScores() {
		TextIO.writeFile("Highscores file");
		for (int index = 0; index < 10; index++) {
			if (index < 5)
				TextIO.put(names[index] + "\n");
			else
				TextIO.put(scores[index - 5] + "\n");
		}
		TextIO.put(Settings.index + "\n");
		TextIO.put(Settings.level);
		// Shuts down the Java VM according to TextIO
		// This shuts down the main program as well
		// Make a button to close app?
		// System.exit(0);
	}

	// Main method that is called to run
	public static void highscoreList() {
		while (Zen.isRunning()) {

			Zen.setColor(200, 100, 50);
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());

			PixelatedText.drawPixelatedText("Highscore List", 300, 20, 6);

			// Names here
			PixelatedText.drawPixelatedText("1. " + names[0], 104, 100, 4);
			PixelatedText.drawPixelatedText("2. " + names[1], 100, 130, 4);
			PixelatedText.drawPixelatedText("3. " + names[2], 100, 160, 4);
			PixelatedText.drawPixelatedText("4. " + names[3], 100, 190, 4);
			PixelatedText.drawPixelatedText("5. " + names[4], 100, 220, 4);

			// Scores here
			PixelatedText.drawPixelatedText(scores[0], 600, 100, 4);
			PixelatedText.drawPixelatedText(scores[1], 600, 130, 4);
			PixelatedText.drawPixelatedText(scores[2], 600, 160, 4);
			PixelatedText.drawPixelatedText(scores[3], 600, 190, 4);
			PixelatedText.drawPixelatedText(scores[4], 600, 220, 4);

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

	// public method to check if this score is a high score
	// use this inside the main runner class
	public static boolean isHighScore(int score) {
		return Integer.parseInt(scores[4]) < score;
	}

	// private method to find index of this score
	private static int getHighScoreIndex(int score) {
		for (int index = 0; index < 5; index++) {
			if (score > Integer.parseInt(scores[index]))
				return index;
		}
		return 6;
	}

	// use this inside the main runner class
	public static void setHighScore(String name, String score) {
		int s = Integer.parseInt(score);
		if (isHighScore(s)) {
			String[] tempScore = new String[5];
			String[] tempName = new String[5];
			int index = getHighScoreIndex(s);
			for (int i = 0; i < 5; i++) {
				if (i < index) {
					tempScore[i] = scores[i];
					tempName[i] = names[i];
				} else if (i > index) {
					tempScore[i] = scores[i - 1];
					tempName[i] = names[i - 1];
				} else {
					tempScore[i] = score;
					tempName[i] = name;
				}
			}
			scores = tempScore;
			names = tempName;
		}
		saveHighScores();
	}

	public static void clearHighScores() {
		for (int index = 0; index < 5; index++) {
			scores[index] = "0";
			names[index] = "None";
		}
		saveHighScores();
	}
}