package Task5;

import java.util.Arrays;
import java.util.HashMap;

public class Level1 {
    public static int[] SynchronizingTables(int N, int[] ids, int[] salary) {
        int[] idsBeforeSorting = Arrays.copyOf(ids, ids.length);
        return createResultMas(saveToMap(N, sortToMax(ids),  sortToMax(salary)), idsBeforeSorting);
    }

    public static int[] createResultMas(HashMap<Integer, Integer> map, int[] ids){
        int[] mas = new int[ids.length];
        for (int i = 0; i < ids.length; i++) {
            mas[i] = map.get(ids[i]);
        }
        return mas;
    }

    public static HashMap<Integer, Integer> saveToMap(int N, int[] ids, int[] salary) {
        HashMap<Integer, Integer> map = new HashMap<>(N);
        for (int i = 0; i < ids.length; i++) {
            map.put(ids[i], salary[i]);
        }
        return map;
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
}
