public class Level1 {
    public static int ConquestCampaign(int N, int M, int L, int[] battalion) {
        int daysCount = 1;

        Field[] allFields = generateAllFields(N, M);
        Field[] battalionFields = findBattalionFields(battalion);
        stepToStartFields(allFields, battalionFields);

        while (checkIsNotApproved(allFields)) {
            daysCount += 1;
            Field[] possibleFields = findPossibleFields(battalionFields);
            stepToPossibleFields(allFields, possibleFields);
            battalionFields = findApprovedFields(allFields);
        }
        return daysCount;
    }

    private static Field[] findApprovedFields(Field[] allFields) {
        Field[] approvedFields = new Field[allFields.length];
        for (int i = 0; i < allFields.length; i++) {
            if (allFields[i].isApproved) approvedFields[i] = allFields[i];
        }
        approvedFields = Arrays.stream(approvedFields).filter(Objects::nonNull).toArray(Field[]::new);
        return approvedFields;
    }

    private static void stepToStartFields(Field[] allFields, Field[] battalionFields) {
        for (Field field : allFields) {
            for (Field battalionField : battalionFields) {
                if (field.equalsByCoordinate(battalionField)) {
                    field.isApproved = true;
                    break;
                }
            }
        }
    }

    private static boolean checkIsNotApproved(Field[] fields) {
        for (Field field : fields) {
            if (!field.isApproved) {
                return true;
            }
        }
        return false;
    }

    private static void stepToPossibleFields(Field[] allFields, Field[] possibleFields) {
        for (Field resultField : allFields) {
            for (Field possibleField : possibleFields) {
                if (resultField.equalsByCoordinate(possibleField)) {
                    resultField.isApproved = true;
                    break;
                }
            }
        }
    }

    private static Field[] findPossibleFields(Field[] battalionFields) {
        Field[] possibleFields = new Field[battalionFields.length * 4];
        int counter = 0;
        for (Field battalionField : battalionFields) {
            int N = battalionField.N;
            int M = battalionField.M;
            possibleFields[counter] = new Field(N + 1, M, true);
            possibleFields[counter + 1] = new Field(N - 1, M, true);
            possibleFields[counter + 2] = new Field(N, M + 1, true);
            possibleFields[counter + 3] = new Field(N, M - 1, true);
            counter += 4;
        }
        return possibleFields;
    }

    private static Field[] findBattalionFields(int[] battalion) {
        Field[] battalionFields = new Field[battalion.length / 2];
        for (int i = 0; i < battalionFields.length; i++) {
            battalionFields[i] = new Field(battalion[i], battalion[i + 1], true);
        }
        return battalionFields;
    }

    private static Field[] generateAllFields(int N, int M) {
        int[] variantsN = generateVariantsMas(N);
        int[] variantsM = generateVariantsMas(M);
        Field[] allFields = new Field[N * M];
        int fieldPos = 0;
        for (int i = 1; i <= variantsN.length; i++) {
            for (int j = 1; j <= variantsM.length; j++) {
                allFields[fieldPos] = new Field(i, j, false);
                fieldPos += 1;
            }
        }
        return allFields;
    }

    private static int[] generateVariantsMas(int length) {
        int[] variants = new int[length];
        for (int i = 1; i <= length; i++) {
            variants[i - 1] = i;
        }
        return variants;
    }

    private static class Field {
        private final int N;
        private final int M;
        boolean isApproved;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Field field = (Field) o;

            if (N != field.N) return false;
            if (M != field.M) return false;
            return isApproved == field.isApproved;
        }

        @Override
        public int hashCode() {
            int result = N;
            result = 31 * result + M;
            result = 31 * result + (isApproved ? 1 : 0);
            return result;
        }

        public boolean equalsByCoordinate(Field field) {
            return field.N == N && field.M == M;
        }

        public Field(int n, int m, boolean isApproved) {
            N = n;
            M = m;
            this.isApproved = isApproved;
        }
    }
}
