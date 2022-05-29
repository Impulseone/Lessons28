public class Level1 {
    public static String Keymaker(int k) {
        int[] array = new int[k];
        firstStep(array);
        if (k > 1) secondStep(array);
        if (k > 2) {
            for (int i = 3; i <= k; i++) {
                lastStep(array, i);
            }
        }
        return fromArrayToString(array);
    }

    private static void firstStep(int[] array) {
        Arrays.fill(array, 1);
    }

    private static void secondStep(int[] array) {
        for (int i = 1; i < array.length; i += 2) {
            array[i] = 0;
        }
    }

    private static void lastStep(int[] array, int step) {
        for (int i = step - 1; i < array.length; i += step) {
            array[i] = array[i] == 1 ? 0 : 1;
        }
    }

    private static String fromArrayToString(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : array) {
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }
}
