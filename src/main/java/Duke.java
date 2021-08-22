import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        int count = 0;
        String input;
        Task[] lists = new Task[100];
        Scanner in = new Scanner(System.in);
        String line = "________________________________________________________";

        //logo
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome!\n" + logo);

        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line);

        //Ask user for input
        input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");

                //Lists down all the tasks added along with its status
                for (int i = 0; i < count; i++) {
                    String taskStatus = lists[i].getStatusIcon();
                    System.out.println((i + 1) + ".[" + taskStatus + "] " + lists[i].description);
                }

                System.out.println(line);
            } else if (input.contains("done")) {

                //Takes the index number needed to change the status
                String[] temp = input.split(" ");
                int index = Integer.parseInt(temp[1]);
                index = index - 1;
                lists[index].markAsDone();
                String taskStatus = lists[index].getStatusIcon();

                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + taskStatus + "] " + lists[index].getDescription());
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("Added: " + input);

                //Object created for each task
                Task t = new Task(input);
                lists[count] = t;
                count++;

                System.out.println(line);
            }
            input = in.nextLine();
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
