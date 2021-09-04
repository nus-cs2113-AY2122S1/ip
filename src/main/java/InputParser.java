import java.util.Scanner;

public abstract class InputParser {
    public static final int TASK_INDEX = 1;

    public static final String SEPARATOR = " ";

    public static String getTaskDetails(String[] words) {
        StringBuilder taskName = new StringBuilder();
        for (int i = TASK_INDEX; i < words.length; i++) {
            taskName.append(words[i]);
            taskName.append(SEPARATOR);
        }
        return taskName.toString().trim();
    }

    public static String[] getCommandComponents(Scanner consoleInput) {
        String commandInput = consoleInput.nextLine();
        return commandInput.trim().split(SEPARATOR);
    }

    public static int getTaskNumber(String[] commandComponents) {
        return Integer.parseInt(commandComponents[TASK_INDEX]) - 1;
    }
}
