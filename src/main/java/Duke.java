import java.util.Scanner;

public class Duke {

    private static Scanner in = new Scanner(System.in);
    private static String lineInput = "";
    private static Task[] taskList = new Task[100];
    private static int tasksCount = 0;

    public static void add(String args){
        if (args.length() > 0) {
            System.out.println("added: " + args);
            Task newTask = new Task(args);
            taskList[tasksCount] = newTask;
            tasksCount++;
        }
    }

    private static void list_out(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasksCount; i++) {
            System.out.println(i + ". " + taskList[i - 1].getStatusIcon() + taskList[i - 1].getDescription());
        }
    }

    private static void printBorder(){
        System.out.println("____________________________________________________________");
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "Hello I'm Duke\n"
                + "What can I do for you?";
        String exit = "Bye. Hope to see you again soon!";

        printBorder();
        System.out.println("Hello from\n" + logo);
        System.out.println(greet);
        printBorder();

        while (!lineInput.equals("bye")) {
            if (lineInput.equals("list")) {
                printBorder();
                list_out();
                printBorder();
            }
            else if (lineInput.length() > 5){
                if (lineInput.substring(0, 4).equals("done")) {
                    int index = Integer.parseInt(lineInput.substring(5, 6)) - 1;
                    printBorder();
                    taskList[index].markDone();
                    System.out.println((index + 1) + ". " + taskList[index].getStatusIcon() + taskList[index].getDescription());
                    printBorder();
                }
                else {
                    printBorder();
                    add(lineInput);
                    printBorder();
                }
            }
            else if (lineInput.length() != 0){
                printBorder();
                add(lineInput);
                printBorder();
            }
            lineInput = in.nextLine();
        }

        System.out.println(exit);
    }
}
