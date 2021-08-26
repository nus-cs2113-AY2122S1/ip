import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        Task[] storage = new Task[100];
        int inputCount = 0;
        String status;
        String[] lineSplitter;
        int taskNumber = 0;

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        line = in.nextLine();
        while (!(line.equals("bye"))) {

            if (line.equals("list")) {
                System.out.println("    Here are the tasks in your list:");

                for (int outputCount = 0; outputCount < inputCount; outputCount++) {
                    status = storage[outputCount].getStatusIcon();
                    System.out.println("    " + (outputCount + 1) + ".[" + status + "] " + storage[outputCount].description);
                }
                line = in.nextLine();

            } else if (line.contains("done")) {
                lineSplitter = line.split(" ");
                taskNumber = Integer.parseInt(lineSplitter[1]);
                taskNumber = taskNumber - 1;

                if (((taskNumber + 1) > 0) && ((taskNumber + 1) < inputCount)) { //check for invalid inputs

                    storage[taskNumber].markAsDone(); //mark x in [ ]
                    System.out.println("    Nice! I've marked this task as done:");
                    status = storage[taskNumber].getStatusIcon();

                    System.out.println("    [" + status + "] " + storage[taskNumber].description);
                    line = in.nextLine();

                } else {
                    System.out.println("    Sorry, Invalid Input! Please check the list again!");
                    line = in.nextLine();
                }

            } else {
                System.out.println("    added: " + line);
                Task t = new Task(line);
                storage[inputCount] = t;
                inputCount++;

                line = in.nextLine();
            }
        }
        System.out.println("    " + "Bye. Hope to see you again soon!");
    }
}

