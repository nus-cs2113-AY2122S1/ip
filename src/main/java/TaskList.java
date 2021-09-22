import java.util.Scanner;

public class TaskList {
    public static int inputCount = 0;

    /**
     * Returns a print statement of all tasks in the file Duke.txt
     *
     * @param input input of user
     * @return prints list of all tasks
     */
    static void findList(String input) {
        String[] findSplitter;
        int number = 1;
        findSplitter = input.split(" ");
        System.out.println("    Here are the matching tasks in your list:");

        for (Task storageChecker : Duke.tasks) {
            if (storageChecker.description.contains(findSplitter[1])) {
                System.out.println("    " + (number) + "." + storageChecker);
                number++;
            }
        }
        if (number == 1) {
            System.out.println("    none of them fool");
        }


    }

    /**
     * Returns a print statement of all tasks in the file Duke.txt
     *
     * @param in input of user
     * @return prints list of all tasks
     */
    static String printList(Scanner in) {
        String input;
        System.out.println("    Here are the tasks in your list:");

        for (int outputCount = 0; outputCount < Duke.tasks.size(); outputCount++) {
            System.out.println("    " + (outputCount + 1) + "." + Duke.tasks.get(outputCount));
        }
        input = in.nextLine();
        return input;
    }

    /**
     * Test if the index number of the user's input after calling "done" is within
     * the index number of all the tasks in the file. If it is valid,
     * enter setTaskDone, if not, prints error message
     *
     * @param input String containing the user command
     * @return setTaskDone or invalid message if index number provided is less than 1,
     * or more than the last task
     */
    static String testTaskDone(String input, Scanner in) {
        int taskNumber;
        String[] inputSplitter;
        inputSplitter = input.split(" ");
        taskNumber = Integer.parseInt(inputSplitter[1]);
        taskNumber = taskNumber - 1;

        if (((taskNumber + 1) > 0) && ((taskNumber + 1) <= Duke.tasks.size())) { //check for invalid inputs
            input = setTaskDone(in, Duke.tasks.get(taskNumber));

        } else {
            System.out.println("    Invalid input! Please check the list again!");
            input = in.nextLine();
        }
        return input;
    }

    /**
     * Test if the index number of the user's input after calling "delete" is within
     * the index number of all the tasks in the file. If it is valid,
     * enter setTaskDone, if not, prints error message
     *
     * @param input String containing the user command
     * @return setTaskDone or invalid message if index number provided is less than 1,
     * or more than the last task
     */
    static String testDeleteTask(String input, Scanner in) {
        int taskNumber;
        String[] inputSplitter;
        inputSplitter = input.split(" ");
        taskNumber = Integer.parseInt(inputSplitter[1]); //get which task to delete
        taskNumber = taskNumber - 1;

        if (((taskNumber + 1) > 0) && ((taskNumber + 1) <= Duke.tasks.size())) { //check for invalid inputs
            input = setTaskDelete(in, Duke.tasks.remove(taskNumber));

        } else {
            System.out.println("    Invalid input! Please check the list again!");
            input = in.nextLine();

        }

        return input;
    }

    /**
     * Sets the task, identified by its index number, to be done, marking an x in its [ ]
     * when printList is called
     *
     * @param in   String containing the user command
     * @param task list of all tasks
     * @return marks x in [ ] in the list operation when printList is called
     */
    static String setTaskDone(Scanner in, Task task) {
        String input;
        System.out.println("    Nice! I've marked this task as done:" + System.lineSeparator());
        System.out.println("    " + task + " has been updated to -->");

        task.markAsDone(); //mark x in [ ]

        System.out.println("    " + task);
        input = in.nextLine();

        return input;
    }

    /**
     * Deletes the task, identified by its index number, by removing it from task
     *
     * @param in   String containing the user command
     * @param task list of all tasks
     * @return prints out the task that has been deleted and shows the remaining number
     * of tasks left.
     */
    static String setTaskDelete(Scanner in, Task task) {
        String input;
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    " + task);
        System.out.println("    Now you have " + Duke.tasks.size() + " tasks in the list.");
        input = in.nextLine();

        return input;
    }

    /**
     * creates a new todo task by using child class Todo and ovverride toString() function
     *
     * @param input String containing the user command
     * @return tasksToDo which contains the new todo task to be added
     */
    public static Task getToDoMethod(String input) {
        String toDoDescription = input.substring(4).trim();

        Task tasksToDo = new Todo(toDoDescription);
        Duke.tasks.add(tasksToDo);
        inputCount++;

        return tasksToDo;

    }

    public static Task getDeadlineMethod(String input) {
        String[] deadlineSplitter = input.substring(9).split(" /by ");
        String deadlineDescription = deadlineSplitter[0]; //before /by
        String[] dateTimeSplitter = deadlineSplitter[1].split(" ");
        String date = dateTimeSplitter[0];
        String time = dateTimeSplitter[1];
        String deadlineBy = date; // after /by
        String deadlineBy1 = time;

        Task description = new Deadline(deadlineDescription, deadlineBy, deadlineBy1);
        Duke.tasks.add(description);
        inputCount++;

        return description;

    }

    public static Task getEventMethod(String input) {
        String[] eventSplitter = input.substring(6).split(" /at ");
        String eventDescription = eventSplitter[0]; //before /at
        String eventAt = eventSplitter[1]; // after /at

        Task description = new Event(eventDescription, eventAt);
        Duke.tasks.add(description);
        inputCount++;

        return description;
    }

    public static void testInput(String input) throws DukeExceptions {
        if (input.length() < 5) { //check for description after todo
            throw new DukeExceptions();
        }
    }
}
