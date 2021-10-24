import java.io.IOException;
import java.util.Scanner;

public class Parser {


    /**
     * This method is called in the main method to check the command the user inputs.
     * It checks to see if the String input which is scanned in by scanner contains the
     * command keywords stored in Keywords class
     *
     * @return nil, this is a void method
     */
    public static void commandChecker() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        while (!(input.equals(Keywords.INPUT_BYE))) {

            if (input.equals(Keywords.INPUT_LIST)) {
                input = TaskList.printList(in);

            } else if (input.contains(Keywords.INPUT_DONE)) {
                input = doneInputCommand(input, in);

            } else if (input.contains(Keywords.INPUT_TODO)) {
                input = todoInputCommand(input, in);

            } else if (input.contains(Keywords.INPUT_DEADLINE)) {
                input = deadlineInputCommand(input, in);

            } else if (input.contains(Keywords.INPUT_EVENT)) {
                input = eventInputCommand(input, in);

            } else if (input.contains(Keywords.INPUT_DELETE)) {
                input = deleteInputCommand(input, in);

            } else if (input.contains(Keywords.INPUT_FIND)) {
                TaskList.findList(input);
                input = in.nextLine();

            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");//overall error check
                input = in.nextLine();

            }
        }
        System.out.println("    Bye! Hope to see you again soon!");

    }

    /**
     * This method is called when the user inputs "delete ..."
     * it calls the testDeleteTask method in TaskList class and the
     * replaceAllTasks method in Storage class.
     *
     * @param in scanner input
     * @param input user's command
     * @return nil, this is a void method
     * @throws NumberFormatException if delete command does not contain a valid index number of
     * task to be deleted
     * @throws ArrayIndexOutOfBoundsException if there is no number typed in after delete command
     * @throws IOException if there is incorrect input or output
     */
    private static String deleteInputCommand(String input, Scanner in) {
        try {
            input = TaskList.testDeleteTask(input, in);
            Storage.replaceAllTasks();

        } catch (NumberFormatException e8) {
            System.out.println("Did you type in a number?");
            input = in.nextLine();

        } catch (ArrayIndexOutOfBoundsException e9) {
            System.out.println("Finish the command!");
            input = in.nextLine();

        } catch (IOException e34) {
            System.out.println("IOexception, incorrect input or output");
            input = in.nextLine();
        }
        return input;
    }

    /**
     * This method is called when the user inputs "event ..."
     * it calls the getEventMethod method in TaskList class and the
     * appendEvent method in Storage class.
     *
     * @param in scanner input
     * @param input user's command
     * @return input, containing the user's next commands
     * @throws StringIndexOutOfBoundsException if the user has a typo while typing event
     * @throws ArrayIndexOutOfBoundsException if the user's input does not have the "/at"
     */
    private static String eventInputCommand(String input, Scanner in) {
        try {
            Storage.appendEvent(input); //add new entry to duke.txt
            Task whatEvent = TaskList.getEventMethod(input);
            System.out.println("    Got it. I've added this task:\n" + "    " + whatEvent + "\n"
                    + "    Now you have " + Duke.tasks.size() + " tasks in the list.");

            input = in.nextLine();

        } catch (StringIndexOutOfBoundsException e6) {
            System.out.println("input = " + input + "\n☹ OOPS!!! Please retype your input!");
            input = in.nextLine();

        } catch (ArrayIndexOutOfBoundsException e7) {
            System.out.println("you have typed in = " + input + "\nw☹ OOPS!!! where is your /at brother? ");
            input = in.nextLine();
        }
        return input;
    }

    /**
     * This method is called when the user inputs "deadline ..."
     * it calls the getDeadlineMethod method in TaskList class and the
     * appendDeadline method in Storage class.
     *
     * @param in scanner input
     * @param input user's command
     * @return input, containing the user's next commands
     * @throws StringIndexOutOfBoundsException if the user has a typo while typing event
     * @throws ArrayIndexOutOfBoundsException if the user's input does not have the "/by"
     */
    private static String deadlineInputCommand(String input, Scanner in) {
        try {
            Storage.appendDeadline(input); //add new entry to duke.txt
            Task whatDeadline = TaskList.getDeadlineMethod(input);
            System.out.println("    Got it. I've added this task:\n" + "    " + whatDeadline + "\n"
                    + "    Now you have " + Duke.tasks.size() + " tasks in the list.");

            input = in.nextLine();

        } catch (StringIndexOutOfBoundsException e1) {
            System.out.println("input = " + input + "\n☹ OOPS!!! Please retype your input!");
            input = in.nextLine();

        } catch (ArrayIndexOutOfBoundsException e2) {
            System.out.println("you have typed in = " + input + "\n☹ OOPS!!! where is your /by my friend? ");
            input = in.nextLine();
        }
        return input;
    }
    /**
     * This method is called when the user inputs "todo ..."
     * it calls the testInput method in TaskList class and the
     * appendTodo method in Storage class.
     *
     * @param in scanner input
     * @param input user's command
     * @return input, containing the user's next commands
     * @throws DukeExceptions if description following todo keyword is empty
     */
    private static String todoInputCommand(String input, Scanner in) {
        try {
            TaskList.testInput(input); // test todo input
            Storage.appendTodo(input); // add new entry to duke.txt
            Task whatToDo = TaskList.getToDoMethod(input);
            System.out.println("    Got it. I've added this task:\n" + "    " + whatToDo + "\n"
                    + "    Now you have " + Duke.tasks.size() + " tasks in the list.");

            input = in.nextLine();

        } catch (DukeExceptions e5) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            input = in.nextLine();

        }
        return input;
    }

    /**
     * This method is called when the user inputs "done..."
     * it calls the testTaskDone method in TaskList class and the
     * replaceAllTasks method in Storage class.
     *
     * @param in scanner input
     * @param input user's command
     * @return input, containing the user's next commands
     * @throws NumberFormatException if delete command does not contain a valid index number of
     * task to be deleted
     * @throws ArrayIndexOutOfBoundsException if there is no number typed in after delete command
     * @throws IOException if there is incorrect input or output
     */
    private static String doneInputCommand(String input, Scanner in) {
        try {
            input = TaskList.testTaskDone(input, in);
            Storage.replaceAllTasks();

        } catch (NumberFormatException e3) {
            System.out.println("Did you type in a number?");
            input = in.nextLine();

        } catch (ArrayIndexOutOfBoundsException e4) {
            System.out.println("Finish the command!");
            input = in.nextLine();

        } catch (IOException e33) {
            System.out.println("IOexception, incorrect input or output");
            input = in.nextLine();

        }
        return input;
    }
}
