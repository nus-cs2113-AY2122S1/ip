import java.util.Scanner;

public class Duke {
    public static final String MESSAGE = "Here are the tasks in your list:";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "___________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
        String[] arrayInput = new String[100];
        int inputCount = 0;
        int i = 0;
        int[] taskStatus = new int[100];
        for(i = 0; i < 100; i++) {
            taskStatus[i] = 0;		//No "X" in output
        }

        Scanner userInput = new Scanner(System.in);
        String userCommand = userInput.nextLine();
        char taskID;
        int taskIDInt;

        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) {
                System.out.println(line);
                System.out.println(MESSAGE);

                for (i = 1; i <= inputCount; i++) {
                    if(taskStatus[i-1] == 1) {
                        System.out.println(i + ".[X] " + arrayInput[i-1]);
                    }	else {
                        System.out.println(i + ".[ ] " + arrayInput[i-1]);
                    }

                }
                System.out.println(line);
                userCommand = userInput.nextLine();
                continue;
            }	else if (userCommand.contains("done")) {
                int len = userCommand.length();
                taskID = userCommand.charAt(len - 1);
                taskIDInt = taskID - 49;
                taskStatus[taskIDInt] = 1;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("	" + "[X] " + arrayInput[taskIDInt]);
                System.out.println(line);
                userCommand = userInput.nextLine();
                continue;
            }
            arrayInput[inputCount++] = userCommand;
            System.out.println(line);
            System.out.println("added: " + userCommand);
            System.out.println(line);
            userCommand = userInput.nextLine();
        }

        if (userCommand.equals("bye")) {
            System.out.println(line);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(line);
        }

    }
}
