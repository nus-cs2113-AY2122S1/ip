import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        //VISUALS//
        String horizontalLine = "____________________________________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = horizontalLine
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + horizontalLine;
        String farewell = horizontalLine
                + " Bye. Hope to see you again soon!\n"
                + horizontalLine;

        //MAIN//
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);

        String[] textData = new String[100];
        int textDataSize = 0;
        String command = "start";
        Scanner in = new Scanner(System.in);
        while (!command.equals("bye")) {
            command = in.nextLine();
            if (command.equals("list")) {
                System.out.println(horizontalLine);
                for (int i = 0; i < textDataSize; i++) {
                    int j = i + 1;
                    System.out.println(" " + j + ". " + textData[i]);
                }
                System.out.println(horizontalLine);
            } else {
                textData[textDataSize] = command;
                textDataSize++;
                String added = horizontalLine
                        + " added: " + command + "\n"
                        + horizontalLine;
                System.out.println(added);
            }
        }

        System.out.println(farewell);
    }
}
