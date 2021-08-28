import java.util.Scanner;

public class UserInterface {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static final String LOGO = "\n" +
            "______  ___ ________  ___ ___  _   _ \n" +
            "| ___ \\/ _ |_   _|  \\/  |/ _ \\| \\ | |\n" +
            "| |_/ / /_\\ \\| | | .  . / /_\\ |  \\| |\n" +
            "| ___ |  _  || | | |\\/| |  _  | . ` |\n" +
            "| |_/ | | | || | | |  | | | | | |\\  |\n" +
            "\\____/\\_| |_/\\_/ \\_|  |_\\_| |_\\_| \\_/";

    private static final int FIRST_WORD = 0;
    private static final int SECOND_WORD = 1;

    private static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void greet() {
        System.out.println("Greetings from\n" + LOGO);
        printLine();
        System.out.print("Hello! I'm BATMAN\n" + "What can I do for you?\n");
        printLine();
    }

    private static final Scanner sc = new Scanner(System.in);
    
    public static Command interpretUserInput() {
        
        String line = sc.nextLine();

        if (line.equals("bye")) {
            showExitMessage();
            return Command.EXIT;
        } else if (line.equals("list")) {
            showList();
            return Command.LIST;
        }

        String[] words;
        words = line.split(" ", 2);

        if (words.length < 2) {
            showInvalidCommand();
            return Command.CONTINUE;
        }

        switch (words[FIRST_WORD]) {
        case "todo":
            if (TaskManager.addTask(Command.ADD_TODO, words[SECOND_WORD])) {
                showItemAdded();
            } else {
                showWrongFormat();
            }
            return Command.ADD_TODO;

        case "deadline":
            if (TaskManager.addTask(Command.ADD_DEADLINE, words[SECOND_WORD])) {
                showItemAdded();
            } else {
                showWrongFormat();
            }
            return Command.ADD_DEADLINE;

        case "event":
            if (TaskManager.addTask(Command.ADD_EVENT, words[SECOND_WORD])) {
                showItemAdded();
            } else {
                showWrongFormat();
            }
            return Command.ADD_EVENT;

        case "done":
            int taskIndex;
            try {
                taskIndex = Integer.parseInt(words[SECOND_WORD].replaceAll(" ", ""));
            } catch (Exception e) {
                showWrongFormat();
                return Command.CONTINUE;
            }
            if (!TaskManager.checkCorrectIndex(taskIndex)) {
                showInvalidIndex();
                return Command.CONTINUE;
            }
            TaskManager.setDone(taskIndex);
            showItemRemoved(taskIndex);
            return Command.CONTINUE;

        default:
            showInvalidCommand();
            return Command.CONTINUE;
        }
    }

    private static void showInvalidCommand() {
        printLine();
        System.out.println("Invalid command!");
        printLine();
    }

    private static void showItemRemoved(int taskIndex) {
        printLine();
        System.out.println("Got it. I've eliminated this task:\n" + TaskManager.getTask(taskIndex));
        printLine();
    }

    private static void showInvalidIndex() {
        printLine();
        System.out.println("Invalid index");
        printLine();
    }

    private static void showList() {
        printLine();
        TaskManager.printList();
        printLine();
    }

    private static void showExitMessage() {
        printLine();
        System.out.print("Bye. Hope to see you again soon!\n");
        printLine();
    }

    private static void showItemAdded() {
        printLine();
        System.out.println("Got it. I've added this task:\n" + TaskManager.getLatestTask());
        System.out.println("Now you have " + TaskManager.getNumOfTask() + " tasks");
        printLine();
    }

    private static void showWrongFormat() {
        UserInterface.printLine();
        System.out.println("Wrong format!");
        UserInterface.printLine();
    }
}
