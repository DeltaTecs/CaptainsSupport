package main;

import ui.ControlWindow;
import ui.ErrorHandling;

public class Launcher {

	public static final String VERSION = "2.0 alpha";
	public static final String LATEST_CHANGES = "nix dega";

	public static void main(String[] args) {

		try {
			ControlWindow.create();
		} catch (Exception e) {
			System.err.println("oh shit");
			e.printStackTrace();
			ErrorHandling.popup(e);
		}

		/*
		Bot bot = new Bot();
		bot.registerFeatures();
		bot.start();
		 */

	}
}
