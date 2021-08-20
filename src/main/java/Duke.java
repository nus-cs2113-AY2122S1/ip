import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "____________________________________________________________";
        System.out.println(" Hello! I'm Duke\n" +
                " What can I do for you?");
        System.out.println(line);

        String[] items = new String[100];
        int index = 0;
        String text;
        text = in.nextLine();
        while (!text.equals("bye")) {
            System.out.println(line);
            if (text.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(i+1 + ". " + items[i]);
                }
            } else {
                items[index] = text;
                System.out.println("added: " + text);
                index++;
            }
            System.out.println(line);
            text = in.nextLine();
        }

        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
