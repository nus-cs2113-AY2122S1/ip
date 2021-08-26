import java.util.Scanner;

public class Duke {
    public static void printStartOfInput() {
        System.out.print(">>> ");
    }
    public static void printStartOfOutputSpace() {
        System.out.print("  ");
    }
    public static void printOutputSeparator() {
        System.out.println("********************************************");
    }
    public static void echoUserInput(String input) {
        printOutputSeparator();
        printStartOfOutputSpace();
        System.out.println(">>> " + input);
        printOutputSeparator();
    }

    public static void printAddItemToList(String item) {
        printOutputSeparator();
        printStartOfOutputSpace();
        System.out.println("As you command. Added: " + item);
        printOutputSeparator();
    }

    public static void printFormattedList(String[] list) {
        int listItemNum = 0;

        printOutputSeparator();
        for (int i = 0; i < list.length && list[i] != null; i++) {
            listItemNum = i + 1;
            printStartOfOutputSpace();
            System.out.println(listItemNum + ". " + list[i]);
        }
        printOutputSeparator();
    }

    public static void main(String[] args) {
        String line;
        String[] list = new String[100];
        int listIndex = 0;
        Scanner in = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        printStartOfInput();
        line = in.nextLine();
        while (!line.toLowerCase().equals("bye")) {
          if (line.toLowerCase().equals("list")) {
              printFormattedList(list);
          } else {
              list[listIndex] = line;
              listIndex++;
              printAddItemToList(line);
          }
          printStartOfInput();
          line = in.nextLine();
        }
        System.out.println("Farewell. Hope to see you again soon!");
    }
}
