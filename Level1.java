public class Level1 {
    public static int squirrel(int N) {
        int result = 1;
        for (int i = 1; i <= N; i++) {
            result = result * i;
        }
        return Integer.parseInt(String.valueOf(Integer.toString(result).toCharArray()[0]));
    }
}
