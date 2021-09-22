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
    private static final String FILE_PATH = "data/jim.txt";
    private static final String FOLDER_PATH = "data";
    //Scanner
    private static final Scanner sc = new Scanner(System.in);
    //The sacred texts
    protected static Messages messages;

    //Prints greeting message
    public static void greeting() {
        messages.showWelcomeMessage();
    }

    //Begins echo function
    public static void echo() {
        messages.showEchoMessage();
        boolean echoState = true;
        while (echoState) {
            String s = sc.nextLine();
            if (s.equalsIgnoreCase("quit")) {
                messages.showEchoQuitMessage();
                echoState = false;
            } else {
                String output = " " + s  + System.lineSeparator();
                messages.showLines();
                System.out.println(output);
                messages.showLines();
            }
        }
    }

    //list function
    public static void list() {
        if (tasks.size() == 0) {
            messages.showEmptyListMessage();
        } else {
            messages.showLines();
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + "." + tasks.get(i - 1));
            }
            messages.showLines();
        }
    }

    //Removes a specified task
    public static void removeTask(String input) {
        try {
            int index = Integer.parseInt(input.substring(7));
            //given task does not exist in list
            if (index > tasks.size()) {
                messages.showNoSuchTaskMessage();
            } else {
                Task thisTask = tasks.get(index - 1);
                messages.showDeleteTaskMessage(thisTask, (tasks.size() - 1));
                tasks.remove(thisTask);
                updateDatabase();
            }
        } catch (IndexOutOfBoundsException e) {
            messages.showNothingToRemoveMessage();
        } catch (NumberFormatException e) {
            messages.showRemoveCommandErrorMessage();
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
                messages.showNoSuchTaskMessage();
            } else if (tasks.get(index - 1).getStatusIcon().equals("X")) {
                messages.showTaskAlreadyDoneMessage();
            } else {
                tasks.get(index - 1).markAsDone();
                messages.showTaskCompleteMessage(tasks.get(index - 1));
                updateDatabase();
            }
        } catch (StringIndexOutOfBoundsException e) {
            messages.showNoTaskSpecifiedMessage();
        } catch (NumberFormatException e) {
            messages.showDoneCommandErrorMessage();
        }
    }

    //Adds task to tasks
    public static void addTask(String input) {
        try {
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
        } catch (IndexOutOfBoundsException e) {
            //find out how motherfkin catch works
        }
    }
    private static void addTodo(String input) {
        try {
            tasks.add(new Todo(input.trim().substring(5)));
            int taskIndex = tasks.size() - 1;
            printTaskAcknowledgement(tasks.get(taskIndex));
        } catch (StringIndexOutOfBoundsException e){
            messages.showMissingTaskDescriptionMessage();
        }
    }
    private static void addDeadline(String input) {
        try {
            int slashIndex = input.indexOf("/by");
            String task = input.substring(9, slashIndex).trim();
            String dueDate = input.substring(slashIndex + 3).trim();
            if (task.length() <= 0) {
                messages.showMissingTaskDescriptionMessage();
            } else if (!input.contains("/by")) {
                messages.showMissingByMessage();
            } else if (dueDate.length() <= 0) {
                messages.showMissingTaskDeadlineMessage();
            } else {
                tasks.add(new Deadline(task, dueDate));
                int taskIndex = tasks.size() - 1;
                printTaskAcknowledgement(tasks.get(taskIndex));
            }
        } catch (StringIndexOutOfBoundsException e) {
            messages.showMissingTaskOrDeadlineMessage();
        }
    }
    private static void addEvent(String input) {
        try {
            int slashIndex = input.indexOf("/at");
            String task = input.substring(6, slashIndex).trim();
            String timeRange = input.substring(slashIndex + 3).trim();
            if (task.length() <= 0) {
                messages.showMissingTaskDescriptionMessage();
            } else if (!input.contains("/at")) {
                messages.showMissingAtMessage();
            } else if (timeRange.length() <= 0) {
                messages.showMissingEventTimeRangeMessage();
            } else {
                tasks.add(new Event(task, timeRange));
                int taskIndex = tasks.size() - 1;
                printTaskAcknowledgement(tasks.get(taskIndex));
            }
        } catch (StringIndexOutOfBoundsException e) {
            messages.showMissingTaskOrTimeRangeMessage();
        }
    }
    private static void printTaskAcknowledgement(Task task) {
        messages.showTaskAddedMessage(task, tasks.size());
    }

    public static void appendDatabase(String type, String input, String isDone) {
        //append new tasks to database
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
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
            messages.showNoDatabaseFileMessage();
        }
    }

    public static void clearDatabase() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            messages.showNoDatabaseFileMessage();
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
            tasks = new ArrayList<>();
            File file = new File(FILE_PATH);
            File folder = new File(FOLDER_PATH);
            messages = new Messages();
            folderChecker(folder);
            databaseChecker(file);
            Scanner s = new Scanner(file);
            initList(s);
        } catch (JimException e) {
            messages.showCorruptedDatabaseFileMessage(tasks.size() + 1);
        } catch (FileNotFoundException e) {
            messages.showNoDatabaseFileMessage();
        }
    }

    public static void folderChecker(File folder) {
        boolean result = folder.mkdir();
        messages.showCheckFolderMessage();
        if (result) {
            messages.showCreateMissingFolderMessage();
        } else {
            messages.showFolderFoundMessage();
        }
    }

    public static void databaseChecker(File file) {
        try {
            boolean result = file.createNewFile();
            messages.showCheckDatabaseFileMessage();
            if (result) {
                 messages.showCreateMissingDatabaseFileMessage();
            } else {
                messages.showDatabaseFileFoundMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getUserInput() {
        messages.showUserInputMessage();
        return sc.nextLine();
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
                tasks.clear();
                messages.showClearDatabaseMessage();
            } else if (input.toUpperCase().contains("BIRTHDAY")) {
                messages.showBirthdayMessage();
            } else if (input.equalsIgnoreCase("help")) {
                messages.showHelpMessage();
            } else if (input.equalsIgnoreCase("bye")) {
                messages.showExitMessage();
                System.exit(0);
            } else {
                throw new JimException();
            }
        } catch (JimException e) {
            messages.showInvalidCommandMessage();
        }
    }
    public static void main(String[] args) {
        initJim();
        greeting();
        while (true) {
            String userCommand = getUserInput();
            commandExecute(userCommand);
        }
    }
}