package io.update;

import main.Launcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class UpdateChecker {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Will query github for new releases, returns null if none available
     * @return
     */
    public static final Update check() {

        LOGGER.info("Querying Git for updated release");

        List<String> tags;

        try {
            // query GitHub repository releases page
            tags = getTags("DeltaTecs", "CaptainsSupport");
        } catch (IOException e) {
            throw new RuntimeException("Updated failed (internetz ciao?)", e);
        }

        tags.sort((Comparator<String>) Comparator.naturalOrder().reversed());

        String latest = tags.get(0);

        if (!latest.equalsIgnoreCase(Launcher.VERSION) && Launcher.VERSION.compareTo(latest) < 0) {
            LOGGER.info("Update found: " + latest + " supersedes " + Launcher.VERSION);

            return new Update(latest, "https://github.com/DeltaTecs/CaptainsSupport/releases/download/" + latest + "/build.zip");
        } else {
            LOGGER.info("No Update found. Latest Version found is " + latest + ", local version is " + Launcher.VERSION);
            return null;
        }
    }



    private static List<String> getTags(final String gitUser, final String gitRepo) throws IOException {

        List<String> result = new ArrayList<>();

        final String url = "https://github.com/" + gitUser + "/" + gitRepo + "/tags";
        Document doc = Jsoup.connect(url).get();
        Elements tags = doc.getElementsByClass("f4 d-inline");
        tags.forEach(e -> {
            result.add("v" + e.toString().split("releases/tag/v")[1].split("\" data-view-component=")[0]);
        });
        return result;
    }


}
