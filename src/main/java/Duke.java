import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String userCommand;
        String[] taskList = new String[100];
        int listSize = 0;
        String logo =
                " ______    __                     __ \n" +
                "/  ____|  |  |                   |__|\n" +
                "|  |      |  |_____    _______    __ \n" +
                "|  |      |   __   \\  /  __   |  |  |\n" +
                "|  |____  |  |  |  |  | |__|  |  |  |\n" +
                "\\______|  |__|  |__|  \\____/\\_|  |__|\n" ;

        System.out.println("This is\n" + logo);
        System.out.println("____________________________________________________________\n"
        + "Hey there! I'm Chai\n" + "What are you doing today?\n" +
        "____________________________________________________________");

        Scanner in = new Scanner(System.in);
        userCommand = in.nextLine();
        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < listSize; i++) {
                    System.out.println(taskList[i]);
                }
                System.out.println("____________________________________________________________");
            }
            else {
                System.out.println("____________________________________________________________\n" +
                        "Added to the list: " + userCommand);
                System.out.println("____________________________________________________________");
                taskList[listSize] = (listSize + 1) + ". " + userCommand;
                listSize++;
            }
            userCommand = in.nextLine();
        }
        System.out.println("____________________________________________________________\n" + "GoodBye!\n" +
        "____________________________________________________________");
    }
}
