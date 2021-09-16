import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static int inputCount = 0;
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        input = in.nextLine();

        while (!(input.equals("bye"))) {

            if (input.equals("list")) {
                input = printList(in);


            } else if (input.contains("done")) {
                try{
                input = testTaskDone(input, in);
                } catch (NumberFormatException e3) {
                    System.out.println("Did you type in a number?");
                    input = in.nextLine();
                } catch (ArrayIndexOutOfBoundsException e4) {
                    System.out.println("Finish the command!");
                    input = in.nextLine();
                }

            } else if (input.contains("todo")) {
                try {
                    testInput(input); // test todo input

                    Task whatToDo = toDoMethod(input);
                    System.out.println("    Got it. I've added this task:\n" + "    " + whatToDo + "\n"
                            + "    Now you have " + tasks.size() + " tasks in the list.");

                    input = in.nextLine();
                } catch (DukeExceptions e5) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    input = in.nextLine();
                }


            } else if (input.contains("deadline")) {
                try {
                    Task whatDeadline = deadlineMethod(input);
                    System.out.println("    Got it. I've added this task:\n" + "    " + whatDeadline + "\n"
                            + "    Now you have " + tasks.size() + " tasks in the list.");

                    input = in.nextLine();
                } catch (StringIndexOutOfBoundsException e1) {
                    System.out.println("input = " + input + "\n☹ OOPS!!! Please retype your input!");
                    input = in.nextLine();
                } catch (ArrayIndexOutOfBoundsException e2) {
                    System.out.println("you have typed in = " + input + "\n☹ OOPS!!! where is your /by my friend? ");
                    input = in.nextLine();
                }

            } else if (input.contains("event")) {
                try {
                    Task whatEvent = eventMethod(input);
                    System.out.println("    Got it. I've added this task:\n" + "    " + whatEvent + "\n"
                            + "    Now you have " + tasks.size() + " tasks in the list.");
                    
                    input = in.nextLine();
                } catch (StringIndexOutOfBoundsException e6) {
                    System.out.println("input = " + input + "\n☹ OOPS!!! Please retype your input!");
                    input = in.nextLine();
                } catch (ArrayIndexOutOfBoundsException e7) {
                    System.out.println("you have typed in = " + input + "\nw☹ OOPS!!! where is your /at brother? ");
                    input = in.nextLine();
                }

            }
            else if (input.contains("delete")) {
                try{
                    input = testDeleteTask(input, in);
                } catch (NumberFormatException e8) {
                    System.out.println("Did you type in a number?");
                    input = in.nextLine();
                } catch (ArrayIndexOutOfBoundsException e9) {
                    System.out.println("Finish the command!");
                    input = in.nextLine();
                }

            }
            else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");//overall error check
                input = in.nextLine();

            }
        }
        System.out.println("    Bye! Hope to see you again soon!");

    }

    private static String printList(Scanner in) {
        String input;
        System.out.println("    Here are the tasks in your list:");

        for (int outputCount = 0; outputCount < tasks.size(); outputCount++) {
            System.out.println("    " + (outputCount + 1) + "." + tasks.get(outputCount));
        }
        input = in.nextLine();
        return input;
    }

    private static String testTaskDone(String input, Scanner in) {
        int taskNumber;
        String[] inputSplitter;
        inputSplitter = input.split(" ");
        taskNumber = Integer.parseInt(inputSplitter[1]);
        taskNumber = taskNumber - 1;

        if (((taskNumber + 1) > 0) && ((taskNumber + 1) <= tasks.size())) { //check for invalid inputs
            input = taskDone(in, tasks.get(taskNumber));

        } else {
            System.out.println("    Invalid input! Please check the list again!");
            input = in.nextLine();
        }
        return input;
    }

    private static String testDeleteTask(String input, Scanner in) {
        int taskNumber;
        String[] inputSplitter;
        inputSplitter = input.split(" ");
        taskNumber = Integer.parseInt(inputSplitter[1]); //get which task to delete
        taskNumber = taskNumber - 1;

        if (((taskNumber + 1) > 0) && ((taskNumber + 1) <= tasks.size())) { //check for invalid inputs
            input = taskDelete(in, tasks.remove(taskNumber));

        } else {
            System.out.println("    Invalid input! Please check the list again!");
            input = in.nextLine();
        }
        return input;
    }

    private static String taskDone(Scanner in, Task task) {
        String input;
        System.out.println("    Nice! I've marked this task as done:" + System.lineSeparator());
        System.out.println("    " + task + " has been updated to -->");

        task.markAsDone(); //mark x in [ ]

        System.out.println("    " + task);
        input = in.nextLine();
        return input;
    }
    private static String taskDelete(Scanner in, Task task) {
        String input;
        System.out.println("    Noted. I've removed this task:" );
        System.out.println("    " + task);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        input = in.nextLine();
        return input;
    }

    public static Task toDoMethod(String input) {
        String toDoDescription = input.substring(4).trim();

        Task tasksToDo = new Todo(toDoDescription);
        tasks.add(tasksToDo);
        inputCount++;

        return tasksToDo;

    }

    public static Task deadlineMethod(String input) {

        String[] deadlineSplitter = input.substring(9).split(" /by ");
        String deadlineDescription = deadlineSplitter[0]; //before /by
        String deadlineBy = deadlineSplitter[1]; // after /by

        Task description = new Deadline(deadlineDescription, deadlineBy);
        tasks.add(description);
        inputCount++;

        return description;

    }

    public static Task eventMethod(String input) {
        String[] eventSplitter = input.substring(6).split(" /at ");
        String eventDescription = eventSplitter[0]; //before /at
        String eventAt = eventSplitter[1]; // after /at

        Task description = new Event(eventDescription, eventAt);
        tasks.add(description);
        inputCount++;

        return description;
    }

    public static void testInput(String input) throws DukeExceptions {
        if (input.length() < 5) { //check for description after todo
            throw new DukeExceptions();
        }
    }

}

