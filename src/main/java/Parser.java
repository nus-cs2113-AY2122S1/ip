public class Parser {

    private static final int DESCRIPTION_PARAMETERS = 2;
    private static final int FIRST_ARRAY_PARAMETER = 0;
    private static final int SECOND_ARRAY_PARAMETER = 1;
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";

    /**
     * Returns lateral location of the specified position.
     *
     * @param words String input by user.
     */
    public static void checkCommand(String[] words, String input) throws
            UnknownCommandException, DukeException {
        String[] descriptionInput = parseInput(words, input);
        switch (words[FIRST_ARRAY_PARAMETER]) {
        case COMMAND_BYE:
            Ui.printGoodbyeMessage();
            break;
        case COMMAND_LIST:
            Ui.printList();
            break;
        case COMMAND_DONE:
            int taskNumber;
            checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            taskNumber = Integer.parseInt(words[SECOND_ARRAY_PARAMETER]);
            //might need to catch errors in the future
            TaskList.checkDoneTask(taskNumber);
            break;
        case COMMAND_DELETE:
            checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            taskNumber = Integer.parseInt(words[SECOND_ARRAY_PARAMETER]);
            TaskList.deleteTask(taskNumber);
            break;
        case COMMAND_TODO:
            checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            Todo todo = new Todo(descriptionInput[FIRST_ARRAY_PARAMETER]);
            TaskList.addTask(todo);
            break;
        case COMMAND_DEADLINE:
            checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            checkTimeframe(descriptionInput);
            Deadline deadline = new Deadline(descriptionInput[FIRST_ARRAY_PARAMETER],
                    descriptionInput[SECOND_ARRAY_PARAMETER]);
            TaskList.addTask(deadline);
            break;
        case COMMAND_EVENT:
            checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            checkTimeframe(descriptionInput);
            Event event = new Event(descriptionInput[FIRST_ARRAY_PARAMETER],
                    descriptionInput[SECOND_ARRAY_PARAMETER]);
            TaskList.addTask(event);
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    private static void checkDescription(String command, String[] descriptionInput) throws DukeException {
        //want this function to throw another exception

        if (descriptionInput[FIRST_ARRAY_PARAMETER].equals("") ||
                descriptionInput[FIRST_ARRAY_PARAMETER].equals(" ")) {
            switch (command) {
            case COMMAND_TODO:
                throw new DukeException(ErrorMessage.EXCEPTION_MISSING_DESCRIPTION_TODO);
            case COMMAND_DEADLINE:
                throw new DukeException(ErrorMessage.EXCEPTION_MISSING_DESCRIPTION_DEADLINE);
            case COMMAND_EVENT:
                throw new DukeException(ErrorMessage.EXCEPTION_MISSING_DESCRIPTION_EVENT);
            default:
                throw new DukeException(ErrorMessage.EXCEPTION_MESSAGE_MISSING_PARAMETERS_AFTER_COMMAND);
            }
        }
    }

    private static void checkTimeframe(String[] descriptionInput) throws DukeException {
        if (descriptionInput[SECOND_ARRAY_PARAMETER].equals("") ||
                descriptionInput[SECOND_ARRAY_PARAMETER].equals(" ")) {
            throw new DukeException(ErrorMessage.EXCEPTION_MESSAGE_MISSING_PARAMETERS_AFTER_KEYWORD);
        }
    }

    private static String[] parseInput(String[] words, String input) {
        String[] output = new String[DESCRIPTION_PARAMETERS];
        output[0] = "";
        output[1] = "";
        if (words.length < 2) {
            return output;
        }
        String[] newWord = input.split(" ", 2);
        if (words[0].equals("deadline") && newWord[1].contains("/by")) {
            // need to try new outliers
            output = newWord[1].split(" /by ", 2);
        } else if (words[0].equals("event") && newWord[1].contains(" /at ")) {
            output = newWord[1].split(" /at ", 2);
        } else {
            output[0] = newWord[1];
        }
        return output;
    }
}

