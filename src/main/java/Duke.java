import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskManager t1 = new TaskManager();
        t1.printWelcomeMessage();
        String input;
        String[] inputWords;
        String command;
        Scanner in = new Scanner(System.in);
        do {
            input = in.nextLine();
            inputWords = t1.decodeInput(input);
            command = inputWords[0];
            switch (command) {
            case "done":
                t1.crossOff(inputWords);
                break;
            case "todo":
                t1.addToDo(input, inputWords);
                break;
            case "deadline":
                t1.addDeadline(input, inputWords);
                break;
            case "event":
                t1.addEvent(input, inputWords);
                break;
            case "list":
                t1.printList();
                break;
            default:
                break;
            }
        } while (!command.equals("bye"));
        t1.printEndMessage();
    }
}
