package Task24;

import java.util.ArrayList;

public class Level1 {
    public static void MatrixTurn(String[] Matrix, int M, int N, int T) {
        int[][] matrix = parseToIntArray(Matrix, M, N);
        int[][] newMatrix = changeMatrixAnySize(matrix, T, 1, 1, matrix.length - 1, matrix[0].length - 1, matrix.length - 2, matrix[0].length - 2);
        if (newMatrix.length < matrix.length) {
            int[][] bigMatrix = changeMatrixManyTimes(matrix, T, 0);
            pasteMatrix(bigMatrix, newMatrix, 1, 1, matrix.length - 1, matrix[0].length - 1);
            String[] newMatrixString = parseIntMatrixToString(bigMatrix);
            System.arraycopy(newMatrixString, 0, Matrix, 0, newMatrixString.length);
        } else {
            String[] newMatrixString = parseIntMatrixToString(newMatrix);
            System.arraycopy(newMatrixString, 0, Matrix, 0, newMatrixString.length);
        }
    }

    private static int[][] parseToIntArray(String[] Matrix, int M, int N) {
        int[][] matrix = new int[M][N];
        for (int i = 0; i < Matrix.length; i++) {
            String s = Matrix[i];
            char[] chars = s.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                matrix[i][j] = Integer.parseInt((String.valueOf(chars[j])));
            }
        }
        return matrix;
    }

    private static int[][] changeMatrixAnySize(int[][] matrix, int T, int from1, int from2, int to1, int to2, int lines, int columns) {
        if (matrix.length <= 2 || matrix[0].length <= 2) {
            return changeMatrixManyTimes(matrix, T, 0);
        }
        int[][] changedBigMatrix = changeMatrixManyTimes(matrix, T, 0);
        int[][] smallMatrix = getSmallMatrix(matrix, from1, from2, to1, to2, lines, columns);
        int[][] changedSmallMatrix = changeMatrixManyTimes(smallMatrix, T, 0);
        pasteMatrix(changedBigMatrix, changedSmallMatrix, from1, from2, to1, to2);
        if (!(smallMatrix.length <= 2 || smallMatrix[0].length <= 2)) {
            return changeMatrixAnySize(smallMatrix, T, 1, 1, smallMatrix.length - 1, smallMatrix[0].length - 1, smallMatrix.length - 2, smallMatrix[0].length - 2);
        } else return changedBigMatrix;
    }

    private static int[][] changeMatrixManyTimes(int[][] oldMatrix, int T, int k) {
        if (k >= T) {
            return oldMatrix;
        }
        k++;
        oldMatrix = changeMatrix(oldMatrix);
        return changeMatrixManyTimes(oldMatrix, T, k);
    }

    private static int[][] changeMatrix(int[][] oldMatrix) {
        int[][] newMatrix = new int[oldMatrix.length][oldMatrix[0].length];
        for (int i = 0; i < oldMatrix.length; i++) {
            int[] line = oldMatrix[i];
            int[] newLine = new int[line.length];

            boolean isFirstLine = i == 0;
            boolean isLastLine = i == oldMatrix.length - 1;

            for (int j = 0; j < line.length; j++) {

                boolean isFirstElement = j == 0;
                boolean isLastElement = j == line.length - 1;

                if (isFirstLine && isFirstElement) {
                    newLine[j] = oldMatrix[i + 1][0];
                }
                if (isFirstLine && !isFirstElement) {
                    newLine[j] = line[j - 1];
                }
                if (!isFirstLine && !isLastLine && isFirstElement) {
                    newLine[j] = oldMatrix[i + 1][0];
                }
                if (!isFirstLine && !isLastLine && isLastElement) {
                    newLine[j] = oldMatrix[i - 1][oldMatrix[0].length - 1];
                }
                if (isLastLine && !isLastElement) {
                    newLine[j] = line[j + 1];
                }
                if (isLastLine && isLastElement) {
                    newLine[j] = oldMatrix[i - 1][oldMatrix[0].length - 1];
                }
            }
            newMatrix[i] = newLine;
        }
        return newMatrix;
    }

    private static String[] parseIntMatrixToString(int[][] matrix) {
        String[] stringMatrix = new String[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int[] line = matrix[i];
            StringBuilder stringBuilder = new StringBuilder();
            for (int i1 : line) {
                stringBuilder.append(i1);
            }
            stringMatrix[i] = stringBuilder.toString();
        }
        return stringMatrix;
    }

    private static int[][] getSmallMatrix(int[][] oldMatrix, int from1, int from2, int to1, int to2, int lines, int columns) {
        int[][] newMatrix = new int[lines][columns];
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        for (int i = from1; i < to1; i++) {
            int[] line = oldMatrix[i];
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = from2; j < to2; j++) {
                list.add(line[j]);
            }
            arrayLists.add(list);
        }
        for (int i = 0; i < arrayLists.size(); i++) {
            ArrayList<Integer> currentList = arrayLists.get(i);
            for (int j = 0; j < currentList.size(); j++) {
                newMatrix[i][j] = currentList.get(j);
            }
        }
        return newMatrix;
    }

    private static int[][] pasteMatrix(int[][] bigMatrix, int[][] smallMatrix, int from1, int from2, int to1, int to2) {
        int index1 = 0;
        for (int i = from1; i < to1; i++) {
            int index2 = 0;
            for (int j = from2; j < to2; j++) {
                bigMatrix[i][j] = smallMatrix[index1][index2];
                index2 += 1;
            }
            index1 += 1;
        }

        return bigMatrix;
    }
}
