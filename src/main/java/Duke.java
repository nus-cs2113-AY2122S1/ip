import java.util.Scanner;
import java.util.ArrayList;



public class Duke {
    public static void main(String[] args) {
        showStartMessage();
        executeTillExit();
        showExitMessage();
    }

    private static void showStartMessage() {
        String line = "_______________________________________________________________________________________________";
        String logo =
            "      ____                U _____ u   ____      ____        _" + System.lineSeparator() +
            "     / __\"| u      ___    \\| ___\"|/U |  _\"\\ uU |  _\"\\ u U  /\"\\  u" + System.lineSeparator() +
            "    <\\___ \\/      |_\"_|    |  _|\"   \\| |_) |/ \\| |_) |/  \\/ _ \\/" + System.lineSeparator() +
            "     u___) |       | |     | |___    |  _ <    |  _ <    / ___ \\" + System.lineSeparator() +
            "     |____/>>    U/| |\\u   |_____|   |_| \\_\\   |_| \\_\\  /_/   \\_\\" + System.lineSeparator() +
            "      )(  (__).-,_|___|_,-.<<   >>   //   \\\\_  //   \\\\_  \\\\    >>" + System.lineSeparator() +
            "     (__)      \\_)-' '-(_/(__) (__) (__)  (__)(__)  (__)(__)  (__)";

        System.out.println("Hello from" + System.lineSeparator() + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Sierra!" + System.lineSeparator() + "What can I do for you?");
        System.out.println(line);
    }

    private static void executeTillExit() {
        boolean isStop = false;
        Scanner scannerObj = new Scanner(System.in);
        ArrayList<Tasks> tasksAL = new ArrayList<>();

        while (true) {
            //User choice of what to do
            System.out.println("I can do the following: Echo, List");
            String userChoice = scannerObj.nextLine();

            //Check if choice is valid
            while (!checkValidInput(userChoice)) {
                System.out.println("Invalid input, please try again. You may choose: Echo, List");
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
                    System.out.println("To view your list, enter 'list'. " +
                            "To add to your list type 'todo', 'event' or 'deadline' then your task.");
                    String userInput = scannerObj.nextLine();
                    if (userInput.equalsIgnoreCase("bye")) {
                        isStop = true;
                        break;
                    } else if (userInput.equalsIgnoreCase("change")) {
                        break;
                    } else {
                        Tasks.manageTasks(userInput, tasksAL);
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
    }

    private static boolean checkValidInput(String input){
        String[] validInputs = {"echo", "list"};
        for (String validInput: validInputs){
            if (input.equalsIgnoreCase(validInput)){
                return true;
            }
        }
        return false;
    }

    private static void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}


