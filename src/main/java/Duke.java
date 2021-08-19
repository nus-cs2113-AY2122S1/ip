import java.util.Scanner;  // Import the Scanner class


public class Duke {
    
    private static Scanner myScan= new Scanner(System.in);
    private static Tasks tasks = new Tasks();

    public static void main(String[] args) {
        greet();
        // level1();
        run();
    }


    public static void run() {

        while (true) {
            String input = myScan.nextLine();
            String arr[] = input.split(" ");
            switch(arr[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    myScan.close();
                    return;
                case "list":
                    tasks.listTasks();
                    break;
                case "done" :
                    int idx = Integer.parseInt(arr[1])-1;
                    tasks.updateTask(idx, 1);
                    System.out.println("Nice! I've marked this task as done: ");
                    tasks.showTask(idx);
                    break;
                default:
                    System.out.println("added: " + input);
                    tasks.addTask(input);
                    
            }
        }
    }


    public static void level1() {

        while (true) {
            String input = myScan.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                myScan.close();
                return;
            }
            System.out.println(input);  // Output user input
        }
        
    }

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
}

