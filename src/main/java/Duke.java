import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    // Fixed Variables
    private static final String INVALID_TASK_MESSAGE = " That is invalid... Please use the syntax - "
            + "[taskType] [taskName] ([/by dateTime] or [/at dateTime] depending on taskType)";

    private static final String BY_WHEN_PREFIX = "/by";
    private static final String AT_WHEN_PREFIX = "/at";

    // Variables for managing of Tasks
    private static int taskCounter = 0;

    // Print logo
    public static void printLogo() {
        // Generated ASCII Art - https://patorjk.com/software/taag/#p=display&f=Dancing%20Font&t=Duke
        String logo = "  ____     _   _    _  __  U _____ u\n"
                + " |  _\"\\ U |\"|u| |  |\"|/ /  \\| ___\"|/\n"
                + "/| | | | \\| |\\| |  | ' /    |  _|\"\n"
                + "U| |_| |\\ | |_| |U/| . \\u   | |___\n"
                + " |____/ u<<\\___/   |_|\\_\\   |_____|\n"
                + "  |||_  (__) )(  ,-,>> \\\\,-.<<   >>\n"
                + " (__)_)     (__)  \\.)   (_/(__) (__)";
        System.out.println(logo);
    }

    // Print Level-0. Skeletal Barebone, Greeting and Farewell
    public static void printGreeting() {
        String greeting = "____________________________________________________________\n"
                + " Hello! You probably know that Iron Man has the best AI-assistant called Jarvis\n"
                + " and Spiderman has hmmm, maybe his tingly spidey senses?\n"
                + " But don't worry! You have me, Duke! I am your personal SIDEKICK that does \"something\"!\n"
                + " What is \"something\" you want me to do?\n"
                + "____________________________________________________________";
        System.out.println(greeting);
    }

    public static void printFarewell() {
        String farewell = "____________________________________________________________\n"
                + " Bye, have a nice day! From your friendly neighbourhood assistant, Duke~\n"
                + " (NICE, I can finally binge watch Rick and Morty~)\n"
                + "____________________________________________________________";
        System.out.println(farewell);
    }

    // Level-2 Add method to add to Tasklist, List method to print Task in the current Tasklist
    public static void addToTasklist(Task[] tasklist, String taskType, String taskName) {
        Task newTask;
        // actualTaskName contains the actual Task's Name after stripping out the portion for the specified DateTime
        String actualTaskName;
        if (taskType.equalsIgnoreCase("Todo")) {
            newTask = new Todo(taskName);
        } else if (taskType.equalsIgnoreCase("Deadline")) {
            if (!taskName.contains("/by")) {
                System.out.println(INVALID_TASK_MESSAGE);
                return;
            }
            String byWhen = taskName.split(BY_WHEN_PREFIX)[1].trim();
            actualTaskName = taskName.replace(BY_WHEN_PREFIX, "").replace(byWhen, "");
            actualTaskName = actualTaskName.trim();
            newTask = new Deadline(actualTaskName, byWhen);
        }
        else if (taskType.equalsIgnoreCase("Event")) {
            if (!taskName.contains("/at")) {
                System.out.println(INVALID_TASK_MESSAGE);
                return;
            }
            String atWhen = taskName.split(AT_WHEN_PREFIX)[1].trim();
            actualTaskName = taskName.replace(AT_WHEN_PREFIX, "").replace(atWhen, "");
            actualTaskName = actualTaskName.trim();
            newTask = new Event(actualTaskName, atWhen);
        }
        else {
            System.out.println(INVALID_TASK_MESSAGE);
            return;
        }
        tasklist[taskCounter] = newTask;
        taskCounter++;

        System.out.println("____________________________________________________________\n"
                + " Here you go...\n Added to stuff you would definitely forget to do (*facepalm*): "
                + newTask.getTaskName()
                + "\n"
                + "____________________________________________________________");
    }

    public static void printTasklist(Task[] tasklist, int taskCounter) {
        // validTasklist contains only Tasks that are not NULL
        Task[] validTasklist = Arrays.copyOf(tasklist, taskCounter);
        if (taskCounter == 0) {
            System.out.println(" Hi there! You have no dates! LITERALLY");
            return;
        }
        System.out.println(" EEEEEOOOOOO~ ALL RIGHT~ Oops was jamming away in my virtual garage, here's your PLAN/S...");
        for (int i = 0; i < taskCounter; i++) {
            System.out.println(" " + (i + 1) + ". " + validTasklist[i].toString());
        }
        System.out.println("____________________________________________________________");
    }

    // Level-3 Mark Task as done and print out after-message
    public static void markTaskAsDone(Task task) {
        task.setDone();
        System.out.println(" Great! You didn't forget to do it! I have marked it as done!\n"
                + " " + task.toString() + "\n"
                + "____________________________________________________________");
    }

    public static void main(String[] args) {
        printLogo();
        printGreeting();

        // Level-1. Greet, Echo, Exit Logic
        // initialize the user input "scanner"
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        Task[] tasklist = new Task[100];
        //int taskCounter = 0;

        while (true) {
            System.out.print(" What's your plans/command for today (No... I am not hitting on you) : ");
            userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("Bye")) {
                break;
            }
            if (userInput.equalsIgnoreCase("List") || userInput.equals("")) {
                printTasklist(tasklist, taskCounter);
            } else {
                String[] userParams = userInput.split(" ");
                if (userParams[0].equalsIgnoreCase("Done")) {
                    int index = Integer.parseInt(userParams[1]);
                    markTaskAsDone(tasklist[index - 1]);
                } else {
                    String taskType = userParams[0];
                    String taskName = userInput.replace(taskType, "").trim();
                    addToTasklist(tasklist, taskType, taskName);
                }
            }
        }
        sc.close();
        printFarewell();
    }
}
