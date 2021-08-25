import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[110];
    private static int taskSum = 0;

    public static void printList() {
        Task now;
        System.out.println("____________________________________________________________");
        for(int i=0; i<taskSum; i++){
            now = tasks[i];
            System.out.println(" " + (i+1) + "." + now.content);
        }
        System.out.println("____________________________________________________________");
    }
    public static void bye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String command;
        Scanner in = new Scanner(System.in);

        do{
            command = in.nextLine();
            switch (command){
                case "list":
                    printList();
                    break;
                    case "bye":
                        break;
                    default:
                        tasks[taskSum] = new Task(command);
                        taskSum = taskSum + 1;
                        System.out.println("____________________________________________________________");
                        System.out.println("added:" + command);
                        System.out.println("____________________________________________________________");
            }
        }while(!command.equals("bye"));
        bye();


    }
}
