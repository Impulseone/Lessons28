package Task23;

import java.util.ArrayList;

public class Level1 {
    public static String[] TreeOfLife(int H, int W, int N, String[] tree) {
        ArrayList<StickCoordinate> stickCoordinates = new ArrayList<>();
        for (int i = 0; i < tree.length; i++) {
            String s = tree[i];
            int[] line = fromStringToArray(s);
            for (int j = 0; j < line.length; j++) {
                StickCoordinate stickCoordinate = new StickCoordinate(j + 1, i + 1, line[j]);
                stickCoordinates.add(stickCoordinate);
            }
        }
        Tree coordinatesTree = new Tree(stickCoordinates.toArray(new StickCoordinate[0]), W, H);
        for (int i = 0; i < N; i++) {
            coordinatesTree.incrementYear();
        }
        return coordinatesTree.toStringArray();
    }

    private static class Tree {
        StickCoordinate[] coordinates;
        int year = -1;

        final int W;
        final int H;

        public Tree(StickCoordinate[] coordinates, int w, int h) {
            this.coordinates = coordinates;
            W = w;
            H = h;
        }

        public void incrementYear() {
            for (StickCoordinate coordinate : coordinates) {
                coordinate.incrementAge();
            }
            year += 1;
            checkStickAge();
        }

        private void checkStickAge() {
            if (year % 2 == 0) return;
            StickCoordinate[] coordinatesNew = copyArray(coordinates);
            for (StickCoordinate stickCoordinate : coordinates) {
                int x = stickCoordinate.x;
                int y = stickCoordinate.y;
                if (stickCoordinate.age >= 3) {
                    setZeroAgeOfCoordinate(coordinatesNew, x, y);
                    setZeroAgeOfCoordinate(coordinatesNew, x - 1, y);
                    setZeroAgeOfCoordinate(coordinatesNew, x, y - 1);
                    setZeroAgeOfCoordinate(coordinatesNew, x + 1, y);
                    setZeroAgeOfCoordinate(coordinatesNew, x, y + 1);
                }
            }
            coordinates = coordinatesNew;
        }

        private void setZeroAgeOfCoordinate(StickCoordinate[] stickCoordinatesNew, int x, int y) {
            for (int i = 0; i < coordinates.length; i++) {
                if (coordinates[i].x == x && coordinates[i].y == y) {
                    stickCoordinatesNew[i].setZeroAge();
                }
            }
        }

        private StickCoordinate[] copyArray(StickCoordinate[] coordinates) {
            StickCoordinate[] result = new StickCoordinate[coordinates.length];
            for (int i = 0, coordinatesLength = coordinates.length; i < coordinatesLength; i++) {
                StickCoordinate stickCoordinate = coordinates[i];
                result[i] = new StickCoordinate(stickCoordinate.x, stickCoordinate.y, stickCoordinate.age);
            }
            return result;
        }

        private String[] toStringArray() {
            ArrayList<String> resultList = new ArrayList<>();
            for (int i = 0; i < H; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 1; j <= W; j++) {
                    stringBuilder.append(findStickAge(j, i + 1));
                }
                String line = toSymbols(stringBuilder.toString());
                resultList.add(line);
            }
            return resultList.toArray(new String[0]);
        }

        private int findStickAge(int x, int y) {
            for (StickCoordinate coordinate : coordinates) {
                if (coordinate.x == x && coordinate.y == y) {
                    return coordinate.age;
                }
            }
            return -1;
        }
    }

    private static class StickCoordinate {
        final int x;
        final int y;
        int age;

        public StickCoordinate(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        public void incrementAge() {
            age += 1;
        }

        public void setZeroAge() {
            age = 0;
        }
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

    private static String toSymbols(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar != '0') stringBuilder.append('+');
            else stringBuilder.append('.');
        }
        return stringBuilder.toString();
    }
}
