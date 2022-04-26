public class Level1 {
    public static String PatternUnlock(int N, int[] hits) {
        List<Pair> pairs = shortPathsList();
        double sum = 0;
        for (int i = 0; i < N - 1; i++) {
            int a = hits[i];
            int b = hits[i + 1];
            if (b - a == 1 || a - b == 1 || pairs.contains(new Pair(a, b))) sum += 1;
            else sum += Math.sqrt(2);
        }
        return String.format("%.5f", sum).replaceAll("\\D", "").replaceAll("0", "");
    }

    private static List<Pair> shortPathsList() {

        ArrayList<Pair> list = new ArrayList<>();
        list.add(new Pair(6, 1));
        list.add(new Pair(1, 6));

        list.add(new Pair(1, 9));
        list.add(new Pair(9, 1));

        list.add(new Pair(5, 2));
        list.add(new Pair(2, 5));

        list.add(new Pair(2, 8));
        list.add(new Pair(8, 2));

        list.add(new Pair(3, 7));
        list.add(new Pair(7, 3));

        return list;
    }

    private record Pair(int a, int b) {}
}
