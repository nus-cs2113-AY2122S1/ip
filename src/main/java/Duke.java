import java.util.Scanner;
public class Duke {
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
            command = in.nextLine();
//            System.out.println(command);
            Greet.miniDuke(command);
            isConversation = !command.equals("bye");
        }while(isConversation);

    }
}
