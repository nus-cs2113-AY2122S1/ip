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
        Task[] list = new Task[100];
        int numItemsAdded = 0;
        while (!endTask) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] words = input.split(" ");
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
                    String completed = " ";
                    if (list[i].completed) {
                        completed = "X";
                    }
                    System.out.println(i+1 + ".[" + completed + "] " + list[i].name);
                    i += 1;
                }
                System.out.println(line);
                continue;
            }
            if (words[0].equals("done")) {
                if (Integer.parseInt(words[1]) <= numItemsAdded && Integer.parseInt(words[1]) > 0) {
                    System.out.println(line + "\nNice! I've marked this task as done:\n[X] " + list[Integer.parseInt(words[1])-1].name);
                    list[Integer.parseInt(words[1])-1].completed = true;
                }
                else {
                    System.out.println("That task does not exist!");
                }
                continue;
            }
            list[numItemsAdded] = new Task(false, input);
            numItemsAdded += 1;
            System.out.println(line + "\nadded: " + input + "\n" + line);
        }
        System.out.println(line + "\n Bye. Hope to see you again soon!\n" + line);
    }
}