import java.util.Scanner;

public class Duke {
    public static final String DIVIDER = "___________________________________________________________";
    public static final String INDENTATION = "      ";
    public static Task[] tasks = new Task[100];

    public static void printIndentationAndDivider() {
        System.out.print(INDENTATION);
        System.out.println(DIVIDER);
    }

    public static void printWordsWithIndentation(String words) {
        System.out.print(INDENTATION);
        System.out.println(words);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printIndentationAndDivider();
        printWordsWithIndentation("Hello! I'm Duke, your friendly neighbourhood task manager");
        printWordsWithIndentation("What can I do for you? :D");
        printIndentationAndDivider();
        System.out.println();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            switch(input.split(" ")[0]) {
            case "list" :
                printIndentationAndDivider();
                for (int i = 0; i < Task.getTotalTasks(); i++) {
                    String description = tasks[i].getDescription();
                    String statusIcon = tasks[i].getStatusIcon();
                    printWordsWithIndentation(i + 1 + "." + "[" + statusIcon + "] " + description);
                }
                printIndentationAndDivider();
                System.out.println();
                break;
            case "done" :
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[index].markAsDone();
                printIndentationAndDivider();
                printWordsWithIndentation("You've completed the task! Well done!");
                printWordsWithIndentation("[" + tasks[index].getStatusIcon() + "] " + tasks[index].getDescription());
                printIndentationAndDivider();
                break;
            default :
                printIndentationAndDivider();
                printWordsWithIndentation("added: " + input);
                printIndentationAndDivider();
                System.out.println();
                tasks[Task.getTotalTasks()] = new Task(input);
                Task.setTotalTasks(Task.getTotalTasks() + 1);
                break;
            }
            input = in.nextLine();
        }

        printIndentationAndDivider();
        printWordsWithIndentation("Bye, hope to see you again soon! :)");
        printIndentationAndDivider();
    }
}
