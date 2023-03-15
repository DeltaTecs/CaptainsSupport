package io;

import javax.swing.*;
import java.io.File;

public final class Images {

    private static final String DIR = "images";

    private static final ImageIcon PLACE_HOLDER = new ImageIcon();

    public static ImageIcon play = PLACE_HOLDER;
    public static ImageIcon stop = PLACE_HOLDER;


    private Images() {}

    public static void loadAll() {

        play = new ImageIcon(DIR + File.separator + "play.png");
        stop = new ImageIcon(DIR + File.separator + "stop.png");

    }


}
