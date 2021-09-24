package karen.exception;


public abstract class ErrorCheck {

    /**
     * This method checks for errors when parsing Task Commands, ie. Deadline Command, Event Command,
     * ToDo Command.
     *
     * @param taskType String that represents the Task Command
     * @param separator String used to separate task description and date of task for Deadline and Event Commands
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @throws NoDescriptionException if user inputs a Task Command with no description
     * @throws IncorrectDescriptionFormatException if user inputs a Task Command with incorrect formatting of description
     */
    public static void checkTaskExceptions(String taskType, String separator, String rawUserInput)
            throws NoDescriptionException, IncorrectDescriptionFormatException {
        String[] inputWords = rawUserInput.split(" ");
        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }
        if (taskType.equals("todo")) {
            return;
        }
        String[] separatedDescription = rawUserInput.split(separator, 2);
        if (separatedDescription.length == 1) {
            throw new IncorrectDescriptionFormatException();
        }
        if (!rawUserInput.contains(separator)) {
            throw new IncorrectDescriptionFormatException();
        }
        if (separatedDescription[0].equalsIgnoreCase(taskType)) {
            throw new IncorrectDescriptionFormatException();
        }
    }

    /**
     * This method checks for errors when parsing the Commands given by user.
     *
     * @param command String that represents the user command
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @throws NoDescriptionException if user inputs a Command with no description
     * @throws IncorrectDescriptionFormatException if user inputs a Command with incorrect formatting of description
     */
    public static void checkCommandDescriptionExceptions(String command, String rawUserInput)
            throws NoDescriptionException, IncorrectDescriptionFormatException {
        String[] inputWords = rawUserInput.split(" ");
        if (command.equals("bye")) {
            if (inputWords.length != 1) {
                throw new IncorrectDescriptionFormatException();
            }
            return;
        }
        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }
        if (inputWords.length > 2) {
            throw new IncorrectDescriptionFormatException();
        }
    }



}
