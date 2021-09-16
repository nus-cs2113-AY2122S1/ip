package Duke;

import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Duke {

    private static int taskCounter = 0;
    private static final ArrayList<Task> tasksArrayList = new ArrayList<>();
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final java.nio.file.Path filePath = java.nio.file.Paths.get(CURRENT_DIRECTORY);


    public static void main(String[] args) {
        initialiseFile();
        printHeaderMessage();
        handleInputs();
        printByeMessage();
    }

    private static void initialiseFile() {
        try {
            //get the file, else create file if it does not exist.
            File taskFile = getTaskFile();
            Scanner fileTaskLists = new Scanner(taskFile);

            //read and extract the data in the file to store it in the task array list.
            readAndExtractFile(fileTaskLists);
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
    }

    private static void readAndExtractFile(Scanner fileTaskLists) throws DukeException {
        while (fileTaskLists.hasNextLine()) {
            String data = fileTaskLists.nextLine();
            String[] splittedData = data.split("\\|");
            String taskType = splittedData[0];
            boolean isMarkedDone = Integer.parseInt(splittedData[1]) == 1;
            String taskDescription = splittedData[2];
            String taskCommand;

            switch (taskType) {
            case "T":
                taskCommand = "todo " + taskDescription;
                addTodoTask(taskCommand);
                break;
            case "D":
                taskCommand = "deadline " + taskDescription;
                addDeadlineTask(taskCommand);
                break;
            case "E":
                taskCommand = "event " + taskDescription;
                addEventTask(taskCommand);
                break;
            }
            if (isMarkedDone) {
                markTaskAsDone("done " + taskCounter);
            }
        }
    }

    private static File getTaskFile() throws IOException {
        File taskFile = new File(filePath + "/taskLists.txt");
        if (taskFile.createNewFile()) {
            System.out.println("A new file has been created at " + filePath);
        } else {
            System.out.println(taskFile + " accessed.");
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        return taskFile;
    }


    private static void handleInputs() {
        String input = getInput();
        while (!input.equals("bye")) {
            //Splits the input string by WhiteSpace into an array of strings.
            String[] splittedInput = input.split(" ");

            try {
                switch (splittedInput[0].toLowerCase()) {
                case "todo":
                    addTodoTask(input);
                    printNewTaskMsg();
                    break;
                case "deadline":
                    addDeadlineTask(input);
                    printNewTaskMsg();
                    break;
                case "event":
                    addEventTask(input);
                    printNewTaskMsg();
                    break;
                case "list":
                    printListMessage();
                    break;
                case "done":
                    markTaskAsDone(input);
                    break;
                case "delete":
                    deleteTask(input);
                    break;
                case "bye":
                    return;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means." + System.lineSeparator()
                            + "\tPlease enter a valid input!" + System.lineSeparator()
                            + "\ti.e. todo, deadline, event, list, done or bye.");
                }

                updateFile();

            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }

            input = getInput();
        }
//
    }

    private static String getInput() {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        printLineSeparator();
        return input;
    }

    private static void addTodoTask(String input) throws DukeException {
        //get the name of the task
        String taskName = getTodoTaskName(input);

        //create new Todo task
        tasksArrayList.add(new Todo(taskName));

        taskCounter++;
    }

    private static String getTodoTaskName(String input) throws DukeException {
        int spaceIndex = input.indexOf(' ');
        String taskName = input.substring(spaceIndex + 1);
        if (taskName.equals("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo task cannot be empty.");
        }
        return taskName;
    }

    private static void addEventTask(String input) throws DukeException {


        //get the name of the task
        int slashIndex = input.indexOf('/');

        String taskName = getEventTaskName(input, slashIndex);

        //get the due date of the task
        String dueDate = getDueDate(input, slashIndex);

        //create new Event task
        tasksArrayList.add(new Event(taskName, dueDate));
        taskCounter++;
    }

    private static String getEventTaskName(String input, int slashIndex) throws DukeException {
        final int EVENT_WORD_LENGTH = 6;
        int taskNameLastIndex = slashIndex - 1; //the last index of the task name

        //if the slash is not present in the input
        if (slashIndex == -1) {
            throw new DukeException("☹ OOPS!!! The description of an event task requires a task name" + System.lineSeparator()
                    + "\tfollowed by a front slash, and then a specific time after." + System.lineSeparator()
                    + "\ti.e. event team project meeting /on 2-10-2019 2-4pm");
        } else if (taskNameLastIndex <= EVENT_WORD_LENGTH) { //if the slash exists but the task name is empty
            throw new DukeException("☹ OOPS!!! The name of an event task cannot be empty.");
        }

        String taskName = input.substring(EVENT_WORD_LENGTH, taskNameLastIndex);

        //if taskName are just filled with whitespaces
        if (taskName.replace(" ", "").isEmpty()) {
            throw new DukeException("☹ OOPS!!! The name of an event task cannot be empty.");
        }
        return taskName;
    }

    private static void addDeadlineTask(String input) throws DukeException {

        //get the name of the task
        int slashIndex = input.indexOf('/');
        String taskName = getDeadlineTaskName(input, slashIndex);

        //get the due date of the task
        String dueDate = getDueDate(input, slashIndex);

        //create new Deadline task
        tasksArrayList.add(new Deadline(taskName, dueDate));

        taskCounter++;
    }

    private static String getDeadlineTaskName(String input, int slashIndex) throws DukeException {
        final int DEADLINE_WORD_LENGTH = 9;
        int taskNameLastIndex = slashIndex - 1; //the last index of the tsak name

        //if the slash is not present in the input
        if (slashIndex == -1) {
            throw new DukeException("☹ OOPS!!! The description of an deadline task requires a task name" + System.lineSeparator()
                    + "\tfollowed by a front slash, and then a specific date/time after." + System.lineSeparator()
                    + "\ti.e. deadline submit report /by 11-10-2019 5pm");
        } else if (taskNameLastIndex <= DEADLINE_WORD_LENGTH) { //if the slash exists but the task name is empty
            throw new DukeException("☹ OOPS!!! The name of an deadline task cannot be empty.");
        }

        String taskName = input.substring(DEADLINE_WORD_LENGTH, taskNameLastIndex);

        //if taskName are just filled with whitespaces
        if (taskName.replace(" ", "").isEmpty()) {
            throw new DukeException("☹ OOPS!!! The name of a deadline task cannot be empty.");
        }
        return taskName;
    }

    private static String getDueDate(String input, int slashIndex) throws DukeException {
        if (slashIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! The description of this task type requires a specific time");
        }
        return input.substring(slashIndex + 1);
    }

    private static void printNewTaskMsg() {
        int taskIndex = taskCounter - 1;
        System.out.println("\tAlright! I've just added this task:");
        System.out.println("\t" + tasksArrayList.get(taskIndex).toString());
        System.out.println("\tYou now have " + taskCounter + " tasks on your task list.");
        printLineSeparator();
    }

    private static void printListMessage() {
        if (taskCounter == 0) {
            System.out.println("\tThe list is empty!");
        } else {
            System.out.println("\tHere's the list of your tasks: ");
            for (int j = 0; j < taskCounter; j++) {
                int itemNumber = j + 1;
                System.out.println("\t" + itemNumber + "." + tasksArrayList.get(j).toString());
            }
        }
        printLineSeparator();
    }

    private static void markTaskAsDone(String s) {

        String[] splittedInput = s.split(" ");
        try {
            markParticularTaskAsDone(splittedInput[1]);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            if (taskCounter == 0) {
                System.out.println("\t☹ OOPS!!! The list is empty!");
            } else {
                System.out.println("\t☹ OOPS!!! Please enter a valid task index.");
            }
        }
        printLineSeparator();
    }


    private static void markParticularTaskAsDone(String s1) {
        int taskIndex = Integer.parseInt(s1) - 1;
        tasksArrayList.get(taskIndex).markAsDone();
        System.out.println("\tGood job! I've marked this task as done: ");
        System.out.println("\t" + tasksArrayList.get(taskIndex));
    }

    private static void deleteTask(String s) throws IndexOutOfBoundsException, NullPointerException {
        String[] splittedInput = s.split(" ");

        try {
            deleteParticularTask(splittedInput[1]);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            if (taskCounter == 0) {
                System.out.println("\t☹ OOPS!!! The list is empty!");
            } else {
                System.out.println("\t☹ OOPS!!! Please enter a valid task index.");
            }
        }
        printLineSeparator();
    }

    private static void deleteParticularTask(String s1) {
        int taskIndex = Integer.parseInt(s1) - 1;

        System.out.println("\tAlright, I've deleted this task: " + System.lineSeparator()
                + "\t" + tasksArrayList.get(taskIndex).toString());
        taskCounter--;
        tasksArrayList.remove(taskIndex);
        System.out.println("\tYou now have " + taskCounter + " tasks on your task list.");
    }

    private static void updateFile() throws IOException {
        FileWriter taskWriter = new FileWriter(filePath + "/taskLists.txt", false);
        for (Task task: tasksArrayList) {
            String classType = task.getClass().getName();
            String taskAbbreviation = getTaskAbbreviation(classType);
            String taskStatus = getTaskStatus(task);
            String dataWritten = taskAbbreviation + "|" + taskStatus + "|" + task.getDescription() + System.lineSeparator();
            taskWriter.write(dataWritten);
        }
        taskWriter.close();
    }

    private static String getTaskStatus(Task task) {
        if (task.getStatusIcon().equals("[X] ")) {
            return "1";
        }
        return "0";
    }

    private static String getTaskAbbreviation(String classType) {
        if (classType.equals("Duke.task.Todo")) {
            return "T";
        } else if (classType.equals("Duke.task.Deadline")) {
            return "D";
        }
        return "E";
    }

    private static void printHeaderMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("\tHey, how are you?");
        System.out.println("\tWhat can I do for you today?");
        printLineSeparator();
    }

    private static void printByeMessage() {
        System.out.println("\tGoodbye! Hope to see you again soon!");
        printLineSeparator();
    }

    private static void printLineSeparator() {
        System.out.println("\t----------------------------------------------------------------------");
    }
}
