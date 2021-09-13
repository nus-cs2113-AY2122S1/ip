package duke;

import exception.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";

    private static final String SEPARATOR_SPACE = " ";
    private static final String SEPARATOR_BY = " /by ";
    private static final String SEPARATOR_AT = " /at ";
    private static final String SEPARATOR_VERTICAL_LINE = " \\| ";
    private static final String SEPARATOR_WORD_FILE = " | ";

    private static final int LENGTH_TODO = 4;
    private static final int LENGTH_DEADLINE = 8;
    private static final int LENGTH_EVENT = 5;

    private static final String TASK_IS_DONE = "1";
    private static final String TASK_IS_NOT_DONE = "0";

    private static final String DATA_FILEPATH = "data/duke.txt";

    private static final String DIVIDER = "    ____________________________________________________________";

    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    private int getTasksSize() {
        return tasks.size();
    }

    public void loadSavedData() throws FileNotFoundException {
        File f = new File(DATA_FILEPATH);
        Scanner s = new Scanner(f);

        while(s.hasNext()) {
            loadSavedInputToTasks(s.nextLine());
        }
    }

    private void loadSavedInputToTasks(String input) {
        String[] inputs = input.split(SEPARATOR_VERTICAL_LINE);
        String taskType = inputs[0];
        String isTaskDone = inputs[1];
        String taskDescription = inputs[2];

        if (taskType.equals("T")) {
            addTodoTaskWithException(COMMAND_TODO + " " + taskDescription);
        } else if (taskType.equals("D")) {
            String taskDeadline = inputs[3];
            addDeadlineTaskWithException(COMMAND_DEADLINE + " " + taskDescription
                    + SEPARATOR_BY + taskDeadline);
        } else {
            String eventTime = inputs[3];
            addEventTaskWithException(COMMAND_EVENT + " " + taskDescription
                    + SEPARATOR_AT + eventTime);
        }

        if (isTaskDone.equals(TASK_IS_DONE)) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    public void processUserInput() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String userInput = sc.nextLine();
            if (userInput.equals(COMMAND_BYE)) {
                break;
            }

            if (userInput.equals(COMMAND_LIST)) {
                printAllTasks();
            } else if (userInput.startsWith(COMMAND_DONE)) {
                String[] inputs = userInput.split(SEPARATOR_SPACE);
                int taskDoneNumber = Integer.parseInt(inputs[1]);

                setTaskDoneWithException(taskDoneNumber - 1);
            } else if (userInput.startsWith(COMMAND_DELETE)) {
                String[] inputs = userInput.split(SEPARATOR_SPACE);
                int taskDeleteNumber = Integer.parseInt(inputs[1]);

                deleteTaskWithException(taskDeleteNumber - 1);
            }
            else {
                try {
                    addTask(userInput);
                } catch (WrongCommandException e) {
                    printHorizontalLine();
                    System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    printHorizontalLine();
                }

            }
        }
    }

    private void deleteTask(int taskIndex) throws IndexTooSmallException, IndexTooBigException {
        if(taskIndex < 0) {
            throw new IndexTooSmallException();
        }
        if(taskIndex > getTasksSize() - 1){
            throw new IndexTooBigException();
        }

        printDeleteTaskMessage(taskIndex);
        tasks.remove(taskIndex);
    }

    private void deleteTaskWithException(int taskIndex) {
        try {
            deleteTask(taskIndex);
        } catch (IndexTooSmallException e) {
            printTaskIndexTooSmallMessage();
        } catch (IndexTooBigException e) {
            printTaskIndexTooBigMessage();
        }

    }

    private void addTask(String taskInput) throws WrongCommandException {
        if (taskInput.startsWith(COMMAND_TODO)) {
            addTodoTaskWithException(taskInput);
        } else if (taskInput.startsWith(COMMAND_DEADLINE)) {
            addDeadlineTaskWithException(taskInput);
        } else if(taskInput.startsWith(COMMAND_EVENT)) {
            addEventTaskWithException(taskInput);
        } else {
            throw new WrongCommandException();
        }
        saveTasksToDiskWithException();
        printAddTaskMessage();
    }

    private void saveTasksToDisk() throws IOException {
        FileWriter fw = new FileWriter(DATA_FILEPATH);
        for(Task task : tasks) {
            String inputToWrite = "";
            if (task instanceof Todo) {
                inputToWrite += "T";
            } else if (task instanceof Deadline) {
                inputToWrite += "D";
            } else {
                inputToWrite += "E";
            }

            inputToWrite += SEPARATOR_WORD_FILE
                    + (task.getIsDone() ? TASK_IS_DONE : TASK_IS_NOT_DONE)
                    + SEPARATOR_WORD_FILE + task.getTaskName();

            if (task instanceof Deadline) {
                inputToWrite += SEPARATOR_WORD_FILE;
                inputToWrite += ((Deadline) task).getBy();
            } else if (task instanceof Event) {
                inputToWrite += SEPARATOR_WORD_FILE;
                inputToWrite += ((Event) task).getAt();
            }

            inputToWrite += "\n";

            fw.write(inputToWrite);
        }
        fw.close();
    }

    private void saveTasksToDiskWithException() {
        try {
            saveTasksToDisk();
        } catch (IOException e) {
            System.out.println("Failed to save new data to the disk");
        }
    }


    private void addTodoTaskWithException(String taskInput) {
        try {
            addTodoTask(taskInput);
        } catch (EmptyDescriptionException e) {
            printHorizontalLine();
            System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
            printHorizontalLine();
        }
    }

    private void addTodoTask(String taskInput) throws EmptyDescriptionException {
        String taskName = taskInput.substring(LENGTH_TODO).trim();
        if (taskName.length() == 0) {
            throw new EmptyDescriptionException();
        }
        tasks.add(new Todo(taskName));
    }

    private void addDeadlineTask(String taskInput) throws EmptyDescriptionException, MissingInformationException{
        String taskDescriptionAndDeadline = taskInput.substring(LENGTH_DEADLINE).trim();
        if (taskDescriptionAndDeadline.length() == 0) {
            throw new EmptyDescriptionException();
        }

        String[] taskDescriptionAndDeadlineArray = taskDescriptionAndDeadline.split(SEPARATOR_BY);
        if (taskDescriptionAndDeadlineArray.length == 1) {
            throw new MissingInformationException();
        }

        String taskDescription = taskDescriptionAndDeadlineArray[0];
        String deadline = taskDescriptionAndDeadlineArray[1];
        tasks.add(new Deadline(taskDescription, deadline));
    }

    private void addDeadlineTaskWithException(String taskInput) {
        try {
            addDeadlineTask(taskInput);
        } catch (EmptyDescriptionException e) {
            printHorizontalLine();
            System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
            printHorizontalLine();
        } catch (MissingInformationException e) {
            printHorizontalLine();
            System.out.println("     ☹ OOPS!!! Please follow this deadline format: {task_description} /by {deadline}");
            printHorizontalLine();
        }
    }

    private void addEventTask(String taskInput) throws EmptyDescriptionException, MissingInformationException {
        String taskDescriptionAndStartTime = taskInput.substring(LENGTH_EVENT).trim();
        if (taskDescriptionAndStartTime.length() == 0) {
            throw new EmptyDescriptionException();
        }

        String[] taskDescriptionAndStartTimeArray = taskDescriptionAndStartTime.split(SEPARATOR_AT);
        if (taskDescriptionAndStartTimeArray.length == 1) {
            throw new MissingInformationException();
        }

        String taskDescription = taskDescriptionAndStartTimeArray[0];
        String startTime = taskDescriptionAndStartTimeArray[1];
        tasks.add(new Event(taskDescription, startTime));
    }

    private void addEventTaskWithException(String taskInput) {
        try {
            addEventTask(taskInput);
        } catch (EmptyDescriptionException e) {
            printHorizontalLine();
            System.out.println("     ☹ OOPS!!! The description of an event cannot be empty.");
            printHorizontalLine();
        } catch (MissingInformationException e) {
            printHorizontalLine();
            System.out.println("     ☹ OOPS!!! Please follow this deadline format: " +
                    "{event_description} /at {date/time}");
            printHorizontalLine();
        }
    }

    private void setTaskDone(int taskIndex) throws IndexTooSmallException, IndexTooBigException {
        if (taskIndex < 0) {
            throw new IndexTooSmallException();
        }
        if (taskIndex > getTasksSize() - 1){
            throw new IndexTooBigException();
        }
        tasks.get(taskIndex).markAsDone();
        saveTasksToDiskWithException();
        printMarkAsDoneMessage(taskIndex);
    }

    private void setTaskDoneWithException(int taskIndex) {
        try {
            setTaskDone(taskIndex);
        } catch (IndexTooSmallException e) {
            printTaskIndexTooSmallMessage();
        } catch (IndexTooBigException e) {
            printTaskIndexTooBigMessage();
        }
    }

    private void printAddTaskMessage() {
        printHorizontalLine();
        System.out.println("     Got it. I've added this task:\n"
                + "      " + tasks.get(getTasksSize() - 1)
                + "\n     Now you have " + getTasksSize()
                + (getTasksSize() == 1? " task" : " tasks")
                + " in the list.");
        printHorizontalLine();
    }

    private void printAllTasks() {
        printHorizontalLine();
        for(int i = 0; i < getTasksSize(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        printHorizontalLine();
    }

    private void printTaskIndexTooSmallMessage() {
        printHorizontalLine();
        System.out.println("     Please enter a valid task number!");
        printHorizontalLine();
    }

    private void printTaskIndexTooBigMessage() {
        printHorizontalLine();
        System.out.println("     There is only " + getTasksSize()
                + " item(s) in the list!");
        printHorizontalLine();
    }

    private void printMarkAsDoneMessage(int taskIndex) {
        printHorizontalLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + tasks.get(taskIndex));
        printHorizontalLine();
    }

    private void printDeleteTaskMessage(int taskIndex) {
        printHorizontalLine();
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       " + tasks.get(taskIndex));
        System.out.println("     Now you have " + (getTasksSize() - 1)
                + (getTasksSize() - 1 <= 1? " task" : " tasks")
                + " in the list.");
        printHorizontalLine();
    }

    private void printHorizontalLine() {
        System.out.println(DIVIDER);
    }
}
