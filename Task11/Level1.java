public class Level1 {
    public static String BigMinus(String s1, String s2) {
        int[] first = reverseArray(convertToIntArray(s1.toCharArray()));
        int[] second = reverseArray(convertToIntArray(s2.toCharArray()));
        if (first.length < second.length) {
            return standardMinus(addZeros(first, second.length), second);
        }
        if (second.length < first.length) {
            return standardMinus(addZeros(second, first.length), first);
        }
        return standardMinus(first, second);
    }

    private static String standardMinus(int[] reversed1, int[] reversed2) {
        boolean needRevert = false;
        int[] start1 = Arrays.copyOf(reversed1, reversed1.length);
        int[] start2 = Arrays.copyOf(reversed2, reversed2.length);
        StringBuilder result = new StringBuilder();
        if (reversed1.length == 1 && reversed2.length == 1)
            return result.append(reversed1[0] - reversed2[0]).toString();
        if (Arrays.equals(reversed1, reversed2)) return result.append(0).toString();
        for (int i = 0; i < reversed1.length; i++) {
            int minus = reversed1[i] - reversed2[i];
            if (minus >= 0) {
                result.append(minus);
            } else {
                result.append(10 + minus);
                if (i != reversed1.length - 1) {
                    reversed1[i + 1] = reversed1[i + 1] - 1;
                } else {
                    needRevert = true;
                    break;
                }
            }
        }
        if (needRevert) {
            return standardMinus(start2, start1);
        }
        String resultString = result.reverse().toString();
        if (resultString.charAt(0) == '0') {
            return removeFirstZerosFromString(resultString);
        }
        return resultString;
    }

    private static int[] convertToIntArray(char[] chars) {
        int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            ints[i] = Integer.parseInt("" + chars[i]);
        }
        return ints;
    }

    private static int[] reverseArray(int[] array) {
        int[] resultArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (i == 0) resultArray[array.length - 1] = array[i];
            else resultArray[array.length - i - 1] = array[i];
        }
        return resultArray;
    }

    private static int[] addZeros(int[] array, int length) {
        int[] result = new int[length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    private static String removeFirstZerosFromString(String string) {
        int firstIndex = 0;
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                firstIndex = i;
                break;
            }
        }
        return string.substring(firstIndex);
    }
}
