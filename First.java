public class First
{
    public static int squirrel(int N)
    {
        int sum = N;
        for (int i = N - 1; i >= 1; i--) {
            sum *= N;
        }
        if (sum < 0) sum = sum * -1;
        return Integer.parseInt(String.valueOf(Integer.toString(sum).toCharArray()[0]));
    }
}
