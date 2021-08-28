import java.util.Scanner;

public class Duke {
    public static void welcomeMessage() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\t____________________________________________________________");
        System.out.println(logo);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");
    }
        

    public static void main(String[] args) {
        welcomeMessage();

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine().trim();

        Task[] tasks = new Task[100];

        //index of task in tasks array
        int tasksIndex = 0;

        //check userInput with certain command keywords

        while (!userInput.equals("bye")) {
            System.out.println("\t____________________________________________________________");

            if (userInput.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                //listing out tasks if userInput == "list"
                for (int i = 0; i < tasksIndex; i++) {
                    System.out.println("\t" + (i + 1) + "." +
                            "[" + tasks[i].getStatusIcon() + "] " +
                            tasks[i].getDescription());
                }
                System.out.println("\t____________________________________________________________");
            } else if (userInput.startsWith("done")) {
                String taskNumberStr = userInput.substring(5);
                int taskNumber = Integer.parseInt(taskNumberStr);

                //taskNumber displayed starting with 1
                //but array starts with 0
                (tasks[taskNumber - 1]).markAsDone();

                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  [X] " + (tasks[taskNumber - 1]).getDescription());

            } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {

                //instantiate new Task, store in array
                if (userInput.startsWith("todo")) {
                    tasks[tasksIndex] = new Todo(userInput);
                } else if (userInput.startsWith("deadline")) {
                    tasks[tasksIndex] = new Deadline(userInput);
                } else {
                    tasks[tasksIndex] = new Event(userInput);
                }
                tasksIndex++;
                System.out.println("\tadded: " + userInput);

                System.out.println("\t____________________________________________________________");
            }

            //catch all for invalid inputs
            else {
                userInput = in.nextLine().trim();
                continue;
            }

            userInput = in.nextLine().trim();
        }

        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
