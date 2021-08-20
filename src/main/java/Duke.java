import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greetings);

        Scanner scanner = new Scanner(System.in);

        int itemNumber = 0;
        String[] listAllAddedItems = new String[100];

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                if (listAllAddedItems.length == 0) {
                    System.out.println("No items added yet!");
                } else {
                    for (int i = 0; i < listAllAddedItems.length; i++) {
                        String currentItem = listAllAddedItems[i];
                        if (currentItem == null) {
                            break;
                        } else {
                            System.out.println(i + 1 + ". " + currentItem);
                        }
                    }
                }
                System.out.println();
            } else {
                listAllAddedItems[itemNumber] = userInput;
                itemNumber ++;
                System.out.println("added: " + userInput + "\n");
            }
        }
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }
}
