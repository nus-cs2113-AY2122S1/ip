import java.util.Scanner;

public class Duke {

    public static void printMessage(String message) {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine + System.lineSeparator() + message + System.lineSeparator()
                            + horizontalLine);
    }

    public static void main(String[] args) {
        //Starting message
        String horizontalLine = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(horizontalLine + System.lineSeparator()
                            + "Hello! I'm Duke" + System.lineSeparator()
                            + "What can I do for you?" + System.lineSeparator()
                            + horizontalLine);
        //initialise Scanner
        Scanner in = new Scanner(System.in);
        boolean conversationIsOver = false;
        while (!conversationIsOver) {
            String command = in.nextLine();
            if (command.equalsIgnoreCase("bye")) conversationIsOver = true;
            printMessage(command);
        }


        //Ending message
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
        //
    }
}
