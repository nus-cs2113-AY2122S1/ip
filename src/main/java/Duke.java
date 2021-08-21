import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        int count = 0;
        String[] tasks = new String[100];
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________\n");
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________\n");

        //Echo
        line = in.nextLine();
        while (line.compareTo("bye") != 0) {

            switch (line) {
                case "list":
                    System.out.println("____________________________________\n");
                    for (int i = 0; i < count; i++) {
                        System.out.println(Integer.toString(i + 1) + "." + tasks[i]);
                    }
                    System.out.println("____________________________________\n");
                    break;
                default:
                    System.out.println("____________________________________\n");
                    System.out.println("Added: " + line);
                    System.out.println("____________________________________\n");
                    tasks[count++] = line;
            }
            line = in.nextLine();
        }

        System.out.println("____________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________\n");
    }
}
