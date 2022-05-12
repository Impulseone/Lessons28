public class Level1 {
    public static int MaximumDiscount(int N, int[] price) {
        int[] sorted = sortToMin(price);
        if (N <= 5) return sorted[sorted.length - 1];
        return calculateDiscount(splitArray(sorted));
    }

    private static int[] sortToMin(int[] mas) {
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

    private static List<List<Integer>> splitArray(int[] array) {
        List<List<Integer>> splitted = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int j : array) {
            if (list.size() != 3) {
                list.add(j);
            } else {
                splitted.add(list);
                list = new ArrayList<>();
                list.add(j);
            }
        }
        if (list.size() == 3) splitted.add(list);
        return splitted;
    }

    private static int calculateDiscount(List<List<Integer>> list) {
        int discount = 0;
        for (List<Integer> integers : list) {
            discount += integers.get(integers.size() - 1);
        }
        return discount;
    }


}
