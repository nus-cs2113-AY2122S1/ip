import java.util.Scanner;  // Import the Scanner class


public class Duke {
    public static void main(String[] args) {
        level1();

    }

    public static void level1() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        while (true) {
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                myObj.close();
                return;
            }
            System.out.println(input);  // Output user input
        }
        
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("_________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("_________________________");
        System.out.println("Bye. Hope to see you again soon!");
    }
}

