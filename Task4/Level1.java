public class Level1 {
    public static int[] MadMax(int N, int[] Tele) {
        int[] resultMas = new int[Tele.length];
        if (N == 1) return new int[Tele[0]];
        else {
            int middleElementPos = Tele.length / 2;
            sortToMax(Tele);
            resultMas[middleElementPos] = Tele[Tele.length-1];
            int[] sortedMasBeforeMiddle = sortToMax(createMasBeforeMiddlePos(Tele));
            int[] sortedMasAfterMiddle = sortToMin(createMasAfterMiddlePos(Tele));
            System.arraycopy(sortedMasBeforeMiddle, 0, resultMas, 0, sortedMasBeforeMiddle.length);
            int counter = middleElementPos + 1;
            for (int j : sortedMasAfterMiddle) {
                resultMas[counter] = j;
                counter++;
            }
        }
        return resultMas;
    }

    public static int[] createMasBeforeMiddlePos(int[] mas) {
        int[] result = new int[mas.length / 2];
        int counter = 0;
        for (int i = 0; i < mas.length / 2; i++) {
            result[counter] = mas[i];
            counter++;
        }
        return result;
    }

    public static int[] createMasAfterMiddlePos(int[] mas) {
        int[] result = new int[mas.length / 2];
        int counter = 0;
        for (int i = mas.length / 2; i < mas.length-1; i++) {
            result[counter] = mas[i];
            counter++;
        }
        return result;
    }

    public static int[] sortToMax(int[] mas) {
        for (int i = mas.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (mas[j] > mas[j + 1]) {
                    int temp = mas[j];
                    mas[j] = mas[j + 1];
                    mas[j + 1] = temp;
                }
            }
        }
        return mas;
    }

    public static int[] sortToMin(int[] mas) {
        for (int i = mas.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (mas[j] < mas[j + 1]) {
                    int temp = mas[j];
                    mas[j] = mas[j + 1];
                    mas[j + 1] = temp;
                }
            }
        }
        return mas;
    }
}
