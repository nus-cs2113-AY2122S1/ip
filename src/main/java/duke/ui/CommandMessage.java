package duke.ui;

public class CommandMessage {

    /* Command message */
    public static final String DELETE_TASK_MESSAGE = "Why would you delete the following task? But anyways I have "
            + "removed the following task.\n"
            + "%s\n"
            + "Now you have left %d tasks in the list.";
    public static final String COMPLETE_TASK_MESSAGE = "Good lad, you have finally completed the task you needed "
            + "to do.\n"
            + "%s";
    public static final String ADD_TASK_MESSAGE = "Gaben have seen and will add the following task for you:\n"
            + "%s\n"
            + "You now have %d task in the list";
    public static final String HELP_MESSAGE = "The following commands accepted are: "
            + "LIST (Show the list of task)\n"
            + "TODO <description> (Create a task with todo tag)\n"
            + "DEADLINE <description> /by <date and time> (Create a task with deadline tag)\n"
            + "EVENT <description> /at <date and time> (Create a task with event tag)\n"
            + "DONE <index of task> (To mark indicated task as completed)\n"
            + "BYE (End program)\n"
            + "HELP (List out available commands)";
    public static final String FIND_TASK_MESSAGE = "Total of %d task(s) found that matches your description.\n";
}
