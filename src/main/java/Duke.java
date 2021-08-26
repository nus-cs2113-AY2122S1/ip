import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        public static final String MESSAGE = "added: ";
        public static void main(String[] args) {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            String line = "___________________________________________________";
            System.out.println(line);
            System.out.println("Hello! I'm Duke");
            System.out.println("What can I do for you?");
            System.out.println(line);
            String[] arrayInput = new String[100];
            int inputCount = 0;
            int i = 0;

            Scanner userInput = new Scanner(System.in);
            String userCommand = userInput.nextLine();

            while (!userCommand.equals("bye")) {
                if (userCommand.equals("list")) {
                    System.out.println(line);
                    for (i = 1; i <= inputCount; i++) {
                        System.out.println(i + ". " + arrayInput[i-1]);
                    }
                    System.out.println(line);
                    userCommand = userInput.nextLine();
                    continue;
                }
                arrayInput[inputCount++] = userCommand;
                System.out.println(line);
                System.out.println(MESSAGE + userCommand);
                System.out.println(line);
                userCommand = userInput.nextLine();
            }

            if (userCommand.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
            }

        }
}
