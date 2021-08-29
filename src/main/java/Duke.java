import java.util.Scanner;

public class Duke {

    public static final String DIVIDER_LINE = "\t__________________________________________________";

    public static void printLine(){
        System.out.println(DIVIDER_LINE);
    }

    public static void Greet(){
        printLine();
        System.out.println("\tHello! I'm Duke, your friendly agenda chatbot!\n"
                + "\tIs there anything I can do for you today?");
        printLine();
    }

    public static void Bye(){
        printLine();
        System.out.println("\tBye. Have a productive day!");
        printLine();
    }

    private static void programLogic(TaskManager t1, String line, String[] words) {
        switch (words[0]) {
        case "todo":
            t1.addTodo(line);
            break;
        case "deadline":
            t1.addDeadline(line);
            break;
        case "event":
            t1.addEvent(line);
            break;
        case "list":
            t1.listTasks();
            break;
        case "done":
            int markedIndex = Integer.parseInt(words[1]) - 1;
            t1.markAsDone(markedIndex);
            break;
        default:
            t1.printInvalid();
            break;
        }
    }

    private static void DukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void main(String[] args) {
        DukeLogo();
        Greet();
        TaskManager t1 = new TaskManager();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] words = line.split(" ");
        while (!words[0].equals("bye")) {
            programLogic(t1, line, words);
            line = in.nextLine();
            words = line.split(" ");
        }
        Bye();
    }
}
