package Task2;

public class Level1
{
    public static int odometer(int[] oksana)
    {
        int lastHour = 0;
        int result = 0;
        for (int i = 0; i < oksana.length - 1; i+=2) {
            int currentSpeed = oksana[i];
            int hoursSpent = oksana[i + 1];
            result += currentSpeed * (hoursSpent - lastHour);
            lastHour = hoursSpent;
        }
        return result;
    }
}
