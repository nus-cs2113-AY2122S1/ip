import java.util.Scanner;

public class Duke {
    public static void Greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        String horizontalLine = "________________________";
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
    }

    public static void ListOperations() {
        Scanner sc = new Scanner(System.in);
        String horizontalLine = "________________________";
        boolean isBye;
        boolean isList;
        boolean isDone;

        Task[] taskList = new Task[100];
        int taskCount = 0;

        do {
            String userInput = sc.nextLine();
            isBye = userInput.equals("bye");
            isList = userInput.equals("list");
            isDone = userInput.startsWith("done");
            System.out.println(horizontalLine);

            if (isBye) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (isList) {
                for (int i = 0; i < taskCount; i++) {
                    int indexNumber = i + 1;
                    System.out.println(indexNumber + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
                }
            } else if (isDone) {
                char userInputIntChar = userInput.charAt(userInput.length() - 1);
                int userInputInt = Character.getNumericValue(userInputIntChar);
                taskList[userInputInt].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + taskList[userInputInt].getStatusIcon() + "] " + taskList[userInputInt].description);
            } else {
                System.out.println("added: " + userInput);
                Task newTask = new Task(userInput, taskCount);
                taskList[taskCount] = newTask;
                taskCount++;
            }
            System.out.println(horizontalLine);

        } while (!isBye);
    }

    public static void main(String[] args) {
        Greet();
        ListOperations();
    }
}
