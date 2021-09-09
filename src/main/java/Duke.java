import java.util.Locale;
import java.util.Scanner;


public class Duke {

    private static int byeFlag = 0;
    private static int positionCheck = 0;
    private static int SIZE = 100;
    private static String EMPTY = "There is no data in your list master!";
    private static String EXCEEDED = "Oh dear me! We have exceeded my system's maximum capacity!";
    private static String UNSPECIFIED_DONE = "Oh no master, I am not quite sure which task you would like me to mark as done!";
    private static String INVALID = "Please type in a valid number master! Type \"list\" to check the index number of your list data";
    public static String DEADLINE_ERROR = ("Sorry Master! I don't think you have properly keyed in the parameters.\n" +
                                           "Please enter \"deadline\", followed by the task, followed by \"/by\", \n" +
                                           "and lastly followed by the due date to specify the deadline Master!");
    public static String EVENT_ERROR = ("Sorry Master! I don't think you have properly keyed in the parameters. \n" +
                                        "Please enter \"event\", followed by the event, followed by \"/at\", and \n" +
                                        "lastly followed by the event duration to specify the timing of the event Master!");
    public static String TODO_ERROR = ("Sorry Master! I don't think you have properly keyed in the parameters.\n" +
                                       " Please enter \"todo\", followed by the task you wish to add to your \n" +
                                       "Todo list Master!");
    public static String UNSPECIFIED_TASK = ("Sorry Master! Despite the fact that I am fluent in over six million forms\n" +
                                             " of communication, I am unable to comprehend your request. Please specify\n" +
                                             " the type of task that you wish to add Master!");

    private static Task[] commands = new Task[SIZE];

    // sendCommand() is a method used to allow the user to send his/her commands to C3PO
    private static void sendCommand() {
        String line;
        Scanner in = new Scanner(System.in);
        while (byeFlag != 1) {
            try {
                System.out.println("____________________________________________________________\n");
                System.out.print("Type something: ");
                line = in.nextLine();
                System.out.println("____________________________________________________________\n");
                checkCommand(line);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    // checkCommand() is a method that allows us to determine when the user says bye.
    private static void checkCommand(String line) throws DukeException {
        String[] input = line.split(" ");
        if (line.equals("bye")) {
            byeFlag = 1;
        } else if (line.equals("list")) {
            if (positionCheck == 0) {
                throw new DukeException(EMPTY);
            } else {
                printList();
            }
        } else if (line.equals("done")) {
            throw new DukeException(UNSPECIFIED_DONE);
        } else if (input[0].equals("done")) {
            if (positionCheck<=0) {
                throw new DukeException(EMPTY);
            } else if ((Integer.parseInt(input[1]) > positionCheck ) || (Integer.parseInt(input[1]) <= 0)) {
                throw new DukeException(INVALID);
            } else {
                markDone(Integer.parseInt(input[1])-1);
            }
        } else if (positionCheck >= SIZE) {
            throw new DukeException(EXCEEDED);
        } else {
            checkTypeOfTask(line);
        }
    }

    public static void sayBye() {
        System.out.println("Goodbye master! May the force be with you!\n");
        System.out.println("____________________________________________________________\n");
    }

    public static void addDeadline(String[] input, int length) throws DukeException {
        String description;
        String by;
        for (int i = 1 ; i < length ; i++) {
            if ((input[i].equals("/by")) && (i != 1) && (i != (length-1))) {
                description = input[1];
                by = input[i+1];
                for (int j = 2 ; j < i ; j++) {
                    description += (" " + input[j]);
                }
                for (int k = i+2 ; k < length ; k++) {
                    by += (" " + input[k]);
                }
                commands[positionCheck] = new Deadline(description,by);
                System.out.println("Added to Galactic database:" );
                System.out.println(commands[positionCheck]);
                positionCheck += 1;
                return;
            }
        }
        throw new DukeException(DEADLINE_ERROR);
    }

    public static void addEvent(String[] input, int length) throws DukeException{
        String description;
        String at;
        for (int i = 1 ; i < length ; i++) {
            if ((input[i].equals("/at")) && (i != 1) && (i != (length-1))) {
                description = input[1];
                at = input[i+1];
                for (int j = 2 ; j < i ; j++) {
                    description += (" " + input[j]);
                }
                for (int k = i+2 ; k < length ; k++) {
                    at += (" " + input[k]);
                }
                commands[positionCheck] = new Event(description,at);
                System.out.println("Added to Galactic database:" );
                System.out.println(commands[positionCheck]);
                positionCheck += 1;
                return;
            }
        }
        throw new DukeException(EVENT_ERROR);
    }

    public static void addTodo (String[] input, int length) throws DukeException{
        if (length == 1) {
            throw new DukeException(TODO_ERROR);
        } else {
            String description = input[1];
            for (int i = 2 ; i < length ; i++) {
                description += (" " + input[i]);
            }
            commands[positionCheck] = new Todo(description);
            System.out.println("Added to Galactic database:" );
            System.out.println(commands[positionCheck]);
            positionCheck += 1;
        }
    }

    public static void checkTypeOfTask(String line) throws DukeException {
        String[] input = line.split(" ");
        int length = input.length;
        if (input[0].toLowerCase().equals("deadline")) {
            addDeadline(input,length);
        } else if (input[0].toLowerCase().equals("event")) {
            addEvent(input,length);
        } else if (input[0].toLowerCase().equals("todo")) {
            addTodo(input,length);
        } else {
            throw new DukeException(UNSPECIFIED_TASK);
        }
    }

    private static void printList() {
        System.out.println("Accessing archives...");
        for (int i = 0; i < positionCheck; i++) {
            System.out.println((i+1) + ". " + commands[i]);
        }
    }

    private static void markDone(int doneTaskNumber) {
        commands[doneTaskNumber].markAsDone();
        System.out.println("The following task has been marked as done Master!");
        System.out.println((doneTaskNumber+1) + ". " + commands[doneTaskNumber]);
    }

    public static void greetUser() {
        String logo = "       /~\\\n"
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
        System.out.println("____________________________________________________________\n");
        System.out.println("Hello! I am C3P0! Human-cyborg relations! \n" + " \n" + logo);
        System.out.println("What can I do for you my master?\n");
    }

    public static void main (String[] args) throws DukeException {
        greetUser();
        sendCommand();
        sayBye();
    }
}
