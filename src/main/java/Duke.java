import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("bye")){
            System.out.println("____________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________");
            userInput = in.nextLine();
        }
        System.out.println("____________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________");
    }
}
