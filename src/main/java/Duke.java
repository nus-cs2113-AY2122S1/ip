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
        Scanner in = new Scanner(System.in);
        String text;
        String order;

        text = in.nextLine();
        while(!text.equals("bye")){
            if(text.equals("list"))
                printList();
            else if(text.startsWith("done")){
                order = text.substring(text.indexOf(' ') + 1);
                doneTask(Integer.parseInt(order) - 1);
            }else{
                addTask(text);
            }
            text = in.nextLine();
        }
        Exit();
    }

    public static void Exit(){
        String bye = " Bye. Hope to see you again soon!\n";
        System.out.println(MESSAGE_BOUNDARY + "\n" + bye + MESSAGE_BOUNDARY);
    }

    public static void doneTask(int index){
        tasks[index].markAsDone();
        System.out.println(MESSAGE_BOUNDARY);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index].toString());
        System.out.println(MESSAGE_BOUNDARY);
    }

    public static void addTask(String task){
        tasks[numoftask] = new Task(task);
        numoftask++;
        System.out.println(MESSAGE_BOUNDARY + "\n" + "added: " + task + "\n" + MESSAGE_BOUNDARY);
    }

    public static void printList(){
        System.out.println(MESSAGE_BOUNDARY + System.lineSeparator() + "Here are the tasks in your list:");
        for(int i=0; i<numoftask; i++){
            System.out.println(i+1 + "." + tasks[i].toString());
        }
        System.out.println(MESSAGE_BOUNDARY);
    }

}
