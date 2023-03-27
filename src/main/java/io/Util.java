package io;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Util {

    private static final DateFormat FORMAT_DETAILED = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private Util(){}


    public static String getDate(long unix) {
        Date date = new Date(unix);
        return FORMAT_DETAILED.format(date);
    }
}
