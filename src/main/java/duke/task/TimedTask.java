package duke.task;

import duke.Parser;
import duke.exception.WrongNumberOfArgumentsException;


/**
 * Abstract class to represent a timed task (deadline or event).
 *
 * <code>isDone</code> boolean corresponds to whether the task is done.
 * <code>description</code> String corresponds to the task description.
 * <code>dateTime</code> String corresponds to datetime specifications of task.
 * <code>type</code> enum corresponds to respective class.
 */
abstract class TimedTask extends Task {
    public static final String PRINT_TASK_REGEX_FORMAT = "%s (%s: %s)";
    public static final char PREPOSITION_PREFIX = '/';
    private String dateTime;

    /**
     * TimedTask constructor with <code>isDone</code> set to <code>false</code>.
     *
     * @param description Task description.
     * @param dateTime    String that descripes the dateTime of the task.
     * @param type        enum that corresponds to task.
     */
    TimedTask(String description, String dateTime, Type type) {
        super(description, type);
        this.dateTime = dateTime;
    }

    /**
     * TimedTask constructor.
     *
     * @param isDone      boolean to show whether task is completed.
     * @param description Task description.
     * @param dateTime    String that descripes the dateTime of the task.
     * @param type        enum that corresponds to task.
     */
    TimedTask(boolean isDone, String description, String dateTime, Type type) {
        super(isDone, description, type);
        this.dateTime = dateTime;
    }

    /**
     * Helper function to split the arguments.
     * Uses the task type enum to get the preposition (by or at) to split the <code>argumentString</code>.
     *
     * @param taskType       enum that corresponds to task.
     * @param argumentString argument to be split.
     * @return arguments String array.
     * @throws WrongNumberOfArgumentsException if the number of argument != the number of arguments for the task.
     */
    private static String[] getArguments(Type taskType, String argumentString) throws WrongNumberOfArgumentsException {
        String[] arguments = Parser.splitOnArgument(argumentString, PREPOSITION_PREFIX + taskType.PREPOSITION);
        // 'NUMBER_OF_ARGUMENTS - 1' because the first argument [deadline || event] is excluded.
        if (arguments.length != taskType.NUMBER_OF_ARGUMENTS - 1) {
            throw new WrongNumberOfArgumentsException(taskType);
        }
        return arguments;
    }

    /**
     * Creates and uses the task type enum to get the preposition
     * (by or at) to split the <code>argumentString</code>.
     *
     * @param taskType       enum that corresponds to task
     * @param argumentString argument to be split
     * @return new TimedTask (deadline or event) created
     * @throws WrongNumberOfArgumentsException throws from <code>getArguments()</code> function.
     */
    public static TimedTask newTimedTask(Type taskType, String argumentString) throws WrongNumberOfArgumentsException {
        String[] parsedInputs = getArguments(taskType, argumentString);
        if (taskType == Type.EVENT) {
            return new Event(parsedInputs[0], parsedInputs[1]);
        }
        return new Deadline(parsedInputs[0], parsedInputs[1]);
    }

    /**
     * adds the preposition and dateTime to
     * the {@link Task#toString()} function in Task.
     */
    @Override
    public String toString() {
        return String.format(PRINT_TASK_REGEX_FORMAT, super.toString(), type.PREPOSITION, dateTime);
    }

    /**
     * adds the preposition and dateTime to the
     * {@link Task#getFormattedString()} function in Task.
     */
    @Override
    String getFormattedString() {
        return super.getFormattedString() + Task.SAVE_FILE_SEPARATOR + dateTime;
    }

}
