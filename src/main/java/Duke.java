import java.util.*;

// The Terminal

public class Duke {

    public static void main(String[] args) {
        //Initialize helper variables
        Command command;
        boolean exit = false;
        TaskManager taskMgr = new TaskManager();

        printGreeting();

        //INPUT LOOP
        while(true) {
            String userInput = getUserInput();

            try {
                command = Parser.parse(userInput);
            }catch(Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (command.getAction()) {
                case BYE:
                    exit = true;
                    break;
                case LIST:
                    String output = taskMgr.listTasks();
                    System.out.print(output);
                    break;
                case DO_TASK:
                    String indexParam = command.getParams("index");
                    int index = Integer.parseInt(indexParam);

                    try {
                        Task completedTask = taskMgr.doTask(index);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.printf("[%s][%s] %s\n",
                                completedTask.getTypeIcon(),
                                completedTask.getStatusIcon(),
                                completedTask);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case NO_ACTION:
                    break;
                case ADD_TASK:
                    Task newTask;
                    try{
                        newTask = taskMgr.addTask(command.getParams());
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println("Got it. I've added this task: ");
                    System.out.printf("[%s][ ] %s\n", newTask.getTypeIcon(), newTask);

                    int numOfTasks = taskMgr.getTasklistLength();
                    String plural = (numOfTasks <= 1) ? "task" : "tasks";

                    System.out.printf("Now you have %d %s in the list.\n\n", numOfTasks, plural);

                    break;
                default:
                    System.out.println("Invalid command"); //should never reach here
            }

            System.out.println();
            if (exit)
                break;
        }

        //EXIT
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String getUserInput() {
        System.out.print("duke:$ ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void printGreeting() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
    }

}