import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("...................................................");
        System.out.println("Hi! I'm Duke\n" + "How can I help make your life easier?");
        System.out.println("...................................................");
        Scanner in = new Scanner(System.in);
        String string = "";
        Task[] array = new Task[100];
        int number = 0;
        /**
         * Read the input.
         */
        while(!string.equals("bye")) {
            string = in.nextLine();
            String[] input = string.split(" ");
            System.out.println("...................................................");
            if (input[0].equals("bye")) {
                break;
            }
            if (input[0].equals("list")) {
                show(array, number);
            } else if (input[0].equals("done")){
                finish(array, input);
            } else if (input[0].equals("event") || input[0].equals("deadline") || input[0].equals("todo")) {
                record(array, string, number, input[0]);
                number++;
            }
        }
        System.out.println("Byebye! Have a wonderful day!");
        System.out.println("...................................................");
    }

    /**
     * Show the Task.
     */
    private static void show(Task[] listIn, int totalNumber) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < totalNumber; i++) {
            System.out.println((i + 1) + "." + listIn[i].toString());
        }
    }

    /**
     * Finish the Task.
     */

    private static void finish(Task[] listIn, String[] lineInput) {
        int inputIndex = Integer.parseInt(lineInput[1]) - 1;
        listIn[inputIndex].markAsDone();
        System.out.println("Wonderful! This task is now marked as done: ");
        System.out.println(listIn[inputIndex].toString());
    }

    /**
     * Record the Task.
     */

    private static void record(Task[] listIn, String lineInput, int totalNumber, String firstInput) {
        System.out.println("Got it. I've added this task:");
        if (firstInput.equals("event")) {
            listIn[totalNumber] = new Event(lineInput.substring(6, lineInput.indexOf("/")), lineInput.substring(lineInput.indexOf("/") + 3));
        } else if (firstInput.equals("deadline")) {
            listIn[totalNumber] = new Deadline(lineInput.substring(9, lineInput.indexOf("/")), lineInput.substring(lineInput.indexOf("/") + 3));
        } else {
            listIn[totalNumber] = new Todo(lineInput.substring(5));
        }
        System.out.println(listIn[totalNumber].toString());
        System.out.println("Now you have " + (totalNumber + 1) + " tasks in your list");
        System.out.println("...................................................");
    }
}