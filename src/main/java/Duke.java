import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n"
        + "Hey there! I'm Chai\n" + "What do we have today?\n" +
        "____________________________________________________________");
        String userCommand;
        Scanner in = new Scanner(System.in);
        userCommand = in.nextLine();
        while (!userCommand.equals("bye")) {
            System.out.println("____________________________________________________________\n" + userCommand);
            System.out.println("____________________________________________________________");
            userCommand = in.nextLine();
        }
        System.out.println("____________________________________________________________\n" + "GoodBye!\n" +
        "____________________________________________________________");
    }
}
