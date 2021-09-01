import java.util.Scanner;

public class Duke {
    public static final String DIVIDER = "___________________________________________________________";
    public static final String INDENTATION = "      ";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String HELLO_MESSAGE_2 = "Hello! I'm Duke, your friendly neighbourhood task manager";
    public static final String HELLO_MESSAGE_3 = "What can I do for you? :D";
    public static final String TASK_COMPLETED_MESSAGE = "You've completed the task! Well done!";
    public static final String ADDED_TO_LIST = "I've added this to your list :D";
    public static final String DEADLINE_PROMPT = " /by ";
    public static final String EVENT_PROMPT = " /at ";
    public static final String GOODBYE_MESSAGE = "Bye, hope to see you again soon! :)";
    public static final String TYPE_SUITABLE_COMMAND_MESSAGE = "Sorry, you are using me wrongly. Please type in a suitable command :)";
    public static final boolean IS_FINE = true;
    public static final int TODO_STARTING_INDEX = 5;
    public static final int DEADLINE_STARTING_INDEX = 9;
    public static final int EVENT_STARTING_INDEX = 6;
    public static Task[] tasks = new Task[100];

    public static void main(String[] args) {
        printStartingMessage();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            processInput(input);
            input = in.nextLine();
        }
        printGoodbyeMessage();
    }

    public static void printIndentationAndDivider() {
        System.out.print(INDENTATION);
        System.out.println(DIVIDER);
    }

    public static void printWordsWithIndentation(String words) {
        System.out.print(INDENTATION);
        System.out.println(words);
    }

    private static void printGoodbyeMessage() {
        printIndentationAndDivider();
        printWordsWithIndentation(GOODBYE_MESSAGE);
        printIndentationAndDivider();
    }

    private static void processInput(String input) {
        switch(input.split(" ")[0].toLowerCase()) {
        case "list" :
            executeListCase();
            break;
        case "done" :
            executeDoneCase(input);
            break;
        case "todo" :
            executeTaskCase(input, TODO_STARTING_INDEX,TaskType.TODO);
            break;
        case "deadline" :
            executeTaskCase(input, DEADLINE_STARTING_INDEX,TaskType.DEADLINE);
            break;
        case "event" :
            executeTaskCase(input, EVENT_STARTING_INDEX,TaskType.EVENT);
            break;
        default :
            printIncorrectInputMessage();
        }
    }

    private static void printTaskMessage() {
        printIndentationAndDivider();
        printWordsWithIndentation(ADDED_TO_LIST);
        printWordsWithIndentation(tasks[Task.getTotalTasks() - 1].getStatusIconAndDescription());
        printWordsWithIndentation(notifyNumberOfTasks());
        printIndentationAndDivider();
        System.out.println();
    }

    private static void printIncorrectInputMessage() {
        printIndentationAndDivider();
        printWordsWithIndentation(TYPE_SUITABLE_COMMAND_MESSAGE);
        printIndentationAndDivider();
        System.out.println();
    }

    private static void executeTaskCase(String input, int starting_index, TaskType type) {
        String description = input.strip().substring(starting_index);
        boolean isFine = addTask(description, type);
        if(isFine) {
            printTaskMessage();
        }else {
            printIncorrectInputMessage();
        }
    }

    private static String[] parseInputForDifferentTask(String input,TaskType type) {
        String[] parsedOutput = {};
        switch(type) {
        case TODO:
            parsedOutput = new String[]{input};
            break;

        case DEADLINE:
            parsedOutput = input.split(DEADLINE_PROMPT);
            break;

        case EVENT:
            parsedOutput = input.split(EVENT_PROMPT);
            break;
        }
        return parsedOutput;
    }

    private static boolean addTask(String input,TaskType type) {
        String[] parsedOutput = parseInputForDifferentTask(input,type);
        switch(type){
        case TODO:
            tasks[Task.getTotalTasks()] = new ToDo(parsedOutput[0]);
            break;
        case EVENT:
            if(parsedOutput.length < 2) {
                return !IS_FINE;
            }
            tasks[Task.getTotalTasks()] = new Event(parsedOutput[0], parsedOutput[1]);
            break;
        case DEADLINE:
            if(parsedOutput.length < 2) {
                return !IS_FINE;
            }
            tasks[Task.getTotalTasks()] = new Deadline(parsedOutput[0], parsedOutput[1]);
            break;
        }
        Task.setTotalTasks(Task.getTotalTasks() + 1);
        return IS_FINE;
    }

    private static String notifyNumberOfTasks() {
        return "Now you have " + Task.getTotalTasks() + " task(s) in the list";
    }

    private static void executeDoneCase(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks[index].markAsDone();
        printIndentationAndDivider();
        printWordsWithIndentation(TASK_COMPLETED_MESSAGE);
        printWordsWithIndentation(tasks[index].getStatusIconAndDescription());
        printIndentationAndDivider();
    }

    private static void executeListCase() {
        printIndentationAndDivider();
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            printWordsWithIndentation(i + 1 + "." + tasks[i].getStatusIconAndDescription());
        }
        printIndentationAndDivider();
        System.out.println();
    }

    private static void printStartingMessage() {
        System.out.println("Hello from\n" + LOGO);
        printIndentationAndDivider();
        printWordsWithIndentation(HELLO_MESSAGE_2);
        printWordsWithIndentation(HELLO_MESSAGE_3);
        printIndentationAndDivider();
        System.out.println();
    }
}
