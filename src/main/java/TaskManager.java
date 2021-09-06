import java.util.Scanner;

public class TaskManager {
    private static final int MAX_SIZE = 100;

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";

    private static final String SEPARATOR_SPACE = " ";
    private static final String SEPARATOR_BY = " /by ";
    private static final String SEPARATOR_AT = " /at ";

    private static final int LENGTH_TODO = 4;
    private static final int LENGTH_DEADLINE = 8;
    private static final int LENGTH_EVENT = 5;

    private static final String DIVIDER = "    ____________________________________________________________";

    private Task[] tasks;
    private int numberOfTasks;

    public TaskManager() {
        this.tasks = new Task[MAX_SIZE];
        this.numberOfTasks = 0;
    }

    public void processUserInput() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String userInput = sc.nextLine();
            if(userInput.equals(COMMAND_BYE)) {
                break;
            }

            if(userInput.equals(COMMAND_LIST)) {
                printAllTasks();
            } else if (userInput.startsWith(COMMAND_DONE)) {
                String[] inputs = userInput.split(SEPARATOR_SPACE);
                int taskDoneNumber = Integer.parseInt(inputs[1]);

                setTaskDoneWithException(taskDoneNumber - 1);
            } else {
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

    private void addTask(String taskInput) throws WrongCommandException {
        if(taskInput.startsWith(COMMAND_TODO)) {
            addTodoTaskWithException(taskInput);
        } else if (taskInput.startsWith(COMMAND_DEADLINE)) {
            addDeadlineTaskWithException(taskInput);
        } else if(taskInput.startsWith(COMMAND_EVENT)) {
            addEventTaskWithException(taskInput);
        } else {
            throw new WrongCommandException();
        }
    }


    private void addTodoTaskWithException(String taskInput) {
        try {
            addTodoTask(taskInput);
            printAddTaskMessage();
        } catch (EmptyDescriptionException e) {
            printHorizontalLine();
            System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
            printHorizontalLine();
        }
    }

    private void addTodoTask(String taskInput) throws EmptyDescriptionException {
        String taskName = taskInput.substring(LENGTH_TODO).trim();
        if(taskName.length() == 0) {
            throw new EmptyDescriptionException();
        }
        tasks[numberOfTasks] = new Todo(taskName);
        numberOfTasks++;
    }

    private void addDeadlineTask(String taskInput) throws EmptyDescriptionException, MissingInformationException {
        String taskDescriptionAndDeadline = taskInput.substring(LENGTH_DEADLINE).trim();
        if(taskDescriptionAndDeadline.length() == 0) {
            throw new EmptyDescriptionException();
        }

        String[] taskDescriptionAndDeadlineArray = taskDescriptionAndDeadline.split(SEPARATOR_BY);
        if(taskDescriptionAndDeadlineArray.length == 1) {
            throw new MissingInformationException();
        }

        String taskDescription = taskDescriptionAndDeadlineArray[0];
        String deadline = taskDescriptionAndDeadlineArray[1];
        tasks[numberOfTasks] = new Deadline(taskDescription, deadline);
        numberOfTasks++;
    }

    private void addDeadlineTaskWithException(String taskInput) {
        try {
            addDeadlineTask(taskInput);
            printAddTaskMessage();
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

    private void addEventTask(String taskInput) throws EmptyDescriptionException, MissingInformationException{
        String taskDescriptionAndStartTime = taskInput.substring(LENGTH_EVENT).trim();
        if(taskDescriptionAndStartTime.length() == 0) {
            throw new EmptyDescriptionException();
        }

        String[] taskDescriptionAndStartTimeArray = taskDescriptionAndStartTime.split(SEPARATOR_AT);
        if(taskDescriptionAndStartTimeArray.length == 1) {
            throw new MissingInformationException();
        }

        String taskDescription = taskDescriptionAndStartTimeArray[0];
        String deadline = taskDescriptionAndStartTimeArray[1];
        tasks[numberOfTasks] = new Event(taskDescription, deadline);
        numberOfTasks++;
    }

    private void addEventTaskWithException(String taskInput) {
        try {
            addEventTask(taskInput);
            printAddTaskMessage();
        } catch (EmptyDescriptionException e) {
            printHorizontalLine();
            System.out.println("     ☹ OOPS!!! The description of an event cannot be empty.");
            printHorizontalLine();
        } catch (MissingInformationException e) {
            printHorizontalLine();
            System.out.println("     ☹ OOPS!!! Please follow this deadline format: {event_description} /at {date/time}");
            printHorizontalLine();
        }
    }

    private void setTaskDone(int taskIndex) throws IndexTooSmallException, IndexTooBigException{
        if(taskIndex < 0) {
            throw new IndexTooSmallException();
        }
        if(taskIndex > numberOfTasks - 1){
            throw new IndexTooBigException();
        }
        tasks[taskIndex].markAsDone();
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
                + "      " + tasks[numberOfTasks - 1]
                + "\n     Now you have " + numberOfTasks
                + (numberOfTasks == 1? " task" : " tasks")
                + " in the list.");
        printHorizontalLine();
    }

    private void printAllTasks() {
        printHorizontalLine();
        for(int i = 0; i < numberOfTasks; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i]);
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
        System.out.println("     There is only " + numberOfTasks
                + " item(s) in the list!");
        printHorizontalLine();
    }

    private void printMarkAsDoneMessage(int taskIndex) {
        printHorizontalLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + tasks[taskIndex]);
        printHorizontalLine();
    }

    private void printHorizontalLine() {
        System.out.println(DIVIDER);
    }
}
