package main;

import java.util.Random;

public class Constants {

    public static Random RANDOM = new Random(System.currentTimeMillis());


    public static String[] RANDOM_FACTS = ("Der tiefste Ort der Erde ist der Marianengraben im Pazifischen Ozean. Er ist 11.034 m (36.201 Fuß) tief. Das sind fast 7 Meilen!XX-XX\n" +
            "Der längste Fluss der Welt ist der Nil, mit einer Länge von 6.853 km. Seine Wasserressourcen werden von 11 verschiedenen Ländern geteilt.XX-XX\n" +
            "Hummer sind nicht “biologisch unsterblich”, aber sie produzieren ein Enzym, das ihre Zellen repariert und ihrer DNA hilft, sich unbegrenzt zu replizieren. Daher rührt der Mythos.XX-XX\n" +
            "Der tiefste Süßwassersee der Welt ist der Baikalsee, der in Sibirien liegt. Er fällt bis zu einer Tiefe von 1.620 m (5.315 Fuß) ab. Wahnsinn!XX-XX\n" +
            "Ananas brauchen zwei Jahre, um zu wachsen.XX-XX\n" +
            "Akazienbäume in Afrika kommunizieren miteinander. Sie stoßen Gase aus, um andere Bäume zu warnen, damit diese rechtzeitig das Gift Tannin produzieren können, welches sie vor hungrigen Tieren schützt.XX-XX\n" +
            "Gürteltiere sind kugelsicher. (Dies ist KEINE Aufforderung, diese Tatsache zu testen.)XX-XX\n" +
            "Die Niagarafälle gefrieren nie.XX-XX\n" +
            "Jeder Kalkstein/Granitblock, aus dem die große Pyramide von Gizeh besteht, wiegt 2,5 Tonnen. Und es gibt 2,3 Millionen davon. Ja, du hast richtig gelesen.XX-XX\n" +
            "Du bräuchtest ungefähr 18 Monate, um die gesamte Chinesische Mauer entlangzulaufen. (Sie ist über 5.000 Meilen lang).XX-XX\n" +
            "Die Nationalflagge mit den meisten Farben ist Belize (1981), mit 12 Farben.XX-XX\n" +
            "Der erste Handyanruf wurde am 3. April 1973 in NYC getätigt.XX-XX\n" +
            "Buzz Aldrin (der zweite Mensch, der 1969 den Mond betrat) hat sich anscheinend beim Betreten der Oberfläche bepinkelt.XX-XX\n" +
            "Im alten Ägypten wurde das Wort für “Katze”, “mew” oder “miau” ausgesprochen.XX-XX\n" +
            "Die amerikanische Revolution (1765-1783) fand vor der französischen Revolution (1789-1799) statt.XX-XX\n" +
            "Der Anglo-Sansibar-Krieg (1896) war der kürzeste Krieg aller Zeiten – er dauerte nur 38 Minuten!XX-XX\n" +
            "Die Druckerpresse, die die Weitergabe von Informationen revolutionierte, wurde von Gutenberg um das Jahr 1440 erfunden.XX-XX\n" +
            "Das größte zusammenhängende Landreich der Geschichte ist das Mongolenreich (13. & 14. Jahrhundert).XX-XX\n" +
            "Ägypten wird als das älteste Land der Welt eingestuft und geht auf das Jahr 3100 v. Chr. zurück.XX-XX\n" +
            "Tim Berners-Lee schuf 1990 den allerersten Webbrowser (World Wide Web).XX-XX\n" +
            "Im Jahr 2019 entdeckten Wissenschaftler das älteste bekannte Kunstwerk der Welt auf der indonesischen Insel Sulawesi. Es wurde vor 44.000 Jahren geschaffen.XX-XX\n" +
            "Der Name “Sandwich” stammt von einem Aristokraten aus dem 18. Jahrhundert, dem 4. Earl of Sandwich.XX-XX\n" +
            "9310 Tweets werden jede Sekunde verschickt.XX-XX\n" +
            "“Salvator Mundi” von Leonardo da Vinci ist das teuerste Gemälde der Welt und hat einen Wert von 450,3 Mio. Dollar.XX-XX\n" +
            "Das meistbesuchte Land der Welt, mit 90M Besuchern, ist, laut Zahlen der UNWTO um Jahr 2018, Frankreich. Wer ist auf Platz 2? Spanien.XX-XX\n" +
            "92% der weltweiten Währung ist digital.XX-XX\n" +
            "“Avengers: Endgame” ist der umsatzstärkste Film aller Zeiten und hat über 2,7 Mrd. Us-Dollar eingespielt!XX-XX\n" +
            "Das reichste Unternehmen der Welt im Jahr 2020 war der Erdölriese Saudi Aramco.XX-XX\n" +
            "Die “Statue of Unity” in der chinesischen Provinz Henan ist mit einer Höhe von 182 m die höchste der Welt. Anmerkung: Die Freiheitsstatue hingegen ist lediglich 93 m hoch!XX-XX\n" +
            "Brasilien hat mit mehr als 50.000 Baum- und Pflanzenarten die größte Artenvielfalt aller Länder der Erde.XX-XX\n" +
            "Wissenschaftler sagen, dass man an den Tränen den Grund für das Weinen einer Person erkennen kann. Wenn der erste Tropfen aus dem rechten Auge kommt, sind es Tränen der Freude. Ansonsten sind es Tränen des Schmerzes.XX-XX\n" +
            "Im Jahr 2019 wurde in Großbritannien zum ersten Mal mehr Strom aus erneuerbaren Energien als aus fossilen Brennstoffen erzeugt. Wusstest du außerdem, dass Norwegen 0% Strom aus Kohle bezieht? Und Deutschland in den letzten zehn Jahren pro Person 1 kW an erneuerbarer Leistung installiert hat? (Der Klimawandel ist das größte globale Problem unserer Zeit, aber es gibt einige Dinge, denen man positiv gegenüberstehen kann!)XX-XX\n" +
            "Der am längsten regierende Monarch aller Zeiten war Ludwig XIV. von Frankreich. Er regierte 72 Jahre und 110 Tage lang. Wie anstrengend.XX-XX\n" +
            "Marie Curie war der erste Mensch, der ZWEI Nobelpreise erhielt – einen für Physik im Jahr 1903, den anderen für Chemie im Jahr 1911 für ihre Arbeit über Radioaktivität.XX-XX\n" +
            "König Heinrich VIII. von England hatte Diener, die “Grooms of Stool” (zu deutsch “Stuhlpfleger”) genannt wurden und ihn nach dem Toilettengang sauber wischten. Igitt.XX-XX\n" +
            "0,5 % der männlichen Bevölkerung stammen von Dschingis Khan ab. (Wissenschaftler haben 2003 eine Studie durchgeführt, die zeigt, dass etwa 16 Millionen Kerle ein Y-Chromosom mit dem berühmten Kaiser teilen).XX-XX\n" +
            "Island, die Färöer-Inseln und die Isle of Man erheben alle Anspruch darauf, das älteste Parlament der Geschichte zu haben. Sie alle wurden im 9. und 10. Jahrhundert gegründet.XX-XX\n" +
            "Russland ist mit 815 Millionen Hektar das waldreichste Land der Welt.XX-XX\n" +
            "China ist mit rund 1,4 Milliarden Menschen, das bevölkerungsreichste Land der Welt.XX-XX\n" +
            "Die jüngste Friedensnobelpreisträgerin ist Malala Yousafzai (2014 war sie erst 17 Jahre alt). Ausgezeichnet wurde sie für ihre Arbeit als Kinderrechtsaktivistin und als Verfechterin des Rechts von Mädchen auf Bildung.XX-XX\n" +
            "Sonnenuntergänge gibt es nur, weil die Erdatmosphäre wie ein Prisma für das Licht wirkt. In der Wissenschaft wird dieses Phänomen “Streuung” genannt.XX-XX\n" +
            "Moleküle und Partikel in der Atmosphäre (die bei Sonnenuntergang zahlreicher sind) streuen das kurzwellige violette und blaue Licht von Ihren Augen weg, so dass wir die anderen Farben des Spektrums, wie Gelb und Orange, sehen können.XX-XX\n" +
            "Der abgelegenste Ort der Welt sind die Tristan da Cunha Inseln im südlichen Atlantik. Sie sind 2.434 km von St. Helena, dem nächstgelegenen bewohnten Ort, entfernt. Stell dir vor, deine Mutter schickt dich los, um Lebensmittel einzukaufen, aber der örtliche Supermarkt ist geschlossen? Das ist eine lange Reise.XX-XX\n" +
            "Wenn Sie eine Google-Anfrage stellen, werden 1000 Computer eingesetzt, um die Antwort in 0,2 Sekunden zu finden.XX-XX\n" +
            "Es gibt fast 5 Milliarden Internetnutzer auf der Welt.XX-XX\n" +
            "Das Durchschnittsalter der Weltbevölkerung liegt bei etwa 30 Jahren (Stand 2019).XX-XX\n" +
            "Wir produzieren eigentlich genug Lebensmittel, um alle Menschen auf dem Planeten zu ernähren; das Problem ist die Verteilung.XX-XX\n" +
            "Im Jahr 2010 versuchte Google herauszufinden, wie viele Bücher es auf der Welt gibt. Sie schätzten den Bestand auf 130.000.000. (Nennen wir das mal einen Semi-Fakt, ok?)XX-XX\n" +
            "Das Gebrüll eines Tigers kann man bis zu zwei Meilen weit hören.XX-XX\n" +
            "Die Erde ist 147,2 Millionen Kilometer von der Sonne entfernt, und etwa 4,5 Milliarden Jahre alt. Das ist ein schweres Erbe.XX-XX\n" +
            "Eulen haben keine Augäpfel.XX-XX\n" +
            "Deine Mudda ist fett").split("XX-XX\\n");
}
