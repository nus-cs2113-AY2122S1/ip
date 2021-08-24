import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        int count = 0;
        String[] tasks = new String[100];
        Task[] stuff = new Task[100];
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________\n");
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________\n");

        line = in.nextLine();
        while (line.compareTo("bye") != 0) {
            switch (line.split(" ")[0]) {
                case "done":
                    int pos = Integer.parseInt(line.split(" ")[1]) - 1;
                    System.out.println("____________________________________\n");
                    System.out.println("Nice I've marked this task as done:");
                    System.out.println("[X] " + stuff[pos].getName());
                    System.out.println("____________________________________\n");
                    stuff[pos].setDone();
                    break;
                case "list":
                    System.out.println("____________________________________\n");
                    for (int i = 0; i < count; i++) {
                        if (stuff[i].getDone())
                            System.out.println(Integer.toString(i + 1) + "." + "[X] " + stuff[i].getName());
                        else
                            System.out.println(Integer.toString(i + 1) + "." + "[ ] " + stuff[i].getName());
                    }
                    System.out.println("____________________________________\n");
                    break;
                default: //Add is default function
                    System.out.println("____________________________________\n");
                    System.out.println("Added: " + line);
                    System.out.println("____________________________________\n");
                    stuff[count] = new Task(line);
                    tasks[count++] = line;
            }
            line = in.nextLine();
        }

        System.out.println("____________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________\n");
    }
}
