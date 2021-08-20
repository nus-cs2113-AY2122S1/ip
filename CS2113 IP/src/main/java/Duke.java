import java.util.Scanner;

public class Duke {
    public static void Greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        String horizontalLine = "________________________";
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
    }

    public static void Echo() {
        Scanner sc = new Scanner(System.in);
        String horizontalLine = "________________________";
        boolean isBye = false;
        while (!isBye) {
            String userInput = sc.nextLine();
            isBye = userInput.equals("bye");
            System.out.println(horizontalLine);
            if (isBye) {
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(userInput);
            }
            System.out.println(horizontalLine);
        }
    }

    public static void main(String[] args) {
        Greet();
        Echo();
    }
}
