import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("\tHey, how are you?");
        System.out.println("\tWhat can I do for you today?");
        System.out.println("\t----------------------------------------------------------------------");

        String input = "";
        int i = 0;
        Task[] tasks = new Task[100];

        do {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            System.out.println("\t----------------------------------------------------------------------");

            if (!input.equals("bye")) {
                String[] splittedInput = input.split(" ");
                if (input.equals("list")) {
                    System.out.println("\tHere's the list of your tasks: ");

                    for (int j = 0; j < i; j++) {
                        int itemNumber = j + 1;
                        System.out.println("\t" + itemNumber + ". [" + tasks[j].getStatusIcon() + "] " + tasks[j].description);
                    }

                    System.out.println("\t----------------------------------------------------------------------");
                } else if (splittedInput[0].equals("done")) {
                    int taskInt = Integer.parseInt(splittedInput[1]) - 1;
                    tasks[taskInt].markAsDone();

                    System.out.println("\tGood job! I've marked this task as done: ");
                    System.out.println("\t[" + tasks[taskInt].getStatusIcon() + "] " + tasks[taskInt].description);
                    System.out.println("\t----------------------------------------------------------------------");
                } else {
                    tasks[i] = new Task(input);
                    
                    System.out.println("\tnew task added: " + input);
                    System.out.println("\t----------------------------------------------------------------------");
                    i++;
                }
            }
        } while (!input.equals("bye"));

        System.out.println("\tGoodbye! Hope to see you again soon!");
        System.out.println("\t----------------------------------------------------------------------");
    }
}
