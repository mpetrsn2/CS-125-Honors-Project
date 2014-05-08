public class SettingsClear {

	public static void clear() {
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
			Zen.drawLine(315, 90, 690, 90);
			Zen.drawLine(315, 89, 690, 89);
			Zen.drawLine(315, 88, 690, 88);
			Zen.drawLine(315, 290, 690, 290);
			Zen.drawLine(315, 291, 690, 291);
			Zen.drawLine(315, 292, 690, 292);
			Zen.drawLine(315, 88, 315, 292);
			Zen.drawLine(314, 88, 314, 292);
			Zen.drawLine(313, 88, 313, 292);
			Zen.drawLine(690, 88, 690, 292);
			Zen.drawLine(691, 88, 691, 292);
			Zen.drawLine(692, 88, 692, 292);
			Zen.setColor(0, 0, 255);
			Zen.fillRect(316, 230, 188, 60);
			Zen.setColor(255, 0, 0);
			Zen.fillRect(503, 230, 187, 60);
			PixelatedText.drawPixelatedText("Clear highscore", 360, 120, 4);
			PixelatedText.drawPixelatedText("list", 470, 160, 4);
			PixelatedText.drawPixelatedText("No", 390, 250, 4);
			PixelatedText.drawPixelatedText("Yes", 570, 250, 4);

			if (Zen.getMouseClickX() >= 503 && Zen.getMouseClickX() <= 690
					&& Zen.getMouseClickY() >= 230
					&& Zen.getMouseClickY() <= 290) {
				// yes
				HighScoreList.clearHighScores();
				//Settings.settings();
				return;
			}
			if (Zen.getMouseClickX() >= 316 && Zen.getMouseClickX() <= 502
					&& Zen.getMouseClickY() >= 230
					&& Zen.getMouseClickY() <= 290) {
				// no
				//Settings.settings();
				return;
			}

			Zen.sleep(45);
		}

	}
}