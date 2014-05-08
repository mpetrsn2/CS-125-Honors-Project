public class Credits {

	public static void displayCredits() {
		while (Zen.isRunning()) {

			Zen.setColor(200, 100, 50);
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());

			PixelatedText.drawPixelatedText("Credits", 400, 20, 6);
			
			PixelatedText.drawPixelatedText("Runner Game was created by", 100, 100, 4);
			PixelatedText.drawPixelatedText("Austin", 150, 130, 4);
			PixelatedText.drawPixelatedText("Mark", 150, 160, 4);
			PixelatedText.drawPixelatedText("Shunping", 150, 190, 4);
			
			Zen.setColor(0, 200, 200);
			// Back button design
			Zen.fillRect(700, 270, 200, 60);
			
			PixelatedText.drawPixelatedText("Back", 762, 290, 4);
			
			if(Zen.getMouseClickX() >= 700 && Zen.getMouseClickX() <= 900 && Zen.getMouseClickY() >= 270 && Zen.getMouseClickY() <= 330) {
				// Goto the interface menu
				//Interface.start();
				return;
			}
			
			Zen.sleep(45);
			Zen.flipBuffer();
		}
	}
	/**
	 * this was just to test the file by itself
	public static void main(String[] args) {
		Credits.displayCredits();
	}
	*/
}
