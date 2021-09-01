import java.util.Scanner;

public class Duke {

    private static Scanner in = new Scanner(System.in);

    private static String lineInput = "";
    private static Task[] taskList = new Task[100];
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

    private static void showTaskDone(int index) {
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
            String action = lineInput.split(" ", 2)[0];

            switch(action){
                case "list":
                    listTasks();
                    break;
                case "done":
                    int index = Integer.parseInt(lineInput.split(" ", 2)[1]) - 1;
                    showTaskDone(index);
                    break;
                case "todo":
                    String TaskToDo = lineInput.split(" ", 2)[1];
                    Todo newTodo = new Todo(TaskToDo);
                    addTask(newTodo);
                    break;
                case "deadline":
                    String DeadlineToDo = lineInput.split(" ", 2)[1].split("/", 2)[0];
                    String dueDate = lineInput.split(" ", 2)[1].split("/by", 2)[1];
                    Deadline newDeadline = new Deadline(DeadlineToDo, dueDate);
                    addTask(newDeadline);
                    break;
                case "event":
                    String EventToDo = lineInput.split(" ", 2)[1].split("/", 2)[0];
                    String dueTime = lineInput.split(" ", 2)[1].split("/at", 2)[1];
                    Event newEvent = new Event(EventToDo, dueTime);
                    addTask(newEvent);
                    break;
                default:
                    Task newTask = new Task(action);
                    addTask(newTask);
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
