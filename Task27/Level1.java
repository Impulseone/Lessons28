public class Level1 {
    public static boolean Football(int[] F, int N) {
        int[] sortedArray = sortToMax(F);
        boolean ruleOne = sortByRuleOne(F, sortedArray);
        boolean ruleTwo = sortByRuleTwo(F, sortedArray);
        return ruleOne || ruleTwo;
    }

    private static boolean sortByRuleOne(int[] F, int[] sortedArray) {
        for (int i = 0; i < F.length; i++) {
            int[] newArray = Arrays.copyOf(F, F.length);
            for (int j = i + 1; j < F.length; j++) {
                int temp = newArray[i];
                newArray[i] = newArray[j];
                newArray[j] = temp;
                if (Arrays.equals(newArray, sortedArray)) return true;
                newArray = Arrays.copyOf(F, F.length);
            }
        }
        return false;
    }

    private static boolean sortByRuleTwo(int[] F, int[] sortedArray) {
        for (int i = 0; i < F.length; i++) {
            int[] newArray = Arrays.copyOf(F, F.length);
            for (int j = i + 1; j < F.length; j++) {
                reverse(i, j, newArray);
                if (Arrays.equals(newArray, sortedArray)) return true;
                newArray = Arrays.copyOf(F, F.length);
            }
        }
        return false;
    }

    private static int[] sortToMax(int[] mas) {
        int[] result = Arrays.copyOf(mas, mas.length);
        for (int i = result.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (result[j] > result[j + 1]) {
                    int temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }
        return result;
    }

    private static void reverse(int from, int to, int[] array) {
        int[] reversed = new int[to - from + 1];
        int[] temp = new int[to - from + 1];
        int tempIndex = 0;
        for (int i = from; i <= to; i++) {
            temp[tempIndex] = array[i];
            tempIndex += 1;
        }
        int reversedIndex = 0;
        for (int i = temp.length - 1; i >= 0; i--) {
            reversed[reversedIndex] = temp[i];
            reversedIndex += 1;
        }
        int reversedIndex2 = 0;
        for (int i = from; i <= to; i++) {
            array[i] = reversed[reversedIndex2];
            reversedIndex2 += 1;
        }
    }
}
