import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        String line = "________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line);
        boolean isStop = false;
        ArrayList<Tasks> tasksAL = new ArrayList<>();

        while (true) {
            //User choice of what to do
            System.out.println("I can do the following: Echo, List");
            String userChoice = scannerObj.nextLine();

            //Check if choice is valid
            while (!userChoice.equalsIgnoreCase("echo") && !userChoice.equalsIgnoreCase("list")) {
                System.out.println("Invalid input, please try again. You may choose: List, Echo");
                userChoice = scannerObj.nextLine();
            }

            //Execute choice
            switch (userChoice.toLowerCase()) {
            case "echo": //Echo what is said to user
                System.out.println("I'm a copy cat!");
                while (true) {
                    String userInput = scannerObj.nextLine();
                    if (userInput.equalsIgnoreCase("bye")) {
                        isStop = true;
                        break;
                    } else if (userInput.equalsIgnoreCase("change")){
                        break;
                    } else {
                        System.out.println(userInput);
                    }
                }
                break;

            case "list": //Activate list actions
                while (true) {
                    System.out.println("To view your list, enter 'list'. To add to your list just type what you would like to add.");
                    String userInput = scannerObj.nextLine();
                    if (userInput.equalsIgnoreCase("bye")) {
                        isStop = true;
                        break;
                    } else if (userInput.equalsIgnoreCase("change")) {
                        break;
                    } else {
                        Tasks.list(userInput, tasksAL);
                    }
                }
                break;

            default:
                break;
            }
            if (isStop) {
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}


