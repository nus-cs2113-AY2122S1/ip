import java.util.Scanner;

public class Duke {
    static final int DESCRIPTION = 1;
    static final int TIME = 2;
    static final String LINE = "--------------------------------";

    static Task[] tasks = new Task[100];
    static int taskCount = 0;

    public static void greet() {
        String logo = "  _                                         _   _            \n" +
                " | |__   __ _ _ __ _ __ _   _   _ __   ___ | |_| |_ ___ _ __ \n" +
                " | '_ \\ / _` | '__| '__| | | | | '_ \\ / _ \\| __| __/ _ \\ '__|\n" +
                " | | | | (_| | |  | |  | |_| | | |_) | (_) | |_| ||  __/ |   \n" +
                " |_| |_|\\__,_|_|  |_|   \\__, | | .__/ \\___/ \\__|\\__\\___|_|   \n" +
                "                        |___/  |_|                         \n" +
                "  S. Lu - I solemnly swear that I am up to no good.\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(
                "       Messrs Moony, Wormtail, Padfoot, and Prongs\n" +
                "       Purveyors of Aids to Magical Mischief-Makers\n" +
                "                  are proud to present\n" +
                "                 --THE MARAUDER'S MAP--");
    }

    public static void hello() {
        System.out.println("I see you are lost. \n" +
                "Read the charm beneath out loud, and I shall serve you.");
        System.out.println("- \"I solemnly swear that I am up to no good.\" ");
    }

    public static void alarm() {
        System.out.println("Invalid charm. What did Professor Flitwick told you?");
    }

    public static void exit() {
        System.out.println("Mischief managed.");
    }

    public static void add(Task t) {
        tasks[taskCount]= t;
        taskCount++;
        System.out.println("|| Got it. I've added this task");
        System.out.println("|| \t" + t.toString());
        System.out.println("|| Now you have " + taskCount + " in the list.");
    }

    public static void printList() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < taskCount; i++) {
            Task t = tasks[i];
            System.out.println(t.id + ". " + t.toString());
        }
        System.out.println(LINE);
    }

    public static void markDone(String input){

        String[] txt = input.split(" ", 2);

        int i = Integer.parseInt(txt[1])-1;
        tasks[i].setDone(true);
        System.out.println("Nice!I've marked this task as done:") ;
        System.out.println(tasks[i].id + ". " + tasks[i].toString());

    }

    public static String removeFirstWord(String input) {
        int i = input.indexOf(" ");
        String firstWord = input.substring(0, i);
        return input.replace(firstWord, "");
    }

    public static String[] getDetails(String input) {
        String[] details = new String[3];

        details[0] = removeFirstWord(input);

        int j = details[0].indexOf("/");

        details[DESCRIPTION] = details[0].substring(0, j);
        details[TIME] = details[0].substring(j+4);

        return details;
    }

    public static void main(String[] args) {
        greet();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.startsWith("bye")) {
            String[] details;

            if (input.startsWith("hello")) {
                hello();
            } else if (input.startsWith("list")) {
                printList();
            } else if (input.startsWith("done")) {
                markDone(input);
            } else if (input.startsWith("todo")) {
                String description = removeFirstWord(input);
                Todo t = new Todo(description);
                add(t);
            } else if (input.startsWith("deadline")) {
                details = getDetails(input);
                Deadline t = new Deadline(details[DESCRIPTION], details[TIME]);
                add(t);
            } else if (input.startsWith("event")) {
                details = getDetails(input);
                Event t = new Event(details[DESCRIPTION],details[TIME]);
                add(t);
            } else {
                alarm();
            }

            input = in.nextLine();
        }
        exit();
    }
}
