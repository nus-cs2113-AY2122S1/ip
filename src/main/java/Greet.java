public class Greet {
    protected static Task[] list = new Task[100];
    private static int tasksAdded = 0;

    public static void printWelcomeMessage(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }
    public static void printGoodbyeMessage(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
    public static void printList(){
        String stringTaskAdded;
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasksAdded; i++) {
            int numbering = i + 1;
            stringTaskAdded = Integer.toString( numbering);
            String isDone = list[i].getStatusIcon();
            System.out.println(stringTaskAdded + ". " + "[" + isDone + "] " + list[i].getDescription());
        }
        System.out.println("    ____________________________________________________________");
    }
    public static void addTask(String taskDescription){
        Task newTask = new Task(taskDescription);
        list[tasksAdded] = newTask;
        tasksAdded += 1;
        printList();
    }
    public static void checkDoneTask(int taskNumber){
        int taskIndex = taskNumber - 1;
        list[taskIndex].markAsDone();
    }
}
