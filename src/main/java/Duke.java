import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Duke {

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
            + "☹ OOPS!!! The description of a task cannot be empty.\n"
            + helpMessage
            + "____________________________________________________________\n";

    public static String invalidTaskError = "____________________________________________________________\n"
            + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
            + helpMessage
            + "____________________________________________________________\n";

    public static String fileNotFound = "____________________________________________________________\n"
            + "No preloaded file found! Please input your own data.\n"
            + "____________________________________________________________\n";

    //public static Task[] taskList = new Task[100];
    //public static int itemCount = 0;\
    public static ArrayList<Task> taskList = new ArrayList<>();

    private static char getTaskType(Task task) {
        if (task instanceof Todo) {
            return 'T';
        }
        if (task instanceof Deadline) {
            return 'D';
        }
        return 'E';
    }

    private static void updateData(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath); //to write first line
        FileWriter fa = new FileWriter(filePath, true); //append the rest

        Task currentTask = taskList.get(0);
        char taskType = getTaskType(currentTask);
        int isDone = (currentTask.getStatus()) ? 1 : 0;
        String fullTask = currentTask.toString().substring(7);

        fw.write(taskType + " | " + isDone + " | " + fullTask + System.lineSeparator());

        for (int i = 1; i < taskList.size(); i++) {
            currentTask = taskList.get(i);
            taskType = getTaskType(currentTask);
            isDone = (currentTask.getStatus()) ? 1 : 0;
            fullTask = currentTask.toString().substring(7);
            fa.write(taskType + " | " + isDone + " | " + fullTask + System.lineSeparator());
        }

        fw.close();
        fa.close();
    }

    private static void readData(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {

            String taskInfo = s.nextLine();

            if (taskInfo.startsWith("T")) {
                Task newTask = new Todo(taskInfo.substring(8));
                taskList.add(newTask);
                if (taskInfo.charAt(4) == '1') {
                    newTask.markAsDone();
                }

            } else if (taskInfo.startsWith("D")) {
                int indexOfSlash = taskInfo.indexOf("/");
                Task newTask = new Deadline(taskInfo.substring(8, indexOfSlash - 1), taskInfo.substring(indexOfSlash + 4));
                if (taskInfo.charAt(4) == '1') {
                    newTask.markAsDone();
                }

            } else {
                int indexOfSlash = taskInfo.indexOf("/");
                Task newTask = new Event(taskInfo.substring(8, indexOfSlash - 1), taskInfo.substring(indexOfSlash + 4));
                if (taskInfo.charAt(4) == '1') {
                    newTask.markAsDone();
                }
            }
        }
    }


    //main function to process input
    public static void processLine(String line) throws UnknownCommandException, EmptyTaskException {
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

            if (isTodoTask) {

                String actualTask = line.substring(4).trim();
                if (actualTask.isBlank()) {
                    throw new EmptyTaskException();
                }
                taskList.add(new Todo(actualTask));

            } else if (isDeadlineTask) {
                int indexOfSlash = line.indexOf("/");
                String actualTask = line.substring(8, indexOfSlash - 1).trim();
                String deadlineBy = line.substring(indexOfSlash + 4);
                if (actualTask.isBlank()) {
                    throw new EmptyTaskException();
                }
                taskList.add(new Deadline(actualTask, deadlineBy));


            } else {
                int indexOfSlash = line.indexOf("/");
                String actualTask = line.substring(5, indexOfSlash - 1).trim();
                String eventAt = line.substring(indexOfSlash + 4);
                if (actualTask.isBlank()) {
                    throw new EmptyTaskException();
                }
                taskList.add(new Event(actualTask, eventAt));
            }

            System.out.println("____________________________________________________________\n"
                    + "Got it! I've added this task: "
                    + System.lineSeparator()
                    + "  "
                    + taskList.get(taskList.size() - 1)
                    + System.lineSeparator()
                    + "Now you have "
                    + taskList.size()
                    + " tasks left in the list."
                    + System.lineSeparator()
                    + "____________________________________________________________\n"
            );


        } else if (line.startsWith("help")) {
            System.out.println(instructions);

        } else {
            throw new UnknownCommandException();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(greeting);

        File dukeData = new File("data/duke.txt");

        //read in duke.txt
        try {
            readData(dukeData.getPath());

        } catch (FileNotFoundException e) {
            System.out.println(fileNotFound);

        }


        String line;
        Scanner in = new Scanner(System.in);

        //user input loop
        while (true) {
            line = in.nextLine();

            if (line.equals("bye")) {
                System.out.println(goodbye);
                return;
            }

            try {
                processLine(line);
                updateData(dukeData.getPath());

            } catch (UnknownCommandException | IndexOutOfBoundsException e) {
                System.out.println(invalidTaskError);

            } catch (EmptyTaskException e) {
                System.out.println(emptyTaskError);

            } catch (IOException e) {
                System.out.println("An error has occurred!");
            }

        }
    }
}

