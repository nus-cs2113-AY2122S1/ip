import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        Task[] storage = new Task[100];
        int inputCount = 0;
        int taskNumber = 0;
        String statusOfTask;
        String[] inputSplitter;


        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        input = in.nextLine();

        while (!(input.equals("bye"))) {

            if (input.equals("list")) {
                System.out.println("    Here are the tasks in your list:");

                for (int outputCount = 0; outputCount < inputCount; outputCount++) {
                    statusOfTask = storage[outputCount].getStatusIcon();
                    System.out.println("    " + (outputCount + 1) + ".[" + statusOfTask + "] " + storage[outputCount].description);
                }
                input = in.nextLine();

            } else if (input.contains("done")) {
                inputSplitter = input.split(" ");
                taskNumber = Integer.parseInt(inputSplitter[1]);
                taskNumber = taskNumber - 1;

                if (((taskNumber + 1) > 0) && ((taskNumber + 1) < inputCount)) { //check for invalid inputs
                    storage[taskNumber].markAsDone(); //mark x in [ ]
                    System.out.println("    Nice! I've marked this task as done:");
                    statusOfTask = storage[taskNumber].getStatusIcon();

                    System.out.println("    [" + statusOfTask + "] " + storage[taskNumber].description);
                    input = in.nextLine();

                } else {
                    System.out.println("    Invalid input! Please check the list again!");
                    input = in.nextLine();
                }

            } else {
                System.out.println("    added: " + input);
                Task t = new Task(input);
                storage[inputCount] = t;
                inputCount++;

                input = in.nextLine();
            }
        }
        System.out.println("    " + "Bye! Hope to see you again soon!");
    }
}

