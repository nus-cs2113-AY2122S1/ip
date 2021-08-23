
import java.util.Scanner;

public class Duke {

    public static void chatFunction() {
        Scanner in = new Scanner(System.in);

        while (true) {
            String chatInput = in.nextLine();
            switch (chatInput.toLowerCase()) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                return;
            default:
                System.out.println(chatInput);
                break;
            }

        }
    }


    public static void main(String[] args) {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you? Type something below! :D\n");

        chatFunction();

    }
}
