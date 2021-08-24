import java.util.Scanner;

public class Duke {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        int i;
        String line;
        String[] tasks = new String[100];
        int taskCounter = 0;

        Scanner in = new Scanner(System.in);

        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();

        line = in.nextLine();

        while (!(line.equals("bye"))) {

            if (line.equals("list")) {
                printLine();
                for (i = 0; i < taskCounter; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
            } else {
                tasks[taskCounter] = line;
                taskCounter++;
                printLine();
                System.out.println("added : " + line);
            }

            printLine();
            line = in.nextLine();
        }

        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
        for (i = 0; i < taskCounter; i++) {
            tasks[i] = null;
        }
    }
}

