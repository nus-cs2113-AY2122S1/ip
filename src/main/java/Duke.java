import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "------------------------------------------------------------------------------------------\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "Hello! I'm Duke.\n" + logo + "What can i do for you?\n" + line);

        // getting user input and echoing it
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!input.equals("bye")) {
            System.out.println(line + "\n" + input + "\n" + line);
            input = scan.nextLine();
        }
            System.out.println(line + "\n" + input + ". Hope to see you again soon!\n" + line);
    }
}