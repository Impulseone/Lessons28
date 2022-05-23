public class Level1 {
    public static String[] TreeOfLife(int H, int W, int N, String[] tree) {
        String[] resultStringArray = new String[H];
        StickArray[] stickArrays = new StickArray[H];
        for (int i = 0; i < H; i++) {
            String currentString = tree[i];
            StickArray stickArray = new StickArray(fromStringToArray(currentString));
            stickArrays[i] = stickArray;
        }
        for (int i = 0; i < N; i++) {
            for (StickArray stickArray : stickArrays) {
                stickArray.incrementAge();
            }
        }
        for (int i = 0; i < H; i++) {
            resultStringArray[i] = stickArrays[i].toString();
        }
        return resultStringArray;
    }

    private static int[] fromStringToArray(String treeElement) {
        char[] chars = treeElement.toCharArray();
        int[] array = new int[treeElement.length()];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '+') array[i] = 1;
            else array[i] = 0;
        }
        return array;
    }

    private static class StickArray {
        int[] sticksAge;
        int year = -1;

        public StickArray(int[] sticksAge) {
            this.sticksAge = sticksAge;
        }

        public void incrementAge() {
            for (int j = 0; j < sticksAge.length; j++) {
                sticksAge[j] = sticksAge[j] + 1;
            }
            year += 1;
            checkStickAge();
        }

        private void checkStickAge() {
            if (year % 2 == 0) return;
            int[] sticksAgeNew = Arrays.copyOf(sticksAge, sticksAge.length);
            for (int i = 0; i < sticksAge.length; i++) {
                if (sticksAge[i] >= 3) sticksAgeNew[i] = 0;
                if (i > 0 && sticksAge[i] >= 3) sticksAgeNew[i - 1] = 0;
                if (i < sticksAge.length - 1 && sticksAge[i] >= 3) sticksAgeNew[i + 1] = 0;
            }
            sticksAge = sticksAgeNew;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i : sticksAge) {
                if (i != 0) stringBuilder.append("+");
                else stringBuilder.append(".");
            }
            return stringBuilder.toString();
        }
    }
}
