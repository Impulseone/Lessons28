public class Level1 {
    public static boolean TankRush(int H1, int W1, String S1, int H2, int W2, String S2) {
        Field field1 = Field.createFromStringsArray(S1.split(" "));
        Field field2 = Field.createFromStringsArray(S2.split(" "));
        return field1.containsField(field2);
    }

    private static class Coordinate {
        public int x;
        public int y;
        public final int value;

        public Coordinate(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coordinate that = (Coordinate) o;

            if (x != that.x) return false;
            if (y != that.y) return false;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + value;
            return result;
        }
    }

    private static class Field {
        public final int width;
        public final int height;
        public List<Coordinate> coordinates;

        public Field(int width, int height, List<Coordinate> coordinates) {
            this.width = width;
            this.height = height;
            this.coordinates = coordinates;
        }

        public boolean containsField(Field field) {
            if (coordinates.size() == field.coordinates.size()) return coordinates.equals(field.coordinates);
            return checkContains(field);
        }

        private boolean checkContains(Field field) {
            if (containsCoordinates(field)) return true;
            for (int i = 0; i < height; i++) {
                while (width > findMaxXCoordinate(field)) {
                    field.incrementCoordinates(1, 0);
                    if (containsCoordinates(field)) return true;
                }
                if (findMaxYCoordinate(field) >= height) continue;
                field.incrementCoordinates(0, 1);
                if (containsCoordinates(field)) return true;
                while (findMinXCoordinate(field) > 1) {
                    field.incrementCoordinates(-1, 0);
                    if (containsCoordinates(field)) return true;
                }

            }
            return false;
        }

        private boolean containsCoordinates(Field field){
            return new HashSet<>(coordinates).containsAll(field.coordinates);
        }

        private int findMaxXCoordinate(Field field) {
            int max = 0;
            for (Coordinate coordinate : field.coordinates) {
                if (coordinate.x > max) max = coordinate.x;
            }
            return max;
        }

        private int findMinXCoordinate(Field field) {
            int min = field.coordinates.get(0).x;
            for (Coordinate coordinate : field.coordinates) {
                if (coordinate.x < min) min = coordinate.x;
            }
            return min;
        }

        private int findMaxYCoordinate(Field field) {
            int max = 0;
            for (Coordinate coordinate : field.coordinates) {
                if (coordinate.y > max) max = coordinate.y;
            }
            return max;
        }

        private void incrementCoordinates(int x, int y) {
            List<Coordinate> newList = new ArrayList<>();
            for (Coordinate coordinate : coordinates) {
                newList.add(new Coordinate(coordinate.x + x, coordinate.y + y, coordinate.value));
            }
            coordinates = newList;
        }

        public static Field createFromStringsArray(String[] strings) {
            int width = strings[0].length();
            int height = strings.length;
            ArrayList<Coordinate> coordinateList = new ArrayList<>();
            for (int i = 0; i < strings.length; i++) {
                int[] intArray = createIntArrayFromString(strings[i]);
                for (int i1 = 0; i1 < intArray.length; i1++) {
                    Coordinate coordinate = new Coordinate(i1 + 1, i + 1, intArray[i1]);
                    coordinateList.add(coordinate);
                }
            }
            return new Field(width, height, coordinateList);
        }

        private static int[] createIntArrayFromString(String s) {
            char[] chars = s.toCharArray();
            int[] resultArray = new int[chars.length];
            for (int i = 0; i < chars.length; i++) {
                int element = Integer.parseInt(String.valueOf(chars[i]));
                resultArray[i] = element;
            }
            return resultArray;
        }
    }
}
