public class Level1 {
    public static boolean TransformTransform(int[] A, int N) {
        ArrayList<Integer> firstList = S(A, N);
        ArrayList<Integer> secondList = S(fromListToArray(firstList), N);
        int sum = sum(secondList);
        return sum % 2 == 0;
    }

    private static ArrayList<Integer> S(int[] A, int N) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                int k = i + j;
                list.add(findMax(A, j, k));
            }
        }
        return list;
    }

    private static int findMax(int[] A, int j, int k) {
        int max = 0;
        for (int i = j; i < k; i++) {
            if (A[i] > max) max = A[i];
        }
        return max;
    }

    private static int[] fromListToArray(ArrayList<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
            ;
        }
        return array;
    }

    private static int sum(ArrayList<Integer> list) {
        int sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }
        return sum;
    }
}
