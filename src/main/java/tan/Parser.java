package tan;

import java.util.regex.PatternSyntaxException;

public class Parser {
    /**
     * Returns the command of the user's input.
     * Takes in the whole user input
     * splits it along the spaces & returns
     * the first "word" of the string.
     *
     * @param x The whole user input as a String.
     * @return The Command in String.
     */
    public static String getCommand(String x) {
        return x.split(" ")[0];
    }

    /**
     * Returns the index of the desired task in string.
     * Else, -1. It takes in the user input as a string
     * and assumes the index is after the first space.
     * Note that this function does not verify if the
     * index is within rage of the list.
     *
     * @param input The whole user's input as a String.
     * @return The Index as an integer, else -1.
     */
    public static int getIndexFromInput(String input) {
        int index = -1;
        try {
            String[] listOfInputs = input.split(" ");
            index = Integer.parseInt(listOfInputs[1]);
        } catch (NumberFormatException e) {
            System.out.println("Index not recognized. Try again!");
            index = -1;
        } catch (ArrayIndexOutOfBoundsException exp) {
            System.out.println("Please check your input.");
            return -1;
        } catch (PatternSyntaxException exp) {
            System.out.println("Please stop using the app and contact an Admin!");
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Error in converting Index." + System.lineSeparator() + e);
            e.printStackTrace();
            return -1;
        }

        return index;
    }

    /**
     * Returns the index in integer.
     * Else, Returns -1 if the input can't be converted into an int.
     * Takes in the whole user input as a String
     * splits it into an array along spaces
     * and assumes the user keyed in the corresponding
     * index after the first space.
     *
     * @param x The whole user input as a string.
     * @return The index of the task in integer, else -1.
     */
    public static int getIndexOfTask(String x) {
        int taskIndex = -1;
        try {
            taskIndex = getIndexFromInput(x);
            if (taskIndex < 0) {
                //If user keyed in -ve number.
                throw new NumberFormatException();
            }
        } catch (NumberFormatException exp) {
            System.out.println("Please input a proper index!");
            return -1;
        } catch (Exception exp) {
            System.out.println("Error: " + exp);
            System.out.println("Please try again.");
            return -1;
        }
        return taskIndex;
    }
}
