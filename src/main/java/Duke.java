import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    //Array of tasks
    private static ArrayList<Task> tasks;
    //Creation of database file
    private static final String FILEPATH = "data/jim.txt";
    private static final String FOLDERPATH = "data";
    //Scanner
    private static final Scanner sc = new Scanner(System.in);
    //GenshinImpact conversion rates
    private static final int TOP_LOW_CONVERSION_RATE = 27;
    private static final int HIGH_LOW_CONVERSION_RATE = 9;
    private static final int MID_LOW_CONVERSION_RATE = 3;
    //The sacred texts
    private static final String LINES = "____________________________________________________________"
            + System.lineSeparator();
    private static final String GREETING = " HeLLO! I'm Jim, a real person who definitely passes" +
            " reCaptchas!" + System.lineSeparator();
    private static final String CLEAR_DB_MESSAGE = "Database wiped clean!" + System.lineSeparator();
    private static final String BIRTHDAY_MESSAGE = " ^o^ Happy birthday to you! ^o^" + System.lineSeparator();
    private static final String ECHO_MESSAGE = " Echoing after you!" + System.lineSeparator();
    private static final String QUIT_MESSAGE = " That was annoying huh..." + System.lineSeparator();
    private static final String DELETE_MESSAGE = " This task has been spirited away:" + System.lineSeparator();
    private static final String EMPTY_LIST_MESSAGE = " This list is empty and sad :(" + System.lineSeparator();
    private static final String EMPTY_REMOVE_MESSAGE = " Please identify something to remove!" + System.lineSeparator();
    private static final String NO_TASK_MESSAGE = "No such task! You're not THAT productive..." +
            System.lineSeparator();
    private static final String NO_FILE_MESSAGE = "No database file!" + System.lineSeparator();
    private static final String TASK_DONE_MESSAGE = "This task was already marked done!" + System.lineSeparator();
    private static final String NO_TASK_DONE_NUMBER_MESSAGE = "Please specify the task you would like to " +
            "mark as done!" + System.lineSeparator();
    private static final String DONE_NOT_INTEGER_MESSAGE = "Please input an integer after done!" +
            System.lineSeparator();
    private static final String TASK_COMPLETE_MESSAGE = "Nice! You're a real champ for finishing this: " +
            System.lineSeparator();
    private static final String BYE_MESSAGE = " Bye! Remember, stay out of fire, suuuuuuper high level " +
            "tactic yea?" + System.lineSeparator();
    private static final String ADD_MESSAGE = " Got it. I've added this task:"  + System.lineSeparator() + "   ";
    private static final String PLEASE_ADD_A_TASK_DESCRIPTION = "Please add a task description!"
            + System.lineSeparator();
    private static final String MISSING_IN_DEADLINE_MESSAGE = "Please format your input as 'deadline [task]" +
            " /by [deadline]'!" + System.lineSeparator();
    private static final String MISSING_IN_EVENT_MESSAGE = "Please format your input as 'event [task]" +
            " /at [time range]'!" + System.lineSeparator();
    private static final String MISSING_BY_MESSAGE = "Please add '/by' in between your task and deadline!" +
            System.lineSeparator();
    private static final String MISSING_AT_MESSAGE = "Please add '/at' in between your task and time range!" +
            System.lineSeparator();
    private static final String INVALID_COMMAND = "I don't quite understand :/" + System.lineSeparator();
    private static final String HELP_MESSAGE = " Here are the commands for the things I can do:" +
            System.lineSeparator() +
            "    1. todo [task] = adds a Todo task" + System.lineSeparator() +
            "    2. deadline [task] /by [deadline] = adds a Deadline task" + System.lineSeparator() +
            "    3. event [task] /at [time range] = adds an Event task" + System.lineSeparator() +
            "    4. done [taskIndex] = marks the inputted task as done" + System.lineSeparator() +
            "    5. list = lists out all current tasks with their taskIndex" + System.lineSeparator() +
            "    6. echo = turn into a huge cave and echo your inputs back to you!!" + System.lineSeparator() +
            "    7. quit (only in echo mode) = turn back to normal and stop echoing" + System.lineSeparator() +
            "    8. genshin = begin the genshin helper" + System.lineSeparator() +
            "    9. bye = shuts me down... ;-;" + System.lineSeparator();

    //Prints greeting message
    public static void greeting() {
        System.out.println(LINES + GREETING + LINES);
    }

    //Begins echo function
    public static void echo() {
        System.out.println(LINES + ECHO_MESSAGE + LINES);
        boolean echoState = true;
        while (echoState) {
            String s = sc.nextLine();
            if (s.equalsIgnoreCase("quit")) {
                System.out.println(LINES + QUIT_MESSAGE + LINES);
                echoState = false;
            } else {
                String output = LINES + " " + s  + System.lineSeparator() + LINES;
                System.out.println(output);
            }
        }
    }

    //list function
    public static void list() {
        if (tasks.size() == 0) {
            System.out.println(LINES + EMPTY_LIST_MESSAGE + LINES);
        } else {
            System.out.print(LINES);
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + "." + tasks.get(i - 1));
            }
            System.out.print(LINES);
        }
    }

    //Removes a specified task
    public static void removeTask(String input) {
        try {
            int index = Integer.parseInt(input.substring(7));
            //given task does not exist in list
            if (index > tasks.size()) {
                System.out.println(LINES + NO_TASK_MESSAGE + LINES);
            } else {
                Task thisTask = tasks.get(index - 1);
                System.out.println(LINES + DELETE_MESSAGE + "   " + tasks.get(index - 1));
                tasks.remove(thisTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list." +
                        System.lineSeparator() + LINES);
                updateDatabase();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(LINES + EMPTY_REMOVE_MESSAGE + LINES);
        }
    }

    //Marks a specified task input as done
    public static void markDone(String input) {
        try {
            //isolate 'x' from 'done x', where x is a number
            int index = Integer.parseInt(input.substring(5));
            int taskCount = tasks.size();
            //task given is not in list
            if (index > taskCount) {
                System.out.println(LINES + NO_TASK_MESSAGE + LINES);
            } else if (tasks.get(index - 1).getStatusIcon().equals("X")) {
                System.out.println(LINES + TASK_DONE_MESSAGE + LINES);
            } else {
                tasks.get(index - 1).markAsDone();
                System.out.print(LINES + TASK_COMPLETE_MESSAGE + tasks.get(index - 1)  +
                        System.lineSeparator() + LINES);
                updateDatabase();
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LINES + NO_TASK_DONE_NUMBER_MESSAGE + LINES);
        } catch (NumberFormatException e) {
            System.out.println(LINES + DONE_NOT_INTEGER_MESSAGE + LINES);
        }
    }

    //Adds task to tasks
    public static void addTask(String input) {
        if (input.toLowerCase().startsWith("todo")) {
            addTodo(input);
            int indexOfAddedTask = tasks.size() - 1;
            appendDatabase("T", tasks.get(indexOfAddedTask).description, "0");
        } else if (input.toLowerCase().startsWith("deadline")) {
            addDeadline(input);
            int indexOfAddedTask = tasks.size() - 1;
            Deadline addedDeadline = (Deadline) tasks.get(indexOfAddedTask);
            String deadlineInput = addedDeadline.description + "/by" + addedDeadline.by;
            appendDatabase("D", deadlineInput, "0");
        } else if (input.toLowerCase().startsWith("event")){
            addEvent(input);
            int indexOfAddedTask = tasks.size() - 1;
            Event addedEvent = (Event) tasks.get(indexOfAddedTask);
            String eventInput = addedEvent.description + "/at" + addedEvent.from;
            appendDatabase("E", eventInput, "0");
        }
    }
    private static void addTodo(String input) {
        try {
            tasks.add(new Todo(input.substring(5)));
            int taskIndex = tasks.size() - 1;
            printTaskAcknowledgement(tasks.get(taskIndex));
        } catch (StringIndexOutOfBoundsException e){
            System.out.println(LINES + PLEASE_ADD_A_TASK_DESCRIPTION + LINES);
        }
    }
    private static void addDeadline(String input) {
        try {
            if (input.trim().length() == 8) {
                System.out.println(LINES + PLEASE_ADD_A_TASK_DESCRIPTION + LINES);
            } else if (!input.contains("/by")) {
                System.out.println(LINES + MISSING_BY_MESSAGE + LINES);
            } else {
                int slashIndex = input.indexOf("/by");
                String task = input.substring(9, slashIndex).trim();
                String dueDate = input.substring(slashIndex + 3).trim();
                tasks.add(new Deadline(task, dueDate));
                int taskIndex = tasks.size() - 1;
                printTaskAcknowledgement(tasks.get(taskIndex));
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LINES + MISSING_IN_DEADLINE_MESSAGE + LINES);
        }
    }
    private static void addEvent(String input) {
        try {
            if (input.trim().length() == 5) {
                System.out.println(LINES + PLEASE_ADD_A_TASK_DESCRIPTION + LINES);
            } else if (!input.contains("/at")) {
                System.out.println(LINES + MISSING_AT_MESSAGE + LINES);
            } else {
                int slashIndex = input.indexOf("/at");
                String task = input.substring(6, slashIndex).trim();
                String timeRange = input.substring(slashIndex + 3).trim();
                tasks.add(new Event(task, timeRange));
                int taskIndex = tasks.size() - 1;
                printTaskAcknowledgement(tasks.get(taskIndex));
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LINES + MISSING_IN_EVENT_MESSAGE + LINES);
        }
    }
    private static void printTaskAcknowledgement(Task task) {
        System.out.println(LINES + ADD_MESSAGE + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list." +
                System.lineSeparator() + LINES);
    }

    public static void appendDatabase(String type, String input, String isDone) {
        //append new tasks to database
        try {
            FileWriter fw = new FileWriter(FILEPATH, true);
            switch (type) {
            case "T":
                fw.write(type + " | " + isDone + " | " + input + System.lineSeparator());
                break;
            case "D":
                String[] deadlineSplit = input.split("/by", 2);
                fw.write(type + " | " + isDone + " | " + deadlineSplit[0] + " | " +
                        deadlineSplit[1] + System.lineSeparator());
                break;
            case "E":
                String[] eventSplit = input.split("/at", 2);
                fw.write(type + " | " + isDone + " | " + eventSplit[0] + " | " + eventSplit[1]
                        + System.lineSeparator());
                break;
            default:
                break;
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(LINES + NO_FILE_MESSAGE + LINES);
        }
    }

    public static void clearDatabase() {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            fw.write("");
            fw.close();
            tasks.clear();
        } catch (IOException e) {
            System.out.println(LINES + NO_FILE_MESSAGE + LINES);
        }
    }

    public static void updateDatabase() {
        //update after mark done.
        //clear file then append from list
        clearDatabase();
        for (Task task: tasks) {
            String isDone = task.isDone? "1" : "0";
            if (task instanceof Todo) {
                appendDatabase("T", task.description, isDone);
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                String input = deadline.description + "/by" + deadline.getDeadline();
                appendDatabase("D", input , isDone);
            } else if (task instanceof Event) {
                Event event = (Event) task;
                String input = event.description + "/at" + event.getDeadline();
                appendDatabase("E", input , isDone);
            }
        }
    }

    public static void initList(Scanner s) throws JimException {
        while (s.hasNext()) {
            String task = s.nextLine();
            String[] listedTask = task.split(" \\| ", 4);
            switch (listedTask[0]) {
            case "T":
                tasks.add(new Todo(listedTask[2]));
                if (listedTask[1].equals("1")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
                break;
            case "D":
                tasks.add(new Deadline(listedTask[2], listedTask[3]));
                if (listedTask[1].equals("1")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
                break;
            case "E":
                tasks.add(new Event(listedTask[2], listedTask[3]));
                if (listedTask[1].equals("1")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
                break;
            default:
                throw new JimException();
            }
        }
        s.close();
    }

    //Initialises the list
    public static void initJim() {
        try {
            tasks = new ArrayList<Task>();
            File file = new File(FILEPATH);
            File folder = new File(FOLDERPATH);
            folderChecker(folder);
            databaseChecker(file);
            Scanner s = new Scanner(file);
            initList(s);
        } catch (JimException e) {
            System.out.println(LINES + "File is corrupted at Line " + (tasks.size()+1) + "!" +
                    System.lineSeparator() + LINES);
        } catch (FileNotFoundException e) {
            System.out.println(LINES + NO_FILE_MESSAGE + LINES);
        }
    }

    public static void folderChecker(File folder) {
        boolean result = folder.mkdir();
        System.out.println(LINES + "Checking for database folder..." + System.lineSeparator());
        if (result) {
            System.out.println("Folder not present...creating new folder!" + System.lineSeparator() + LINES);
        } else {
            System.out.println("Folder located!" + System.lineSeparator() + LINES);
        }
    }

    public static void databaseChecker(File file) {
        try {
            boolean result = file.createNewFile();
            System.out.println(LINES + "Checking for database..." + System.lineSeparator());
            if (result) {
                System.out.println("Database not present...creating new database!" + System.lineSeparator() + LINES);
            } else {
                System.out.println("Database located!" + System.lineSeparator() + LINES);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getUserInput() {
        System.out.print(" How can I help you: ");
        return sc.nextLine();
    }

    public static void genshinHelper() {
        int totalMats = 0;
        System.out.print(LINES + " How many Top Tier: ");
        String top = sc.nextLine();
        totalMats += Integer.parseInt(top)*TOP_LOW_CONVERSION_RATE;
        System.out.print(" How many High Tier: ");
        String high = sc.nextLine();
        totalMats += Integer.parseInt(high)*HIGH_LOW_CONVERSION_RATE;
        System.out.print(" How many Mid Tier: ");
        String mid = sc.nextLine();
        totalMats += Integer.parseInt(mid)*MID_LOW_CONVERSION_RATE;
        System.out.print(" How many Low Tier: ");
        String low = sc.nextLine();
        totalMats += Integer.parseInt(low);
        System.out.println(" Total low tier mats required: " + totalMats  + System.lineSeparator() + LINES);
    }

    //Executes next line command
    public static void commandExecute(String input) {
        try {
            if (input.toLowerCase().startsWith("todo") || input.toLowerCase().startsWith("deadline")
                    || input.toLowerCase().startsWith("event")) {
                addTask(input);
            } else if (input.toLowerCase().startsWith("done")) {
                markDone(input);
            } else if (input.equalsIgnoreCase("list")) {
                list();
            } else if (input.toLowerCase().startsWith("remove")) {
                removeTask(input);
            } else if (input.equalsIgnoreCase("echo")) {
                echo();
            } else if (input.equalsIgnoreCase("clear database")) {
                clearDatabase();
                System.out.println(LINES + CLEAR_DB_MESSAGE + LINES);
            } else if (input.toUpperCase().contains("BIRTHDAY")) {
                System.out.println(LINES + BIRTHDAY_MESSAGE + LINES);
            } else if (input.equalsIgnoreCase("help")) {
                System.out.println(LINES + HELP_MESSAGE + LINES);
            } else if (input.equalsIgnoreCase("genshin")) {
                genshinHelper();
            } else if (input.equalsIgnoreCase("bye")) {
                System.out.println(LINES + BYE_MESSAGE + LINES);
                System.exit(0);
            } else {
                throw new JimException();
            }
        } catch (JimException e) {
            System.out.println(LINES + INVALID_COMMAND + LINES);
        }
    }
    public static void main(String[] args) {
        greeting();
        initJim();
        while (true) {
            String userCommand = getUserInput();
            commandExecute(userCommand);
        }
    }
}