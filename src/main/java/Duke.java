import java.util.Scanner;

public class Duke {
    public static final int STOP_ADD = -1;
    public static final int ADD_SUCCESS = 1;
    private static boolean isFail = false;
    private static int taskCount = 0;
    public static void printLine() {
        System.out.println("                 ...                 ");
    }

    public static void sayHi(String username) {
        printLine();
        System.out.println("Hello " + username + "!" + "\n" + "I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void sayGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you  again soon!");
        printLine();
    }

    public static String readInputEchoCommand() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        System.out.println("    " + command);
        return command;
    }

    public static String printTask(Task item) {
        if (item.isToDo()) {
            return " (by: " + item.getDeadline() + ") ";
        }
        else if (item.isEvent()) {
            return " (at: " + item.getEventDescription() + ") ";
        }
        return "";
    }


    public static void listTasks(Task[] items) {
        int in = 1;
        System.out.println(" /          / ");
        for (Task item : items) {
            if (item != null) {
                String tick = (item.isDone()) ? "✓" : " ";
                System.out.println(in + ". " + "[" + item.getType() + "] " + "[" + tick + "]" + " " + item.getDescription() + printTask(item));
                in++;
            }
        }
        System.out.println(" /          / ");
    }

    public static boolean getIsFail() {
        return isFail;
    }

    public static void setIsFail() {
        isFail = Boolean.logicalXor(isFail, true);
    }

    public static void runDuke() {
        Scanner in = new Scanner(System.in);
        if (isDukeFail()) {
            return;
        }
        setupUsernamePassword(in);
        sayHi(AccountDetail.getUsername());
        String command;
        Task[] taskList = new Task[100];
        do {
            command = in.nextLine();
            switch (command) {
            case ("list"):
                listTasks(taskList);
                break;
            case ("add"):
                addTaskToList(in, taskList);
                break;
            case ("done"):
                markTasksAsDone(in, taskList);
                break;
            case ("clear"):
                taskList = clearTaskList();
                break;
            case ("mascot"):
                mascotSay(in);
                break;
            case("echo"):
                readInputEchoCommand();
                break;
            case("todo"):
                addTodoToList(in, taskList);
                break;
            case("event"):
                addEventToList(in, taskList);
                break;
            case("deadline"):
                amendTaskDeadline(in, taskList);
                break;
            case ("bye"):
                break;
            default:
                System.out.println("Command not recognized. Please enter a command again!");
            }
        } while (!command.equals("bye"));
        sayGoodbye();
    }

    private static boolean isDukeFail() {
        if (Duke.getIsFail()) {
            System.out.println("        bot failed, sometimes we all need a break       ");
            return true;
        }
        return false;
    }

    private static void mascotSay(Scanner in) {
        Mascot jim = new Mascot();
        String text = in.nextLine();
        Mascot.penguinSay(text);
    }

    private static void setupUsernamePassword(Scanner in) {
        AccountDetail user = new AccountDetail();
        System.out.print("Username [dukeBot]: ");
        AccountDetail.setUsername(in.nextLine());
        System.out.print("Password [bukeDot]: ");
        AccountDetail.setPassword(in.nextLine());
    }

    private static Task[] clearTaskList() {
        Task[] taskList = new Task[100];
        taskCount = 0;
        return taskList;
    }

    private static void markTasksAsDone(Scanner in, Task[] taskList) {
        String number = in.nextLine();
        String[] numberList = number.split(" ");
        System.out.print("remove ");
        for (String i : numberList) {
            int index = Integer.parseInt(i) - 1;
            taskList[index].setDone(true);
            System.out.print(taskList[index].getDescription() + ", ");
        }
        System.out.println("\n / done tasks, good job! / ");
    }

    private static int addTaskToList(Scanner in, Task[] taskList) {
        String taskName;
        do {
            taskName = in.nextLine();
            if (taskName.equals("stop")) {
                return STOP_ADD;
            }
            taskList[taskCount] = new Task(taskName);
            taskCount++;
        } while (!taskName.equals("stop"));
        System.out.println("Finished adding tasks!");
        return ADD_SUCCESS;
    }

    private static int addTodoToList(Scanner in, Task[] taskList) {
        String todoName;
        do {
            todoName = in.nextLine();
            if (todoName.equals("stop")) {
                return STOP_ADD;
            }
            taskList[taskCount] = new Task(todoName, "todo");
            taskCount++;
        } while (!todoName.equals("stop"));
        System.out.println("Finished adding todos!");
        return ADD_SUCCESS;
    }

    private static int addEventToList(Scanner in, Task[] taskList) {
        String eventName;
        do {
            eventName = in.nextLine();
            if (eventName.equals("stop")) {
                return STOP_ADD;
            }
            taskList[taskCount] = new Task(eventName, "event");
            taskCount++;
        } while (!eventName.equals("stop"));
        System.out.println("Finished adding todos!");
        return ADD_SUCCESS;
    }

    private static void amendTaskDeadline(Scanner in,Task[] taskList) {
        String input = in.nextLine();
        input = input.trim();
        String[] separateInput = input.split("/", 2);
        if (separateInput.length == 2) {
            int index = Integer.parseInt(separateInput[0].trim()) - 1;
            Task toChange = taskList[index];
            toChange.setDeadline(separateInput[1]);
            toChange.setToDo(true);
        }
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        runDuke();
    }

}
