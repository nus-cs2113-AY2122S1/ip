package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class Parser {

    /**
     * Takes the user's inputs and calls different methods that carries out the user's commands depending on the user's
     * inputs.
     *
     * @param userInput Line input by the user.
     */
    public static void parseInput(String userInput)  {
        boolean isPrint = true;
        String[] splitInputs = userInput.split(" ");
        String userCommand = splitInputs[0];
        try {
            switch (userCommand) {
            case "find":
                TaskList.findEntry(splitInputs);
                break;
            case "list":
                TaskList.getList();
                break;
            case "done":
                int completeIndex = Integer.parseInt(splitInputs[1]);
                TaskList.completeTask(completeIndex, isPrint);
                break;
            case "bye":
                break;
            case "delete":
                String deleteIndex = splitInputs[1];
                TaskList.deleteTask(deleteIndex);
                break;
            case "event":
            case "todo":
            case "deadline":
                TaskList.taskHandler(userCommand, userInput, isPrint);
                break;
            default:
                System.out.println("\tSorry mate, I don't understand what you are trying to get me to do.");
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\tPlease check the index of your input");
        }
    }

    /**
     * Creates an event task based on the input of the user and returns the new task.
     *
     * @param userLineInput Line input by the user.
     * @return Event task created from the user line input
     * @throws DukeExceptions if "/by" is used instead of "/at"
     */
    public static Task getEventTask(String userLineInput) throws DukeExceptions {
        int infoIndex = userLineInput.indexOf("/");
        int endOfInfoIndex = infoIndex + 3;
        String taskDescription = userLineInput.substring(6, infoIndex);
        String eventTime = userLineInput.substring(infoIndex + 3);
        if (userLineInput.substring(infoIndex, endOfInfoIndex).equals("/by")) {
            throw new DukeExceptions("\tHey, please use proper formatting." +
                    System.lineSeparator() + "\tYou should add a /at before your due date.");
        }
        Task newEvent = new Event(taskDescription, eventTime);
        return newEvent;
    }

    /**
     * Creates a deadline task based on the input of the user and returns the new task.
     * @param userLineInput Line input by the user.
     * @return Event task created from the user line input
     * @throws DukeExceptions if "/at" is used instead of "/by"
     */
    public static Task getDeadlineTask(String userLineInput) throws DukeExceptions {
        int infoIndex = userLineInput.indexOf("/");
        int endOfInfoIndex = infoIndex + 3;
        String taskDescription = userLineInput.substring(9, infoIndex);
        String deadlineTime = userLineInput.substring(infoIndex + 3);
        if (userLineInput.substring(infoIndex, endOfInfoIndex).equals("/at")) {
            throw new DukeExceptions("\tHey, please use proper formatting." +
                    System.lineSeparator() + "\tYou should add a /by before your due date.");
        }
        Task newDeadline = new Deadline(taskDescription, deadlineTime);
        return newDeadline;
    }
}
