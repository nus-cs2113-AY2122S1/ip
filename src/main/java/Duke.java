import java.util.Scanner;


public class Duke {
    public static void hi() {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void running() {
        Task[] tasks= new Task[100];
        int taskNumber = 0;
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            //new scanner
            line = in.nextLine();
            //if goodbye
            if (line.equals("bye")) {
                return;
            }
            //if want to list out tasks or mark as done or add items
            if (line.equals("list")) {
                for (int i = 0; i < taskNumber; i++) {
                    System.out.println((i + 1) + "." + tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
                }
            }
            else if (line.startsWith("done")) {
                int index = Integer.parseInt(line.substring(5)) - 1;
                tasks[index].setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[index].getStatusIcon() + " " + tasks[index].getDescription());
            }
            else {
                tasks[taskNumber] = new Task(line);
                taskNumber++;
                System.out.println("added: " + line);
            }
        }
    }

    public static void main(String[] args) {
        hi();
        running();
        bye();
    }
}
