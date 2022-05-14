public class Level1 {
    public static boolean LineAnalysis(String line) {
        return calculatePattern(line);
    }

    private static boolean calculatePattern(String line) {
        String[] split = line.split("\\*");
        boolean result = true;
        for (int i = 1; i < split.length - 1; i++) {
            if (!split[i].equals(split[i + 1])) {
                result = false;
                break;
            }
        }
        return result;
    }
}
