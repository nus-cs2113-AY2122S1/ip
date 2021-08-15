import java.util.*;

public class Duke {

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();

        while(true) {
            String userInput = getUserInput();
            if(userInput.equals("bye"))
                break;
            else
                System.out.println(userInput);
            System.out.println();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String getUserInput() {
        System.out.print("duke:$ ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}