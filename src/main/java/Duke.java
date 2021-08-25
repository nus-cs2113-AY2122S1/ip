import java.util.Scanner;

public class Duke {
    public static String divider = "___________________________________________________________";
    public static String indentation = "      ";

    public static void printIndentationAndDivider(){
        System.out.print(indentation);
        System.out.println(divider);
    }
    public static void printWordsWithIndentation(String words){
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

        Scanner in = new Scanner (System.in);
        String input = in.nextLine();
        while(!input.equals("bye")){
            printIndentationAndDivider();
            printWordsWithIndentation(input);
            printIndentationAndDivider();
            System.out.println();
            input = in.nextLine();
        }

        printIndentationAndDivider();
        printWordsWithIndentation("Bye, hope to see you again soon! :)");
        printIndentationAndDivider();
    }
}
