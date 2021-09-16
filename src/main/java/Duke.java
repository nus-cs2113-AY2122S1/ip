import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;



public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();
    /**
     *
     * Informs the user what task they will be adding into their task list and the number of tasks
     * in their list.
     * Prints the new task and the new number of tasks in their list.
     *
     * @param toPrint The new task that will be printed
     */
    public static void printTask(Task toPrint) {
        System.out.println("Ok! I've added this task:");
        System.out.println(toPrint);
        System.out.println("Now you have " + taskList.size() + " tasks in your list uwu");
    }

    /**
     * Deletes the task specified by user from the taskList.
     *
     * @param delTask Index of task to be deleted, passed in as String and will be converted to integer.
     * @throws IllegalDoneException Exception thrown when user inputs an incorrect index of the number.
     */
    public static void deleteTask(String delTask) throws IllegalDoneException {
        String n = delTask.substring(7);
        int delIndex = Integer.parseInt(n) - 1;
        if (delIndex >= taskList.size()) {
            throw new IllegalDoneException();
        } else {
            System.out.println("Okies! I've removed this task <3 :");
            System.out.println(taskList.get(delIndex));
            taskList.remove(delIndex);
            System.out.println("Now you have " + taskList.size() + " tasks in your list uwu");
        }
    }
    /**
     * Prints all the tasks that the user has in their list.
     *
     *
     */
    public static void printTaskList() {
        int pos = 0;
        System.out.println("Here are the tasks in your list:");
        while (pos < taskList.size()) {
            pos += 1;
            System.out.println(pos + ". " + taskList.get(pos - 1));
        }
    }

    /**
     * Marks the task that is specified by user, by number starting from 1, as done.
     *
     * @param doneTask Task that is to be marked as done.
     * @throws IllegalDoneException If doneIndex >= taskCount, the task that user wants to mark as
     * done does not exist in the list
     */
    public static void markDone(String doneTask) throws IllegalDoneException {
        String n = doneTask.substring(5);
        int doneIndex = Integer.parseInt(n) - 1;
        if(doneIndex >= taskList.size()) {
            throw new IllegalDoneException();
        } else {
            taskList.get(doneIndex).setDone();
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
    public static void typeOfTask(String t) throws IllegalTaskException, InvalidDeadlineFormat, InvalidEventFormat {
        int startOfDate = -1;
        if (t.contains("todo")) { // create a new todo
            Todo newTask = new Todo(t.substring(5));
            taskList.add(newTask);
            printTask(newTask);
            return;
        } else if (t.startsWith("deadline")) {
            startOfDate = t.indexOf('/');
            if(!t.contains("/by")) {
                throw new InvalidDeadlineFormat();
            }
            String task = t.substring(9, startOfDate - 1);
            String date = t.substring(startOfDate + 4);
            Deadline newTask = new Deadline(task, date);
            taskList.add(newTask);
            printTask(newTask);
            return;
        } else if (t.startsWith("event")) {
            startOfDate = t.indexOf('/');
            if(!t.contains("/at")) {
                throw new InvalidEventFormat();
            }
            String task = t.substring(6, startOfDate - 1);
            String date = t.substring(startOfDate + 4);
            Event newTask = new Event(task, date);
            taskList.add(newTask);
            printTask(newTask);
            return;
        }
        throw new IllegalTaskException();
    }

    /**
     * Stores taskList to the file specified, tasks will be stored as Todo, Deadline or Event.
     *
     * @param filePath Path to file to be written to
     * @throws IOException Exception is thrown when there is no valid file of the name and path.
     */
    private static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        int i = 0;
        while(i < taskList.size()) {
            fw.write(taskList.get(i).saveToFile() + System.lineSeparator());
            i += 1;
        }
        fw.close();
    }

    /**
     * Reads content of the file and stores the tasks into taskList to be used by the user.
     *
     * @param filePath Path to the file that is read from
     */
    private static void readFileContents(String filePath) {
        File f = new File(filePath);
        try {
            if (!f.createNewFile()) {
                Scanner s = new Scanner(f); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    String line = s.nextLine();
                    if (line.startsWith("E")) {
                        int startDate = line.indexOf('|');
                        String description = line.substring(4, startDate - 1);
                        String date = line.substring(startDate + 2);
                        Event newTask = new Event(description, date);
                        taskList.add(newTask);
                        if (line.substring(2).startsWith("1")) {
                            taskList.get(taskList.size() - 1).setDone();
                        }
                    } else if (line.startsWith("D")) {
                        int startDate = line.indexOf('|');
                        String description = line.substring(4, startDate - 1);
                        String date = line.substring(startDate + 2);
                        Deadline newTask = new Deadline(description, date);
                        taskList.add(newTask);
                        if (line.substring(2).startsWith("1")) {
                            taskList.get(taskList.size() - 1).setDone();
                        }
                    } else {
                        String description = line.substring(4);
                        Todo newTask = new Todo(description);
                        taskList.add(newTask);
                        if (line.substring(2).startsWith("1")) {
                            taskList.get(taskList.size() - 1).setDone();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("sth");
        }
    }



    public static void main(String[] args) {
        readFileContents("lines.txt");

        Scanner in = new Scanner(System.in);
        int taskCount = 0;

        System.out.println("Hello Bbygirl! I'm Your Boyfriend <3");
        System.out.println("How can I help you today? ;)");
        String t = in.nextLine();

        while (!t.equals("bye")) {
            if (t.equals("list")) {
                printTaskList();
            } else if (t.startsWith("done")) {
                try {
                    markDone(t);
                } catch (IllegalDoneException e) {
                    System.out.println("You need to input a correct number BB... ;'( try typing again");
                }
            } else if(t.startsWith("delete")) {
                try {
                    deleteTask(t);
                } catch (IllegalDoneException e) {
                    System.out.println("You need to input a correct number BB... ;'( try typing again");
                }
            } else {
                try {
                    typeOfTask(t);
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
            t = in.nextLine();
        }
        try {
            writeToFile("lines.txt");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println("Goodbye. I will miss you sooo much :(");
    }
}
