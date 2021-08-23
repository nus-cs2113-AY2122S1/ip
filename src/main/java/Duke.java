import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("    **************************************************");
        System.out.println("    Hello! I'm Duke\n" + "    What can I do for you?");
        System.out.println("    **************************************************");

        Task[] userList = new Task[100];
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        int chosenEntry;
        int currentEntry = 0;

        while(!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" ");
            System.out.println("    **************************************************");

            if (!splitInput[0].equals(("bye"))) {
                if (splitInput[0].equals("list")) {
                    System.out.println("    Here are the tasks in your list");
                    for (int i = 0; i < currentEntry; i++) {
                        System.out.println("    " + (i + 1) + ".[" + userList[i].getStatusIcon() + "] " + userList[i].getDescription());
                    }
                    System.out.println("    **************************************************");
                } else if (splitInput[0].equals("done")) {
                    chosenEntry = Integer.parseInt(splitInput[1]) - 1;
                    userList[chosenEntry].markAsDone();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("     [" + userList[chosenEntry].getStatusIcon() + "] " + userList[chosenEntry].getDescription());
                    System.out.println("    **************************************************");
                } else {
                    userList[currentEntry] = new Task(userInput);
                    System.out.println("    Added: " + userInput);
                    currentEntry++;
                    System.out.println("    **************************************************");
                }
            }
        }

        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    **************************************************");
    }
}
