import java.util.Scanner;

public class Duke {

    public static void sayHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you, my liege?");
    }

    public static void sayBye() {
        printFormattedOutput("Farewell, my liege. Happy hunting!");
    }

    public static void printFormattedOutput(String output) {
        String[] outputLines = output.split(System.lineSeparator());

        System.out.println(InoutputFormatter.printOutputSeparator());
        for (String line : outputLines) {
            System.out.print(InoutputFormatter.printOutputStart());
            System.out.println(line);
        }
        System.out.println(InoutputFormatter.printOutputSeparator());
    }

    public static void echoUserInput(String input) {
        InoutputFormatter.printOutputSeparator();
        InoutputFormatter.printInputStart();
        System.out.println(input);
        InoutputFormatter.printOutputSeparator();
    }

    public static void main(String[] args) {
        String line;
        TaskHandler th = new TaskHandler();

        Scanner in = new Scanner(System.in);

        sayHello();

        System.out.print(InoutputFormatter.printInputStart());
        line = in.nextLine();
        
        while (!th.inputIsBye(line.toLowerCase())) {
            printFormattedOutput(th.handleTasks(line));
            System.out.print(InoutputFormatter.printInputStart());
            line = in.nextLine();
        }

        sayBye();
    }
}
