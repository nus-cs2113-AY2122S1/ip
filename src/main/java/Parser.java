public class Parser {
    /**
     * Takes the user input and identify the index which,
     * the user wants to mark as completed.
     * @param userInput
     * @return taskIndex.
     */
    public String[] identifyUserInput(String userInput){
        String[] parts = userInput.split(" ");
        return parts;
    }

    /**
     * Identify Event and Deadline Task when they are
     * added into the list.
     * @param userInput
     * @return
     */
    public String identifyUserTask(String userInput) {
        int indexOfLast = userInput.indexOf("/") - 1;
        String taskName = userInput.substring(userInput.indexOf(" "), indexOfLast) + " ";
        return taskName;
    }

    /**
     * Identify the event and deadline task being loaded from Duke.txt
     * @param userInput
     * @return
     */
    public String identifyStorageUserTask(String userInput) {
        int indexOfLast = userInput.indexOf("/");
        int indexOfStart = 7;
        String taskName = userInput.substring(indexOfStart,indexOfLast);
        return taskName;
    }

    /**
     * Identify todo task when being loaded from Duke.txt
     * @param userInput
     * @return
     */
    public String identifyStorageUserToDoTask(String userInput) {
        int indexOfStart = 7;
        String userTask = userInput.substring(indexOfStart, userInput.length());
        return userTask;
    }

    /**
     * Takes the user input and identify the dateline which,
     * the user wants the task to be.
     *
     * @param userInput
     * @return String array of string split at "/".
     */
    public String[] identifyDeadlineCommand(String userInput) {
        String[] parts = userInput.split("/");
        return parts;
    }

    /**
     * Splits userinput to find the keyword they are looking for.
     * @param userInput
     * @return
     */
    public String identifyKeyword(String userInput) {
        String keyword = userInput.substring(5);
        return keyword;
    }
}
