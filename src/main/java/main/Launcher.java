package main;

import io.IO;
import io.SoundManager;
import io.update.UpdateChecker;
import ui.ControlWindow;
import ui.CrashHandler;

public class Launcher {

	public static final String VERSION = "v2.0.1-alpha";
	public static final String LATEST_CHANGES = "Raid alert, follow alert, reward alert, auto update";

	public static void main(String[] args) {

		try {

			UpdateChecker.check();

			/*
			// Launch UI
			ControlWindow.create();

			// Deploy Twitch IO
			TwitchConnector connector = new TwitchConnector();
			connector.registerFeatures();
			*/


		} catch (Exception e) {
			// display Error and exit
			e.printStackTrace();
			CrashHandler.popup(e);
			System.exit(1);
		}


	}
}
