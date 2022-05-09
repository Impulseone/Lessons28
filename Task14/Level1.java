package Task14;

public class Level1 {
    public static int Unmanned(int L, int N, int[][] track) {
        TrafficLight[] trafficLights = createTrafficLightsArray(N, track);
        int coordinate = 0;
        int time = 0;
        while (coordinate < L) {
            if (!isRed(coordinate, time, trafficLights)) {
                coordinate += 1;
            }
            time += 1;
        }
        return time;
    }

    private static boolean isRed(int coordinate, int time, TrafficLight[] trafficLights) {
        for (TrafficLight trafficLight : trafficLights) {
            if (trafficLight.coordinate == coordinate && trafficLight.getRedState(time)) {
                return true;
            }
        }
        return false;
    }

    private static TrafficLight[] createTrafficLightsArray(int N, int[][] track) {
        TrafficLight[] trafficLights = new TrafficLight[N];
        for (int i = 0; i < N; i++) {
            trafficLights[i] = new TrafficLight(track[i][0], track[i][1], track[i][2]);
        }
        return trafficLights;
    }

    private static class TrafficLight {
        private final int coordinate;
        private final boolean[] defaultRedStatesArray;

        public TrafficLight(int coordinate, int redTime, int greenTime) {
            this.coordinate = coordinate;
            this.defaultRedStatesArray = setDefaultRedStatesArray(redTime, greenTime);
        }

        private boolean[] setDefaultRedStatesArray(int redTime, int greenTime) {
            boolean[] array = new boolean[redTime + greenTime];
            for (int i = 0; i < array.length; i++) {
                array[i] = i + 1 < redTime;
            }
            return array;
        }

        public boolean getRedState(int time) {
            int k = (int) Math.floor((double) time / defaultRedStatesArray.length);
            int index = time - defaultRedStatesArray.length * k;
            return defaultRedStatesArray[index - 1];
        }
    }
}
