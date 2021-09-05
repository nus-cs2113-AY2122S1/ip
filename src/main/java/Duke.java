import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static final int MAX_TASK = 100;

    private static final String LINE = "    ____________________________________________________________";

    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";


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

    public static void executeToDoCommand(Task[] taskList, int listIndex, String rawUserInput){
        Task task = new ToDo(rawUserInput);
        System.out.printf("    Okay! I've added this task: \n       [%s][%s] %s\n",task.getType(), task.getStatusIcon(),task.getFormattedDescription());
        System.out.printf("    Now you have %d tasks in the list.\n", Task.getTotalTasks());
        taskList[listIndex] = task;
    }

    public static void executeDeadlineCommand(Task[] taskList, int listIndex, String rawUserInput){
        Task task = new Deadline(rawUserInput);
        System.out.printf("    Okay! I've added this task: \n       [%s][%s] %s\n",task.getType(), task.getStatusIcon(),task.getFormattedDescription());
        System.out.printf("    Now you have %d tasks in the list.\n", Task.getTotalTasks());
        taskList[listIndex] = task;
    }

    public static void executeEventCommand(Task[] taskList, int listIndex, String rawUserInput){
        Task task = new Event(rawUserInput);
        System.out.printf("    Okay! I've added this task: \n       [%s][%s] %s\n",task.getType(), task.getStatusIcon(),task.getFormattedDescription());
        System.out.printf("    Now you have %d tasks in the list.\n", Task.getTotalTasks());
        taskList[listIndex] = task;
    }


    public static void giveWelcomeMessage(){
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
    }

    public static String getInputCommand(String rawUserInput) {
        String[] inputWords = rawUserInput.toLowerCase().split(" ");
        String inputCommand = inputWords[0];
        return inputCommand;
    }

    public static String getFullTaskDescription(String rawUserInput) {
        int startIndex = rawUserInput.indexOf(" ") + 1;
        String FullTaskDescription = rawUserInput.substring(startIndex);
        return FullTaskDescription;
    }

    public static void processInput(String rawUserInput, Task[] taskList) {
        int listIndex = Task.getTotalTasks();
        String inputCommand = getInputCommand(rawUserInput);
        String fullTaskDescription;

        System.out.println(LINE);

        switch(inputCommand) {
        case LIST_COMMAND:
            executeListCommand(taskList,listIndex);
            break;
        case DONE_COMMAND:
            executeDoneCommand(taskList, listIndex, rawUserInput);
            break;
        case TODO_COMMAND:
            fullTaskDescription = getFullTaskDescription(rawUserInput);
            executeToDoCommand(taskList, listIndex, fullTaskDescription);
            break;
        case DEADLINE_COMMAND:
            fullTaskDescription = getFullTaskDescription(rawUserInput);
            executeDeadlineCommand(taskList, listIndex, fullTaskDescription);
            break;
        case EVENT_COMMAND:
            fullTaskDescription = getFullTaskDescription(rawUserInput);
            executeEventCommand(taskList, listIndex, fullTaskDescription);
            break;
        default:
            System.out.println("    Invalid Command :( Please try again.");
            break;
        }

        System.out.println(LINE);
    }

    public static void giveFarewellMessage() {
        System.out.println(LINE);
        System.out.println("    Bye, my friend :( ");
        System.out.println(LINE);
    }




    public static void main(String[] args) {
        // create a list of tasks
        Task[] taskList = new Task[MAX_TASK];
        String rawUserInput;

        giveWelcomeMessage();

        Scanner in = new Scanner(System.in);
        rawUserInput = in.nextLine().trim();


        while (!rawUserInput.equalsIgnoreCase("bye")) {
            processInput(rawUserInput, taskList);

            //get the new input
            rawUserInput = in.nextLine();
        }

        //if bye is the input
        giveFarewellMessage();
    }
}
