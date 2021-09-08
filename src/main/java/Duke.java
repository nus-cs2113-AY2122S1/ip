import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        final String LOGO = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        MessageBubble.printMessageBubble("Hello from\n" + LOGO + "What can I do for you?");

        List list = new List();
        keepAsking:
        while (true) {
            String command = in.nextLine();
            String commandWord = command.contains(" ")? command.substring(0, command.indexOf(' ')): command;
            String commandDescription = command.contains(" ")? command.substring(command.indexOf(' ')  +1) : command;

            switch (commandWord) {
            case "bye":
                break keepAsking;
            case "list":
                list.printList();
                break;
            case "todo":
                ToDos tempTodo = new ToDos(commandDescription);
                list.addItem(tempTodo);
                break;
            case "event":
                try {
                    list.addItem(Events.parseEvent(commandDescription));
                } catch (ArrayIndexOutOfBoundsException e) {
                    MessageBubble.printMessageBubble("Oops! The description of a event cannot be empty");
                }
                break;
            case "deadline":
                try {
                    list.addItem(Deadlines.parseDeadlines(commandDescription));
                } catch (ArrayIndexOutOfBoundsException e) {
                    MessageBubble.printMessageBubble("Oops! The description of a deadline cannot be empty");
                }
                break;
            case "done":
                try {
                    int indexOfDoneItem = Integer.parseInt(command.split(" ")[1]);
                    list.doneItem(indexOfDoneItem);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    MessageBubble.printMessageBubble("You can mark item as done using \"done (index of item)\"");
                }
                break;
            case "undone":
                try {
                    int indexOfUndoneItem = Integer.parseInt(command.split(" ")[1]);
                    list.undoneItem(indexOfUndoneItem);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    MessageBubble.printMessageBubble("You can mark item as not done using \"undone (index of item)\"");
                }
                break;
            default:
                MessageBubble.printMessageBubble("Oops! I'm sorry, but I don't know what that means :-(");
            }
        }

        MessageBubble.printMessageBubble("Bye. Hope to see you again soon!");
    }
}
