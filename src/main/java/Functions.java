public class Functions {
    private static String[] tasks = new String[100];
    private static int numTasks = 0;
    private static final String line = "____________________________________________________________";

    //constructor
    public Functions(){
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
        System.out.println(line);
    }

    //add tasks to string array tasks
    public void taskAdder(String task){
        tasks[numTasks] = task;
        numTasks++;
        System.out.println(line);
        System.out.println("added: " + task);
        System.out.println(line);
    }

    //list tasks in numbered format
    public void listTasks(){
        int taskOrder = 1;
        for(int i = 0;i < numTasks;i++){
            System.out.println(taskOrder + ". " + tasks[i]);
            taskOrder++;
        }
        System.out.println(line);
    }

    //exits program
    public void exitDuke(){
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        System.exit(0);
    }

}
