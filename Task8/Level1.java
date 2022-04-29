public class Level1 {
    public static int SumOfThe(int N, int[] data) {
        if (N == 2) return data[0];
        return findNumber(data);
    }

    public static int findNumber(int[] data) {
        int result = 0;
        for (int datum : data) {
            int sum = calculateSum(data, datum);
            if (datum == sum) {
                result = datum;
                break;
            }
        }
        return result;
    }

    public static int calculateSum(int[] data, int excludedElement) {
        int result = 0;
        for (int number : data) {
            if (number == excludedElement) continue;
            result += number;
        }
        return result;
    }
}
