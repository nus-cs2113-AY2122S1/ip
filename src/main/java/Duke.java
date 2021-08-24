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
        System.out.println("    Hey, what's up!\n" + "    What can I help you with today?");
        System.out.println("    **************************************************");

        Task[] userLists = new Task[100];
        Scanner scanner = new Scanner(System.in);
        String userInputs = "";
        int chosenEntry;
        int currentEntry = 0;

        while(!userInputs.equals("bye")) {
            userInputs = scanner.nextLine();
            String[] splitInput = userInputs.split(" ");
            System.out.println("    **************************************************");

            if (!splitInput[0].equals(("bye"))) {
                if (splitInput[0].equals("list")) {
                    System.out.println("    Here's your list of tasks:");
                    for (int i = 0; i < currentEntry; i++) {
                        System.out.println("    " + (i + 1)
                                + ".[" + userLists[i].getStatusIcon() + "] " + userLists[i].getDescription());
                    }
                    System.out.println("    **************************************************");
                } else if (splitInput[0].equals("done")) {
                    chosenEntry = Integer.parseInt(splitInput[1]) - 1;
                    userLists[chosenEntry].markAsDone();
                    System.out.println("    Good job on completing a task!");
                    System.out.println("      [" + userLists[chosenEntry].getStatusIcon() + "] "
                            + userLists[chosenEntry].getDescription());
                    System.out.println("    **************************************************");
                } else {
                    userLists[currentEntry] = new Task(userInputs);
                    System.out.println("    Added: " + userInputs);
                    currentEntry++;
                    System.out.println("    **************************************************");
                }
            }
        }

        System.out.println("    Aight. See you soon mate.");
        System.out.println("    **************************************************");
    }
}
