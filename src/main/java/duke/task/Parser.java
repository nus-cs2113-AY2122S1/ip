package duke.task;

import duke.DukeException;

public class Parser {
    private static final String FAREWELL = "     Bye. Hope to see you again soon!";
    private static final String TO_DO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String FIND = "find";
    private static final String ADD_SUCCESS = "     Nice! I've marked this task as done: ";
    private static final String DELETE_SUCCESS = "     Noted. I've removed this task:";
    private static final String SHOW_MATCHES = "     Here are the matching tasks in your list:";
    private static final String INVALID_COMMAND = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String INVALID_TASK_INDEX = "     ☹ OOPS!!! The task's index should be an integer.";

    /**
     * Parses input and executes respective command.
     *
     * @param input Input to be parsed.
     * @return False if "bye" command not entered.
     */
    public static boolean parse(String input) {
        String[] params = input.split(" ");
        String command = params[0];
        try {
            switch (command) {
            case BYE:
                System.out.println(FAREWELL);
                return true;
            case LIST:
                TaskManager.list();
                break;
            case DONE:
                int taskNumber = Integer.parseInt(params[1]);
                System.out.println(ADD_SUCCESS);
                TaskManager.setDone(params);
                System.out.println("       " + TaskManager.getName(taskNumber));
                break;
            case TO_DO:
            case DEADLINE:
            case EVENT:
                TaskManager.add(input);
                break;
            case DELETE:
                System.out.println(DELETE_SUCCESS);
                TaskManager.deleteTask(params);
                break;
            case FIND:
                System.out.println(SHOW_MATCHES);
                String query = params[1];
                TaskManager.find(query);
                break;
            default:
                System.out.println(INVALID_COMMAND);
            }
        } catch (DukeException e) {
            System.out.println("     ☹ OOPS!!! The description of a " + command + " cannot be empty.");
        } catch (NumberFormatException e) {
            System.out.println(INVALID_TASK_INDEX);
        }
        return false;
    }
}
