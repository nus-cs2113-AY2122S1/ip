import java.util.Scanner;

public class Duke {

    public static final int TASK_LIST_SIZE = 100;

    public static void main(String[] args) {

        //Greeting
        Functions.printGreeting();

        //Initialization
        Task[] taskList = new Task[TASK_LIST_SIZE];
        int taskListSize = 0;
        String userInput = "start";
        Scanner in = new Scanner(System.in);

        //Main Loop
        while (!userInput.equals("bye")) {

            //Scans for user input
            userInput = in.nextLine();

            //Processes user input
            String[] processedUserInput = Functions.processUserInput(userInput);
            String command = processedUserInput[0];

            //Duke's actions based on command given
            switch (command) {
            case "list":
                //Prints all tasks in task list
                Functions.printTaskList(taskList, taskListSize);
                break;
            case "done":
                //Marks task as "done"
                int taskNumber = Integer.parseInt(processedUserInput[1]) - 1;
                Functions.markAsDone(taskList, taskListSize, taskNumber);
                break;
            case "todo":
            case "deadline":
            case "event":
                //Creates and adds task to task list
                Task newTask = Functions.createTask(processedUserInput);
                Functions.addTask(taskList, taskListSize, newTask);
                taskListSize++;
                break;
            case "bye":
                break;
            default:
                Functions.printError();
                break;
            }
        }

        //Farewell
        Functions.printFarewell();
    }

}
