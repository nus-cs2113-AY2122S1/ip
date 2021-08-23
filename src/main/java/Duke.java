
import java.util.Scanner;

public class Duke {

    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public static void chatFunction() {
        Scanner in = new Scanner(System.in);
        String[] list = new String[100];
        int idx = 0;

        while (true) {
            String chatInput = in.nextLine();
            printDivider();
            switch(chatInput.toLowerCase()) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                printDivider();
                return;
            case "list":
                for (int i = 0; i < list.length; i++) {
                    if (list[i] == null) {
                        break;
                    }
                    System.out.println(i + ". " + list[i]);
                }
                break;
            default:
                list[idx] = chatInput;
                idx++;
                System.out.println("added: " + chatInput);
                break;
            }
            printDivider();
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
