import java.util.Scanner;

public class Duke {
    private static Task[] List = new Task[100];

    /**
     * Store inputs in a list.
     *
     * @param args  the string the user inputs
     * @param taskNumber the ith number of task the user entered
     **/
    public static void addTaskToList(String args, int taskNumber) {
        Task t = new Task(args);
        List[taskNumber] = t;
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
        int taskNumber = 0;
        while(true) {
            String inputStr = sc.nextLine();
            if (inputStr.equals("bye")) {
                // exit
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_____________________________");
                break;
            } else if (inputStr.equals("list")) {
                // list
                for (int i = 0; List[i] != null; i++) {
                    System.out.print((i + 1) + ". ");
                    System.out.print("[" + List[i].getStatusIcon() + "] ");
                    System.out.println(List[i].getDescription());
                }
                System.out.println("_____________________________");
            } else if (inputStr.contains("done")) {
                // mark task as done
                int stringLength = inputStr.length();
                int doneTaskNumber = Integer.parseInt(inputStr.substring(stringLength - 1));
                List[doneTaskNumber - 1].markAsDone();

                System.out.println("Good job! This task is marked as done:");
                System.out.println("[" + List[doneTaskNumber - 1].getStatusIcon() + "] " + List[doneTaskNumber - 1].getDescription());
                System.out.println("_____________________________");
            } else {
                addTaskToList(inputStr, taskNumber);
                taskNumber++;
            }
        }
    }
}
