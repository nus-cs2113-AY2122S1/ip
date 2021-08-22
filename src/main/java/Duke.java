import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        String line = "____________________________________________________________";
        System.out.println(line + "\n Hello! I'm Duke\n What can I do for you?\n" + line);
        Boolean endTask = false;
        String[] list = new String[100];
        int numItemsAdded = 0;
        while (!endTask) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.equals("bye")) {
                endTask = true;
                in.close();
                continue;
            }
            if (input.equals("list")){
                System.out.println(line);
                if (list[0] == null) {
                    System.out.println("No items added!");
                }
                int i = 0;
                while (list[i] != null) {
                    System.out.println(i+1 + ". " + list[i]);
                    i += 1;
                }
                System.out.println(line);
                continue;
            }
            list[numItemsAdded] = input;
            numItemsAdded += 1;
            System.out.println(line + "\nadded: " + input + "\n" + line);
        }
        System.out.println(line + "\n Bye. Hope to see you again soon!\n" + line);
    }
}