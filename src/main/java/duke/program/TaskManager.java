package duke.program;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidIndexException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;


public class TaskManager {
    private static final String FILE_PATH = "./data/duke.txt";
    private static final String FOLDER_PATH = "./data";

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";

    private static final String SEPARATOR_SPACE = " ";
    private static final String SEPARATOR_DOT = ".";
    private static final String SEPARATOR_DOT_WITH_ESCAPE = "\\.";


    private static final String TASK_TYPE_ICON_TODO = "T";
    private static final String TASK_TYPE_ICON_DEADLINE = "D";
    private static final String TASK_TYPE_ICON_EVENT = "E";
    private static final String ICON_DONE = "X";

    private static final int LINE_LENGTH = 40;

    private ArrayList<Task> tasks;
    private int taskCount;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }
//
//    public static void printLine() {
//        for (int i = 0; i < LINE_LENGTH; i++) {
//            System.out.print("_");
//        }
//        System.out.println("");
//    }
//
//    private void writeToFile(ArrayList<Task> tasks) throws IOException {
//        FileWriter fw = new FileWriter(FILE_PATH);
//        for (int i = 0; i < taskCount; i++) {
//            String taskType = tasks.get(i).getType();
//            String taskStatus = tasks.get(i).getStatusIcon();
//            String taskDescription = tasks.get(i).getDescription();
//
//            if (taskType.equals(TASK_TYPE_ICON_TODO)) {
//                fw.write(taskType + SEPARATOR_DOT + taskStatus + SEPARATOR_DOT + taskDescription);
//            } else if (taskType.equals(TASK_TYPE_ICON_DEADLINE)) {
//                String byTime = tasks.get(i).getByDateTime();
//                fw.write(taskType + SEPARATOR_DOT + taskStatus + SEPARATOR_DOT + taskDescription + SEPARATOR_DOT + byTime);
//            } else if (taskType.equals((TASK_TYPE_ICON_EVENT))) {
//                String atTime = tasks.get(i).getStartAndEndTime();
//                fw.write(taskType + SEPARATOR_DOT + taskStatus + SEPARATOR_DOT + taskDescription + SEPARATOR_DOT + atTime);
//            } else {
//                printGenericErrorMessage();
//            }
//
//            fw.write(System.lineSeparator());
//        }
//
//        fw.close();
//    }

//    private void readFileIntoTasks(ArrayList<Task> tasks) throws IOException {
//        File f = new File(FILE_PATH);
//        File folder = new File(FOLDER_PATH);
//
//        folder.mkdir();
//        f.createNewFile();
//
//        Scanner s = new Scanner(f);
//
//        while (s.hasNext()) {
//            String[] taskArgs = s.nextLine().split(SEPARATOR_DOT_WITH_ESCAPE);
//            String taskType = taskArgs[0];
//            boolean taskStatus = taskArgs[1].equals(ICON_DONE);
//            String taskDescription = taskArgs[2];
//
//            if (taskType.equals(TASK_TYPE_ICON_TODO)) {
//                tasks.add(new ToDo(taskDescription, taskStatus));
//            } else if (taskType.equals(TASK_TYPE_ICON_DEADLINE)) {
//                String taskByTime = taskArgs[3];
//                tasks.add(new Deadline(taskDescription, taskByTime, taskStatus));
//            } else if (taskType.equals(TASK_TYPE_ICON_EVENT)) {
//                String taskAtTime = taskArgs[3];
//                tasks.add(new Event(taskDescription, taskAtTime, taskStatus));
//            } else {
//                printGenericErrorMessage();
//            }
//
//            taskCount++;
//        }
//
//    }

//    public void parseUserInput() throws IOException {
//        Scanner in = new Scanner(System.in);
//        readFileIntoTasks(tasks);
//
//        while (true) {
//            String line = in.nextLine();
//            if (line.equals(COMMAND_BYE)) {
//                break;
//            }
//            printLine();
//            String[] lineArgs = line.split(SEPARATOR_SPACE);
//            String command = lineArgs[0];
//            if (command.equals(COMMAND_LIST)) {
//                printTaskList(taskCount, tasks);
//            } else if (command.equals(COMMAND_DONE)) {
//                markAsDoneWithExceptionHandling(lineArgs);
//            } else if (command.equals(COMMAND_DELETE)) {
//                deleteTaskWithExceptionHandling(lineArgs);
//            } else if (command.equals(COMMAND_TODO)) {
//                addNewTodoWithExceptionHandling(line);
//            } else if (command.equals(COMMAND_DEADLINE)) {
//                addNewDeadlineWithExceptionHandling(line);
//            } else if (command.equals(COMMAND_EVENT)) {
//                addNewEventWithExceptionHandling(line);
//            } else {
//                printInvalidCommandMessage();
//            }
//
//            writeToFile(tasks);
//            printLine();
//        }
//    }


}
