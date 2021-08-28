import java.util.Scanner; // Import the Scanner class

public class Duke {
    private static Scanner myScan = new Scanner(System.in);
    private static Tasks tasks = new Tasks();
    private static boolean isRunning = true;

    public static void greet() {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        // System.out.println("_________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void processInput(String input) {
        String[] arr = input.split(" ");
        switch (arr[0]) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            myScan.close();
            isRunning = false;
            return;
        case "list":
            tasks.listTasks();
            break;
        case "done":
            int idx = Integer.parseInt(arr[1]) - 1;
            tasks.updateTask(idx, true);
            System.out.println("Nice! I've marked this task as done: ");
            tasks.printTask(idx);
            break;
        case "deadline":
            int u = input.indexOf("/by");
            String by = input.substring(u+4, input.length());
            String DLname = input.substring(9,u);
            tasks.addTask(new Deadline(DLname,false,by));
            break;
        case "event":
            int v = input.indexOf("/at");
            String at = input.substring(v+4, input.length());
            String EVname = input.substring(6,v);
            tasks.addTask(new Event(EVname,false,at));
            break;
        case "todo":
        //Fallthrough
        default:
            int nameStart = 0;
            if (arr[0].equals("todo")) {
                nameStart = 5;
            }
            String TDname = input.substring(nameStart,input.length());
            tasks.addTask(new Todo(TDname,false));
            break;
        }
    }

    public static void run() {
        while (isRunning) {
            String input = myScan.nextLine();
            System.out.println("------------------------------------------");
            processInput(input);
            System.out.println("------------------------------------------");
        }
    }

    public static void main(String[] args) {
        greet();
        run();
    }
}
