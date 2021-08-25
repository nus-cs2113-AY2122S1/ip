import java.util.Scanner;

public class Duke {
    public static String divider = "___________________________________________________________";
    public static String indentation = "      ";
    public static String[] input_list = new String[100];
    public static int index = 0;

    public static void printIndentationAndDivider() {
        System.out.print(indentation);
        System.out.println(divider);
    }

    public static void printWordsWithIndentation(String words) {
        System.out.print(indentation);
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
        printWordsWithIndentation("Hello! I'm Duke");
        printWordsWithIndentation("What can I do for you? :D");
        printIndentationAndDivider();
        System.out.println();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            if(input.equals("list")) {
                printIndentationAndDivider();
                for (int i = 0; i < index; i++) {
                    printWordsWithIndentation(i + 1 + ". " + input_list[i]);
                }
                printIndentationAndDivider();
                System.out.println();
            }
            else {
                printIndentationAndDivider();
                printWordsWithIndentation("added: " + input);
                printIndentationAndDivider();
                System.out.println();
                input_list[index] = input;
                index++;
            }
            input = in.nextLine();
        }

        printIndentationAndDivider();
        printWordsWithIndentation("Bye, hope to see you again soon! :)");
        printIndentationAndDivider();
    }
}
