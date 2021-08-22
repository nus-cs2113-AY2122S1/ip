import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    final static String line = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(line);
        printWelcomeMesage();
        getMenu();
    }

    public static void printExitMessage() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void printWelcomeMesage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void printLogoMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
    }

    public static void printMessage(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public static void printUserInputs(String[] userInputs) {
        if (userInputs == null) {
            System.out.println(line);
            System.out.println(line);
            return;
        }
        int i = 1;
        System.out.println(line);
        for (String userInput : userInputs) {
            System.out.printf("%d. %s\n", i, userInput);
            i++;
        }
        System.out.println(line);
    }

    public static String[] addItemToList(String[] itemsList, String item) {
        if (itemsList == null) {
            itemsList = new String[1];
            itemsList[0] = item;
            return itemsList;
        }
        String[] newItemsList = new String[itemsList.length + 1];
        for (int i = 0; i < itemsList.length; i++) {
            newItemsList[i] = itemsList[i];
        }
        newItemsList[itemsList.length] = item;
        return newItemsList;
    }

    public static void getMenu() {
        Scanner in = new Scanner(System.in);
        String userInputs = in.nextLine();
        String[] itemsList = null;
        menuLoop:
        while (true) {
            switch (userInputs) {
            case "":
                break;
            case "list":
                printUserInputs(itemsList);
                break;
            case "bye":
                printExitMessage();
                break menuLoop;
            default:
                itemsList = addItemToList(itemsList, userInputs);
                printMessage("added: " + userInputs);
                break;
            }
            userInputs = in.nextLine();
        }
    }
}
