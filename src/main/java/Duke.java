import java.util.Scanner;
public class Duke {
    public static void checkCommand(String command, Scanner in) {
        String spam;
        switch(command){
        case "bye":
            spam = in.nextLine();
            Greet.printGoodbyeMessage();
            break;
        case "":
        case " ":
        case "list":
            spam = in.nextLine();
            Greet.printList();
            break;
        case "done":
            int taskNumber = in.nextInt();
            spam = in.nextLine();
            Greet.checkDoneTask(taskNumber);
            break;
        default:
            command = command + in.nextLine();
            Greet.addTask(command);
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet.printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        String command;
        boolean isConversation = true;
        do{
            command = in.next();
            checkCommand(command, in);
            isConversation = !command.equals("bye");
        }while(isConversation);

    }
}
