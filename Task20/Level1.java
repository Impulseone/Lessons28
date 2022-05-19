public class Level1 {

    static State state = new State();

    public static String BastShoe(String command) {
        int operationNumber = Integer.parseInt(command.split(" ")[0]);
        String operationArg = command.length() > 1 ? command.substring(2) : "";
        switch (operationNumber) {
            case 1 -> state.add(operationArg);
            case 2 -> state.delete(Integer.parseInt(operationArg));
            case 3 -> {
                return state.getI(Integer.parseInt(operationArg));
            }
            case 4 -> state.undo();
            case 5 -> state.redo();
        }
        return state.changesList.get(state.position);
    }

    private static class State {
        String currentString = "";
        List<String> changesList = new ArrayList<>();
        int position;

        public State() {
            changesList.add(currentString);
            position = 0;
        }

        void add(String s) {
            clearOperationsIfNeeded();
            currentString += s;
            changesList.add(currentString);
            position = changesList.size() - 1;
        }

        void delete(int n) {
            clearOperationsIfNeeded();
            if (n > currentString.length()) currentString = "";
            else currentString = currentString.substring(0, currentString.length() - n);
            changesList.add(currentString);
            position = changesList.size() - 1;
        }

        String getI(int i) {
            char[] chars = currentString.toCharArray();
            if (i >= currentString.length()) return "";
            return String.valueOf(chars[i]);
        }

        void undo() {
            if (position > 0) {
                position -= 1;
                currentString = changesList.get(position);
            }
        }

        void redo() {
            if (position < changesList.size() - 1) position += 1;
        }

        void clearOperationsIfNeeded() {
            if (position < changesList.size() - 1) {
                String lastChange = changesList.get(position);
                changesList.clear();
                changesList.add(lastChange);
                currentString = lastChange;
            }
        }
    }
}
