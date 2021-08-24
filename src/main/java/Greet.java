public class Greet {
    private static String[] list = new String[100];
    private static int tasksAdded = 0;
    public static void miniDuke(String command) {
    switch(command){
    case "bye":
        printGoodbyeMessage();
        break;
    case "":
    case " ":
    case "list":
        printList();
        break;
    default:
        addTask(command);
    }
    }
    public static void printWelcomeMessage(){
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public static void printGoodbyeMessage(){
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    public static void printList(){
        String stringTaskAdded;
        System.out.println("____________________________________________________________");
        for (int i = 0; i < tasksAdded; i++) {
            int numbering = i + 1;
            stringTaskAdded = Integer.toString( numbering);
            System.out.println(stringTaskAdded + ". " + list[i]);
        }
        System.out.println("____________________________________________________________");
    }
    public static void addTask(String task){
        list[tasksAdded] = task;
        tasksAdded += 1;
        printList();
    }
}
