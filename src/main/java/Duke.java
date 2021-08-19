import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList; // import the ArrayList class


public class Duke {
    
    private static Scanner myScan= new Scanner(System.in);


    public static void main(String[] args) {
        greet();
        // level1();
        level2();
    }


    public static void level2() {
        ArrayList<String> storedInput = new ArrayList<String>();

        while (true) {
            String input = myScan.nextLine();
            switch(input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    myScan.close();
                    return;
                case "list":
                    for (int i = 0; i < storedInput.size(); i++) 
                        System.out.println(i+1 + ". " + storedInput.get(i));
                    break;
                default:
                    System.out.println("added: " + input);
                    storedInput.add(input);
            }
        }
    }


    public static void level1() {

        while (true) {
            String input = myScan.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                myScan.close();
                return;
            }
            System.out.println(input);  // Output user input
        }
        
    }

    public static void greet() {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        // System.out.println("_________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

    }
}

