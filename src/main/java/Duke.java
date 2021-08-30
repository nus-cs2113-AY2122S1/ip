import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //welcome message
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Task[] list = new Task[100];
        int listItemCount = 0; //track number of tasks in the list

        Scanner in = new Scanner(System.in);
        String input = in.nextLine(); //read input

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < listItemCount; i += 1) {
                    System.out.println((i + 1) + ". " + list[i]);
                }

            } else if (input.startsWith("done ")) {
                String[] words = input.split(" ");
                //get task number to be marked as done; use constant
                int taskNum = Integer.parseInt(words[1]) - 1; //use zero indexing (extract magic number here)
                list[taskNum].markAsDone();

                System.out.println("Nice! I've marked the following task as done: ");
                System.out.println("   " + list[taskNum]);

            } else if (input.startsWith("todo ")){
                int indexOfStartOfTask = input.indexOf(" ");
                String task = input.substring(indexOfStartOfTask, input.length()).trim();
                list[listItemCount] = new Todo(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(list[listItemCount]);
                listItemCount += 1;
                System.out.println("Now you have " + listItemCount + " items in the list.");

            } else if (input.startsWith("deadline ")){
                int indexOfStartOfTask = input.indexOf(" ");
                int indexOfStartOfDeadline = input.indexOf("/by ");
                String task = input.substring(indexOfStartOfTask, indexOfStartOfDeadline).trim();
                String by = input.substring(indexOfStartOfDeadline + "/by ".length(), input.length()).trim();
                list[listItemCount] = new Deadline(task, by);
                System.out.println("Got it. I've added this task:");
                System.out.println(list[listItemCount]);
                listItemCount += 1;
                System.out.println("Now you have " + listItemCount + " items in the list.");

            } else if (input.startsWith("event ")){
                int indexOfStartOfTask = input.indexOf(" ");
                int indexOfStartOfEvent = input.indexOf("/at ");
                String task = input.substring(indexOfStartOfTask, indexOfStartOfEvent).trim();
                String at = input.substring(indexOfStartOfEvent + "/at ".length(), input.length()).trim();
                list[listItemCount] = new Event(task, at);
                System.out.println("Got it. I've added this task:");
                System.out.println(list[listItemCount]);
                listItemCount += 1;
                System.out.println("Now you have " + listItemCount + " items in the list.");

            } else {
                //perhaps change the default behaviour to show help?
                System.out.println("default/help message");
            }

            input = in.nextLine(); //clear the buffer
        }

        //exit message
        System.out.println("Bye. Hope to see you again soon!");
    }
}
