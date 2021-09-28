package duke.command;

public class Parser {
    public static Integer getIndexForDelete(String line) {
        return Integer.parseInt(line.substring(7)) - 1;
    }
    public static Integer getIndexForDone(String line) {
        return Integer.parseInt(line.substring(5)) - 1;
    }

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
