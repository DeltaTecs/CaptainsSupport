package main;

import io.IO;

public class Launcher {

	public static final String VERSION = "0.1 Test";
	public static final String LATEST_CHANGES = "noting";


	public static void main(String[] args) {


		System.out.println("-----------------------------------------------------");


		// test stuff
		//IO.saveSettings();
		IO.loadSettings();
		System.out.printf(IO.settings.msg_so, "asdasdasda\n");


		if (true)
			return;

		Bot bot = new Bot();
		bot.registerFeatures();
		bot.start();
	}
}
