import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    private static final int MAX_TASK = 100;

    // Check if the Done Command is valid and returns true/false accordingly
    public static boolean checkValidDoneCommand(String input, int listIndex){
        String[] inputSplit = input.split(" ");
        if (inputSplit.length != 2){
            return false;
        }
        try {
            int doneIndex = Integer.parseInt(inputSplit[1]);
            // if number in the input is not in the range of list, throw error and ask for input again
            if (doneIndex < 1 || doneIndex > listIndex){
                return false;
            }
        } catch (NumberFormatException e) {
           return false;
        }
    return true;
    }

    // reads Done Command then prints out corresponding statements
    public static void executeDoneCommand(Task[] list, int listIndex, String input){
        // split the input string into array
        String[] inputSplit = input.split(" ");
        // check if the done command is valid
        boolean isValid = checkValidDoneCommand(input, listIndex);

        //valid done command
        if (isValid){
            int doneIndex = Integer.parseInt(inputSplit[1]);
            // task has not been marked as done and needs to be marked as done
            if (!list[doneIndex - 1].getIsDone()){
                list[doneIndex - 1].markAsDone();
                System.out.println("    Alright! I've marked this task as done! :)");
                System.out.printf("    [%s] %s\n", list[doneIndex - 1].getStatusIcon(), list[doneIndex - 1].getFormattedDescription());
            }
            //task has already been marked as done
            else{
                System.out.println("    Task is already marked as done!");
            }
        }

        // not a valid done command
        else{
            System.out.println("    Command is not valid :( Please specify a valid task number to be marked as done.");
        }
    }

    // reads List Command then prints out all the tasks
    public static void executeListCommand(Task[] list, int listIndex){
        System.out.println("    Task List:");
        for (int i = 0; i < listIndex; i ++){
            System.out.printf("    %d.[%s][%s] %s\n", i + 1,list[i].getType(), list[i].getStatusIcon(),list[i].getFormattedDescription());
        }
    }

    // reads Task Command then prints out formatted description of the task
    public static void executeTaskCommand(Task[]list, int listIndex, String command, String input){
        Task task = new Task(command);
        if (command.equals("todo")){
            task = new ToDo(input);
        }
        else if (command.equals("deadline")){
            task = new Deadline(input);
        }
        else if (command.equals("event")){
            task = new Event(input);
        }
        System.out.printf("    Okay! I've added this task: \n       [%s][%s] %s\n",task.getType(), task.getStatusIcon(),task.getFormattedDescription());
        System.out.printf("    Now you have %d tasks in the list.\n", Task.getTotalTasks());
        list[listIndex] = task;
    }


    public static void main(String[] args) {
        // create a list of tasks
        Task[] list = new Task[MAX_TASK];
        int listIndex = 0;

        String logo = " \n" +
                "                        .-\"\"\"-.\n" +
                "                       / .//\". \\\n" +
                "                       \\/ o o \\/\n" +
                "                       ( \\___/ )\n" +
                "          ________oooo__\\_____/_____________\n" +
                "         /                                  \\\n" +
                "        |         Hello! I'm Bobby :)        |\n" +
                "        |     What can I can do for you?     |\n" +
                "         \\______________________oooo________/\n" ;


        System.out.println(logo);

        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();


        while (!input.equals("bye")) {
            System.out.println("    ____________________________________________________________");

            // if user inputs list, execute list command to showcase the list
            if (input.equals("list")){
                executeListCommand(list,listIndex);
            }

            //if user inputs done, execute done command to mark task as done
            else if (input.startsWith("done")){
                executeDoneCommand(list,listIndex,input);
            }

            //if user inputs a task command, execute task command and print out necessary statements
            else if (input.startsWith("todo") || input.startsWith("deadline") || (input.startsWith("event"))) {
                String[] inputArr = input.split(" ");
                String command = inputArr[0];
                executeTaskCommand(list, listIndex, command, input);
                listIndex++;
            }

            // not any recognised commands
            else{
                System.out.println("    Invalid Command :( Please try again.");
            }

            System.out.println("    ____________________________________________________________");
            //get the new input
            input = in.nextLine();
        }

        //if bye is the input
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye, my friend :( ");
        System.out.println("    ____________________________________________________________");
    }
}
