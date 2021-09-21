import java.util.Scanner;

public class Duke {
    static final String EXIT = "bye";
    static final String LIST = "list";
    static final String DONE = "done";
    static final String LINE = "--------------------------------";

    static Task[] tasks = new Task[100];
    static int taskCount = 0;

    public static void greet() {
        String logo = "       ___                             | '  \\\n" +
                "   ___  \\ /  ___         ,'\\_           | .-. \\        /|\n" +
                "   \\ /  | |,'__ \\  ,'\\_  |   \\          | | | |      ,' |_   /|\n" +
                " _ | |  | |\\/  \\ \\ |   \\ | |\\_|    _    | |_| |   _ '-. .-',' |_   _\n" +
                "// | |  | |____| | | |\\_|| |__    //    |     | ,'_`. | | '-. .-',' `. ,'\\_\n" +
                "\\\\_| |_,' .-, _  | | |   | |\\ \\  //    .| |\\_/ | / \\ || |   | | / |\\  \\|   \\\n" +
                " `-. .-'| |/ / | | | |   | | \\ \\//     |  |    | | | || |   | | | |_\\ || |\\_|\n" +
                "   | |  | || \\_| | | |   /_\\  \\ /      | |`    | | | || |   | | | .---'| |\n" +
                "   | |  | |\\___,_\\ /_\\ _      //       | |     | \\_/ || |   | | | |  /\\| |\n" +
                "   /_\\  | |           //_____//       .||`  _   `._,' | |   | | \\ `-' /| |\n" +
                "        /_\\           `------'        \\ |               `.\\  | |  `._,' /_\\\n" +
                "                                       \\|                    `.\\\n" +
                "  S. Lu - I solemnly swear that I am up to no good.\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "Trade with me your soul and you shall get what you desire.\n");
    }

    public static void alarm() {
        System.out.println("Invalid charm. What did Professor Flitwick told you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void add(Task t) {
        taskCount++;
        System.out.println("|| Got it. I've added this task");
        System.out.println("|| \t" + t.toString());
        System.out.println("|| Now you have " + taskCount + " in the list.");
    }

    public static void printList() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(tasks[i].id + ". " + tasks[i].toString());
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

    public static String[] getDetails(String input, String keyword) {
        String[] details = new String[3];
        //details[0]: eliminated first word
        //details[1]: description
        //details[2]: time

        int i = input.indexOf(" ");
        String firstWord = input.substring(0, i);
        details[0] = input.replace(firstWord, "");

        if (keyword != null) {
            int j = details[0].indexOf("/");
            details[1] = details[0].substring(0, j);

            String txt = details[0].substring(j);
            details[2] = txt.replace(keyword, "");
        }

        return details;
    }


    public static void main(String[] args) {
        greet();

        String input;
        int i = 0;

        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        while (!input.startsWith("bye")) {
            String[] details;

            if (input.startsWith("hello")) {
                greet();
            } else if (input.startsWith("list")) {
                printList();
            } else if (input.startsWith("done")) {
                markDone(input);
            } else if (input.startsWith("todo")) {
                details = getDetails(input, null);
                tasks[i] = new Todo(details[0]);
                add(tasks[i]);
                i++;
            } else if (input.startsWith("deadline")) {
                details = getDetails(input, "/by");
                tasks[i] = new Deadline(details[1], details[2]);
                add(tasks[i]);
                i++;
            } else if (input.startsWith("event")) {
                details = getDetails(input, "/at");
                tasks[i] = new Event(details[1],details[2]);
                add(tasks[i]);
                i++;
            } else {
                alarm();
            }
            input = in.nextLine();
        }
        exit();
    }
}
