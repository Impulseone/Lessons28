package Task13;

public class Level1 {
    public static int[] UFO(int N, int[] data, boolean octal) {
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            int decimalNumberResult = 0;
            String reversedOctalNumber = new StringBuilder(String.valueOf(data[i])).reverse().toString();
            char[] octalNumberChars = reversedOctalNumber.toCharArray();
            for (int j = 0; j < octalNumberChars.length; j++) {
                int octalNumber = Integer.parseInt(String.valueOf(octalNumberChars[j]));
                int decimalNumber = (int) (Math.pow(octal ? 8 : 16, j) * octalNumber);
                decimalNumberResult += decimalNumber;
            }
            result[i] = decimalNumberResult;
        }
        return result;
    }
}
