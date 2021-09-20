package duke;

import java.util.Locale;

public class Parser {

    public String getTaskType(String input) {
        String[] inputWords = input.split(" ");
        return inputWords[0].toLowerCase();
    }

    public String getTaskBody(String input) {
        String[] inputWords = input.split(" ");
        return inputWords[1];
    }

    public String getTaskDescription(String input) {
        String[] separatedInput = input.split("/");
        return separatedInput[0].trim();
    }

    public String getTaskDate(String input) {
        String[] separatedInput = input.split("/", 2);
        return separatedInput[1].trim();
    }

    public int getInputWordCount(String input) {
        return input.split(" ").length;
    }

    public int getInputIndex(String input) {
        return Integer.parseInt(input.split(" ", 2)[1].trim());
    }

    public boolean isNoTaskDescription(String input) {
        String[] inputWords = getTaskDescription(input).split(" ");
        return inputWords.length == 1;
    }

    public boolean isNoDate(String input) {
        String[] inputWords = input.split(" ", 2)[1].split("/");
        return inputWords.length <= 1;
    }

    public boolean isNoSeparator(String input) {
        return !input.contains("/");
    }

    public boolean isWrongFormat(String input) {
        boolean isNoSeparator = isNoSeparator(input);
        boolean isNoTask = isNoTaskDescription(input);
        boolean isNoDate = isNoDate(input);
        return isNoSeparator || isNoTask || isNoDate;
    }

}
