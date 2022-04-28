public class Level1 {
    public static int[] WordSearch(int len, String s, String subs) {
        return findString(splitByLength(splitString(s, len), len), subs);
    }

    private static String[] splitString(String s, int len) {
        ArrayList<String> result = new ArrayList<>();
        String[] array = s.split(" ");
        for (String string : array) {
            if (string.length() <= len) {
                result.add(string);
                continue;
            }
            int currentStringLengthHalf = string.length() / len;
            int count = string.length() % len == 0 ? currentStringLengthHalf : currentStringLengthHalf + 1;
            int counter = 0;
            for (int j = 0; j < count; j++) {
                String substring = string.substring(counter, j + len);
                result.add(substring);
                counter += len;
            }
        }
        return result.toArray(String[]::new);
    }

    private static String[] splitByLength(String[] mas, int len) {
        StringBuilder resultStringBuilder = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        int counter;
        for (int i = 0; i < mas.length; i += counter) {
            String currentString = mas[i];
            resultStringBuilder.append(currentString);
            counter = 1;
            while (i < mas.length - 1 && mas[i + counter].length() <= len - resultStringBuilder.length()) {
                resultStringBuilder.append(" ").append(mas[i + counter]);
                counter += 1;
            }
            list.add(resultStringBuilder.toString());
            resultStringBuilder = new StringBuilder();
        }
        return list.toArray(String[]::new);
    }

    private static int[] findString(String[] array, String subs) {
        ArrayList<Integer> resultList = new ArrayList<>();
        for (String s : array) {
            if (s.split(" ").length == 1) {
                resultList.add(s.equals(subs.trim()) ? 1 : 0);
                continue;
            }
            resultList.add(s.contains(subs.trim() + " ") ? 1 : 0);
        }
        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }
}
