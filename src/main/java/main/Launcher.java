package main;

import io.IO;
import ui.ControlWindow;
import ui.CrashHandler;

public class Launcher {

	public static final String VERSION = "2.0 alpha:REWARD-REDEEM-EVENT-TEST";
	public static final String LATEST_CHANGES = "alles";

	public static void main(String[] args) {

		IO.saveRewards();

		try {
			ControlWindow.create();
		} catch (Exception e) {
			e.printStackTrace();
			CrashHandler.popup(e);
			System.exit(1);
		}

		TwitchConnector connector = new TwitchConnector();
		connector.registerFeatures();
		connector.connect();

	}
}
