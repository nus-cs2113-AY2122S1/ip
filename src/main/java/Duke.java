//Chatbot creator: Madhan Selvapandian
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n"
                          + "What can I do for you?");
        String line;
        Task[] tasks = new Task[100];
        int count = 0;
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            System.out.println("___________________COMMAND ACTIVATED__________________");
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("___________________SHUTTING DOWN______________________");
            } else if (line.equals("list")) {
                int i;
                if (count == 0) {
                    // if there are no tasks in the list
                    System.out.println("No items were added into the list.");
                } else {
                    System.out.println("Below are the list of tasks in your list:");
                    for (i = 0; i < count; i++) {
                        System.out.print((i + 1) + ". ");
                        tasks[i].printTask();
                    }
                }
            } else if (line.contains("done")){
                int index = Integer.parseInt(line.substring(5)) - 1;
                if (index >= count) {
                    // if the task is not assigned to the number given by the user
                    System.out.println("The task number is invalid.");
                } else {
                    tasks[index].setDone(true);
                    System.out.println("Amazing! I have marked this task as done:");
                    tasks[index].printTask();
                }
            } else {
                // if the user input is a new task
                Task newTask = new Task(line);
                tasks[count] = newTask;
                count++;
                System.out.println("Noted. I have successfully added this task:");
                newTask.printTask();
            }
            if (!line.equals("bye")) {
                System.out.println("___________________COMMAND EXECUTED___________________");
                System.out.println("Anything else?");
            }
        } while (!line.equals("bye"));
    }
}