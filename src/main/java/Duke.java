import DukeUtility.FileWrite;
import DukeUtility.OwlException;
import DukeUtility.PrintManager;
import TypeOfTasks.Deadline;
import TypeOfTasks.Event;
import TypeOfTasks.Task;
import TypeOfTasks.Todo;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static final String INVALID_MESSAGE = "The command doesnt exist.....";
    public static final List<String> ONE_PART_COMMAND = Arrays.asList("list");
    public static final List<String> TWO_PART_COMMAND = Arrays.asList("todo", "done", "deadline", "event", "delete");
    public static final String DATA_SEPARATOR = " ~ ";
    public static final String APPEND_DONE = "1";
    public static final String APPEND_NOT_DONE = "0";
    public static final String DATA_DIRECTORY_PATH = "data";
    public static final String FULL_RELATIVE_MEMORY_PATH = "data/owlmemory";
    public static final String NO_DATA = "";

    public static void main(String[] args) {
        PrintManager.printWelcome();
        ArrayList<Task> tasks = readFile();
        int taskCount = tasks.size();
        
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();
        while(isNotBye(command)) {
            try {
                String[] inputs = command.split(" ", 2);
                int commandLength = inputs.length;
                if(isInvalidOnePartCmd(inputs, commandLength)) {
                    checkException(inputs);
                } else if (isInvalidTwoPartCmd(inputs, commandLength)) {
                    checkException(inputs);
                } else if (isDone(commandLength, inputs[0])) {
                    markCompletionOfTask(tasks, taskCount, inputs[1]);
                } else if (isList(commandLength, inputs[0])) {
                    listTask(tasks, taskCount);
                } else if (isTodo(commandLength, inputs[0])) {
                    taskCount = addTodo(tasks, taskCount, inputs);
                } else if (isDeadline(commandLength, inputs[0])) {
                    taskCount = addDeadline(tasks, taskCount, inputs);
                } else if (isEvent(commandLength, inputs[0])) {
                    taskCount = addEvent(tasks, taskCount, inputs);
                } else if (isDelete(commandLength, inputs[0])) {
                    taskCount = deleteTask(tasks, taskCount, inputs[1]);
                } else {
                    System.out.println(INVALID_MESSAGE);
                }
            } catch(OwlException owlException) {
                owlException.printErrorMsg();
            } catch(NumberFormatException numberFormatException) {
                System.out.println(numberFormatException.getMessage());
            }
            updateFile(tasks);
            command = in.nextLine().trim();
        }
        PrintManager.printBye();
    }

    private static boolean isDelete(int commandLength, String input) {
        return commandLength == 2 && input.equals("delete");
    }
    
    private static void updateFile(ArrayList<Task> tasks) {
        String dataPath = FULL_RELATIVE_MEMORY_PATH;
        try {
            int i = 0;
            wipeData(dataPath);
            for(Task task:tasks) {
                if(task.getTag().equals("T")) {
                    appendTodoData(dataPath, task);
                }
                if(task.getTag().equals("D")) {
                    appendEventDeadlineData(dataPath, task);
                }
                if(task.getTag().equals("E")) {
                    appendEventDeadlineData(dataPath, task);
                }
                i++;
            }
        } catch (IOException ioexception) {
                System.out.println("Something went wrong with writing into the file: " + ioexception.getMessage());
        }
    }

    private static void wipeData(String dataPath) throws IOException {
        FileWrite.writeToFile(dataPath, NO_DATA);
    }

    private static void appendEventDeadlineData(String dataPath, Task task) throws IOException {
        FileWrite.appendToFile(dataPath, task.getTag());
        FileWrite.appendToFile(dataPath, DATA_SEPARATOR);
        FileWrite.appendToFile(dataPath, task.getDescription());
        FileWrite.appendToFile(dataPath, DATA_SEPARATOR);
        FileWrite.appendToFile(dataPath, task.getInfo());
        FileWrite.appendToFile(dataPath, DATA_SEPARATOR);
        if (task.getStatus().equals("X")) {
            FileWrite.appendToFile(dataPath, APPEND_DONE);
        } else {
            FileWrite.appendToFile(dataPath, APPEND_NOT_DONE);
        }
        FileWrite.appendToFile(dataPath, System.lineSeparator());
    }

    private static void appendTodoData(String dataPath, Task task) throws IOException {
        FileWrite.appendToFile(dataPath, task.getTag());
        FileWrite.appendToFile(dataPath, DATA_SEPARATOR);
        FileWrite.appendToFile(dataPath, task.getDescription());
        FileWrite.appendToFile(dataPath, DATA_SEPARATOR);
        if (task.getStatus().equals("X")) {
            FileWrite.appendToFile(dataPath, APPEND_DONE);
        } else {
            FileWrite.appendToFile(dataPath, APPEND_NOT_DONE);
        }
        FileWrite.appendToFile(dataPath, System.lineSeparator());
    }


    private static ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            int taskCount = 0;
            File file = getFile();
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while(s.hasNext()) {
                String textLine = s.nextLine();
                String[] inputs = textLine.split(DATA_SEPARATOR);
                if(inputs[0].equals("T")) {
                    taskCount = loadTodo(tasks, taskCount, inputs);
                }
                if(inputs[0].equals("D")) {
                    taskCount = loadDeadline(tasks, taskCount, inputs);
                }
                if(inputs[0].equals("E")) {
                    taskCount = loadEvent(tasks, taskCount, inputs);
                }
            }
        } catch(FileNotFoundException fileNotFoundException) {
            System.out.println("File not found!");
        } catch(IOException ioexception) {
            System.out.println("Theres a problem with making a new file!");
        }
        return tasks;
    }

    private static int loadEvent(ArrayList<Task> tasks, int taskCount, String[] inputs) {
        tasks.add(new Event(inputs[1], inputs[2]));
        taskCount++;
        if(inputs[3].equals(APPEND_DONE)) {
            tasks.get(taskCount - 1).markDone();
        }
        return taskCount;
    }

    private static int loadDeadline(ArrayList<Task> tasks, int taskCount, String[] inputs) {
        tasks.add(new Deadline(inputs[1], inputs[2]));
        taskCount++;
        if(inputs[3].equals(APPEND_DONE)) {
            tasks.get(taskCount - 1).markDone();
        }
        return taskCount;
    }

    private static int loadTodo(ArrayList<Task> tasks, int taskCount, String[] inputs) {
        tasks.add(new Todo(inputs[1]));
        taskCount++;
        if(inputs[2].equals(APPEND_DONE)) {
            tasks.get(taskCount - 1).markDone();
        }
        return taskCount;
    }

    private static File getFile() throws IOException {
        File file = new File(FULL_RELATIVE_MEMORY_PATH);
        if(!file.exists()) {
            File makeNewDir = new File(DATA_DIRECTORY_PATH);
            if(makeNewDir.mkdir()) {
                System.out.println("Directory not found so I made a new directory!");
            }
            File NewFile = new File(FULL_RELATIVE_MEMORY_PATH);
            if(NewFile.createNewFile()) {
                System.out.println("File not found so I made a new file!");
            }
            return NewFile;
        }
        return file;
    }

    private static boolean isInvalidOnePartCmd(String[] inputs, int commandLength) {
        return commandLength > 1 && isOnePartCmd(inputs[0]);
    }
    
    private static boolean isNotBye(String command) {
        return !command.equals("bye");
    }

    private static boolean isInvalidTwoPartCmd(String[] inputs, int commandLength) {
        return commandLength == 1 && isTwoPartCmd(inputs[0]);
    }
    private static boolean isOnePartCmd(String s) {
        return ONE_PART_COMMAND.contains(s);
    }

    private static boolean isTwoPartCmd(String s) {
        return TWO_PART_COMMAND.contains(s);
    }
    private static void checkException(String[] inputs) throws OwlException {
        if(inputs[0].isEmpty()) {
            throw new OwlException("Command should not be blank!");
        }
        if(inputs[0].equals("todo")) {
            throw new OwlException("The description of todo cannot be empty!");
        }
        if(inputs[0].equals("done")) {
            throw new OwlException("The description of done cannot be empty!");
        }
        if(inputs[0].equals("event")) {
            throw new OwlException("The description of event cannot be empty!");
        }
        if(inputs[0].equals("deadline")) {
            throw new OwlException("The description of deadline cannot be empty!");
        }
        if(inputs[0].equals("list")) {
            throw new OwlException("list does not have description!");
        }
        if(inputs[0].equals("delete")) {
            throw new OwlException("The description of delete cannot be empty!");
        }
    }

    private static void markCompletionOfTask(ArrayList<Task> tasks, int taskCount, String s) throws OwlException {
        try {
            int taskNumber = Integer.parseInt(s);
            int taskIndex = taskNumber - 1;
            if (isInvalidTaskCount(taskCount, taskNumber)) {
                throw new OwlException("invalid task number");
            }
            if (tasks.get(taskIndex).isDone()) {
                throw new OwlException("Task already done!!");
            }
            tasks.get(taskIndex).markDone();
            PrintManager.printTaskCompletionMsg(taskNumber);
        } catch(NumberFormatException numberFormatException) {
            throw new NumberFormatException("You can only done a task number");
        }
    }

    private static int deleteTask(ArrayList<Task> tasks, int taskCount, String input) throws OwlException {
        try {
            int taskNumber = Integer.parseInt(input);
            int taskIndex = taskNumber - 1;
            if(isInvalidTaskCount(taskCount, taskNumber)) {
                throw new OwlException("invalid task number");
            }
            Task deletedTask = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            taskCount--;
            PrintManager.printDeletionMsg(taskCount, deletedTask);
        } catch(NumberFormatException numberFormatException) {
            throw new NumberFormatException("You can only delete a task number!!!");
        }
        return taskCount;
    }

    private static int addTask(int taskCount, String command) {
        taskCount++;
        PrintManager.printTaskCount(taskCount, command);
        return taskCount;
    }
    
    private static int addEvent(ArrayList<Task> tasks, int taskCount, String[] inputs) throws OwlException {
        String[] inputsAt = inputs[1].split(" /at ", 2);
        if (inputs[1].contains(" /at ") && !inputsAt[1].isEmpty()) {
            Event newEvent = new Event(inputsAt[0], inputsAt[1]);
            tasks.add(newEvent);
            taskCount = addTask(taskCount, ("[E] " + inputsAt[0] + "(at: " + inputsAt[1]) + ")");
            return taskCount;
        }
        throw new OwlException("Did not specify /at");
    }

    private static int addDeadline(ArrayList<Task> tasks, int taskCount, String[] inputs) throws OwlException {
        String[] inputsBy = inputs[1].split(" /by ", 2);
        if (inputs[1].contains(" /by ") && !inputsBy[1].isEmpty()) {
            Deadline newDeadline = new Deadline(inputsBy[0], inputsBy[1]);
            tasks.add(newDeadline);
            taskCount = addTask(taskCount, ("[D] " + inputsBy[0] + "(by: " + inputsBy[1]) + ")");
            return taskCount;
        }
        throw new OwlException("Did not specify /by");
    }

    private static int addTodo(ArrayList<Task> tasks, int taskCount, String[] inputs) {
        Todo newTodo = new Todo(inputs[1]);
        tasks.add(newTodo);
        taskCount = addTask(taskCount, ("[T] " + inputs[1]));
        return taskCount;
    }

    private static boolean isInvalidTaskCount(int taskCount, int taskNumber) {
        return taskNumber <= 0 || taskNumber > taskCount;
    }

    private static boolean isSecondPartInvalid(String trim) {
        return trim.isEmpty();
    }

    private static boolean isDone(int commandLength, String s) {
        return commandLength == 2 && s.equals("done");
    }

    private static boolean isEvent(int commandLength, String s) {
        return commandLength == 2 && s.equals("event");
    }

    private static boolean isDeadline(int commandLength, String s) {
        return commandLength == 2 && s.equals("deadline");
    }

    private static boolean isTodo(int commandLength, String s) {
        return commandLength == 2 && s.equals("todo");
    }

    private static boolean isList(int commandLength, String s) {
        return commandLength == 1 && s.equals("list");
    }
    

    private static void listTask(ArrayList<Task> tasks, int taskCount) {
        if(taskCount > 0) {
            int listIndex = 1;
            PrintManager.printListingMsg();
            for(Task task: tasks) {
                task.printList(task,listIndex);
                listIndex++;
            }
            PrintManager.printLine();
            return;
        }
        System.out.println("There are no task in the list!!!");
    }

}

