package io;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public final class Util {

    private static final DateFormat FORMAT_DETAILED = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static final Random RANDOM = new Random(System.currentTimeMillis());

    private Util(){}


    public static String getDate(long unix) {
        Date date = new Date(unix);
        return FORMAT_DETAILED.format(date);
    }

    /**
     * returns true one in bound times
     * @param bound
     * @return
     */
    public static boolean oneInX(int bound) {
        return RANDOM.nextInt(bound) == 0;
    }

    public static boolean fiftyfifty() {
        return RANDOM.nextBoolean();
    }


}
