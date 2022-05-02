package Task9;

public class Level1 {
    public static String TheRabbitsFoot(String s, boolean encode) {
        if (encode) {
            return encode(s);
        }
        return decode(s);
    }

    private static String encode(String s) {
        String trimmedString = s.replaceAll("\\s+", "");
        double sqrt = Math.sqrt(trimmedString.length());
        int max = (int) Math.ceil(sqrt);
        String[] splittedString = splitEqually(trimmedString, max);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < splittedString.length; i++) {
            if (i < splittedString.length - 1)
                stringBuilder.append(splittedString[i]).append(" ");
            else
                stringBuilder.append(splittedString[i]);
        }
        return stringBuilder.toString();
    }

    public static String decode(String s) {
        String trimmedString = s.replaceAll("\\s+", "");
        double sqrt = Math.sqrt(trimmedString.length());
        int max = (int) Math.ceil(sqrt);
        String[] splittedStringArray = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < max; i++) {
            for (String value : splittedStringArray) {
                if (value.length() > i)
                    stringBuilder.append(value.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    private static String[] splitEqually(String text, int size) {
        String[] array = new String[size];
        char[] chars = text.toCharArray();
        for (int i = 0; i < size; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = i; j < chars.length; j += size) {
                stringBuilder.append(chars[j]);
            }
            array[i] = stringBuilder.toString();
        }
        return array;
    }
}
