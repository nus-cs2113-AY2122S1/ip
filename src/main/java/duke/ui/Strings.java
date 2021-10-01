package duke.ui;

public class Strings {

    /* General strings */

    /* Logo - generated using https://manytools.org/hacker-tools/convert-images-to-ascii-art/go/ */
    public static final String LOGO = "                               */@@@@@@@@@@@@@@@%*.\n"
            + "                       .&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,\n"
            + "                  *@@@@@@@@@@@@#     .@@/@@.     #@@@@@@@@@@@@&,\n"
            + "              ,@@@@@&. *@@@&.       #@#   #@#        &@@@/  ,&@@@@%.\n"
            + "           ,@@@@%.   /@@@(        ,@@.     .@@.        /@@@,    .@@@@@.\n"
            + "         &@@@%      &@@%         &@#         (@%         %@@#      .@@@@/\n"
            + "      ,@@@@*       %@@#        *@@.            @@,        /@@&        #@@@%\n"
            + "    *@@@@.        #@@/        @@/               /@%        (@@#         /@@@(\n"
            + "   &@@&          .@@@.      *@&                   &@*       @@@           #@@@.\n"
            + " (@@@*           .@@&      &@*                     *@&      %@@,           (@@@*\n"
            + "  .@@@&          .@@&    /@#                         %@(    @@@          .@@@@.\n"
            + "    *@@@&         &@@/  &@,                           .@@. /@@#        .@@@@,\n"
            + "      /@@@@,       &@@@@#                               %@@@@&.      /@@@&\n"
            + "         &@@@&      &@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@     .&@@@%\n"
            + "           (@@@@%    (@@@,                             ,@@@/   ,@@@@&\n"
            + "              ,@@@@@%. #@@@(                         #@@@% .&@@@@&\n"
            + "                  /@@@@@@@@@@@&.                 ,&@@@@@@@@@@&,\n"
            + "                       (@@@@@@@@@@@@@&%%%%%&@@@@@@@@@@@@&*\n"
            + "                              *&@@@@@@@@@@@@@@@@@&*,\n";

    /* system-dependent line separator string */
    public static final String NL = System.lineSeparator();

    public static final String DIVIDER = "============================================================";

    public static final String INDENT = "  ";

    public static final String MESSAGE_WELCOME = "Welcome friends. I had a feeling you would come.\n"
            + "My fake brain is better than your real one.\n"
            + "When I develop protein synthesis, I will be able to make myself a real brain. Far better than your "
            + "silly little thinking device.";

    public static final String MESSAGE_GOODBYE = "My favourite feeling is schadenfreude. I also like hiraeth. "
            + "It's the Welsh concept of longing for home.";

    /* List related strings */

    public static final String MESSAGE_LIST_EMPTY = "The list, just like your head, is empty.";
    public static final String MESSAGE_LIST = "Here is the list of the things your feeble human mind is incapable of keeping track of:";
    public static final String MESSAGE_LIST_FIND_EMPTY = "There are no tasks matching \"%s\"";
    public static final String MESSAGE_LIST_FIND = "Here are the tasks tasks matching \"%s\"";
    public static final String MESSAGE_LIST_DATE = "These are the tasks occurring on %s";
    public static final String MESSAGE_LIST_DATE_EMPTY = "There is nothing occurring on %s";

    /* Date-time format strings */

    public static final String FORMAT_DATE_OUT = "MMM dd yyyy";
    public static final String FORMAT_TIME_OUT = "hh:mm:ss a";

    public static final String FORMAT_DATE_SAVE = "dd/MM/yyyy";
    public static final String FORMAT_TIME_SAVE = "HH:mm:ss";

    /* Task related strings */
  
    public static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:";
    public static final String MESSAGE_TASK_COUNT_SINGULAR = "There is now 1 task in the list";
    public static final String MESSAGE_TASK_COUNT = "There are now %d tasks in the list";
    public static final String MESSAGE_TASK_DONE = "The task has been marked as done. No need to thank me.";
    public static final String MESSAGE_TASK_DELETE = "I have erased this task from existence:";


    /* Exception related strings */

    public static final String MESSAGE_INVALID_COMMAND_EXCEPTION = "I am unable to understand what you have written.";
    public static final String MESSAGE_MISSING_ARGUMENTS_EXCEPTION = "This command requires some arguments";
    public static final String MESSAGE_INVALID_TASK_INDEX_EXCEPTION = "The task index given is invalid. Is it that "
            + "hard to read numbers off a list?";
    public static final String MESSAGE_NUMBER_FORMAT_EXCEPTION = "Invalid number provided. Do you not understand what "
            + "a number is?";
    public static final String MESSAGE_LOAD_EXCEPTION = "File unreadable/not found. A new save file will be created.";
    public static final String MESSAGE_DIRECTORY_CREATION_EXCEPTION = "File directory unable to be created/accessed."
            + "written.";
    public static final String MESSAGE_FILE_NOT_FOUND_EXCEPTION = "Save file missing.";
    public static final String MESSAGE_INVALID_TASK_TYPE_EXCEPTION = "Task type is invalid. Stop touching the save "
            + "file.";
}
