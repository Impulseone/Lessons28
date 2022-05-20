public class Level1 {

    static ArrayList<String> list = new ArrayList<>();

    public static String BiggerGreater(String input) {
        boolean checkPossibility = checkPossibilityOfChange(input);
        if (checkPossibility) {
            permutation(input);
            return compareResult(input);
        }
        return "";
    }

    private static boolean checkPossibilityOfChange(String input) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] != chars[i + 1]) return true;
        }
        return false;
    }

    public static void permutation(String str) {
        permutation("", str);
    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) list.add(prefix);
        else continuePermutation(n, prefix, str);
    }

    private static void continuePermutation(int n, String prefix, String str) {
        for (int i = 0; i < n; i++)
            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
    }

    private static String compareResult(String input) {
        ArrayList<String> biggerThanInput = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            if (input.compareTo(list.get(i)) < 0) {
                biggerThanInput.add(list.get(i));
            }
        }
        return findMinInBiggers(biggerThanInput);
    }

    private static String findMinInBiggers(List<String> biggers) {
        String result = biggers.get(0);
        for (String bigger : biggers) {
            if (result.compareTo(bigger) > 0) {
                result = bigger;
            }
        }
        return result;
    }
}
