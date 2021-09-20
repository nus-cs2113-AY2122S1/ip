import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    public static int inputCount = 0;
    public static ArrayList<Task> tasks = new ArrayList<>();
    private static final String taskDoneChecker = "0";
    private static final String INPUT_BYE = "bye";
    private static final String INPUT_LIST = "list";
    private static final String INPUT_DONE = "done";
    private static final String INPUT_DELETE = "delete";
    private static final String INPUT_TODO = "todo";
    private static final String INPUT_DEADLINE = "deadline";
    private static final String INPUT_EVENT = "event";
    private static final String INPUT_FIND = "find";


    public static void main(String[] args) {
        try {
            loadFile();
        } catch (FileNotFoundException e22) {
            System.out.println("File not found! Continue entering commands for automatic file creation!");
        }

        String input;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        input = in.nextLine();

        while (!(input.equals(INPUT_BYE))) {

            if (input.equals(INPUT_LIST)) {
                input = printList(in);

            } else if (input.contains(INPUT_DONE)) {
                try {
                    input = testTaskDone(input, in);
                    replaceAllTasks();

                } catch (NumberFormatException e3) {
                    System.out.println("Did you type in a number?");
                    input = in.nextLine();

                } catch (ArrayIndexOutOfBoundsException e4) {
                    System.out.println("Finish the command!");
                    input = in.nextLine();

                } catch (IOException e33) {
                    System.out.println("IOexception, incorrect input or output");
                    input = in.nextLine();

                }

            } else if (input.contains(INPUT_TODO)) {
                try {
                    testInput(input); // test todo input
                    appendTodo(input); // add new entry to duke.txt
                    Task whatToDo = getToDoMethod(input);
                    System.out.println("    Got it. I've added this task:\n" + "    " + whatToDo + "\n"
                            + "    Now you have " + tasks.size() + " tasks in the list.");

                    input = in.nextLine();

                } catch (DukeExceptions e5) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    input = in.nextLine();

                }

            } else if (input.contains(INPUT_DEADLINE)) {
                try {
                    appendDeadline(input); //add new entry to duke.txt
                    Task whatDeadline = getDeadlineMethod(input);
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

            } else if (input.contains(INPUT_EVENT)) {
                try {
                    appendEvent(input); //add new entry to duke.txt
                    Task whatEvent = getEventMethod(input);
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

            } else if (input.contains(INPUT_DELETE)) {
                try {
                    input = testDeleteTask(input, in);
                    replaceAllTasks();

                } catch (NumberFormatException e8) {
                    System.out.println("Did you type in a number?");
                    input = in.nextLine();

                } catch (ArrayIndexOutOfBoundsException e9) {
                    System.out.println("Finish the command!");
                    input = in.nextLine();

                } catch (IOException e34) {
                    System.out.println("IOexception, incorrect input or output");
                    input = in.nextLine();
                }

            } else if (input.contains(INPUT_FIND)) {
                input = findList(input, in);

            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");//overall error check
                input = in.nextLine();

            }
        }
        System.out.println("    Bye! Hope to see you again soon!");

    }

    /**
     * Returns a print statement of all tasks in the file Duke.txt
     *
     * @param in input of user
     * @return prints list of all tasks
     */
    private static String findList(String input, Scanner in) {
        String[] findSplitter;
        findSplitter = input.split(" ");
        System.out.println("    Here are the matching tasks in your list:");

        for (int outputCount = 0; outputCount < tasks.size(); outputCount++) {
            if ((tasks.get(outputCount)).description.contains(findSplitter[1])) {
                System.out.println("    " + (outputCount + 1) + "." + tasks.get(outputCount));
            } else {
                System.out.println("    none of them!");
                break;
            }
        }
        input = in.nextLine();
        return input;

    }

    /**
     * Returns a print statement of all tasks in the file Duke.txt
     *
     * @param in input of user
     * @return prints list of all tasks
     */
    private static String printList(Scanner in) {
        String input;
        System.out.println("    Here are the tasks in your list:");

        for (int outputCount = 0; outputCount < tasks.size(); outputCount++) {
            System.out.println("    " + (outputCount + 1) + "." + tasks.get(outputCount));
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
    private static String testTaskDone(String input, Scanner in) {
        int taskNumber;
        String[] inputSplitter;
        inputSplitter = input.split(" ");
        taskNumber = Integer.parseInt(inputSplitter[1]);
        taskNumber = taskNumber - 1;

        if (((taskNumber + 1) > 0) && ((taskNumber + 1) <= tasks.size())) { //check for invalid inputs
            input = setTaskDone(in, tasks.get(taskNumber));

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
    private static String testDeleteTask(String input, Scanner in) {
        int taskNumber;
        String[] inputSplitter;
        inputSplitter = input.split(" ");
        taskNumber = Integer.parseInt(inputSplitter[1]); //get which task to delete
        taskNumber = taskNumber - 1;

        if (((taskNumber + 1) > 0) && ((taskNumber + 1) <= tasks.size())) { //check for invalid inputs
            input = setTaskDelete(in, tasks.remove(taskNumber));

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
     * @param in String containing the user command
     * @param task list of all tasks
     * @return marks x in [ ] in the list operation when printList is called
     */
    private static String setTaskDone(Scanner in, Task task) {
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
     * @param in String containing the user command
     * @param task list of all tasks
     * @return prints out the task that has been deleted and shows the remaining number
     * of tasks left.
     */
    private static String setTaskDelete(Scanner in, Task task) {
        String input;
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    " + task);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
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
        tasks.add(tasksToDo);
        inputCount++;

        return tasksToDo;

    }

    public static Task getDeadlineMethod(String input) {
        String[] deadlineSplitter = input.substring(9).split(" /by ");
        String deadlineDescription = deadlineSplitter[0]; //before /by
        String deadlineBy = deadlineSplitter[1]; // after /by

        Task description = new Deadline(deadlineDescription, deadlineBy);
        tasks.add(description);
        inputCount++;

        return description;

    }

    public static Task getEventMethod(String input) {
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

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendTodo(String input) {
        String filePath = new File("duke.txt").getAbsolutePath(); //added
        String isDone = taskDoneChecker;
        String addToDoDescription = input.substring(4).trim(); // "return book"

        try {
            writeToFile(filePath,
                    "todo " + addToDoDescription + " | " + isDone + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("IOException error, theres an input/output error");
        }

    }

    public static void appendEvent(String input) {
        String filePath = new File("duke.txt").getAbsolutePath(); //added
        String isDone = taskDoneChecker;
        String[] eventSplitterString = input.substring(6).split(" /at ");
        String addEventDescription = eventSplitterString[0];
        String location = eventSplitterString[1];

        try {
            writeToFile(filePath, "event " + addEventDescription + " /at "
                    + location + " | " + isDone + System.lineSeparator());

        } catch (IOException e) {
            System.out.println("IOException error, theres an input/output error");
        }
    }

    public static void appendDeadline(String input) {
        String filePath = new File("duke.txt").getAbsolutePath(); //addded
        String isDone = taskDoneChecker;
        String[] deadlineSplitterString = input.substring(9).split(" /by ");
        String addDeadlineDescription = deadlineSplitterString[0];
        String date = deadlineSplitterString[1];

        try {
            writeToFile(filePath, "deadline " + addDeadlineDescription + " /by "
                    + date + " | " + isDone + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("IOException error, theres an input/output error");
        }
    }

    public static void replaceAllTasks() throws IOException {
        String filePath = new File("duke.txt").getAbsolutePath(); //added
        FileWriter fw = new FileWriter(filePath, false);
        String taskToSave;

        for (Task individualTask : tasks) {
            if (individualTask instanceof Deadline) {
                taskToSave = individualTask.getStoredDataString();

            } else if (individualTask instanceof Event) {
                taskToSave = individualTask.getStoredDataString();

            } else {
                taskToSave = individualTask.getStoredDataString();
            }

            Files.write(Paths.get(filePath), taskToSave.getBytes(), StandardOpenOption.APPEND);
        }
    }

    public static void loadFile() throws FileNotFoundException {
        String filePath = new File("duke.txt").getAbsolutePath(); //added
        File f = new File(filePath);
        Scanner fileScan = new Scanner(f);
        String taskType;
        int fileScanNumber = 1; //order of tasks in duke text file

        while (fileScan.hasNext()) {
            String data = fileScan.nextLine(); //scans first line of the file
            String[] arraySplitter = data.split(" \\| "); //split "todo hello" "0"
            String[] arraySplitter2 = arraySplitter[0].split(" ");//split "todo hello"  -> "todo" "hello"
            taskType = arraySplitter2[0]; //chooses "todo"

            switch (taskType) {
            case "todo":
                getToDoMethod(arraySplitter[0]);
                if (arraySplitter[1].equals("1")) {
                    loadFileDone(fileScanNumber);
                }
                break;
            case "deadline":
                getDeadlineMethod(arraySplitter[0]);
                if (arraySplitter[1].equals("1")) {
                    loadFileDone(fileScanNumber);
                }
                break;
            case "event":
                getEventMethod(arraySplitter[0]);
                if (arraySplitter[1].equals("1")) {
                    loadFileDone(fileScanNumber);
                }
                break;
            default:
            }

            fileScanNumber++;
        }

        fileScan.close();
    }

    public static void loadFileDone(Integer number) {
        number = number - 1;
        Task task = tasks.get(number);
        task.markAsDone();
    }
}

