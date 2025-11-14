public class palpal {
    private static final int CHAR_DELAY_MS = 30;
    private static final int LINE_PAUSE_MS = 300;

    private static final String[] COLORS = {
        "\u001B[31m",
        "\u001B[32m",
        "\u001B[33m",
        "\u001B[34m",
        "\u001B[35m",
        "\u001B[36m",
        "\u001B[91m",
        "\u001B[92m",
        "\u001B[93m",
        "\u001B[94m",
        "\u001B[95m",
        "\u001B[96m"
    };

    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {

        String[] times = {
            "00:17.71","00:20.17","00:22.10","00:24.75","00:26.84","00:29.25",
            "00:31.68","00:33.87","00:36.14","00:38.28","00:40.28","00:44.89",
            "00:49.54","00:54.24","00:58.78","01:03.56","01:08.02","01:12.87",
            "01:15.73","01:18.07","01:22.41","01:25.08","01:27.24","01:31.37",
            "01:33.36","01:35.11","01:37.99","01:40.13","01:42.12","01:44.67",
            "01:46.93","01:49.06","01:51.96","01:54.47","01:56.78","01:58.36",
            "02:00.47","02:03.05","02:05.52","02:08.46","02:12.68","02:17.36",
            "02:21.88","02:26.88","02:30.93","02:35.59","02:40.60","02:46.71",
            "02:48.93","02:51.31","02:53.61","02:55.96","02:58.01","03:00.29","03:02.69"
        };

        String[] lyrics = {
            "Pal pal jeena muhal",
            "Mera tere bina",
            "Ye sary nashe bekaar",
            "Teri ankhon kay siwa",
            "Ghar nahi jata mein bahar",
            "Rehta tera intazar",
            "Mere, khwabon mein aa na",
            "Kakry sola singhaar",
            "Mein ab kyu hosh mein ata ni",
            "Sukoon ye dil kyu pata ni",
            "Kyu toru khud say jo thy wade kay ab ye ishq nibahana nahi",
            "Mein moru tumsy jo ye chehra dubara nazar milana nahi",
            "Ye duniya janay mera dard tujhe ye nazar kyu ata nahi",
            "Soneya yu tera sharmana meri jaan na lele",
            "Kaan ke peechy zulf chupana meri jan kya kehnay",
            "Zalima toba tera nakhra iske war kya kehne",
            "Tham ky bethe dil ko ghayal kaheen har na bethein",
            "Teri nazrien mujhsy kya kehti hen",
            "Inme wafa bethi hay",
            "Thori thori si razi thori si khafa rehti hein",
            "Log hein zalim bary",
            "Inme jafa dekhi hay",
            "Ye duniya teri nahi mene tunjmy haya dekhi hay",
            "Jeena muhal",
            "Mera tere bina",
            "Ye sary nashe bekaar",
            "Teri ankhon kay siwa",
            "Ghar nahi jata mein bahar",
            "Rehta tera intazar",
            "Mere, khwabon mein aa na",
            "Kakry sola singhaar",
            "Tera mera afsana",
            "Pura huya na jana",
            "Hua band kamre which kalla",
            "fir bhi dis da ye pareshwa",
            "Saare puch de ne menue",
            "Keyon main gallan nain kardan",
            "Kinnay sohne ne chehre ethe",
            "Dil kyun naeee ay bharda",
            "Je naa deedaar hoya",
            "Ohna chirr din naiyo charhda",
            "Dil karay dildaariyan",
            "Bina gallan saareyaan naa larrda",
            "Jidaan rang milde ne odha he aapan mil jaana",
            "Jidaan phull khilde ne odha he donaa khill jaana",
            "Jidaan rang milde ne odha he aapan mil jaana",
            "Jidaan phull khilde ne odha he donaa khill jaana",
            "Tera mera afsana",
            "Pal pal jeena muhaal",
            "Poora hoya naa jaana",
            "Saaray nashay bekaar",
            "Hovaan band kamray vich kalla",
            "Ghar nahi jaata, mein bahar",
            "Kinnay sohne ne chehre ethe",
            "Kar ke solah singhaar"
        };

        if (times.length != lyrics.length) {
            System.err.println("Timestamps and lyric lines count mismatch!");
            return;
        }

        long[] offsetsMs = new long[times.length];
        for (int i = 0; i < times.length; i++) {
            offsetsMs[i] = parseTimestampToMs(times[i]);
        }

        long start = System.currentTimeMillis();
        System.out.println("Starting karaoke...\n");

        for (int i = 0; i < offsetsMs.length; i++) {
            long target = start + offsetsMs[i];
            long now = System.currentTimeMillis();
            long wait = target - now;

            if (wait > 0) {
                try { Thread.sleep(wait); }
                catch (InterruptedException e) { return; }
            }

            String color = COLORS[i % COLORS.length];
            typewriterPrint(color + lyrics[i] + RESET);

            try { Thread.sleep(LINE_PAUSE_MS); }
            catch (InterruptedException e) { return; }
        }

        System.out.println("\nEnd of song");
    }

    private static void typewriterPrint(String line) {
        for (int j = 0; j < line.length(); j++) {
            System.out.print(line.charAt(j));
            System.out.flush();
            try { Thread.sleep(CHAR_DELAY_MS); }
            catch (InterruptedException e) { return; }
        }
        System.out.println();
    }

    private static long parseTimestampToMs(String t) {
        try {
            String[] parts = t.split(":");
            int minutes = Integer.parseInt(parts[0]);
            String[] secParts = parts[1].split("\\.");
            int seconds = Integer.parseInt(secParts[0]);

            int centi = 0;
            if (secParts.length > 1) {
                String frac = secParts[1];
                if (frac.length() == 1) frac += "0";
                if (frac.length() > 2) frac = frac.substring(0, 2);
                centi = Integer.parseInt(frac);
            }

            return (minutes * 60000L) + (seconds * 1000L) + (centi * 10L);

        } catch (Exception e) {
            return 0;
        }
    }
}
