import java.util.Scanner;

public class Duke {

    private static Scanner in = new Scanner(System.in);
    static final int MAX_SIZE = 100;

    private static String lineInput = "";
    private static Task[] taskList = new Task[MAX_SIZE];
    private static int tasksCount = 0;

    public static void addTask(Task t){
        if (t.getDescription().length() > 0) {
            System.out.println("Got it. I've added this task:");
            taskList[tasksCount] = t;
            tasksCount++;
            System.out.println(t);
            System.out.println("Now you have " + tasksCount + " tasks on the list.");
        }
    }


    private static void listTasks(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasksCount; i++) {
            Task currTask = taskList[i - 1];
            System.out.println(i + ". " + currTask);
        }
    }

    private static void markTaskDone(int index) {
        taskList[index].markDone();
        Task currTask = taskList[index];
        System.out.println((index + 1) + ". " + currTask);
    }

    private static void printBorder(){
        System.out.println("____________________________________________________________");
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "Hello I'm Duke\n"
                + "What can I do for you?";
        String exit = "Bye. Hope to see you again soon!";

        printBorder();
        System.out.println("Hello from\n" + logo);
        System.out.println(greet);
        printBorder();

        while (!lineInput.equals("bye")) {
            printBorder();
            String[] inputArray = lineInput.split(" ", 2);
            String inputCommand = inputArray[0];
            String inputAction = null;
            if (inputArray.length > 1){
                inputAction = inputArray[1];
            }

            switch(inputCommand){
                case "":
                    break;
                case "list":
                    listTasks();
                    break;
                case "done":
                    markTaskDone(Integer.parseInt(inputAction) - 1);
                    break;
                case "todo":
                    try {
                        Todo newTodo = new Todo(inputAction);
                        addTask(newTodo);
                    }
                    catch (NullPointerException e){
                        System.out.println("INVALID: Empty 'todo' input");
                    }
                    break;
                case "deadline":
                    try {
                        String DeadlineToDo = inputAction.split("/", 2)[0];
                        String dueDate = inputAction.split("/by", 2)[1];
                        Deadline newDeadline = new Deadline(DeadlineToDo, dueDate);
                        addTask(newDeadline);
                    }
                    catch (NullPointerException e){
                        System.out.println("INVALID: Empty 'deadline' input");
                    }
                    catch (IndexOutOfBoundsException e){
                        System.out.println("INVALID: Insufficient 'deadline' description or time");
                    }
                    break;
                case "event":
                    try {
                        String EventToDo = inputAction.split("/", 2)[0];
                        String dueTime = inputAction.split("/at", 2)[1];
                        Event newEvent = new Event(EventToDo, dueTime);
                        addTask(newEvent);
                    }
                    catch (NullPointerException e){
                        System.out.println("INVALID: Empty 'event' description");
                    }
                    catch (IndexOutOfBoundsException e){
                        System.out.println("INVALID: Insufficient 'event' description or time");
                    }
                    break;
                default:
                    System.out.println("INVALID: Invalid command");
                    break;
            }
            printBorder();
            lineInput = in.nextLine();
        }
        printBorder();
        System.out.println(exit);
        printBorder();
    }
}
