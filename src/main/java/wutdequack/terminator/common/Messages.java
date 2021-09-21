package wutdequack.terminator.common;

public class Messages {

    /**Static messages for greetings**/
    // @@author ObASCII
    // Reused from https://www.asciiart.eu/computers/computers
    // with minor modifications
    public static final String LOGO = "              ,---------------------------,\n"
            + "              |  /---------------------\\  |\n"
            + "              | |                       | |\n"
            + "              | |     404               | |\n"
            + "              | |      Send             | |\n"
            + "              | |       Help            | |\n"
            + "              | |        Pls            | |\n"
            + "              |  \\_____________________/  |\n"
            + "              |___________________________|\n"
            + "            ,---\\_____     []     _______/------,\n"
            + "          /         /______________\\           /|\n"
            + "        /___________________________________ /  | ___\n"
            + "        |                                   |   |    )\n"
            + "        |  _ _ _                 [-------]  |   |   (\n"
            + "        |  o o o                 [-------]  |  /    _)_\n"
            + "        |__________________________________ |/     /  /\n"
            + "    /-------------------------------------/|      ( )/\n"
            + "  /-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /\n"
            + "/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /\n"
            + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    public static final String GREETING_MESSAGE = "\"Hola Amigos, I am the Terminator.\"";
    public static final String GREETING_QUERY_MESSAGE = "What would you like me to do?";
    public static final String GREETING_QUIT_MESSAGE = "[*] Type \"bye\" if you want to leave!";

    /**Error Messages**/
    public static final String ERROR_NUMBER_INVALID_MESSAGE = "Input was not a valid number";
    public static final String ERROR_INDEX_OUT_OF_BOUNDS_MESSAGE = "Index is out of bounds! Try again :(";
    public static final String ERROR_MISSING_PARAMS_MESSAGE = "You are missing parameters! Try again!";
    public static final String ERROR_MISSING_DATETIME_MESSAGE = "Missing Task Name/Date Time!";
    public static final String ERROR_INVALID_TODO_TASK_MESSAGE = "You did not enter a valid ToDo task";
    public static final String ERROR_CONFUSED_MESSAGE = "Sorry, I don't understand you";
    public static final String ERROR_INVALID_TASK_COMMAND = "Put either list/deadline/todo/event";

    /**CRUD Messages**/
    public static final String COMPLETED_TASK_MESSAGE = "Great! The following item has been marked as completed";
    public static final String UPDATED_TASK_MESSAGE = "Noted. The following item has been deleted";
    public static final String POST_CRUD_QUERY_MESSAGE = "Is there anything else you would like me to do?";
    public static final String ADD_TASK_MESSAGE = "Added the following Task";
    public static final String ADD_TASK_COUNTER_MESSAGE = "Added the following Task";
    public static final String ADD_TASK_QUERY_MESSAGE = "Hmmm... what should I do now?";

    /**File IO Messages**/
    public static final String FILE_DONT_EXIST_MESSAGE = "Directory (data) and/or File (data/records.txt) does not "
            + "exist!";
    public static final String FILE_DATABASE_EXIST_MESSAGE = "New database created at ";
    public static final String FILE_PERMISSION_ERROR_MESSAGE = "Cannot create file here. Check your permissions!";

    /**Filter Tasks**/
    public static final String FILTER_TASKS_MESSAGE = "These are a list of tasks based on the search term: %s";


}
