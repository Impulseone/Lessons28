package Task22;

import java.util.ArrayList;
import java.util.List;

public class Level1 {
    public static boolean SherlockValidString(String s) {
        return checkSymbols(s);
    }

    private static boolean checkSymbols(String s) {
        char[] chars = s.toCharArray();
        List<Bukva> list = new ArrayList<>();
        for (char symbol : chars) {
            Bukva bukva = findBukvaInList(list, symbol);
            if (bukva != null) {
                bukva.incrementCount();
            } else list.add(new Bukva(symbol));
        }
        return checkCount(list);
    }

    private static Bukva findBukvaInList(List<Bukva> list, char symbol) {
        for (Bukva bukva : list) {
            if (bukva.symbol == symbol) return bukva;
        }
        return null;
    }

    private static boolean checkCount(List<Bukva> list) {
        list.sort((o1, o2) -> o2.count - o1.count);
        int differences = 0;
        Bukva anotherBukva = null;
        for (int i = 0; i < list.size() - 1; i++) {
            Bukva bukva1 = list.get(i);
            Bukva bukva2 = list.get(i + 1);
            int difference = bukva1.count - bukva2.count;
            if (difference > 1) {
                return checkEqualityCase(list, bukva2);
            }
            if (difference != 0) {
                differences += 1;
                anotherBukva = list.get(list.size() - 1).equals(bukva2) ? bukva2 : bukva1;
            }
            if (differences > 1) return false;
        }
        if (anotherBukva != null) return checkEqualityCase(list, anotherBukva);
        return true;
    }

    private static boolean checkEqualityCase(List<Bukva> list, Bukva bukva) {
        if (list.size() == 2) {
            int difference = list.get(0).count - list.get(1).count;
            return difference < 2;
        }
        List<Bukva> bukvas = new ArrayList<>(list);
        bukvas.remove(bukva);
        for (int i = 0, bukvasSize = bukvas.size() - 1; i < bukvasSize; i++) {
            Bukva bukva1 = bukvas.get(i);
            Bukva bukva2 = bukvas.get(i + 1);
            if (bukva1.count != bukva2.count) return false;
        }
        return true;
    }

    private static class Bukva {
        int count = 1;
        char symbol;

        public Bukva(char symbol) {
            this.symbol = symbol;
        }

        void incrementCount() {
            count += 1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Bukva bukva = (Bukva) o;

            if (count != bukva.count) return false;
            return symbol == bukva.symbol;
        }

        @Override
        public int hashCode() {
            int result = count;
            result = 31 * result + (int) symbol;
            return result;
        }
    }
}
