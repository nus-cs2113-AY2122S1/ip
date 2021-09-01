
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        // Welcome Message
        PrintWelcomeMessage();

        // Active Chat
        ActiveChat();

        // Goodbye Message
        PrintGoodbyeMessage();
    }

    private static void ActiveChat() {
        boolean isBye = false;
        boolean isEmptyList = true;
        String input;
        Scanner in = new Scanner(System.in);
        int counter = 0;
        Task[] list = new Task[100];

        while(!isBye){
            //store input
            input = in.nextLine();
            //check if input is empty
            CheckIsEmptyList(list);
            //process input
            if (input.equals("bye")){ //check if bye
                isBye = true;
            } else if (input.equals("list")) { //check if list
                ProcessList(counter, list);
            } else if (input.contains("done") ) { //check if done
                ProcessDone(input, list);
            } else {
                counter = ProcessTasks(input, counter, list); //process tasks
            }
        }
    }

    private static void CheckIsEmptyList(Task[] list) {
        boolean isEmptyList;
        if(list[0] != null) {
            isEmptyList = false;
        }
    }

    private static void ProcessList(int counter, Task[] list) {
        if (list[0] != null){
            PrintListMessage(counter, list);
        } else {
            PrintListButEmptyMessage();
        }
    }

    private static void ProcessDone(String input, Task[] list) {
        int donePos = input.indexOf("done");
        if (list[0] != null) {
            if (input.length() < donePos + 5) {
                PrintDoneButNotSpecificMessage(); //when input contains done but no number
            } else {
                ProcessDone(input, list, donePos); //when input contains done and specified number
            }
        } else {
            PrintDoneButEmptyMessage(); //when input contains done but list is empty
        }
    }

    private static void ProcessDone(String input, Task[] list, int donePos) {
        String itemNumDone = input.substring(donePos + 5, donePos + 6);
        int itemNum = Integer.parseInt(itemNumDone);
        list[itemNum - 1].setDone();
        PrintDoneMessage(list, itemNum);
    }

    private static int ProcessTasks(String input, int counter, Task[] list) {
        if (input.contains("todo")) {
            String description = input.substring(5);
            ToDo newTask = new ToDo(description);
            list[counter] = newTask;
            counter += 1;
            PrintAddedTaskMessage(newTask, counter);
        } else if (input.contains("deadline")) {
            int donePos = input.indexOf("/"); //finds pos of '/'
            String description = input.substring(9,donePos);
            String date = input.substring(donePos + 4);
            Deadline newTask = new Deadline(description,date);
            list[counter] = newTask;
            counter += 1;
            PrintAddedTaskMessage(newTask, counter);
        } else if (input.contains("event")) {
            int donePos = input.indexOf("/"); //finds pos of '/'
            String description = input.substring(6,donePos);
            String date = input.substring(donePos + 4);
            Event newTask = new Event(description,date);
            list[counter] = newTask;
            counter += 1;
            PrintAddedTaskMessage(newTask, counter);
        } else {
            System.out.println("Please specify tasks: todo, deadline or event");
            System.out.println("Example - type in the following: todo read book");
        }
        return counter;
    }

    private static void PrintDoneMessage(Task[] list, int itemNum) {
        System.out.println("--------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println( itemNum + ".[" + list[itemNum - 1].getStatusIcon() + "] " + list[itemNum - 1].getDescription() );
        System.out.println("--------------------");
    }

    private static void PrintListMessage(int counter, Task[] list) {
        System.out.println("--------------------");
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < counter; i += 1){
            PrintListOfTaskSubMessage(list[i], i);
        }
        System.out.println("--------------------");
    }

    private static void PrintListButEmptyMessage() {
        System.out.println("--------------------");
        System.out.println("List is empty. Time to get productive!");
        System.out.println("--------------------");
    }

    private static void PrintDoneButNotSpecificMessage() {
        System.out.println("Please specify which task is done.");
    }

    private static void PrintDoneButEmptyMessage() {
        System.out.println("--------------------");
        System.out.println("Unable to tick off list.");
        System.out.println("List is empty. Time to get productive!");
        System.out.println("--------------------");
    }

    private static void PrintAddedTaskMessage(Task task, int i) {
        System.out.println("--------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + task.getLetter() + "] "
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription()
                + task.getDate() );
        System.out.println("Now you have " + i + " tasks in the list.");
        System.out.println("--------------------");
    }

    private static void PrintListOfTaskSubMessage(Task task, int i) {
        System.out.println(i + 1
                + ".[" + task.getLetter() + "] "
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription() );
    }

    private static void PrintGoodbyeMessage() {
        System.out.println("--------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("");
        System.out.println("--------------------");
    }

    private static void PrintWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("--------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("");
        System.out.println("--------------------");
    }
}
