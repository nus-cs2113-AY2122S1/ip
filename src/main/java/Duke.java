import java.util.*;

public class Duke {

    public static void main(String[] args) {
        //Initialize helper variables
        Command command;
        boolean exit = false;
        TaskManager tm = new TaskManager();

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
                    String output = tm.listTasks();
                    System.out.println(output);
                    break;
                case DO_TASK:
                    String[] params = command.getParams();
                    int index = Integer.parseInt(params[0]);

                    try {
                        Task completedTask = tm.doTask(index);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.printf("[%s] %s\n\n", completedTask.getStatusIcon(), completedTask);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case NO_ACTION:
                    break;
                case ADD_TASK:
                    String task = command.getParams()[0];
                    tm.addTask(task);
                    System.out.println("added: " + task);
                    break;
                default:
                    System.out.println("Invalid command"); //should never reach here
            }


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