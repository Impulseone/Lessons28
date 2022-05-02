package Task8;

public class Level1 {
    public static int SumOfThe(int N, int[] data) {
        if (N == 2) return data[0];
        return findNumber(data);
    }

    public static int findNumber(int[] data) {
        int result = 0;
        for (int i = 0; i < data.length; i++) {
            int sum = calculateSum(data, i);
            if (data[i] == sum) {
                result = data[i];
                break;
            }
        }
        return result;
    }

    public static int calculateSum(int[] data, int excludedElementPos) {
        int result = 0;
        for (int i = 0; i < data.length; i++) {
            if (i == excludedElementPos) continue;
            result += data[i];
        }
        return result;
    }
}
