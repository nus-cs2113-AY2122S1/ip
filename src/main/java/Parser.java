public class Parser {
    private final static String TASK_TYPE_DONE = "done";
    private final static String TASK_TYPE_DELETE = "delete";
    private final static String TASK_TYPE_FIND = "find";

    private final static String TASK_EXIT = "bye";
    private final static String TASK_LIST = "list";

    private final static String TASK_TYPE_TODO = "todo";
    private final static String TASK_TYPE_EVENT = "event";
    private final static String TASK_TYPE_DEADLINE = "deadline";

    private final static String EVENT_DATE_INDICATOR = "/at";
    private final static String DEADLINE_DATE_INDICATOR = "/by";

    private final static int ARRAY_OF_LENGTH_2 = 2;
    private static final int INDEX_TASK_TYPE = 0;
    private static final int INDEX_MAIN_TASK = 0;
    private static final int INDEX_TASK_DATE = 1;
    private static final int INDEX_TASK_NUMBER = 1;
    private static final int TASK_NUMBER_OFFSET = 1;

    // We split the input given by the user with a single white space
    private final static String USER_INPUT_SPLITTER = " ";

    public Parser() {

    }

    /**
     * @param userInput The input given by the user
     * @return The task type extracted from the user input
     * @throws InvalidTaskTypeException
     * We throw this exception when the task type has not been defined in duke
     * */
    private String getTaskTypeFromUserInput(String userInput)
            throws InvalidTaskTypeException {
        String[] userInputSplit = userInput.split(USER_INPUT_SPLITTER);
        String taskType = userInputSplit[INDEX_TASK_TYPE];
        switch (taskType) {
            case TASK_TYPE_DEADLINE:
                break;
            case TASK_TYPE_TODO:
                break;
            case TASK_TYPE_EVENT:
                break;
            case TASK_EXIT:
                break;
            case TASK_LIST:
                break;
            case TASK_TYPE_DONE:
                break;
            case TASK_TYPE_DELETE:
                break;
            case TASK_TYPE_FIND:
                break;
            default:
                throw new InvalidTaskTypeException();
        }
        return taskType;
    }

    /**
     * @param userInput The input given by the user
     * @return The task given by the user
     * @throws EmptyDescriptionException
     * We throw this exception when the task given by the user is empty
     * */
    private String getTask(String userInput)
            throws EmptyDescriptionException {
        String[] userInputSplitArray = userInput.split(USER_INPUT_SPLITTER);
        if (userInputSplitArray.length < ARRAY_OF_LENGTH_2) {
            String taskType = userInputSplitArray[INDEX_TASK_TYPE];
            throw new EmptyDescriptionException(taskType);
        } else {
            String task = "";
            // We split the user input using white spaces,
            // we then combind the userInputArray back into one string
            // starting from index 1 to get the task description
            for (int i = 1; i < userInputSplitArray.length; i++) {
                task = task + " " + userInputSplitArray[i];
            }
            task = task.stripLeading();
            return task;
        }
    }

    /**
     * @param task The task given by the user
     * @param dateIndicator The seperator to get the date out from the task
     * @return The task given by the user without date
     * @throws InvalidDateIndicatorException
     * We throw this exception when the not date indicator is given by the user
     * */
    private String getMainTaskFromTask(String task, String dateIndicator)
            throws InvalidDateIndicatorException {
        String[] taskDescriptionSplitByDate = task.split(dateIndicator);

        // If the taskDescriptionSplitByDate has length less than 2,
        // it means that the date indicator is not present.
        // We throw the InvalidDateIndicatorException in this case.
        if (taskDescriptionSplitByDate.length < ARRAY_OF_LENGTH_2) {
            throw new InvalidDateIndicatorException();
        } else {
            String mainTask = taskDescriptionSplitByDate[INDEX_MAIN_TASK];
            return mainTask;
        }
    }

    /**
     * @param task The task given by the user
     * @param dateIndicator The separator to get the date out from the task
     * @return The date given by the user
     * @throws InvalidDateIndicatorException
     * We throw this exception when the not date indicator is given by the user
     * */
    private String getDateFromTask(String task, String dateIndicator)
            throws InvalidDateIndicatorException {
        String[] taskDescriptionSplitByDate = task.split(dateIndicator);
        if (taskDescriptionSplitByDate.length < ARRAY_OF_LENGTH_2) {
            throw new InvalidDateIndicatorException();
        } else {
            String date = taskDescriptionSplitByDate[INDEX_TASK_DATE];
            return date;
        }
    }

    /**
     * @param userInput The input given by the user
     * @return The task number given by the user
     * */
    private int getTaskNumber(String userInput) {
        String[] userInputArray = userInput.split(USER_INPUT_SPLITTER);
        int taskNumber = Integer.parseInt(userInputArray[INDEX_TASK_NUMBER]);

        // We subtract 1 from taskNumber because arraylist index starts from 0
        taskNumber = taskNumber - TASK_NUMBER_OFFSET;
        return taskNumber;
    }

    /**
     * @param userInput The input given by the user
     * @return The task to find given by the user
     * */
    private String getTaskToFind(String userInput)
            throws EmptyDescriptionException {
        String taskToFind = getTask(userInput);
        return taskToFind;
    }

    /**
     * @param userInput The input given by the user
     * @return The command based on the task type
     * @throws InvalidTaskTypeException
     * We throw this exception when the task type has not been defined in duke
     * @throws EmptyDescriptionException
     * We throw this exception when the task description is empty
     * @throws InvalidDateIndicatorException
     * We throw this exception when the date indicator in the task description is not found
     * */
    public Command parseUserInput(String userInput)
            throws InvalidTaskTypeException, EmptyDescriptionException, InvalidDateIndicatorException {
        String taskType = getTaskTypeFromUserInput(userInput);
        Command returnCommandFromInput;
        switch (taskType) {
            case TASK_TYPE_TODO:
                returnCommandFromInput = generateTaskCommand(taskType, userInput);
                break;
            case TASK_TYPE_DEADLINE:
                returnCommandFromInput = generateTaskCommand(taskType, userInput);
                break;
            case TASK_TYPE_EVENT:
                returnCommandFromInput = generateTaskCommand(taskType, userInput);
                break;
            case TASK_EXIT:
                returnCommandFromInput = new ByeCommand();
                break;
            case TASK_LIST:
                returnCommandFromInput = new ListCommand();
                break;
            case TASK_TYPE_DONE:
                int doneTaskNumber = getTaskNumber(userInput);
                returnCommandFromInput = new DoneCommand(doneTaskNumber);
                break;
            case TASK_TYPE_DELETE:
                int deleteTaskNumber = getTaskNumber(userInput);
                returnCommandFromInput = new DeleteCommand(deleteTaskNumber);
                break;
            case TASK_TYPE_FIND:
                String taskToFind = getTaskToFind(userInput);
                returnCommandFromInput = new FindCommand(taskToFind);
                break;
            default:
                throw new InvalidTaskTypeException();
        }
        return returnCommandFromInput;
    }

    /**
     * @param taskType The task type given by the user
     * @param userInput The input given by the user
     * @return The command based on the task type
     * @throws InvalidTaskTypeException
     * We throw this exception when the task type has not been defined in duke
     * */
    private Command generateTaskCommand(String taskType, String userInput)
            throws InvalidTaskTypeException, EmptyDescriptionException, InvalidDateIndicatorException {
        String task = getTask(userInput);
        Command commandGenerated;
        switch (taskType) {
            case TASK_TYPE_TODO:
                commandGenerated =  new AddCommand(taskType, task);
                break;
            case TASK_TYPE_DEADLINE:
                String deadlineTask = getMainTaskFromTask(task, DEADLINE_DATE_INDICATOR);
                String deadlineDate = getDateFromTask(task, DEADLINE_DATE_INDICATOR);
                commandGenerated = new AddCommand(taskType, deadlineTask, deadlineDate);
                break;
            case TASK_TYPE_EVENT:
                String eventTask = getMainTaskFromTask(task, EVENT_DATE_INDICATOR);
                String eventDate = getDateFromTask(task, EVENT_DATE_INDICATOR);
                commandGenerated = new AddCommand(taskType, eventTask, eventDate);
                break;
            default:
                throw new InvalidTaskTypeException();
        }
        return commandGenerated;
    }
}
