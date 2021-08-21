import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    // Print logo
    public static void printLogo() {
        // Generated ASCII Art - https://patorjk.com/software/taag/#p=display&f=Dancing%20Font&t=Duke
        String logo = "  ____     _   _    _  __  U _____ u \n"
                + " |  _\"\\ U |\"|u| |  |\"|/ /  \\| ___\"|/ \n"
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
    public static void addToTasklist(Task[] tasklist, int taskCounter, String taskname) {
        Task newTask = new Task(taskname);
        tasklist[taskCounter] = newTask;
        System.out.println("____________________________________________________________\n"
                + " Here you go...\n Added to stuff you would definitely forget to do (*facepalm*): "
                + taskname
                + "\n"
                + "____________________________________________________________");
    }

    public static void printTasklist(Task[] tasklist, int taskCounter) {
        // validTasklist contains only Tasks that are not NULL
        Task[] validTasklist = Arrays.copyOf(tasklist, taskCounter);
        System.out.println(" EEEEEOOOOOO~ ALL RIGHT~ Oops was jamming away in my virtual garage, here's your TO-DO list...");
        for (int i = 0; i < taskCounter; i++) {
            System.out.print(" " + (i + 1) + ".[" + validTasklist[i].getStatusIcon() + "] ");
            System.out.println(validTasklist[i].getTaskname());
        }
        System.out.println("____________________________________________________________");
    }

    // Level-3 Mark Task as done and print out after-message
    public static void markTaskAsDone(Task task) {
        task.setDone();
        System.out.println(" Great! You didn't forget to do it! I have marked it as done!\n"
                + " [" + task.getStatusIcon() + "] " + task.getTaskname() + "\n"
                + "____________________________________________________________");
    }

    public static void main(String[] args) {
        printLogo();
        printGreeting();

        // Level-1. Greet, Echo, Exit Logic
        // initialize the user input "scanner"
        Scanner sc = new Scanner(System.in);
        String userCommand = "";
        Task[] tasklist = new Task[100];
        int taskCounter = 0;

        while (true) {
            System.out.print(" What's your plans/command for today (No... I am not hitting on you) : ");
            userCommand = sc.nextLine();
            if (userCommand.equalsIgnoreCase("Bye")) {
                // Immediately break loop to print farewell and stop program
                break;
            }
            if (userCommand.equalsIgnoreCase("List") || userCommand.equals("")) {
                printTasklist(tasklist, taskCounter);
            } else {
                String[] params = userCommand.split(" ");
                if (params[0].equalsIgnoreCase("Done")) {
                    int index = Integer.parseInt(params[1]);
                    markTaskAsDone(tasklist[index - 1]);
                } else {
                    addToTasklist(tasklist, taskCounter++, userCommand);
                }
            }
        }

        printFarewell();
    }
}
