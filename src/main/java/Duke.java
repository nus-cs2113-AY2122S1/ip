import java.util.Scanner;

public class Duke {
    private static Task[] List = new Task[100];

    /**
     * Store inputs in a list.
     *
     * @param args  the string the user inputs
     * @param i the ith number of task the user entered
     **/
    public static void add(String args, int i) {
        Task t = new Task(args);
        List[i] = t;
        System.out.println("added: " + args);
        System.out.println("_____________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        // greet
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");

        // to read input on each new line, Duke constantly scans input in this loop
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while(true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                // exit
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_____________________________");
                break;
            } else if (str.equals("list")) {
                // list
                for (int j = 0; List[j] != null; j++) {
                    System.out.print((j + 1) + ". ");
                    System.out.print("[" + List[j].getStatusIcon() + "] ");
                    System.out.println(List[j].getDescription());
                }
                System.out.println("_____________________________");
            } else if (str.contains("done")) {
                // mark task as done
                int stringLength = str.length();
                int taskNumber = Integer.parseInt(str.substring(stringLength - 1));
                List[taskNumber - 1].markAsDone();

                System.out.println("Good job! This task is marked as done:");
                System.out.println("[" + List[taskNumber - 1].getStatusIcon() + "] " + List[taskNumber - 1].getDescription());
                System.out.println("_____________________________");
            } else {
                add(str, i);
                i++;
            }
        }
    }
}
