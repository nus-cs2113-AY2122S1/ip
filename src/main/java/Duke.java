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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;  // Import the IOException class to handle errors


public class Duke {
    public static final String INVALID_MESSAGE = "The command doesnt exist.....";
    public static final String LISTING_MESSAGE = "This are all the things I've remembered for you:";
    public static final int MAX_TASK_LENGTH = 100;
    public static final List ONE_PART_COMMAND = Arrays.asList("list");
    public static final List TWO_PART_COMMAND = Arrays.asList("todo", "done", "deadline", "event");
    public static void main(String[] args) {
        PrintManager.printWelcome();
        Scanner in = new Scanner(System.in);
        int taskCount = 0;
        Task[] tasks = readFile();
        for(Task task:tasks) {
            if(task == null) {
                break;
            }
            taskCount++;
        }
        
        
        String command = in.nextLine();
        while(isNotBye(command)) {
            try {
                String[] inputs = command.split(" ", 2);
                int commandLength = inputs.length;
                //if 2 part command but the second part is blank. done <blank> or event <blank> its invalid command
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
                } else {
                    System.out.println(INVALID_MESSAGE);
                }
            } catch(OwlException oe) {
                oe.printErrorMsg();
            } catch(NumberFormatException e) {
                System.out.println("You can only done a task number!!!");
            }
            updateFile(tasks);
            command = in.nextLine();
        }
        PrintManager.printBye();
    }

    private static void updateFile(Task[] tasks) {
        String dataPath = "./ip/text-ui-test/owldata.txt";
        int i = 0;
        try {
            FileWrite.writeToFile(dataPath, "");
            while (tasks[i] != null) {
                Task task = tasks[i];
                if(task.getTag().equals("T")) {
                    FileWrite.appendToFile(dataPath, task.getTag());
                    FileWrite.appendToFile(dataPath, " ~ ");
                    FileWrite.appendToFile(dataPath, task.getDescription());
                    FileWrite.appendToFile(dataPath, " ~ ");
                    if (task.getStatus().equals("X")) {
                        FileWrite.appendToFile(dataPath, "1");
                    } else {
                        FileWrite.appendToFile(dataPath, "0");
                    }
                    FileWrite.appendToFile(dataPath, System.lineSeparator());
                }
                if(task.getTag().equals("D")) {
                    FileWrite.appendToFile(dataPath, task.getTag());
                    FileWrite.appendToFile(dataPath, " ~ ");
                    FileWrite.appendToFile(dataPath, task.getDescription());
                    FileWrite.appendToFile(dataPath, " ~ ");
                    FileWrite.appendToFile(dataPath, task.getInfo());
                    FileWrite.appendToFile(dataPath, " ~ ");
                    if (task.getStatus().equals("X")) {
                        FileWrite.appendToFile(dataPath, "1");
                    } else {
                        FileWrite.appendToFile(dataPath, "0");
                    }
                    FileWrite.appendToFile(dataPath, System.lineSeparator());
                }
                if(task.getTag().equals("E")) {
                    FileWrite.appendToFile(dataPath, task.getTag());
                    FileWrite.appendToFile(dataPath, " ~ ");
                    FileWrite.appendToFile(dataPath, task.getDescription());
                    FileWrite.appendToFile(dataPath, " ~ ");
                    FileWrite.appendToFile(dataPath, task.getInfo());
                    FileWrite.appendToFile(dataPath, " ~ ");
                    if (task.getStatus().equals("X")) {
                        FileWrite.appendToFile(dataPath, "1");
                    } else {
                        FileWrite.appendToFile(dataPath, "0");
                    }
                    FileWrite.appendToFile(dataPath, System.lineSeparator());
                }
                i++;
            }
        } catch (IOException ioe) {
                System.out.println("Something went wrong with writing into the file: " + ioe.getMessage());
        }
    }
    
    
    
    private static Task[] readFile() {
        Task[] tasks = new Task[MAX_TASK_LENGTH];
        try {
            int taskCount = 0;
            File directory = new File("./ip/text-ui-test/owldata.txt");
            Scanner s = new Scanner(directory); // create a Scanner using the File as the source
            while(s.hasNext()) {
                String textLine = s.nextLine();
                String[] inputs = textLine.split(" ~ ");
                if(inputs[0].equals("T")) {
                    tasks[taskCount] = new Todo(inputs[1]);
                    taskCount++;
                    if(inputs[2].equals("1")) {
                        tasks[taskCount - 1].markDone();
                    }
                }
                if(inputs[0].equals("D")) {
                    tasks[taskCount] = new Deadline(inputs[1],inputs[2]);
                    taskCount++;
                    if(inputs[3].equals("1")) {
                        tasks[taskCount - 1].markDone();
                    }
                }
                if(inputs[0].equals("E")) {
                    tasks[taskCount] = new Event(inputs[1],inputs[2]);
                    taskCount++;
                    if(inputs[3].equals("1")) {
                        tasks[taskCount - 1].markDone();
                    }
                }
            }

            return tasks;
        } catch(FileNotFoundException fnfe) {
            System.out.println("FILE NOT FOUND BODOH");
            return new Task[MAX_TASK_LENGTH];
        }
    }
    private static boolean isInvalidOnePartCmd(String[] inputs, int commandLength) {
        return commandLength > 1 && isOnePartCmd(inputs[0]);
    }
    
    private static boolean isNotBye(String command) {
        return !command.equals("bye");
    }

    private static boolean isInvalidTwoPartCmd(String[] inputs, int commandLength) {
        if(commandLength == 1 && isTwoPartCmd(inputs[0])) {
            return true;
        }
        return commandLength == 2 && isSecondPartInvalid(inputs[1].trim());
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
    }

    private static void markCompletionOfTask(Task[] tasks, int taskCount, String s) throws OwlException {
        int taskNumber = Integer.parseInt(s);
        int taskIndex = taskNumber - 1;
        if(!isValidTaskCount(taskCount, taskNumber)) {
            throw new OwlException("invalid task number");
        }
        if(tasks[taskIndex].isDone()) {
            throw new OwlException("Task already done!!");
        }
        tasks[taskIndex].markDone();
        PrintManager.printTaskCompletionMsg(taskNumber);
    }
    private static int addTask(int taskCount, String command) {
        taskCount++;
        PrintManager.printTaskCount(taskCount, command);
        return taskCount;
    }
    private static int addEvent(Task[] tasks, int taskCount, String[] inputs) throws OwlException {
        String[] inputsAt = inputs[1].split(" /at ", 2);
        if (inputs[1].contains(" /at ") && !inputsAt[1].isEmpty()) {
            tasks[taskCount] = new Event(inputsAt[0], inputsAt[1]);
            taskCount = addTask(taskCount, ("[E] " + inputsAt[0] + "(at: " + inputsAt[1]) + ")");
            return taskCount;
        }
        throw new OwlException("Did not specify /at");
    }

    private static int addDeadline(Task[] tasks, int taskCount, String[] inputs) throws OwlException {
        String[] inputsBy = inputs[1].split(" /by ", 2);
        if (inputs[1].contains(" /by ") && !inputsBy[1].isEmpty()) {
            tasks[taskCount] = new Deadline(inputsBy[0], inputsBy[1]);
            taskCount = addTask(taskCount, ("[D] " + inputsBy[0] + "(by: " + inputsBy[1]) + ")");
            return taskCount;
        }
        throw new OwlException("Did not specify /by");
    }

    private static int addTodo(Task[] tasks, int taskCount, String[] inputs) {
        tasks[taskCount] = new Todo(inputs[1]);
        taskCount = addTask(taskCount, ("[T] " + inputs[1]));
        return taskCount;
    }

    private static boolean isValidTaskCount(int taskCount, int taskNumber) {
        return taskNumber > 0 && taskNumber <= taskCount;
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
    

    private static void listTask(Task[] tasks, int taskCount) {
        if(taskCount > 0) {
            Task[] taskList = Arrays.copyOf(tasks, taskCount);
            int taskIndex = 0;
            int index = 1;
            PrintManager.printLine();
            System.out.println(LISTING_MESSAGE);
            PrintManager.printLine();
            for(Task task: taskList) {
                Task theTask = taskList[taskIndex];
                theTask.printList(theTask,index);
                index++;
                taskIndex++;
            }
            PrintManager.printLine();
            return;
        }
        System.out.println("There are no task in the list!!");
    }

}

