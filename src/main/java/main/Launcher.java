package main;

import ui.ControlWindow;
import ui.CrashHandler;

public class Launcher {

	public static final String VERSION = "2.0 alpha";
	public static final String LATEST_CHANGES = "nix dega";

	public static void main(String[] args) {

		try {
			ControlWindow.create();
		} catch (Exception e) {
			e.printStackTrace();
			CrashHandler.popup(e);
			System.exit(1);
		}

		/*
		Bot bot = new Bot();
		bot.registerFeatures();
		bot.start();
		 */

	}
}
