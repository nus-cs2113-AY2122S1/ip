package kate.ui;

import kate.common.Message;
import kate.task.Task;

import java.util.Scanner;

public class KateUI {
    private static final String LOGO_INDENTATION = "                    ";
    private static final String TEXT_WRAPPER = "================================="
            + "====================================\n";
    private static final String TEXT_FIELD_HEADER = ">> ";

    private static final String LOGO_KATE = LOGO_INDENTATION + " _  __     _\n"
            + LOGO_INDENTATION + "| |/ /__ _| |_ ___\n"
            + LOGO_INDENTATION + "| ' </ _` |  _/ -_)\n"
            + LOGO_INDENTATION + "|_|\\_\\__,_|\\__\\___|\n";

    private static final String GREET_MESSAGE = Message.TEXT_INDENTATION
            + "This is Kate, your personal assistant ;)\n"
            + Message.TEXT_INDENTATION + "How can I help you?\n";
    private static final String BYE_MESSAGE = Message.TEXT_INDENTATION
            + "Leaving already? Oh well see you again soon!\n";

    private static final String FAILURE_MESSAGE_INVALID_COMMAND = Message.TEXT_INDENTATION
            + "Please enter a valid command!\n"
            + Message.TEXT_INDENTATION + "Type <help> for the list of commands\n";


    private Scanner in;

    public KateUI() {
        in = new Scanner(System.in);
    }

    public String getInput() {
        System.out.print(TEXT_FIELD_HEADER);
        return in.nextLine();
    }

    /**
     * Greet message from Kate
     */
    public void printGreetMessage() {
        System.out.println(LOGO_KATE);
        printMessage(GREET_MESSAGE);
    }

    /**
     * Prints a general message that is nicely formatted
     *
     * @param message A string input to be formatted and printed
     */
    public void printMessage(String message) {
        String formattedMsg = TEXT_WRAPPER + message + TEXT_WRAPPER;
        System.out.println(formattedMsg);
    }

    /**
     * Bye message from Kate
     */
    public void printByeMessage() {
        printMessage(BYE_MESSAGE);
    }

    /**
     * Prints a confirmation that a task has been added
     *
     * @param addedTask Task object of the newly added Task
     * @param taskSize  Number of tasks
     */
    public void printAddedTask(Task addedTask, int taskSize) {
        String formattedMsg = Message.TEXT_INDENTATION + "Okay, I have added this task!\n"
                + Message.TEXT_INDENTATION + "  " + addedTask.getTaskInfo() + "\n"
                + Message.TEXT_INDENTATION + "You currently have (" + taskSize
                + ") tasks in your list :)\n";
        printMessage(formattedMsg);
    }

    /**
     * Prints the list of all available commands
     * Only prints when user key in an invalid command
     */
    public void printHelpPage() {
        String helpText = Message.TEXT_INDENTATION + "Please enter only the following commands: \n"
                + Message.TEXT_INDENTATION + "- " + Message.COMMAND_TODO + "\n"
                + Message.TEXT_INDENTATION + "- " + Message.COMMAND_DEADLINE + "\n"
                + Message.TEXT_INDENTATION + "- " + Message.COMMAND_EVENT + "\n"
                + Message.TEXT_INDENTATION + "- " + Message.COMMAND_DONE + "\n"
                + Message.TEXT_INDENTATION + "- " + Message.COMMAND_DELETE + "\n"
                + Message.TEXT_INDENTATION + "- " + Message.COMMAND_LIST + "\n"
                + Message.TEXT_INDENTATION + "- " + Message.COMMAND_BYE + "\n";
        printMessage(helpText);
    }

    /**
     * Prints the error message for an invalid command
     */
    public void printInvalidCommandMessage() {
        printMessage(FAILURE_MESSAGE_INVALID_COMMAND);
    }

}
