package duke;

public class Ui {
    public static final String NL = System.lineSeparator();
    public static final String HELP_MESSAGE = "Valid Commands: " + NL
            + "todo {description of task} (eg. todo homework)" + NL
            + "event {description of event} /at {time of event} (eg. event party at/ 9am)" + NL
            + "deadline {description of task} /by {deadline of task}  (eg. deadline assignment /by 6pm)"
            + NL
            + "list" + NL
            + "done {index number of task done}  (eg. done 1)" + NL
            + "delete {index number of task you want to delete}  (eg. delete 1)" + NL
            + "bye";

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String STARTING_MESSAGE = "Hello from" + NL
            + LOGO + NL
            + "Hello! I'm Duke" + NL
            + "What can I do for you?";
    public static final String ENDING_MESSAGE = "Bye. Hope to see you again soon!";
    
    public Ui() {
        
    }
    public void printMessage(String message) {
        final String HORIZONTAL_LINE = "____________________________________________________________";
        System.out.println(HORIZONTAL_LINE + NL + message + NL
                + HORIZONTAL_LINE);
    }

    public void printStartingOrEndingMessage(boolean isStart) {
        if (isStart) {
            printMessage(STARTING_MESSAGE);
        } else {
            printMessage(ENDING_MESSAGE);
        }
    }
    
    public void printHelpMessage() {
        printMessage(HELP_MESSAGE);
    }
    
    
}
