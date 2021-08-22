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
        while (!endTask) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.equals("bye")) {
                endTask = true;
                in.close();
                continue;
            }
            System.out.println(line + "\n" + input + "\n" + line);
        }
        
        System.out.println(line + "\n Bye. Hope to see you again soon!\n" + line);
    }
}