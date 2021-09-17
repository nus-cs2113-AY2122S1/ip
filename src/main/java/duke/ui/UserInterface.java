package duke.ui;

import duke.manager.task.Task;

import java.util.ArrayList;

public class UserInterface {

    public static final String LOGO = System.lineSeparator()
            + "    ___   ______   ________  _______      ___   ____  _____   .--." + System.lineSeparator()
            + "  .'   `.|_   _ \\ |_   __  ||_   __ \\   .'   `.|_   \\|_   _|.'_\\/_'." + System.lineSeparator()
            + " /  .-.  \\ | |_) |  | |_ \\_|  | |__) | /  .-.  \\ |   \\ | |  '. /\\,.'" + System.lineSeparator()
            + " | |   | | |  __'.  |  _| _   |  __ /  | |   | | | |\\ \\| |    \"||\"" + System.lineSeparator()
            + " \\  `-'  /_| |__) |_| |__/ | _| |  \\ \\_\\  `-'  /_| |_\\   |_    || /\\`" + System.lineSeparator()
            + "  `.___.'|_______/|________||____| |___|`.___.'|_____|\\____|/\\ ||//\\)" + System.lineSeparator()
            + "                                                           (/\\\\||/" + System.lineSeparator()
            + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\||^^^^";

    public static final String HORIZONTAL_BAR = "__________________"
            + "__________________"
            + "__________________"
            + "_______________";

    public static final String INPUT_PROMPT = "> Enter Command: ";

    /**
     * Reads in a String and prints the identical String followed by a newline
     *
     * @param input String to be echo-ed
     */
    public static void echo(String input) {
        System.out.println(input);
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        String taskListAsString = "";
        int currentIndexInOnesIndexing;
        int offset = 2;
        for (int i = 0; i < taskList.size(); i++) {
            currentIndexInOnesIndexing = i + 1;
            taskListAsString += "  " + currentIndexInOnesIndexing + ". "
                    + taskList.get(i).getTaskDescriptionWithStatus();
            //add newline for all tasks except the last one in the list
            if (i <= (taskList.size() - offset)) {
                taskListAsString += System.lineSeparator();
            }
        }
        echo(HORIZONTAL_BAR + System.lineSeparator() + taskListAsString
                + System.lineSeparator() + HORIZONTAL_BAR);
    }

    public static void printMessage(String message) {
        echo(HORIZONTAL_BAR + System.lineSeparator() + message
                + System.lineSeparator() + HORIZONTAL_BAR);
    }

    /**
     * Print greeting message upon starting the program
     */
    public static void printGreetingMessage() {
        echo(HORIZONTAL_BAR + System.lineSeparator() + LOGO + System.lineSeparator()
                + "  Hello! I'm Oberon" + System.lineSeparator() + "  What can I do for you?"
                + System.lineSeparator() + HORIZONTAL_BAR);
    }

    /**
     * Print farewell message upon exiting the program
     */
    public static void printFarewellMessage() {
        echo(HORIZONTAL_BAR + System.lineSeparator() + "  Goodbye. Hope to see you again soon!"
                + System.lineSeparator() + LOGO);
    }
}
