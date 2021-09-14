public class Functions {

    //CONSTANTS
    public static final String SEPARATOR = "____________________________________________________________\n";
    public static final String logo = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    //VISUALS
    public static void printGreeting() {
        System.out.println("Hello from\n"
                + logo
                + SEPARATOR
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + SEPARATOR);
    }

    public static void printFarewell() {
        System.out.println(SEPARATOR
                + " Bye. Hope to see you again soon!\n"
                + SEPARATOR);
    }

    //PROCESS USER INPUT
    public static String[] processUserInput(String userInput, String[] splitUserInput, String firstWord, int userInputLength) {
        String[] processedUserInput = new String[3];
        processedUserInput[0] = firstWord;
        switch (firstWord) {
        case "list":
        case "bye":
            //Checks that "list"/"bye" is the only word
            if (userInputLength > 1) {
                DukeException.printFormatError();
                processedUserInput[0] = "error";
            }
            break;
        case "done":
            //Checks that a task number is entered
            if (userInputLength != 2) {
                DukeException.printFormatError();
                processedUserInput[0] = "error";
                break;
            }
            //Checks that the task number is a number
            try {
                Integer.parseInt(splitUserInput[1]);
                int doneSpaceIndex = userInput.indexOf(" ");
                String doneDetail = userInput.substring(doneSpaceIndex + 1);
                processedUserInput[1] = doneDetail;
            } catch (NumberFormatException e) {
                DukeException.printFormatError();
                processedUserInput[0] = "error";
            }
            break;
        case "todo":
            //Checks that a description is entered
            if (userInputLength < 2) {
                DukeException.printMissingParameterError("todoDescription");
                processedUserInput[0] = "error";
                break;
            }
            int todoSpaceIndex = userInput.indexOf(" ");
            String todoDetail = userInput.substring(todoSpaceIndex + 1);
            processedUserInput[1] = todoDetail;
            break;
        case "deadline":
            //Checks that a description is entered
            if (userInputLength < 4) {
                DukeException.printMissingParameterError("deadlineBoth");
                processedUserInput[0] = "error";
                break;
            }
            //Checks that a deadline is entered
            if (!userInput.contains("/by")) {
                DukeException.printMissingParameterError("deadlineDeadline");
                processedUserInput[0] = "error";
                break;
            }
            int deadlineSpaceIndex = userInput.indexOf(" ");
            int deadlineSlashIndex = userInput.indexOf("/");
            //Checks that a description is entered
            if (deadlineSpaceIndex + 1 == deadlineSlashIndex) {
                DukeException.printMissingParameterError("deadlineDescription");
                processedUserInput[0] = "error";
            } else {
                String deadlineDetail = userInput.substring(deadlineSpaceIndex + 1, deadlineSlashIndex - 1);
                String deadlineTime = userInput.substring(deadlineSlashIndex + 1);
                processedUserInput[1] = deadlineDetail;
                processedUserInput[2] = deadlineTime;
            }
            break;
        case "event":
            //Checks that a description is entered
            if (userInputLength < 4) {
                DukeException.printMissingParameterError("eventBoth");
                processedUserInput[0] = "error";
                break;
            }
            //Checks that an event date is entered
            if (!userInput.contains("/at")) {
                DukeException.printMissingParameterError("eventDate");
                processedUserInput[0] = "error";
                break;
            }
            int eventSpaceIndex = userInput.indexOf(" ");
            int eventSlashIndex = userInput.indexOf("/");
            //Checks that a description is entered
            if (eventSpaceIndex + 1 == eventSlashIndex) {
                DukeException.printMissingParameterError("eventDescription");
                processedUserInput[0] = "error";
            } else {
                String eventDetail = userInput.substring(eventSpaceIndex + 1, eventSlashIndex - 1);
                String eventTime = userInput.substring(eventSlashIndex + 1);
                processedUserInput[1] = eventDetail;
                processedUserInput[2] = eventTime;
            }
            break;
        default:
            DukeException.printInvalidCommandError();
            processedUserInput[0] = "error";
            break;
        }
        return processedUserInput;
    }

    //DUKE COMMANDS
    public static void printTaskList(Task[] taskList, int taskListSize) {
        System.out.println(SEPARATOR
                + " Here are the tasks in your list:");
        for (int i = 0; i < taskListSize; i++) {
            int j = i + 1;
            System.out.println(" " + j + "." + taskList[i]);
        }
        System.out.println(SEPARATOR);
    }

    public static void markAsDone(Task[] taskList, int taskListSize, int taskNumber) {
        if (taskNumber > taskListSize - 1) {
            DukeException.printDoneError();
        } else {
            taskList[taskNumber].setDone();
            System.out.println(SEPARATOR
                    + " Nice! I've marked this task as done:\n"
                    + "  " + taskList[taskNumber] + "\n"
                    + SEPARATOR);
        }
    }

    public static Task createTask(String[] processedUserInput) {
        String category = processedUserInput[0];
        String description = processedUserInput[1];
        String details = processedUserInput[2];
        Task newTask;
        switch (category) {
        case "todo":
            newTask = new ToDo(description);
            break;
        case "deadline":
            newTask = new Deadline(description, details);
            break;
        case "event":
            newTask = new Event(description, details);
            break;
        default:
            newTask = new Task("Default");
        }
        return newTask;
    }

    public static void addTask(Task[] taskList, int taskListSize, Task newTask) {
        taskList[taskListSize] = newTask;
        System.out.println(SEPARATOR
                + " Got it. I've added this task:\n"
                + "  " + newTask + "\n"
                + " Now you have " + (taskListSize + 1) + " tasks in the list.\n"
                + SEPARATOR);
    }

}
