package io.serializeables;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Settings implements Serializable {

    private static final long serialVersionUID = 1001L;

    public static HashMap<Field, String> descriptors = new HashMap<>();

    // Put all value descriptions into a map, so could be displayed in the UI
    static {

        try {
            descriptors.put(Settings.class.getDeclaredField("tts_cooldown"), "globaler Cooldown für TTS Nachrichten in Sekunden");
            descriptors.put(Settings.class.getDeclaredField("tts_limit"), "TTS Zeichenlimit bei minimalem Level (startwert). Nutzerlimit = lvl * zeichen_pro_level + startwert");
            descriptors.put(Settings.class.getDeclaredField("tutorial_cooldown"), "Cooldown für Tutorial nachricht in Dekunden");
            descriptors.put(Settings.class.getDeclaredField("mastervolume"), "");
            descriptors.put(Settings.class.getDeclaredField("volume"), "Sound Lautstärke in Prozent digga");
            descriptors.put(Settings.class.getDeclaredField("lurk_reward_amount"), "CC pro legitem !lurk");
            descriptors.put(Settings.class.getDeclaredField("lurk_reward_cooldown"), "Sekunden bis !lurk wieder CC und XP bringt");
            descriptors.put(Settings.class.getDeclaredField("cc_initial"), "CC eines neuen Chatters");
            descriptors.put(Settings.class.getDeclaredField("cc_cost_slots"), "CC Preis für einmal normale Slots drehen (1 roll)");
            descriptors.put(Settings.class.getDeclaredField("cc_cost_slotsx"), "CC Preis für einmal X Slots drehen (1 roll)");
            descriptors.put(Settings.class.getDeclaredField("cc_slots_max_in"), "Maximaler slot input bei LVL 1");
            descriptors.put(Settings.class.getDeclaredField("cc_cost_tts"), "Preis für TTS");
            descriptors.put(Settings.class.getDeclaredField("cc_per_chat"), "CC gewinn pro Chatnachricht");
            descriptors.put(Settings.class.getDeclaredField("cc_return_slots_basic"), "Slot gewinn normal");
            descriptors.put(Settings.class.getDeclaredField("cc_return_slots_peach"), "Slot gewinn Superjackpot");
            descriptors.put(Settings.class.getDeclaredField("cc_return_slots_golden"), "Slot gewinn Goldener Jackpot");
            descriptors.put(Settings.class.getDeclaredField("cc_sound_cost_multiplier"), "Standard Multiplikator für Soundpreise");
            descriptors.put(Settings.class.getDeclaredField("cc_anthem_cost_multiplier"), "Standard Multiplikator für Hymnen");
            descriptors.put(Settings.class.getDeclaredField("msg_welcome"), "Nachricht an neue Chatter");
            descriptors.put(Settings.class.getDeclaredField("msg_lurk"), "Nachricht an !lurk");
            descriptors.put(Settings.class.getDeclaredField("death_cnt_prefix"), "Deathcounter Text (MUSS %d enthalten!)");
            descriptors.put(Settings.class.getDeclaredField("rand_golden"), "Wahrscheinlichkeit für Goldjackpot. Höher ist unwahrscheinlicher");
            descriptors.put(Settings.class.getDeclaredField("golden_emote"), "Goldener Emote selber aussehen");
            descriptors.put(Settings.class.getDeclaredField("gold_status_duration"), "Dauer des Goldstatus (special slot machine)");
            descriptors.put(Settings.class.getDeclaredField("compact_jackpots"), "Slots Gewinne zusammenfassen?");
            descriptors.put(Settings.class.getDeclaredField("slot_rolls_delay"), "Verzögerung zwischen Slot ausgaben (also den Feldern die reinrollen)");
            descriptors.put(Settings.class.getDeclaredField("max_lvl_reward"), "Maximales Level mit freischaltungen und geld (Keine Levelcap, nur Belohnungs maximum)");
            descriptors.put(Settings.class.getDeclaredField("xp_factor_subscriber"), "XP Multiplikator für VIPs und Subscriber");
            descriptors.put(Settings.class.getDeclaredField("xp_base"), "xp für nächstes lvl ist = xp_für_letztes_level + xp_added_per_lvl. xp für lvl 2 ist xp_base");
            descriptors.put(Settings.class.getDeclaredField("xp_added_per_lvl"), "xp für nächstes lvl ist = xp_für_letztes_level + xp_added_per_lvl. xp für lvl 2 ist xp_base");
            descriptors.put(Settings.class.getDeclaredField("xp_per_char"), "XP pro chat zeichen");
            descriptors.put(Settings.class.getDeclaredField("xp_per_captain_emote"), "XP pro captain emote");
            descriptors.put(Settings.class.getDeclaredField("xp_per_slot_cmd"), "XP pro !slots");
            descriptors.put(Settings.class.getDeclaredField("xp_per_slot_win"), "XP pro slots gewinnen");
            descriptors.put(Settings.class.getDeclaredField("xp_per_reward"), "XP pro Reward einlösen");
            descriptors.put(Settings.class.getDeclaredField("xp_per_lurk"), "XP pro legitem !lurk");
            descriptors.put(Settings.class.getDeclaredField("xp_per_pirate_fight"), "XP pro Piraten battle");
            descriptors.put(Settings.class.getDeclaredField("xp_on_pirate_win"), "XP wenn gegen Piraten gewonnen");
            descriptors.put(Settings.class.getDeclaredField("min_lvl_sound"), "Mindest lvl für !sound");
            descriptors.put(Settings.class.getDeclaredField("min_lvl_slotsx"), "Mindest lvl für !slotsx");
            descriptors.put(Settings.class.getDeclaredField("min_lvl_anthems"), "Mindest lvl für !anthem");
            descriptors.put(Settings.class.getDeclaredField("max_lvl_anthems"), "Höchstes lvl der anthemfreischaltungen");
            descriptors.put(Settings.class.getDeclaredField("min_lvl_golden_chance_1"), "lvl für erste golden jackpot erhöhung");
            descriptors.put(Settings.class.getDeclaredField("min_lvl_golden_chance_2"), "lvl für zweite golden jackpot erhöhung");
            descriptors.put(Settings.class.getDeclaredField("min_lvl_tts"), "Mindest lvl für !tts");
            descriptors.put(Settings.class.getDeclaredField("max_lvl_tts_scaling"), "Maximales lvl der tts buchstaben freischaltung");
            descriptors.put(Settings.class.getDeclaredField("slot_rolls_per_lvl"), "");
            descriptors.put(Settings.class.getDeclaredField("max_lvl_slot_scaling"), "");
            descriptors.put(Settings.class.getDeclaredField("slot_rolls_added_last_lvls"), "");
            descriptors.put(Settings.class.getDeclaredField("lvl_per_emblem"), "");
            descriptors.put(Settings.class.getDeclaredField("cc_per_lvl"), "");
            descriptors.put(Settings.class.getDeclaredField("tts_chars_per_lvl"), "");
            descriptors.put(Settings.class.getDeclaredField("min_delay_broadcast"), "");
            descriptors.put(Settings.class.getDeclaredField("max_delay_broadcast"), "");
            descriptors.put(Settings.class.getDeclaredField("pirate_loss_factor"), "");
            descriptors.put(Settings.class.getDeclaredField("pirate_min_win"), "");
            descriptors.put(Settings.class.getDeclaredField("pirate_max_win"), "");
            descriptors.put(Settings.class.getDeclaredField("msg_so"), "");
            descriptors.put(Settings.class.getDeclaredField("link_discord"), "");
            descriptors.put(Settings.class.getDeclaredField("link_reddit"), "");
            descriptors.put(Settings.class.getDeclaredField("brause_emote"), "");
            descriptors.put(Settings.class.getDeclaredField("juwlz_referal"), "");
            descriptors.put(Settings.class.getDeclaredField("enable_slots_bill"), "");


        } catch (NoSuchFieldException e) {
            System.err.println("Oh shit, failed to init description fields. Some Field name was not found...");
            throw new RuntimeException(e);
        }

    }

    public int tts_cooldown = 60; // seconds
    public int tts_limit = 60; // symbols
    public int tutorial_cooldown = 300; // seconds
    public int mastervolume = 80; // percent
    public HashMap<String, Integer> volume = new HashMap<>();
    public int lurk_reward_amount = 60; // coins
    public int lurk_reward_cooldown = 60 * 60; // seconds
    public int cc_initial = 100; // coins
    public int cc_cost_slots = 5; // coins
    public int cc_cost_slotsx = 8; // coins
    public int cc_slots_max_in = 10; // coins
    public int cc_cost_tts = 80; // coins
    public int cc_per_chat = 1; // coins
    public int cc_return_slots_basic = 250; // seconds
    public int cc_return_slots_peach = 1000; // seconds
    public int cc_return_slots_golden = 10000; // seconds
    public int cc_sound_cost_multiplier = 10; // seconds
    public int cc_anthem_cost_multiplier = 100; // seconds
    public String msg_welcome = "Ahoy, Matey! ⛵ Welcome aboard %s"; // format text
    public String msg_lurk = "Thank you for boarding the ship %s ⛵ Lay back and enjoy your drink <3"; // format text
    public String death_cnt_prefix = "DEATHS: "; // text
    public int rand_golden = 71; // prop
    public String golden_emote = "captai1955Golden"; // seconds
    public long gold_status_duration = 1000 * 60 * 60 * 24 * 30; // ms
    public boolean compact_jackpots = true; // on/off
    public int slot_rolls_delay = 600; // ms
    public int max_lvl_reward = 100; // seconds
    public float xp_factor_subscriber = 1.5f; // factor
    public int xp_base = 500; // xp
    public int xp_added_per_lvl = 50; // xp
    public float xp_per_char = 0.5f; // xp
    public float xp_per_captain_emote = 2; // xp
    public int xp_per_slot_cmd = 5; // xp
    public int xp_per_slot_win = 10; // xp
    public int xp_per_reward = 50; // xp
    public int xp_per_lurk = 200; // xp
    public int xp_per_pirate_fight = 400; // xp
    public int xp_on_pirate_win = 100; // xp
    public int min_lvl_sound = 2; // level
    public int min_lvl_slotsx = 20; // level
    public int min_lvl_anthems = 50; // level
    public int max_lvl_anthems = 90; // level
    public int min_lvl_golden_chance_1 = 91; // level
    public int min_lvl_golden_chance_2 = 100; // level
    public int min_lvl_tts = 10; // level
    public int max_lvl_tts_scaling = min_lvl_anthems; // level
    public int slot_rolls_per_lvl = 2; // rolls
    public int max_lvl_slot_scaling = 89; // level
    public int slot_rolls_added_last_lvls = 20; // rolls
    public int lvl_per_emblem = 5; // level
    public int cc_per_lvl = 300; // coins
    public int tts_chars_per_lvl = 5; // symbols
    public int min_delay_broadcast = 10; // minutes
    public int max_delay_broadcast = 30; // minutes
    public float pirate_loss_factor = 0.5f; // level
    public int pirate_min_win = 100; // coins
    public int pirate_max_win = 1000; // coins
    public String msg_so = "Make sure to check out %s"; // format text
    public String link_discord = "https://discord.gg/X5KGBJGTPu"; // text
    public String link_reddit = "https://www.reddit.com/r/captaincasimir/"; // text
    public String brause_emote = "\uD83E\uDDC3"; // text
    public String juwlz_referal = "\uD83D\uDDA4 Check out JulwzDblack! \uD83D\uDDA4 https://www.twitch.tv/juwlzdblack"; // text
    public boolean enable_slots_bill = true; // on/off



    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Settings();
    }
}
