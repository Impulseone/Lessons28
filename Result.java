public class Result {
    public static int squirrel(int N) {
        int sum = N;
        for (int i = N - 1; i >= 1; i--) {
            sum *= i;
        }
        return sum;
    }
}
