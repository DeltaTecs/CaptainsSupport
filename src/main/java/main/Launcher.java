package main;

import io.IO;
import io.SoundManager;
import ui.ControlWindow;
import ui.CrashHandler;

public class Launcher {

	public static final String VERSION = "2.0.0 alpha:REWARD-REDEEM-EVENT-TEST";
	public static final String LATEST_CHANGES = "Raid alert, follow alert, reward alert";

	public static void main(String[] args) {

		try {

			// Launch UI
			ControlWindow.create();

			// Deploy Twitch IO
			TwitchConnector connector = new TwitchConnector();
			connector.registerFeatures();

		} catch (Exception e) {
			// display Error and exit
			e.printStackTrace();
			CrashHandler.popup(e);
			System.exit(1);
		}


	}
}
