package io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SoundManager {

private static final Logger LOGGER = LogManager.getLogger(SoundManager.class.getName());

    public static final String FORMAT = ".wav";

    public static final String SOUNDS_DIR = "sounds";

    private static List<Clip> running = new ArrayList<>();

    /**
     * Plays a sound, adds .wav to the end of the name and plays the file in the sounds directory
     * @param sound
     */
    public static void play(String sound) {

        int masterVolume = IO.settings.mastervolume;
        int soundVolume = 100;
        if (IO.settings.volume.containsKey(sound + FORMAT))
            soundVolume = IO.settings.volume.get(sound + FORMAT);

        float volume = (masterVolume / 100.0f) * (soundVolume / 100.0f);
        play(sound, volume);
    }

    public static void play(String sound, String userId) {
        throw new RuntimeException("Nicht implementiert");

    }

    /**
     * Plays sound with specified linear volume multiplicator
     * @param sound
     * @param volume
     */
    private static void play(String sound, float volume) {

        File file = new File(SOUNDS_DIR + File.separator + sound + FORMAT);

        try {
            // create new stream for each file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
            FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            float volumeClipped = Math.min(Math.max(20.0f * (float) Math.log10(volume), gainControl.getMinimum()), gainControl.getMaximum());
            gainControl.setValue(volumeClipped);
            LOGGER.debug("Playing: " + sound + " at gain " + volumeClipped + "dB");
            audioClip.addLineListener(getRemoveListingListener(audioClip));
            audioClip.start();

            // Add clip to list of running clips
            synchronized (running) {
                running.add(audioClip);
            }

        } catch (Exception e) {
            LOGGER.error("Error when playing audio: ", e);
            throw new RuntimeException("Sound konnt nicht abgespielt werden: " + file.getAbsolutePath());
        }
    }

    public static void stopAll() {
        synchronized (running) {
            for (Clip c : running) {
                c.stop();
            }
            running.clear();
        }
    }

    private static LineListener getRemoveListingListener(final Clip clip) {
        return ev -> {
            if (LineEvent.Type.STOP == ev.getType()) {
                LOGGER.debug("sound finished playing");
                synchronized (running) {
                    running.remove(clip);
                }
            }
        };
    }

    /**
     * Returns all available soundfiles
     * @return
     */
    public static File[] getAllSounds() {
        return new File(SoundManager.SOUNDS_DIR).listFiles();
    }

    /**
     * Returns a displayable name for a soundfile
     * @param f
     * @return
     */
    public static String getSoundName(File f) {
        final String name = f.getName().replace(SoundManager.FORMAT, "");
        return name;
    }



}
