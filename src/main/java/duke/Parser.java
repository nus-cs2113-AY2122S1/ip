package duke;

public class Parser {

    private static String getCommand(String userInput) {
        String[] input = userInput.trim().toLowerCase().split(" ");
        return input[0];
    }

    // parse user input into command for execution
    public static void parseCommand(String command) {

    }

    public static String getTaskType(String userInput) {
        String[] description = userInput.trim().toLowerCase().split(" ");
        return description[0];
    }

    public static String getTodoDescription(String userInput) {
        int descriptionPosition = userInput.trim().indexOf(" ");
        return userInput.trim().substring(descriptionPosition);
    }

    public static int DeadlineOrEventDescriptionPosition(String userInput) {
        return userInput.trim().indexOf(" ");
    }

    // can insert try catch block here
    public static int DeadlineOrEventTimePosition(String userInput) {
        if (userInput.toLowerCase().contains("deadline")) {
           return userInput.trim().indexOf("/by");
        } else if (userInput.toLowerCase().contains("event")) {
           return userInput.trim().indexOf("/at");
        }
    }

    public static String getDeadlineOrEventDescription(String userInput) {
        int descriptionPosition = DeadlineOrEventDescriptionPosition(userInput);
        int timePosition = DeadlineOrEventTimePosition(userInput);
        return userInput.substring(descriptionPosition, timePosition);
    }

    public static String getDeadlineOrEventTime(String userInput) {
        int timePosition = DeadlineOrEventTimePosition(userInput);
        return userInput.substring(timePosition + 3);
    }

    public static int getTaskNumber(String userInput) {
        int taskNumberPosition = userInput.trim().indexOf(" ");
        return Integer.parseInt(userInput.trim().substring(taskNumberPosition + 1));
    }


}
