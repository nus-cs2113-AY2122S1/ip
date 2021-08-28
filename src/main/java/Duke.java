import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        MessageBubble.printMessageBubble("     Hello from\n" + logo + "\n     What can I do for you?");

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
                int eventDesc_separatorInd = commandDescription.indexOf("/at");
                String eventDescription = commandDescription.substring(0, eventDesc_separatorInd-1);
                String eventTime = commandDescription.substring(eventDesc_separatorInd+4);
                Events tempEvent = new Events(eventDescription, eventTime);
                list.addItem(tempEvent);
                break;
            case "deadline":
                int deadlineDesc_separatorInd = commandDescription.indexOf("/by");
                String deadlineDescription = commandDescription.substring(0, deadlineDesc_separatorInd-1);
                String deadlineTime = commandDescription.substring(deadlineDesc_separatorInd+4);
                Deadlines tempDeadline = new Deadlines(deadlineDescription, deadlineTime);
                list.addItem(tempDeadline);
                break;
            case "done":
                int indexOfDoneItem = Integer.parseInt(command.substring(command.indexOf(' ')  +1));
                list.doneItem(indexOfDoneItem);
                break;
            case "undone":
                int indexOfUndoneItem = Integer.parseInt(command.substring(command.indexOf(' ')  +1));
                list.undoneItem(indexOfUndoneItem);
                break;
            default: // unknown command default to add as new item in the list
                Task tempTask = new Task(command);
                list.addItem(tempTask);
            }
        }

        MessageBubble.printMessageBubble("Bye. Hope to see you again soon!");
    }
}
