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
                    System.out.println((i + 1) + ". [" + list[i].getStatusIcon() + "] "
                            + list[i].getDescription());
                }
            } else if (input.startsWith("done")) {
                String[] words = input.split(" ");
                //get task number to be marked as done
                int taskNum = Integer.parseInt(words[1]) - 1; //use zero indexing
                list[taskNum].markAsDone();

                System.out.println("Nice! I've marked the following task as done: ");
                System.out.println("   [X] " + list[taskNum].getDescription());
            } else {
                list[listItemCount] = new Task(input);
                System.out.println("added: " + input);
                listItemCount += 1;
            }

            input = in.nextLine(); //clear the buffer
        }

        //exit message
        System.out.println("Bye. Hope to see you again soon!");
    }
}
