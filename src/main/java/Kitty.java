import java.util.Scanner;

public class Kitty {
    public static final int MAX_TASKS = 100;
    public static Task[] tasks = new Task[MAX_TASKS];
    public static int totalTasksCount = 0;

    //Methods
    public static void printList(Task[] task) {
        System.out.println();
        System.out.println("Here are the tasks you have!");
        for (int i = 0; i < totalTasksCount; i++) {
            System.out.print(i+1 + ".");
            System.out.println(task[i]);
        }
        System.out.println();
        System.out.println(" |\\__/,|   (`\\\n" +
                            " |_ _  |.--.) )\n" +
                            " ( T   )     /\n" +
                            "(((^_(((/(((_/");
        System.out.println("===============================================");
    }

    public static void addToList(String line, String type) {
        switch (type) {
        case "T":
            addTodoTask(line);
            break;
        case "D":
            addDeadlineTask(line);
            break;
        case "E":
            addEventTask(line);
            break;
        }
        totalTasksCount++;
        System.out.println();
        System.out.println("Added: " + tasks[totalTasksCount - 1]);
        System.out.println();
        System.out.println("  /\\_/\\  (\n" +
                            " ( ^.^ ) _)\n" +
                            "   \\\"/  (\n" +
                            " ( | | )\n" +
                            "(__d b__)");
        System.out.println("===============================================");
    }

    private static void addTodoTask(String line) {
        String taskName = getTaskName(line);
        tasks[totalTasksCount] = new Todo(taskName);
    }

    private static String getTaskName(String line) {
        return line.substring(line.indexOf(" ") + 1);
    }

    private static void addDeadlineTask(String line) {
        String task = hasDeadline(line) ? getDeadlineTaskName(line) : getTaskName(line);
        String deadline = hasDeadline(line) ? getDeadline(line) : "No Deadline!";
        tasks[totalTasksCount] = new Deadline(task, deadline);
    }

    private static String getDeadlineTaskName(String line) {
        return line.substring(line.indexOf(" ") + 1, line.indexOf("/by"));
    }

    private static String getDeadline(String line) {
        return line.substring(line.indexOf("/by ") + 4);
    }

    private static boolean hasDeadline(String line) {
        return line.contains("/by");
    }

    private static void addEventTask(String line) {
        String task = hasEventDate(line) ? getEventTaskName(line) : getTaskName(line);
        String EventDate = hasEventDate(line) ? getEventDate(line) : "No Event Date!";
        tasks[totalTasksCount] = new Event(task, EventDate);
    }

    private static String getEventTaskName(String line) {
        return line.substring(line.indexOf(" ") + 1, line.indexOf("/at"));
    }

    private static String getEventDate(String line) {
        return line.substring(line.indexOf("/at ") + 4);
    }

    private static boolean hasEventDate(String line) {
        return line.contains("/at");
    }

    public static void markTask(String line) {
        if (!isTaskNumValid(line)) {
            printCommands.showErrorMessage();
            return;
        }
        int taskNum = Integer.parseInt(line.split(" ")[1]);
        tasks[taskNum-1].setDone();
        System.out.println();
        System.out.println("Good Job!! One more thing off your list!!");
        System.out.println(tasks[taskNum-1]);
        System.out.println("                       /)\n" +
                "              /\\___/\\ ((\n" +
                "              \\`@_@'/  ))\n" +
                "              {_:Y:.}_//\n" +
                "    ----------{_}^-'{_}----------");
        System.out.println("===============================================");
    }

    private static boolean isTaskNumValid(String line) {
        String taskNum = line.split(" ")[1];
        boolean isAllNumbers = taskNum.matches("[0-9]+");
        if (isAllNumbers) {
            int numericTaskNum = Integer.parseInt(taskNum);
            boolean isTaskNumPositive = numericTaskNum > 0;
            boolean isTaskNumInRange = numericTaskNum <= totalTasksCount;
            return isTaskNumPositive && isTaskNumInRange;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        printCommands.greet();
        String line;
        String command;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("You: ");
            line = in.nextLine();
            command = line.split(" ")[0];
            switch (command) {
            case "bye":
                printCommands.exit();
                break;
            case "list":
                printList(tasks);
                break;
            case "done":
                markTask(line);
                break;
            case "todo":
                addToList(line, "T");
                break;
            case "deadline":
                addToList(line, "D");
                break;
            case "event":
                addToList(line, "E");
                break;
            default:
                printCommands.showErrorMessage();
                break;
            }
        }
    }
}
