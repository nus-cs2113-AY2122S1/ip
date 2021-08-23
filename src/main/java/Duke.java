import java.util.Scanner;

public class Duke {

    public static void chatFunction() {
        Scanner in = new Scanner(System.in);

        while (true) {
            String chatInput = in.next();
            if (chatInput.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(chatInput);
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
