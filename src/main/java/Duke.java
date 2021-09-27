import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;
import Exceptions.UnknownCommandException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


public class Duke {

    private static char TODO_TASK = 'T';
    private static char DEADLINE_TASK = 'D';
    private static char EVENT_TASK = 'E';
    private static int taskStartIndex = 7; //7 is the starting index for the actual task, in the data file

    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static String instructions = "Hello! Welcome to Duke. I am your personal task tracker.\n"
            + "As of now, I can help you track Todos, Deadlines and Events. "
            + "Mark your tasks with either \"todo\", \"deadline\" or \"event\" at the start. \n"
            + "For deadlines and events, after your task, please enter either \"by (your deadline)\" "
            + "or \"at (your event timing)\".\n"
            + "To see all your tasks, enter \"list\". \n"
            + "To mark a task as done, enter \"done (task number)\". \n"
            + "To delete a task, enter \"delete (task number)\". \n"
            + "To exit this program, enter \"bye\". \n"
            + "And that's all! Hope you find me helpful! :) \n";

    public static String helpMessage = "Enter \"help\" if you need help using me! \n";

    public static String greeting = "____________________________________________________________\n"
            + "Hello! I'm Duke\n"
            + logo
            + "What can I do for you?\n"
            + helpMessage
            + "____________________________________________________________\n";

    public static String goodbye = "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "____________________________________________________________\n";


    public static String emptyTaskError = "____________________________________________________________\n"
            + "Please do not leave the description of the task empty!\n"
            + helpMessage
            + "____________________________________________________________\n";

    public static String invalidTaskError = "____________________________________________________________\n"
            + "I don't know what that means! It's either an invalid command or you have formatted the task wrongly.\n"
            + helpMessage
            + "____________________________________________________________\n";


    public static ArrayList<Task> taskList = new ArrayList<>();

    public static String fileNotFound = "____________________________________________________________\n"
            + "No preloaded file found! Please input your own data.\n"
            + "____________________________________________________________\n";


    private static void addNewEvent(String line) throws EmptyTaskException, InvalidCommandException {
        int indexOfSlash = line.indexOf("/");
        String actualTask = extractEventTask(line, indexOfSlash);
        String eventAt = extractTiming(line, indexOfSlash);
        if (actualTask.isBlank()) {
            throw new EmptyTaskException();
        }
        if (!line.contains("/at")) {
            throw new InvalidCommandException();
        }
        taskList.add(new Event(actualTask, eventAt));
    }

    private static void addNewDeadline(String line) throws EmptyTaskException, InvalidCommandException {
        int indexOfSlash = line.indexOf("/");
        String actualTask = extractDeadlineTask(line, indexOfSlash);
        String deadlineBy = extractTiming(line, indexOfSlash);
        if (actualTask.isBlank()) {
            throw new EmptyTaskException();
        }
        if (!line.contains("/by")) {
            throw new InvalidCommandException();
        }
        taskList.add(new Deadline(actualTask, deadlineBy));
    }

    private static void addNewTodo(String line) throws EmptyTaskException {
        String actualTask = extractTodoTask(line);
        if (actualTask.isBlank()) {
            throw new EmptyTaskException();
        }
        taskList.add(new Todo(actualTask));
    }

    private static String extractTiming(String line, int indexOfSlash) {
        return line.substring(indexOfSlash + 4);
    }

    private static String extractEventTask(String line, int indexOfSlash) {
        return line.substring(5, indexOfSlash - 1).trim();
    }

    private static String extractDeadlineTask(String line, int indexOfSlash) {
        return line.substring(8, indexOfSlash - 1).trim();
    }

    private static String extractTodoTask(String line) {
        return line.substring(4).trim();
    }

    private static char getTaskType(Task task) {
        if (task instanceof Todo) {
            return TODO_TASK;
        }
        if (task instanceof Deadline) {
            return DEADLINE_TASK;
        }
        return EVENT_TASK;
    }

    private static void updateData(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath); //to write first line
        FileWriter fa = new FileWriter(filePath, true); //append the rest

        if (taskList.isEmpty()) {
            fw.close();
            fa.close();
            return;
        }

        //WRITING to file for first line
        Task currentTask = taskList.get(0);
        char taskType = getTaskType(currentTask);
        int isDone = (currentTask.getStatus()) ? 1 : 0;
        String fullTask = getTaskInStringFromFile(currentTask);

        fw.write(taskType + " | " + isDone + " | " + fullTask + System.lineSeparator());


        //subsequent lines append
        for (int i = 1; i < taskList.size(); i++) {
            currentTask = taskList.get(i);
            taskType = getTaskType(currentTask);
            isDone = (currentTask.getStatus()) ? 1 : 0;
            fullTask = getTaskInStringFromFile(currentTask);
            fa.write(taskType + " | " + isDone + " | " + fullTask + System.lineSeparator());
        }

