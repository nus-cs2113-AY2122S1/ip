package duke.command;

public class Parser {
    public static Integer getIndexForDelete(String line) {
        return Integer.parseInt(line.substring(7)) - 1;
    }

    public static Integer getIndexForDone(String line) {
        return Integer.parseInt(line.substring(5)) - 1;
    }

    /**
     * Returns the index of the word "/by" in the array of strings generated from the input line.
     * @param line The whole line of input from the command line.
     * @return Index position of the the word "/by"
     */
    public static Integer getIndexForDeadline(String line) {
        String[] words = line.split(" ");
        int index = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/by")) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Returns the index of the word "/at" in the array of strings generated from the input line.
     * @param line The whole line of input from the command line.
     * @return Index position of the the word "/at"
     */
    public static Integer getIndexForEvent(String line) {
        String[] words = line.split(" ");
        int index = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/at")) {
                index = i;
                break;
            }
        }
        return index;
    }
}
