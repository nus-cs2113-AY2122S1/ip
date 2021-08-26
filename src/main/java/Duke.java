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

        // getting user input
        String[] array = new String[100];
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        int arrayCount = 0;

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < arrayCount; i++) {
                        System.out.println((i+1) + ". " + array[i] + "\n");
                }
                System.out.println(line);
            } else {
                array[arrayCount] = input;
                arrayCount++;
                System.out.println(line + "added: " + input + "\n" + line);
            }
            input = scan.nextLine();
        }
        System.out.println(line + "\n" + input + "! Hope to see you again soon!\n" + line);
    }
}

