public class Level1 {
    public static String MassVote(int N, int[] Votes) {
        int[] max = findMax(N, Votes);
        if (max == null) return "no winner";
        double percent = calculatePercent(max[0], Votes);
        return checkCondition(percent, max[1]);
    }

    private static int[] findMax(int N, int[] Votes) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < N; i++) {
            if (Votes[i] > max) {
                max = Votes[i];
                index = i + 1;
            }
        }

        int winnersCount = 0;
        for (int i = 0; i < N; i++) {
            if (Votes[i] == max) winnersCount += 1;
        }

        if (winnersCount > 1) return null;
        return new int[]{max, index};
    }

    private static double calculatePercent(int max, int[] Votes) {
        double sum = 0;
        for (int vote : Votes) {
            sum += vote;
        }
        double percent = max / sum;
        return (double) Math.round(percent * 100000d) / 1000d;
    }

    private static String checkCondition(double percent, int index) {
        if (percent > 50.0) return "majority winner " + index;
        else return "minority winner " + index;
    }
}
