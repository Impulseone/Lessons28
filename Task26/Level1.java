public class Level1 {
    public static boolean white_walkers(String village) {
        return calculate(village);
    }

    private static boolean calculate(String village) {
        boolean isZombiesExist = false;
        int zombiesCount = 0;
        int lastInt = -1;
        char[] chars = village.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char symbol = chars[i];
            if (isInt(symbol)) {
                int currentInt = Integer.parseInt(String.valueOf(symbol));
                boolean sumIsTen = currentInt + lastInt == 10;
                if (sumIsTen) isZombiesExist = zombiesCount == 3;
                lastInt = currentInt;
                zombiesCount = 0;
            }
            if (isZombie(symbol)) {
                zombiesCount += 1;
            }
        }
        return isZombiesExist;
    }

    private static boolean isInt(char symbol) {
        try {
            Integer.parseInt(String.valueOf(symbol));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isZombie(char symbol) {
        return symbol == '=';
    }
}
