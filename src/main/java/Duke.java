import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static int inputCount = 0;
    public static Task[] tasks = new Task[100];

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        int taskNumber = 0;
        String statusOfTask;
        String[] inputSplitter;


        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        input = in.nextLine();


        while (!(input.equals("bye"))) {

            if (input.equals("list")) {
                System.out.println("    Here are the tasks in your list:");

                for (int outputCount = 0; outputCount < inputCount; outputCount++) {
                    statusOfTask = tasks[outputCount].getStatusIcon();
                    System.out.println("    " + (outputCount + 1) + "." + tasks[outputCount]);
                }
                input = in.nextLine();

            } else if (input.contains("done")) {
                inputSplitter = input.split(" ");
                taskNumber = Integer.parseInt(inputSplitter[1]);
                taskNumber = taskNumber - 1;

                if (((taskNumber + 1) > 0) && ((taskNumber + 1) <= inputCount)) { //check for invalid inputs
                    System.out.println("    Nice! I've marked this task as done:" + System.lineSeparator());
                    System.out.println("    " + tasks[taskNumber] + " has been updated to -->");

                    tasks[taskNumber].markAsDone(); //mark x in [ ]
                    statusOfTask = tasks[taskNumber].getStatusIcon();

                    System.out.println("    " + tasks[taskNumber]);
                    input = in.nextLine();

                } else {
                    System.out.println("    Invalid input! Please check the list again!");
                    input = in.nextLine();
                }

            } else if (input.contains("todo")) {
                Task whatToDo = toDoMethod(input);

                System.out.println("    Got it. I've added this task:\n" + "    " + whatToDo + "\n"
                        + "    Now you have " + inputCount + " tasks in the list.");
                input = in.nextLine();


            } else if (input.contains("deadline")) {
                Task whatDeadline = deadlineMethod(input);

                System.out.println("    Got it. I've added this task:\n" + "    " + whatDeadline + "\n"
                        + "    Now you have " + inputCount + " tasks in the list.");
                input = in.nextLine();

            } else if (input.contains("event")) {
                Task whatEvent = eventMethod(input);

                System.out.println("    Got it. I've added this task:\n" + "    " + whatEvent + "\n"
                        + "    Now you have " + inputCount + " tasks in the list.");
                input = in.nextLine();

            } else { //adding in general orders
                System.out.println("    added: " + input);
                Task t = new Task(input);
                tasks[inputCount] = t;
                inputCount++;

                input = in.nextLine();
            }
        }
        System.out.println("    Bye! Hope to see you again soon!");

    }

    public static Task toDoMethod(String input) {
        String toDoDescription = input.substring(4).trim();

        Task tasksToDo = new Todo(toDoDescription);
        tasks[inputCount] = tasksToDo;
        inputCount++;

        return tasksToDo;
    }

    public static Task deadlineMethod(String input) {
        String[] deadline_splitter = input.substring(9).split(" /by ");
        String deadlineDescription = deadline_splitter[0]; //before /by
        String deadlineBy = deadline_splitter[1]; // after /by

        Task description = new Deadline(deadlineDescription, deadlineBy);
        tasks[inputCount] = description;
        inputCount++;

        return description;

    }

    public static Task eventMethod(String input) {
        String[] event_splitter = input.substring(6).split(" /at ");
        String eventDescription = event_splitter[0]; //before /at
        String evenAt = event_splitter[1]; // after /at

        Task description = new Event(eventDescription, evenAt);
        tasks[inputCount] = description;
        inputCount++;

        return description;

    }
}

