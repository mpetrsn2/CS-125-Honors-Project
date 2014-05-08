public class GameOverHighScore {
	public static String name = "";
	public static boolean start = true;

	public static void setName(int score) {
		while (Zen.isRunning()) {

			Zen.setColor(200, 100, 50);
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());

			PixelatedText.drawPixelatedText("Game Over", 365, 20, 6);

			// Make the high score pop up with text
			Zen.setColor(255, 255, 255);
			Zen.fillRect(247, 97, 503, 3);
			Zen.fillRect(247, 300, 506, 3);
			Zen.fillRect(247, 97, 3, 203);
			Zen.fillRect(750, 97, 3, 203);
			Zen.setColor(200, 100, 50);
			Zen.fillRect(250, 100, 500, 200);
			Zen.setColor(0, 200, 200);
			//Zen.fillRect(375, 303, 250, 17);

			PixelatedText.drawPixelatedText("New highscore!", 370, 120, 4);
			PixelatedText.drawPixelatedText("Enter your name below", 290, 160,
					4);
			/*Zen.setColor(0, 200, 200);
			Zen.fillRect(375, 380, 250, 60);
			PixelatedText.drawPixelatedText("Menu", 460, 400, 4);*/

			// While the enter key is not pressed
			// Get the input that is typed in
			// Zen.setEditText("");

			name = Zen.getEditText();

			if(start) {
				Zen.setEditText("");
				PixelatedText
				.drawPixelatedText("                                                    ", 290, 220, 4);
				start = false;
			}

			if (Zen.isVirtualKeyPressed(92)) {
				// Make it end here
				// End at press enter key
				// Is 13 even the enter key??? 
				// I don't think it is
				// I could not make this work with the enter key
				// so you have to press the '\' key to finish enter
				// the name for the highscore list
				name = name.substring(0,name.length() - 1);
				HighScoreList.setHighScore(name, Integer.toString(score));
				GameOver.endGame(0);
			}

			PixelatedText.drawPixelatedText(name, 290, 220, 4);
			// Make it output what is being typed in
			if (Zen.isVirtualKeyPressed(8)) {
				// Backspace
				if(name.length() > 2) {
				PixelatedText
						.drawPixelatedText(name.substring(0, name.length() - 2)
								+ " ", 290, 220, 4);
				} else if(name.length() == 2) {
					PixelatedText
					.drawPixelatedText(name.substring(0, 1)
							+ " ", 290, 220, 4);
				} else if(name.length() == 1) {
					PixelatedText
					.drawPixelatedText(name.substring(0, 0)
							+ " ", 290, 220, 4);
				} else
					;
			}

			// Testing name is right
			// PixelatedText.drawPixelatedText(name, 0, 0, 4);

			Zen.sleep(45);
			Zen.flipBuffer();
		}

	}
}