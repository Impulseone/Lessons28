public class Level1 {
    public static String PatternUnlock(int N, int[] hits) {
        double sum = 0;
        for (int i = 0; i < N - 1; i++) {
            int a = hits[i];
            int b = hits[i + 1];
            if (b - a == 1) sum += 1;
            else sum += Math.sqrt(2);
        }
        return String.format("%.5f", sum).replaceAll("\\D", "").replaceAll("0","");
    }
}
