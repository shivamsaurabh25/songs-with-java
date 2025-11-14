public class gataOnly {
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
                "00:18.20", "00:21.20", "00:23.60", "00:26.20", "00:29.70", "00:31.80",
                "00:34.10", "00:37.20", "00:39.00", "00:40.50", "00:42.90", "00:45.20",
                "00:48.40", "00:52.60", "00:58.00", "01:00.30", "01:03.00", "01:07.50",
                "01:09.00", "01:11.60", "01:13.90", "01:17.30", "01:19.70", "01:22.10",
                "01:25.00", "01:26.00", "01:28.40", "01:30.80", "01:33.00", "01:36.70",
                "01:38.90", "01:41.40", "01:44.40", "01:47.40", "01:49.90", "01:51.80",
                "01:53.90", "01:57.60", "01:59.70", "02:02.50", "02:04.80", "02:09.10",
                "02:13.50", "02:15.50", "02:18.70", "02:21.10", "02:23.60", "02:25.90",
                "02:29.90", "02:32.30", "02:34.50", "02:41.00", "02:52.70", "02:55.10",
                "02:57.40", "02:59.60", "03:03.40", "03:05.30", "03:07.80", "03:10.90"
        };

        String[] lyrics = {
    "Mami, te siento lejo', dime dónde está'",
    "Te quiero ******r, te voy a raptar",
    "Déjate llevar, apaga el celular",
    "Que si está' conmigo no te va a pasar na'",
    "**** déjate llevar",
    "Nena, atenta a la jugá'",
    "Si yo quiero contigo y tú quiere' conmigo",
    "Dime si se da, brr",
    "Ba-Ba-Baby, dale, ponte pa' mí, follow me",
    "Tú ere', like, for me y yo 'toy pa' ti",
    "Gata, tú está' only, mándame tu ubi",
    "Mueve los cachete' al ritmo del TikTok",
    "Conmigo esa gata se escapó y en su casa nadie lo notó",
    "E' que conmigo la baby esta noche la pasó ******",
    "Gata, pon el celu en modo avión",
    "Tú mi loca y yo tu locotrón",
    "Tú te pone' pa' mí y yo por ti me tiro to'a la misión",
    "Mami, te siento lejo', dime dónde está' (Ah-ah)",
    "Te quiero ******r, te voy a raptar",
    "Déjate llevar y apaga el celular",
    "Que si está' conmigo no te va a pasar na'",
    "**** déjate llevar",
    "Nena, atenta a la jugá'",
    "Si yo quiero contigo y tú quiere' conmigo (Mj)",
    "(Dime si se da)",
    "Ba-Ba-Baby, dale, ponte pa' mí, follow me",
    "Tú ere', like, for me, yo tu Brad Pitt",
    "Gata, tú está' only, mándame tu ubi",
    "Muévelo, cabecea al ritmo del TikTok",
    "Conmigo esa gata se escapó",
    "El que la rompe soy yo",
    "Me la robo de menor",
    "Otra noche dándote (Eh), dándote (Eh)",
    "Tocándote (Eh), calentándote",
    "Encima viniéndote (Okey)",
    "Al oído gimiéndome (Ja)",
    "Y yo la sigo e*********, regalando, to' el día comprando",
    "Lo que uste', mamita, quiere",
    "Ahora nos 'tamo alejando, no te estoy viendo, los día' volando",
    "Estar sola tú prefiere'",
    "Ven y dame un chance pa' guayarte",
    "Allá abajo mojarte toa' las parte'",
    "Otra noche dándote, dándote",
    "Tocándote, calentándote",
    "Baby, dale, ponte pa' mí, follow me (Brr)",
    "Tú ere', like, for me, yo tu Brad Pitt",
    "Gata, tú está' only, mándame tu ubi",
    "Muévelo, cabecea al ritmo del TikTok",
    "Conmigo esa gata se escapó",
    "El que la rompe soy yo",
    "Me la robo de menor",
    "Ya tú sabe' quiéne', ya tú sabe' quiénes somo' (Ey)",
    "Mami, te siento lejo', dime dónde estás",
    "Te quiero ******r, te voy a raptar",
    "Déjate llevar, apaga el celular",
    "Que si estás conmigo no te va a pasar na'",
    "Tú y yo en La Serena",
    "Por la Avenida del Mar",
    "Si yo quiero contigo y tú quiere' conmigo",
    "Dime si se da"
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
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    return;
                }
            }

            String color = COLORS[i % COLORS.length];
            typewriterPrint(color + lyrics[i] + RESET);

            try {
                Thread.sleep(LINE_PAUSE_MS);
            } catch (InterruptedException e) {
                return;
            }
        }

        System.out.println("\nEnd of song");
    }

    private static void typewriterPrint(String line) {
        for (int j = 0; j < line.length(); j++) {
            System.out.print(line.charAt(j));
            System.out.flush();
            try {
                Thread.sleep(CHAR_DELAY_MS);
            } catch (InterruptedException e) {
                return;
            }
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
                if (frac.length() == 1)
                    frac += "0";
                if (frac.length() > 2)
                    frac = frac.substring(0, 2);
                centi = Integer.parseInt(frac);
            }

            return (minutes * 60000L) + (seconds * 1000L) + (centi * 10L);

        } catch (Exception e) {
            return 0;
        }
    }
}
