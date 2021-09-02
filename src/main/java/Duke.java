import java.util.Scanner;

public class Duke {
    // Constants
    private static final String MESSAGE_BOUNDARY = "____________________________________________________________";

    // Attributes
    private static Task[] tasks = new Task[100];
    private static int numoftask = 0;

    // Constructor
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet();
        Echo();
    }

    public static void Greet(){
        String lines = "____________________________________________________________";
        String greet = " Hello! I'm Duke\n"
                    + " What can I do for you?\n";
        System.out.println(MESSAGE_BOUNDARY + "\n" + greet + MESSAGE_BOUNDARY);
    }

    public static void Echo(){
        boolean continueChat;
        Scanner in = new Scanner(System.in);

        do{
            // Get query from user
            String userInput = in.nextLine();

            // Give response
            continueChat = giveResponse(userInput);

        }while(continueChat);

        in.close();
    }

    public static boolean giveResponse(String userInput){
        if(userInput.equals("list")){
            printList();
        }else if(userInput.equals("bye")){
            Exit();
            return false;
        }else{
            int i = userInput.indexOf(" ");
            String command = userInput.substring(0, i);

            switch (command){
                case "todo":
                case "deadline":
                case "event":
                    addTask(command, userInput.substring(i + 1));
                    break;
                case "done":
                    doneTask(userInput.substring(i + 1));
                    break;
                default:
                    System.out.println("I am sorry, but I don't understand.");
            }
        }

        return true;
    }

    public static void Exit(){
        String bye = " Bye. Hope to see you again soon!\n";
        System.out.println(MESSAGE_BOUNDARY + "\n" + bye + MESSAGE_BOUNDARY);
    }

    public static void doneTask(String index){
        int taskIndex = Integer.parseInt(index);
        tasks[taskIndex-1].markAsDone();
        System.out.println(MESSAGE_BOUNDARY);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskIndex-1].toString());
        System.out.println(MESSAGE_BOUNDARY);
    }

    public static void addTask(String command, String taskInfo){
        switch (command){
            case "todo":
                tasks[numoftask] = ToDos.parse(taskInfo);
                break;
            case "deadline":
                tasks[numoftask] = Deadline.parse(taskInfo);
                break;
            case "event":
                tasks[numoftask] = Events.parse(taskInfo);
                break;
        }

        System.out.println(MESSAGE_BOUNDARY + "\n" + "Got it. I've added this task: ");
        System.out.println(tasks[numoftask].toString());
        System.out.println("Now you have " + (numoftask + 1) + " task(s) in the list.\n" + MESSAGE_BOUNDARY);

        numoftask++;
    }

    public static void printList(){
        System.out.println(MESSAGE_BOUNDARY + System.lineSeparator() + "Here are the tasks in your list:");
        for(int i=0; i<numoftask; i++){
            System.out.println(i+1 + "." + tasks[i].toString());
        }
        System.out.println(MESSAGE_BOUNDARY);
    }

}
