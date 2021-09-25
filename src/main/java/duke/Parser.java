package duke;

import java.util.Arrays;

public class Parser {
    public static final String BY_DIVIDER = "/by";
    public static final String AT_DIVIDER = "/at";

    /**
     * Splits the input string and returns the command at the start of the string
     *
     * @param args the input string
     * @return the command the user entered
     */
    public static String getCommand(String args) {
        String[] words = args.split(" ");
        String[] commandArray = Arrays.copyOf(words, 1);
        String command = getFormattedString(Arrays.toString(commandArray));
        return command;
    }

    /**
     * Splits the input string and returns the item after the command
     *
     * @param args the input string
     * @return the item after the command the user entered
     */
    public static String getItem(String args) {
        String[] words = args.split(" ");
        String[] itemArray = Arrays.copyOfRange(words, 1, words.length);
        String item = getFormattedString(Arrays.toString(itemArray));
        return item;
    }

    /**
     * Formats the string that was converted from an array. Delete brackets and commas
     *
     * @param args the input string that was converted from an array
     * @return the formatted string without brackets and commas
     */
    public static String getFormattedString(String args) {
        String formattedString = args.replace(",", "")
                .replace("]", "")
                .replace("[", "")
                .trim();
        return formattedString;
    }


    /**
     * Checks if the input string has an empty item after a command
     *
     * @param args the input string the user entered
     * @return true if the item after the command is empty, false otherwise
     */
    public static boolean isEmptyItem(String args) {
        if (getItem(args).equals("")) {
            return true;
        }
        return false;
    }

    /**
     * returns if there is a time entered for a deadline or event task
     *
     * @param args the input string the user entered
     * @return true if there is a time entered, false otherwise
     */
    public static boolean canGetTime(String args) {
        String description = getItem(args);
        String[] time = description.substring(description.indexOf("/")).split(" ");
        if (time.length < 2) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the item from the user input is invalid for deadline and event tasks
     *
     * @param args the item to be checked
     * @return true if the item does not contain a '/by' or does not have time after /by, false otherwise
     */
    public static boolean isInvalidDeadline(String args) {
        if (!getItem(args).contains(BY_DIVIDER)) {
            return true;
        }
        if (!canGetTime(args)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the item from the user input is invalid for deadline and event tasks
     *
     * @param args the item to be checked
     * @return true if the item does not contain a '/at' or does not have time after /at, false otherwise
     */
    public static boolean isInvalidEvent(String args) {
        if (!getItem(args).contains(AT_DIVIDER)) {
            return true;
        }
        if (!canGetTime(args)) {
            return true;
        }
        return false;
    }
}
