import java.util.Scanner;

public class Duke {

    private String[] userList = new String[100];
    private int listSize = 0;

    public void handleCommand() {
        String userInput;
        Scanner in = new Scanner(System.in);

        userInput = in.nextLine();

        switch (userInput) {
        case "bye":
            System.out.println("-------------------------------------");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("-------------------------------------");
            break;
        case "list":
            System.out.println("-------------------------------------");
            showList();
            System.out.println("-------------------------------------");
            handleCommand();
            break;
        default:
            System.out.println("-------------------------------------");
            addTask(userInput);
            System.out.println("added: " + userInput);
            System.out.println("-------------------------------------");
            handleCommand();
            break;
        }
    }

    public void addTask(String task) {
        userList[listSize] = task;
        listSize++;
    }

    public void showList() {
        for (int i = 0; i < listSize; i++) {
            System.out.println((i + 1) + ". " + userList[i]);
        }
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("-------------------------------------");

        chatBot.handleCommand();
    }
}
