import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();
    /**
     *
     * Informs the user what task they will be adding into their task list and the number of tasks
     * in their list.
     * Prints the new task and the new number of tasks in their list.
     *
     * @param newTask The new task that will be added to the task list.
     * @param taskCount The number of tasks that are in the list (including the new task).
     */
    public static void printTask(Task toPrint) {
//        String toPrint = newTask.toString();
        System.out.println("Ok! I've added this task:");
        System.out.println(toPrint);
        System.out.println("Now you have " + taskList.size() + " tasks in your list uwu");
    }

    public static void deleteTask(Task delTask) throws IllegalDoneException {
        String n = delTask.description.substring(7);
        int delIndex = Integer.parseInt(n) - 1;
        if (delIndex >= taskList.size()) {
            throw new IllegalDoneException();
        } else {
//            taskList.remove(delTask);
//            taskList.setDone();
            System.out.println("Okies! I've removed this task <3 :");
            System.out.println(taskList.get(delIndex));
            taskList.remove(delIndex);
            System.out.println("Now you have " + taskList.size() + " tasks in your list uwu");
        }
    }
    /**
     * Prints all the tasks that the user has in their list.
     *
     * @param list List that contains all the tasks from user.
     * @param totalTasks Total number of tasks user has in their list.
     */
    public static void printTaskList() {
        int pos = 0;
        System.out.println("Here are the tasks in your list:");
        while (pos < taskList.size()) {

//        Task[] printList = Arrays.copyOf(list, totalTasks); // only copy until the part you want
            pos += 1;
            System.out.println(pos + ". " + taskList.get(pos - 1));
//        for (Task task : printList) {
//            System.out.println(i + "." + task);
//            i += 1;
//        }
        }
    }

    /**
     * Marks the task that is specified by user, by number starting from 1, as done.
     *
     * @param doneTask Task that is to be marked as done.
     * @param taskCount Total number of tasks user has in their list.
     * @param taskList List that contains all the tasks from user.
     * @throws IllegalDoneException If doneIndex >= taskCount, the task that user wants to mark as
     * done does not exist in the list
     */
    public static void markDone(Task doneTask) throws IllegalDoneException {
        String n = doneTask.description.substring(5);
        int doneIndex = Integer.parseInt(n) - 1;
        if(doneIndex >= taskList.size()) {
            throw new IllegalDoneException();
        } else {
            taskList.get(doneIndex).setDone();
//            taskList.setDone();
            System.out.println("Good job! I've marked these tasks as done:");
            printTaskList();
        }
    }

    /**
     * Returns the task that is to be added into user's task list.
     * @param t Task that is to be added into user's list.
     * @return Task that is to be added to the user's list.
     * @throws IllegalTaskException Task description does not start with "todo", "deadline" or "event".
     * @throws InvalidDeadlineFormat Deadline description does not contain the correct format of what is
     * to be expected for deadline, does not contain '/by'.
     * @throws InvalidEventFormat Event description does not contain the correct format of what is
     * to be expected for event, does not contain '/at'.
     */
    public static Task typeOfTask(Task t) throws IllegalTaskException, InvalidDeadlineFormat, InvalidEventFormat {
        Task newTask;
        int startOfDate = -1;
        if (t.description.contains("todo")) { // create a new todo
            newTask = new Todo(t.description.substring(5));
            return newTask;
        } else if (t.description.startsWith("deadline")) {
            startOfDate = t.description.indexOf('/');
            if(!t.description.contains("/by")) {
                throw new InvalidDeadlineFormat();
            }
            String task = t.description.substring(9, startOfDate - 1);
            String date = t.description.substring(startOfDate + 4);
            newTask = new Deadline(task, date);
            return newTask;
        } else if (t.description.startsWith("event")) {
            startOfDate = t.description.indexOf('/');
            if(!t.description.contains("/at")) {
                throw new InvalidEventFormat();
            }
            String task = t.description.substring(6, startOfDate - 1);
            String date = t.description.substring(startOfDate + 4);
            newTask = new Event(task, date);
            return newTask;
        }
        throw new IllegalTaskException();
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int taskCount = 0;

        System.out.println("Hello Bbygirl! I'm Your Boyfriend <3");
        System.out.println("How can I help you today? ;)");
        Task t = new Task(in.nextLine());
//        ArrayList<Task> taskList = new ArrayList<>();
//        Task[] taskList = new Task[100];

        while (!t.description.equals("bye")) {
            if (t.description.equals("list")) {
                printTaskList();
            } else if (t.description.startsWith("done")) {
                try {
                    markDone(t);
                } catch (IllegalDoneException e) {
                    System.out.println("You need to input a correct number BB... ;'( try typing again");
                }
            } else if(t.description.startsWith("delete")) {
                //to fill in
                try {
                    deleteTask(t);
                } catch (IllegalDoneException e) {
                    System.out.println("You need to input a correct number BB... ;'( try typing again");
                }
            } else {
                try {
                    // add the new task into user's task list
                    Task newTask = typeOfTask(t);
                    taskList.add(newTask);
//                    taskCount += 1;
                    printTask(newTask);
                } catch (IllegalTaskException e) {
                    System.out.println("You have a typo BB.. ;'( try typing again");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You need to add an item BB.. ;'( try typing again");
                } catch (InvalidDeadlineFormat e) {
                    System.out.println("You need to input deadline with '/by' ... ;'( try typing again");
                } catch (InvalidEventFormat e) {
                    System.out.println("You need to input event with '/at' ... ;'( try typing again");
                }
            }
            t = new Task(in.nextLine());
        }
        System.out.println("Goodbye. I will miss you sooo much :(");
    }
}
