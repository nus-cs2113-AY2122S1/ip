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
        String taskType = userInputSplit[0];
        if (taskType.equals(TASK_TYPE_DEADLINE) || taskType.equals(TASK_TYPE_TODO) ||
                taskType.equals(TASK_TYPE_EVENT) || taskType.equals(TASK_EXIT) ||
                taskType.equals(TASK_LIST) || taskType.equals(TASK_TYPE_DONE)
                || taskType.equals(TASK_TYPE_DELETE) || taskType.equals(TASK_TYPE_FIND)) {
            return taskType;
        } else {
            throw new InvalidTaskTypeException();
        }
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
        if (userInputSplitArray.length < 2) {
            String taskType = userInputSplitArray[0];
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
        if (taskDescriptionSplitByDate.length < 2) {
            throw new InvalidDateIndicatorException();
        } else {
            String mainTask = taskDescriptionSplitByDate[0];
            return mainTask;
        }
    }

    /**
     * @param task The task given by the user
     * @param dateIndicator The seperator to get the date out from the task
     * @return The date given by the user
     * @throws InvalidDateIndicatorException
     * We throw this exception when the not date indicator is given by the user
     * */
    private String getDateFromTask(String task, String dateIndicator)
            throws InvalidDateIndicatorException {
        String[] taskDescriptionSplitByDate = task.split(dateIndicator);
        if (taskDescriptionSplitByDate.length < 2) {
            throw new InvalidDateIndicatorException();
        } else {
            String date = taskDescriptionSplitByDate[1];
            return date;
        }
    }

    /**
     * @param userInput The input given by the user
     * @return The task number given by the user
     * */
    private int getTaskNumber(String userInput) {
        String[] userInputArray = userInput.split(USER_INPUT_SPLITTER);
        int taskNumber = Integer.parseInt(userInputArray[1]);
        taskNumber = taskNumber - 1;
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
     * */
    public Command parseUserInput(String userInput)
            throws InvalidTaskTypeException, EmptyDescriptionException {
        String taskType = getTaskTypeFromUserInput(userInput);
        switch (taskType) {
            case TASK_TYPE_TODO:
                return generateTaskCommand(taskType, userInput);
            case TASK_TYPE_DEADLINE:
                return generateTaskCommand(taskType, userInput);
            case TASK_TYPE_EVENT:
                return generateTaskCommand(taskType, userInput);
            case TASK_EXIT:
                return new ByeCommand();
            case TASK_LIST:
                return new ListCommand();
            case TASK_TYPE_DONE:
                int doneTaskNumber = getTaskNumber(userInput);
                return new DoneCommand(doneTaskNumber);
            case TASK_TYPE_DELETE:
                int deleteTaskNumber = getTaskNumber(userInput);
                return new DeleteCommand(deleteTaskNumber);
            case TASK_TYPE_FIND:
                String taskToFind = getTaskToFind(userInput);
                return new FindCommand(taskToFind);
            default:
                throw new InvalidTaskTypeException();
        }
    }

    /**
     * @param taskType The task type given by the user
     * @param userInput The input given by the user
     * @return The command based on the task type
     * @throws InvalidTaskTypeException
     * We throw this exception when the task type has not been defined in duke
     * */
    private Command generateTaskCommand(String taskType, String userInput)
            throws InvalidTaskTypeException {
        switch (taskType) {
            case TASK_TYPE_TODO:
                try {
                    String task = getTask(userInput);
                    return new AddCommand(taskType, task);
                } catch (EmptyDescriptionException e) {
                    e.printExceptionMessage();
                }
            case TASK_TYPE_DEADLINE:
                try {
                    String task = getTask(userInput);
                    String mainTask = getMainTaskFromTask(task, DEADLINE_DATE_INDICATOR);
                    String taskDate = getDateFromTask(task, DEADLINE_DATE_INDICATOR);
                    return new AddCommand(taskType, mainTask, taskDate);
                } catch (EmptyDescriptionException e) {
                    e.printExceptionMessage();
                } catch (InvalidDateIndicatorException e) {
                    e.printExceptionMessage();
                }
            case TASK_TYPE_EVENT:
                try {
                    String task = getTask(userInput);
                    String mainTask = getMainTaskFromTask(task, EVENT_DATE_INDICATOR);
                    String taskDate = getDateFromTask(task, EVENT_DATE_INDICATOR);
                    return new AddCommand(taskType, mainTask, taskDate);
                } catch (EmptyDescriptionException e) {
                    e.printExceptionMessage();
                } catch (InvalidDateIndicatorException e) {
                    e.printExceptionMessage();
                }
            default:
                throw new InvalidTaskTypeException();
        }
    }
}
