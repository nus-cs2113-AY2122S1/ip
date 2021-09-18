package duke.parser;

public class Parser {
    private static final int CHAR_TO_DESCRIPTION = 5;

    /**
     * Used for getting a task's description from todo tasks.
     * 
     * @param description task description from user's command.
     * @return a string containing the task description.
     */
    public static String getDescription(String description) {
        return description;
    }

    /**
     * Used for retrieving a task's description from deadline and event type 
     * tasks, as the description input from the user's command contains both
     * the task description and the time.
     * 
     * @param description task description from user's command.
     * @param timeKeyword keyword which specified the time for deadline and event
     *                    type tasks.
     * @return a string containing only the description part of the task
     */
    public static String getDescription(String description, String timeKeyword) {
        return description.substring(0, description.indexOf(timeKeyword));
    }

    /**
     * Used for retrieving a task's time from deadline and event type tasks,
     * as the description input from the user's command contains both the 
     * task description and the time.
     * 
     * @param description task description from user's command.
     * @param timeKeyword keyword which specified the time for deadline and event
     *                    type tasks.
     * @return a string containing only the time of the task.
     */
    public static String getTime(String description, String timeKeyword) {
        return description.substring(description.indexOf(timeKeyword) + CHAR_TO_DESCRIPTION);
    }
}
