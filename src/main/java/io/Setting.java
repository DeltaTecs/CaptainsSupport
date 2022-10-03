package io;

import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * Handles settings access by loading and writing to disk
 */
public final class Setting {


    public static final String TTS_COOLDOWN = "tts_cooldown";


    private static final HashMap<String, Object> values = new HashMap<>();
    private static final HashMap<String, Object> types = new HashMap<>();
    private static final HashMap<String, Object> defaults = new HashMap<>();

    private Setting() {}

    private static final void loadFromFile() {

        Object ob = new JSONParser().parse(new FileReader("JSONFile.json"));


    }

    private static final void applyDefaults() {

    }







}