        fw.close();
        fa.close();
    }

    private static String getTaskInStringFromFile(Task currentTask) {
        return currentTask.toString().substring(taskStartIndex);
    }

    private static void readData(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {

            String taskInfo = s.nextLine();

            Task newTask;
            if (taskInfo.startsWith("T")) {
                newTask = new Todo(fileGetTodoTask(taskInfo));

            } else {
                newTask = createNewDeadlineOrEvent(taskInfo);
            }
            taskList.add(newTask);
            if (fileTaskIsDone(taskInfo)) {
                newTask.markAsDone();
            }
        }
    }

    //creates a new deadline or event, depending on what the line starts with
    private static Task createNewDeadlineOrEvent(String taskInfo) {
        int indexOfColon = taskInfo.indexOf(":");
        int indexOfOpenBracket = taskInfo.indexOf("(");
        int indexOfCloseBracket = taskInfo.indexOf(")");
        if (taskInfo.startsWith("D")) {
            return new Deadline(taskInfo.substring(8, indexOfOpenBracket - 1), taskInfo.substring(indexOfColon + 2, indexOfCloseBracket));
        }
        return new Event(taskInfo.substring(8, indexOfOpenBracket - 1), taskInfo.substring(indexOfColon + 2, indexOfCloseBracket));
    }

    private static String fileGetTodoTask(String taskInfo) {
        return taskInfo.substring(8);
    }

    private static boolean fileTaskIsDone(String taskInfo) {
        return taskInfo.charAt(4) == '1';
    }


    //main function to process input
    public static void processLine(String line) throws UnknownCommandException, EmptyTaskException, InvalidCommandException {
        boolean isTodoTask = line.startsWith("todo");
        boolean isDeadlineTask = line.startsWith("deadline");
        boolean isEventTask = line.startsWith("event");
        boolean isProperTask = isTodoTask || isDeadlineTask || isEventTask;

        if (line.equals("list")) {
            System.out.println("____________________________________________________________");
            System.out.println("Here are your remaining tasks:");

            for (int i = 1; i <= taskList.size(); i++) {
                System.out.println(
                        Integer.toString(i)
                                + ". "
                                + taskList.get(i - 1)
                );
            }
            System.out.println("____________________________________________________________\n");

        } else if (line.startsWith("done")) {
            try {
                int i = Integer.parseInt(line.substring(5)) - 1;
                taskList.get(i).markAsDone();

                System.out.println("____________________________________________________________\n"
                        + "Well done! I've marked this task as done: \n"
                        + taskList.get(i)
                        + System.lineSeparator()
                        + "____________________________________________________________\n"
                );
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number. Please mark a valid task number as done!\n");

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please mark a task number as done!\n");
            }

        } else if (isProperTask) {
            //checks for task commands
            if (isTodoTask) {
                addNewTodo(line);
            } else if (isDeadlineTask) {
                addNewDeadline(line);
            } else {
                addNewEvent(line);
            }

            System.out.println("____________________________________________________________\n"
                    + "Got it! I've added this task: "
                    + System.lineSeparator()
                    + "  "
                    + taskList.get(taskList.size() - 1)
                    + System.lineSeparator()
                    + "Now you have "
                    + Integer.toString(taskList.size())
                    + " tasks left in the list."
                    + System.lineSeparator()
                    + "____________________________________________________________\n"
            );


        } else if (line.startsWith("help")) {
            System.out.println(instructions);

        } else if (line.startsWith("delete")) {
            try {
                int i = Integer.parseInt(line.substring(7)) - 1;
                String deletedTask = taskList.get(i).toString();
                taskList.remove(i);
                System.out.println("____________________________________________________________\n"
                        + "Noted. I've removed this task: \n"
                        + deletedTask
                        + System.lineSeparator()
                        + "Now you have " + taskList.size() + " tasks in the list.\n"
                        + "____________________________________________________________\n"
                );

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Choose a task to delete!\n");

            } catch (NumberFormatException e) {
                System.out.println("Invalid task number. Please choose a valid task number to delete!\n");
            }

        } else {
            throw new UnknownCommandException();
        }
    }



    public static void main(String[] args) throws IOException {
        System.out.println(greeting);

        File dukeData = new File("Duke.txt");

        //read in duke.txt
        try {
            readData(dukeData.getPath());
        } catch (FileNotFoundException e) {
            System.out.println(fileNotFound);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("An error has occurred!");
        }


        String line;
        Scanner in = new Scanner(System.in);

        //user input loop
        while (true) {
            line = in.nextLine();

            //checks for exit command
            if (line.equals("bye")) {
                System.out.println(goodbye);
                return;
            }

            //process the other commands
            try {
                processLine(line);
                updateData(dukeData.getPath());
            } catch (UnknownCommandException | StringIndexOutOfBoundsException | InvalidCommandException e) {
                System.out.println(invalidTaskError);
            } catch (EmptyTaskException e) {
                System.out.println(emptyTaskError);
            } catch (IOException e) {
                System.out.println("An error has occurred!");
            }
        }
    }
}
