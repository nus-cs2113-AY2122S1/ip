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
            String[] InputArray = lineInput.split(" ", 2);
            String InputCommand = InputArray[0];
            String InputAction = null;
            if (InputArray.length > 1){
                InputAction = InputArray[1];
            }

            switch(InputCommand){
                case "list":
                    listTasks();
                    break;
                case "done":
                    showTaskDone(Integer.parseInt(InputAction) - 1);
                    break;
                case "todo":
                    Todo newTodo = new Todo(InputCommand);
                    addTask(newTodo);
                    break;
                case "deadline":
                    String DeadlineToDo = InputAction.split("/", 2)[0];
                    String dueDate = InputAction.split("/by", 2)[1];
                    Deadline newDeadline = new Deadline(DeadlineToDo, dueDate);
                    addTask(newDeadline);
                    break;
                case "event":
                    String EventToDo = InputAction.split("/", 2)[0];
                    String dueTime = InputAction.split("/at", 2)[1];
                    Event newEvent = new Event(EventToDo, dueTime);
                    addTask(newEvent);
                    break;
                default:
                    Task newTask = new Task(InputCommand);
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
