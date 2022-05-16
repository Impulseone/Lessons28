public class Level1 {
    public static boolean MisterRobot(int N, int[] data) {
        return checkElements(N, data);
    }

    private static boolean checkElements(int N, int[] data) {
        int[] sorted = sortToMax(data);
        boolean result = true;

        while (result) {
            if (Arrays.equals(sorted, data)) {
                break;
            }
            for (int i = 0; i < N - 2; i++) {
                int[] array = new int[]{data[i], data[i + 1], data[i + 2]};
                if (isChangeResolvable(array)) {
                    int[] newArray = changeElements(i, array, data);
                    if (!Arrays.equals(newArray, data)) {
                        result = true;
                        data = newArray;
                        break;
                    }
                } else {
                    result = false;
                }
            }
        }

        return result;
    }

    private static boolean isChangeResolvable(int[] array) {
        while (true) {
            if ((array[0] > array[1] && array[1] > array[2])) return false;
            else if ((array[0] < array[1] && array[1] < array[2])) return true;
            else {
                array = new int[]{array[1], array[2], array[0]};
            }
        }
    }

    private static int[] changeElements(int index, int[] array, int[] data) {
        int[] result = Arrays.copyOf(data, data.length);
        while (true) {
            if (array[0] <= array[1] && array[1] <= array[2]) break;
            else {
                array = new int[]{array[1], array[2], array[0]};
            }
        }
        for (int i = 0; i < result.length; i++) {
            if (i == index) {
                result[i] = array[0];
                result[i + 1] = array[1];
                result[i + 2] = array[2];
                break;
            }
        }
        return result;
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
}
