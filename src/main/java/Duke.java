import java.util.Scanner;

public class Duke {
    static int count = -1;
    static Task[] tasks = new Task[100];

    public static void handleInput(String input) {
        switch (input.split(" ")[0]) {
        case "done":
            int pos = Integer.parseInt(input.split(" ")[1]) - 1;
            if (pos > count) {
                System.out.println("____________________________________\n");
                System.out.println("No such task exists");
                System.out.println("____________________________________\n");
            } else {
                System.out.println("____________________________________\n");
                System.out.println("Nice I've marked this task as done:");
                System.out.println("[X] " + tasks[pos].getName());
                System.out.println("____________________________________\n");
                tasks[pos].setDone();
            }
            break;

        case "list":
            System.out.println("____________________________________\n");
            for (int i = 0; i < count; i++) {
                if (tasks[i].getDone()) {
                    System.out.println(i + 1 + "." + "[X] " + tasks[i].getName());
                } else {
                    System.out.println(i + 1 + "." + "[ ] " + tasks[i].getName());
                }
            }
            System.out.println("____________________________________\n");
            break;

        default: //Add is default function
            if (count == -1) {
               count = 0;
            }
            System.out.println("____________________________________\n");
            System.out.println("Added: " + input);
            System.out.println("____________________________________\n");
            tasks[count++] = new Task(input);
            }
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________\n");
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________\n");

        line = in.nextLine();
        while (!line.equals("bye")) {
            handleInput(line);
            line = in.nextLine();
        }

        System.out.println("____________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________\n");
    }
}
