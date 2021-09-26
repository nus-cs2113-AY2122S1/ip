package duke;

import java.util.Scanner;

public class Ui {

    protected Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String sendInput() {
        return in.nextLine();
    }
    public static final String EMPTY = "There is no data in your list master!";
    public static final String UNSPECIFIED_DONE = "Oh no master, I am not quite sure which task you would like me to mark as done!";
    public static final String UNSPECIFIED_DELETE = "Oh no master, I am not quite sure which task you would like me to delete!";
    public static final String UNSPECIFIED_FIND = "Oh no master, I am not quite sure which task you would like me to find!";
    public static final String UNSPECIFIED_DATE = "Oh no master, please specify the date for which you would like me to print the respective tasks!";
    public static final String INVALID = "Please type in a valid number master! Type \"list\" to check the index number of your list data";
    public static final String DEADLINE_ERROR = ("Sorry Master! I don't think you have properly keyed in the parameters.\n" +
                                                 "Please enter \"deadline\", followed by the task, followed by \"/by\", \n" +
                                                 "and lastly followed by the due date to specify the deadline Master!");
    public static final String EVENT_ERROR = ("Sorry Master! I don't think you have properly keyed in the parameters. \n" +
                                              "Please enter \"event\", followed by the event, followed by \"/at\", and \n" +
                                              "lastly followed by the event duration to specify the timing of the event Master!");
    public static final String TODO_ERROR = ("Sorry Master! I don't think you have properly keyed in the parameters.\n" +
                                             " Please enter \"todo\", followed by the task you wish to add to your \n" +
                                             "duke.Todo list Master!");
    public static final String UNSPECIFIED_TASK = ("Sorry Master! Despite the fact that I am fluent in over six million forms\n" +
                                                   " of communication, I am unable to comprehend your request. Please specify\n" +
                                                   " the type of task that you wish to add Master!");
    public static final String NUMBER_FORMAT_EXCEPTION = "Master, please type in a number to indicate the task you want me to perform the necessary actions for!";
    public static final String STARTING_MESSAGE = "Accessing archives, loading in data, C3PO systems online!";
    public static final String ENDING_MESSAGE = "Goodbye master! May the force be with you!\n";
    public static final String USER_PROMPT_MESSAGE = "Type something: ";
    public static final String LINE = "____________________________________________________________\n";
    public static final String LOGO = "       /~\\\n"
                                    + "      |oo )\n"
                                    + "      _\\=/_\n"
                                    + "     /     \\\n"
                                    + "    //|/.\\|\\\\\n"
                                    + "   ||  \\_/  ||\n"
                                    + "   || |\\ /| ||\n"
                                    + "    # \\_ _/  #\n"
                                    + "      | | |\n"
                                    + "      | | |\n"
                                    + "      []|[]\n"
                                    + "      | | |\n"
                                    + "     /_]_[_\\\n";
    public static final String GREETINGS = "____________________________________________________________\n" +
                                  "Hello! I am C3P0! Human-cyborg relations! \n" + " \n" + LOGO + "\n" +
                                  "What can I do for you my master?\n";
    public void sayBye() {
        System.out.println(ENDING_MESSAGE + LINE);
    }

    public void greetUser() {
        System.out.println(GREETINGS);
    }

    public void promptUser() {
        System.out.println(LINE);
        System.out.print(USER_PROMPT_MESSAGE);
    }

    public void printLine() {
        System.out.println(LINE);
    }
}
