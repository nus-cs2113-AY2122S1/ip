import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String Logo = " _____         _____        \n"
                + "|     \\ _____ |     \\ _____ \n"
                + "|  o  /|     ||  o  /|     |\n"
                + "|  o  \\|  o  ||  o  \\|  o  |\n"
                + "|_____/|_____||_____/|_____|\n";
        String HORIZONTAL_LINE = "____________________________________________________________";

        String line;
        boolean isRunning = true;
        Scanner in = new Scanner(System.in);

        System.out.println(Logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Bobo!");
        System.out.println("I'm a little blur, but I'd love to help!");
        System.out.println(HORIZONTAL_LINE);

        while (isRunning) {
            line = in.nextLine();

            if (line.equals("bye")) {
                isRunning = false;
            } else if(line.equals("list")) {
                System.out.println(HORIZONTAL_LINE);
                for (int i = 0; i < Task.numberOfTasks; i++) {
                    System.out.println((i + 1) + ".["
                            + Task.getTask(i).getStatusIcon() + "] "
                            + Task.getTask(i).getDescription());
                }
                System.out.println(HORIZONTAL_LINE);
            } else if (line.startsWith("done")) {
                int taskNumber = Integer.parseInt(line.substring(4).trim()) - 1;
                Task.getTask(taskNumber).markAsDone();

                System.out.println(HORIZONTAL_LINE);
                System.out.println("Okie! Marked this as done: ");
                System.out.println((taskNumber + 1) + ".["
                        + Task.getTask(taskNumber).getStatusIcon() + "] "
                        + Task.getTask(taskNumber).getDescription());
                System.out.println(HORIZONTAL_LINE);
            } else {
                Task t = new Task(line);

                System.out.println(HORIZONTAL_LINE);
                System.out.println("umm ok added: " + line);
                System.out.println(HORIZONTAL_LINE);
            }
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Ok. Bye bye!");
        System.out.println(HORIZONTAL_LINE);
    }
}
