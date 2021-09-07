import java.util.Scanner;

public class Duke {

    public static final int MAX_TASKS = 100;
    public static final String HORIZONTAL = "____________________________________________________________\n";

    public static final String TO_DO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    public static final int INDEX_OF_TO_DO_TASK = 4;
    public static final int INDEX_OF_DEADLINE_TASK = 8;
    public static final int INDEX_OF_EVENT_TASK = 5;
    public static final int INDEX_OF_TASK_NUMBER = 4;

    public static void sayHello() {
        System.out.println(HORIZONTAL + "Hello! I'm Duke\n" + "What can I do for you?\n" + HORIZONTAL);
    }

    public static void sayBye() {
        System.out.println(HORIZONTAL + "Bye. Hope to see you again soon!\n" + HORIZONTAL);
    }

    public static void addToDo(String line, Task[] tasks, int numberOfTasks) throws DukeException {
        String task = line.trim().substring(INDEX_OF_TO_DO_TASK);
        if (task.equals("")) {
            throw new DukeException();
        }
        tasks[numberOfTasks] = new ToDo(task);
        System.out.println(HORIZONTAL + "Got it. I've added this task: \n  " + tasks[numberOfTasks] +
                "\n" + "Now you have " + (numberOfTasks + 1) + " task" + (numberOfTasks == 0? " " : "s ") +
                "in the list. \n" + HORIZONTAL);
    }

    public static void addDeadline(String line, Task[] tasks, int numberOfTasks) throws DukeException {
        if (!line.contains("/by")) {
            throw new DukeException();
        }
        int slash = line.indexOf("/by");
        String task = line.trim().substring(INDEX_OF_DEADLINE_TASK, slash);
        if (task.equals("")) {
            throw new DukeException();
        }
        String dueDate = line.trim().substring(slash + 3);
        if (dueDate.equals("")) {
            throw new DukeException();
        }
        tasks[numberOfTasks] = new Deadline(task, dueDate);
        System.out.println(HORIZONTAL + "Got it. I've added this task: \n  " + tasks[numberOfTasks] +
                "\n" + "Now you have " + (numberOfTasks + 1) + " task" + (numberOfTasks == 0? " " : "s ") +
                "in the list. \n" + HORIZONTAL);
    }

    public static void addEvent(String line, Task[] tasks, int numberOfTasks) throws DukeException {
        if (!line.contains("/at")) {
            throw new DukeException();
        }
        int slash = line.indexOf("/at");
        String task = line.trim().substring(INDEX_OF_EVENT_TASK, slash);
        if (task.equals("")) {
            throw new DukeException();
        }
        String eventTime = line.trim().substring(slash + 3);
        if (eventTime.equals("")) {
            throw new DukeException();
        }
        tasks[numberOfTasks] = new Event(task, eventTime);
        System.out.println(HORIZONTAL + "Got it. I've added this task: \n  " + tasks[numberOfTasks] +
                "\n" + "Now you have " + (numberOfTasks + 1) + " task" + (numberOfTasks == 0? " " : "s ") +
                "in the list. \n" + HORIZONTAL);
    }

    public static int addTask(String line, Task[] tasks, int numberOfTasks) {
        if (line.contains(TO_DO)) {
            try {
                addToDo(line, tasks, numberOfTasks);
                numberOfTasks++;
            } catch (DukeException e) {
                System.out.println(HORIZONTAL + "☹ OOPS!!! The description of a todo cannot be empty. \n" +
                        HORIZONTAL);
            }
        } else if (line.contains(DEADLINE)) {
            try {
                addDeadline(line, tasks, numberOfTasks);
                numberOfTasks++;
            } catch (DukeException e) {
                System.out.println(HORIZONTAL + "☹ OOPS!!! A component of the deadline task is empty. \n" +
                        HORIZONTAL);
            }
        } else if (line.contains(EVENT)) {
            try {
                addEvent(line, tasks, numberOfTasks);
                numberOfTasks++;
            } catch (DukeException e) {
                System.out.println(HORIZONTAL + "☹ OOPS!!! A component of the event task is empty. \n" +
                        HORIZONTAL);
            }
        } else {
            System.out.println(HORIZONTAL + "☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" + HORIZONTAL);
        }
        return numberOfTasks;
    }

    public static void listTasks(Task[] tasks, int numberOfTasks) {
        System.out.print(HORIZONTAL + "Here are the tasks in your list: \n");
        for (int i = 1; i <= numberOfTasks; i++) {
            System.out.println(i + "." + tasks[i - 1]);
        }
        System.out.println(HORIZONTAL);
    }

    public static void markDone(Task[] tasks, int taskDone) {
        tasks[taskDone - 1].markAsDone();
        System.out.println(HORIZONTAL + "Nice! I've marked this task as done: \n  "
                + tasks[taskDone - 1] + "\n" + HORIZONTAL);
    }

    public static int getNumberOfTasks(Task[] tasks, int numberOfTasks, String line) {
        if (line.equals("list")) {
            listTasks(tasks, numberOfTasks);
        } else if (line.contains("done")) {
            int taskDone = Integer.parseInt(line.substring(INDEX_OF_TASK_NUMBER).trim());
            markDone(tasks, taskDone);
        } else {
            numberOfTasks = addTask(line, tasks, numberOfTasks);
        }
        return numberOfTasks;
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_TASKS];
        int numberOfTasks = 0;
        sayHello();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            numberOfTasks = getNumberOfTasks(tasks, numberOfTasks, line);
            line = in.nextLine();
        }
        sayBye();
    }
}
