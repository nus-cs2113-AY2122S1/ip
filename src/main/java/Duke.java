import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        Task[] taskList = new Task[100];
        int itemCount = 0;
        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n" ;

        String goodbye = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                +  "____________________________________________________________\n";

        System.out.println(greeting);

        while(true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println(goodbye);
                return;
            }
            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are your remaining tasks:");

                for (int i = 1; i <= itemCount; i++) {
                    System.out.println(
                            Integer.toString(i)
                            + ". ["
                            + taskList[i-1].getStatusIcon()
                            + "] "
                            + taskList[i-1].description
                    );
                }
                System.out.println("____________________________________________________________\n");

            } else if (line.startsWith("done ")) {
                int i = Integer.parseInt(line.substring(5)) - 1;
                taskList[i].markAsDone();

                System.out.println("____________________________________________________________\n"
                        + "Well done! I've marked this task as done: \n"
                        + "["
                        + taskList[i].getStatusIcon()
                        + "] "
                        + taskList[i].description
                        + "\n"
                        + "____________________________________________________________\n"
                );

            } else {
                System.out.println("____________________________________________________________\n"
                        + "added: "
                        + line + "\n"
                        + "____________________________________________________________\n"
                );
                taskList[itemCount] = new Task(line);
                itemCount++;
            }

        }
    }
}
