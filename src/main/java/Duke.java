import java.util.Scanner;

public class Duke {
    public static void addLine() {
        System.out.println("----------------------");
    }

    public static void dukeGreet() {
        addLine();
        System.out.println("Hello!, I'm Duke");
        System.out.println("How can I help you?");
        addLine();
    }

    public static void dukeEcho(String userInput) {
        addLine();
        System.out.println(userInput);
        addLine();
    }

    public static void dukeBye() {
        addLine();
        System.out.println("Bye, see you again!");
        addLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeGreet();
        String userInput;
        Scanner input = new Scanner(System.in);
        userInput = input.nextLine();
        while (!userInput.equals("bye")) {
            dukeEcho(userInput);
            userInput = input.nextLine();
        }
        dukeBye();
    }
}
