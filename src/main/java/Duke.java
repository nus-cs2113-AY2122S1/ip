import java.util.Scanner;

public class Duke {
    // Task array with counter to keep track of array index to add
    private static Task[] tasks = new Task[100];
    private static int tasksCounter = 0;
    private static String friday = "  __      _     _             \n"
            + " / _|    (_)   | |            \n"
            + "| |_ _ __ _  __| | __ _ _   _ \n"
            + "|  _| '__| |/ _` |/ _` | | | |\n"
            + "| | | |  | | (_| | (_| | |_| |\n"
            + "|_| |_|  |_|\\__,_|\\__,_|\\__, |\n"
            + "                         __/ |\n"
            + "                        |___/ \n";
    private static String dashes = "____________________________________________________________\n";


    public static void main(String[] args) {
        greetUser();
        // Scan in user input of tasks
        mainProgram();
        exitProgram();
    }
    // greet user: called at the start of the program
    public static void greetUser() {
        System.out.print(dashes);
        System.out.println(friday);
        System.out.println("Initiating FRIDAY");
        System.out.println("Hello Mr Stark, how may I be of assistance to you today");
        System.out.print(dashes);
    }

    // get list: prints out list of tasks upon user command "list"
    public static void getList() {
        System.out.print(dashes);
        System.out.println("Listing all tasks for today.");
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(task);
        }
        System.out.print(dashes);
    }

    // add task: when user types in a task (not list, done or bye command).
    // @params in: Task instance of new task created + string of task name
    public static void addTask(Task newTask, String taskName) {
        tasks[tasksCounter++] = newTask;
        System.out.print(dashes);
        System.out.println("Very well, adding task \"" + taskName + "\"");
        System.out.print(dashes);

    }

    public static void addToDo(String taskName) {
        Todo newTodo = new Todo(false, taskName);
        addTask(newTodo, taskName);
    }

    public static void addDeadline(String taskName, String deadline) {
        Deadline newDeadline = new Deadline(false, taskName, deadline);
        addTask(newDeadline, taskName);
    }

    public static void addEvent(String taskName, String date) {
        Event newEvent = new Event(false, taskName, date);
        addTask(newEvent, taskName);
    }

    private static void mainProgram() {
        //String userInput = "";
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();
            while (userInput.trim().isEmpty()) {
                userInput = in.nextLine();
            }

            if (userInput.equals("bye")) {
                break;
            }
            // if user lists
            if (userInput.equals("list")) {
                getList();
                continue;
            }

            // TODO
            if (userInput.startsWith("todo")) {
                addToDo(userInput.substring(userInput.indexOf(" ") + 1));
                continue;
            }

            // DEADLINES
            if (userInput.startsWith("deadline")) {
                String taskName = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/")).trim();
                String deadline = userInput.substring(userInput.indexOf("/") + 3).trim();
                addDeadline(taskName, deadline);
                continue;
            }

            //EVENTS
            if (userInput.startsWith("event")) {
                String taskName = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/")).trim();
                String eventDate = userInput.substring(userInput.indexOf("/") + 3).trim();
                addEvent(taskName, eventDate);
                continue;
            }

            // if user wants to mark as done
            if (userInput.startsWith("done")) {
                // get index of task to change
                int taskIndex = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1)) - 1;

                // if task out of bounds
                if (taskIndex < 0 || taskIndex > 99) {
                    System.out.println(dashes);
                    System.out.println("Apologies sir, there is no such task in your list.");
                    System.out.println(dashes);
                    continue;
                }
                // change task to done
                Task currTask = tasks[taskIndex];
                if (currTask == null) {
                    System.out.println(dashes);
                    System.out.println("Apologies sir, there are currently no tasks in your list");
                    System.out.println(dashes);
                    continue;
                }
                currTask.setDone(true);
                System.out.println(dashes);
                System.out.println("Your task \"" + currTask.getTaskName() + "\" is indicated as complete.");
                System.out.println("[X]" + currTask.getTaskName());
                System.out.println(dashes);
                continue;
            }
            // ask user to input task with todo, event or deadline
            System.out.println(dashes);
            System.out.println("Enter todo, event or deadline before task name to classify task correctly");
            System.out.println(dashes);
        }
    }

    // exit program: message printed when user exits program
    public static void exitProgram() {
        String exit = dashes
                + "Powering Off now. Good Bye Mr Stark.\n"
                + dashes;
        System.out.println(exit);
    }
}
