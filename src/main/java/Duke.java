import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int noOfTasks = 0;

    public static void printLine(){
        String line = "\t__________________________________________________";
        System.out.println(line);
    }

    public static void Greet(){
        printLine();
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        printLine();
    }

    public static void Bye(){
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }
/*
    public static void Echo(String[] words){
        printLine();
        System.out.print("\t");
        for (int i = 0; i < words.length; i += 1){
            System.out.print(words[i] + " ");
        }
        System.out.println("");
        printLine();
    }

 */


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet();
        TaskManager t1 = new TaskManager();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] words = line.split(" ");
        while (!words[0].equals("bye")) {
            //Echo(words);
            if (words[0].equals("todo")) {
                t1.addTodo(line);
            } else if (words[0].equals("deadline")) {
                t1.addDeadline(line);
            } else if (words[0].equals("event")) {
                t1.addEvent(line);
            } else if (words[0].equals("list")) {
                t1.listTasks();
            } else if (words[0].equals("done")){
                int markedIndex = Integer.parseInt(words[1]) - 1;
                t1.markAsDone(markedIndex);
            }
            line = in.nextLine();
            words = line.split(" ");
        }
            Bye();
    }
}
