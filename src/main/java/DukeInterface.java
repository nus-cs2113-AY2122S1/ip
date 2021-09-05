public class DukeInterface {

    private final String LOGO = "+++++++++++++++++++++++++++++++++++++++++++++\n"
            + "  __         __\n"
            + " /  \\.-\"\"\"-./  \\     ____        _        \n"
            + " \\    -   -    /    |  _ \\ _   _| | _____ \n"
            + "  |   o   o   |     | | | | | | | |/ / _ \\\n"
            + "  \\  .-'''-.  /     | |_| | |_| |   <  __/\n"
            + "   '-\\__Y__/-'      |____/ \\__,_|_|\\_\\___|\n"
            + "      `---`\n"
            + "[A NUS CS2113T Project by: Peh Zhenhao, Amos]\n"
            + "+++++++++++++++++++++++++++++++++++++++++++++";

    private final String WELCOME_MSG = "=> Hello! I'm Duke \uD83D\uDC3B\n"
            + "=> I'm so hungry, would you mind feeding me tasks?";

    private final String EXIT_MSG = "=> Come back soon, I'm still hungry \uD83D\uDE0B";

    private final String HELP_MSG = "Types of Commands Available [\uD83D\uDCAC]:\n"
            + "1. Terminate Duke            -> bye \n"
            + "1. Print Tasklist            -> list \n"
            + "1. Print Available Commands  -> help \n"
            + "2. Add Todos                 -> {todo <task description>}\n"
            + "3. Add Deadlines             -> {deadline <task description> /by <task date&time>}\n"
            + "4. Add Events                -> {event <task description> /by <task date&time>}\n"
            + "5. Set Task as Done          -> {done <task ID>}";

    private final String UNKNOWN_CMD_MSG = "Yikes, I do not recognise your input! (refer to 'help' command)";
    private final String TODO_NO_DESC_MSG = "\uD83D\uDE35 OOPS!!! The todo description field cannot be empty.";
    private final String DEADLINE_NO_DESC_MSG = "\uD83D\uDE35 OOPS!!! The deadline description field cannot be empty.";
    private final String EVENT_NO_DESC_MSG = "\uD83D\uDE35 OOPS!!! The event description field cannot be empty.";
    private final String NO_TASK_ID_MSG = "\uD83D\uDE35 OOPS!!! The Task ID field cannot be empty.";
    private final String TASK_ID_NOT_INT_MSG = "\uD83D\uDE35 OOPS!!! The Task ID field must be an integer.";
    private final String INVALID_TASK_ID_MSG = "\uD83D\uDE35 OOPS!!! The Task ID does not exist in the tasklist.";

    private final String ADD_TASK_VALID_MSG = "Chomp-chomp! I've added this new task [\uD83D\uDCDD]:";
    private final String SET_TASK_COMPLETE_MSG = "Burrrp! I've marked this task as done [\u2705]:";
    private final String SET_TASK_ALREADY_COMPLETE_MSG = "Hold up! This task is already marked done [\u2705]:";
    private final String PRINT_TASKLIST_MSG = "Ahh! Here are the tasks in your list [\uD83D\uDCC5]:";
    private final String PRINT_TASKLIST_EMPTY_MSG = "Sorry, but there are currently no tasks added!";
    private final String GENERIC_PROMPT_MSG = "You may choose to add/complete another task.";

    public void printUserName() {
        System.out.println("\n[You]:");
    }

    public void printDukeName() {
        System.out.println("\n[Duke]:");
    }

    public void printCursor() {
        System.out.print("=> ");
    }

    public void printMsgWithCursor(String message) {
        System.out.print("=> " + message + "\n");
    }

    public void printWithPadding(String message) {
        System.out.println("   <" + message + ">");
    }

    public void printWelcomeMsg() {
        System.out.println(LOGO);
        printDukeName();
        System.out.println(WELCOME_MSG);
    }

    public void printExitMsg() {
        printDukeName();
        System.out.println(EXIT_MSG);
    }

    public void printHelpMsg() {
        printDukeName();
        System.out.println(HELP_MSG);
    }

    public void printInvalidCmdMsg(InvalidCommandException e) {
        printDukeName();
        printMsgWithCursor(UNKNOWN_CMD_MSG);
        System.out.println("   [Input Received by Duke: " + e + "]");
    }

    public void printInvalidTaskIdMsg(InvalidTaskIdException e) {
        printDukeName();
        printMsgWithCursor(INVALID_TASK_ID_MSG);
        System.out.println("   [Input Received by Duke: " + e + "]");
    }

    public void printTodoNoDescMsg() {
        printDukeName();
        printMsgWithCursor(TODO_NO_DESC_MSG);
    }

    public void printDeadlineNoDescMsg() {
        printDukeName();
        printMsgWithCursor(DEADLINE_NO_DESC_MSG);
    }

    public void printEventNoDescMsg() {
        printDukeName();
        printMsgWithCursor(EVENT_NO_DESC_MSG);
    }

    public void printNoTaskIdMsg() {
        printDukeName();
        printMsgWithCursor(NO_TASK_ID_MSG);
    }

    public void printTaskIdNotIntegerMsg() {
        printDukeName();
        printMsgWithCursor(TASK_ID_NOT_INT_MSG);
    }

    public void printAddTaskMsg(String taskDescription) {
        printDukeName();
        printMsgWithCursor(ADD_TASK_VALID_MSG);
        printWithPadding(taskDescription);
    }

    public void printNumTasksInList(int numTasks) {
        printMsgWithCursor("Now you have " + numTasks + " tasks in your list.");
    }

    public void printSetTaskCompleteMsg(String taskDescription) {
        printDukeName();
        printMsgWithCursor(SET_TASK_COMPLETE_MSG);
        printWithPadding(taskDescription);
    }

    public void printSetTaskAlreadyCompleteMsg(String taskDescription) {
        printDukeName();
        printMsgWithCursor(SET_TASK_ALREADY_COMPLETE_MSG);
        printWithPadding(taskDescription);
        printMsgWithCursor(GENERIC_PROMPT_MSG);
    }

    public void printNumTaskComplete(int numComplete, int numTasks) {
        printMsgWithCursor("You have done " + numComplete + "/" + numTasks + " tasks in your list.");
    }

    public void printTasklistMsg() {
        printDukeName();
        printMsgWithCursor(PRINT_TASKLIST_MSG);
    }

    public void printTasklistEmptyMsg() {
        printDukeName();
        printMsgWithCursor(PRINT_TASKLIST_EMPTY_MSG);
        printMsgWithCursor(GENERIC_PROMPT_MSG);
    }


}
