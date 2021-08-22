import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String leadingSpaces = "    ";
        String underscores = leadingSpaces + "___________________________________________________________\n";
        String logo = leadingSpaces + " ____        _        \n"
                + leadingSpaces + "|  _ \\ _   _| | _____ \n"
                + leadingSpaces + "| | | | | | | |/ / _ \\\n"
                + leadingSpaces + "| |_| | |_| |   <  __/\n"
                + leadingSpaces + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        String greetMessage = underscores
                + leadingSpaces + "Hello! I'm Duke\n"
                + leadingSpaces + "What can I do for you?\n"
                + underscores;
        System.out.println(greetMessage);

        boolean isEnd = false;
        while (!isEnd) {
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            if (userInput.strip().equals("bye")) {
                isEnd = true;
                continue;
            }
            System.out.println(underscores + leadingSpaces + userInput + "\n" + underscores);
        }

        String byeMessage = underscores
                + leadingSpaces + "Bye. Hope to see you again soon!\n"
                + underscores;

        System.out.println(byeMessage);
    }
}
