import java.util.Scanner;

public class Duke {
    public static void Greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        String horizontalLine = "________________________";
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
    }

    public static void AddAndList() {
        Scanner sc = new Scanner(System.in);
        String horizontalLine = "________________________";
        boolean isBye;
        boolean isList;
        String[] textList = new String[100];
        int textCount = 0;

        do {
            String userInput = sc.nextLine();
            isBye = userInput.equals("bye");
            isList = userInput.equals("list");
            System.out.println(horizontalLine);

            if (isBye) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (isList) {
                for (int i = 0; i < textCount; i++) {
                    int indexNumber = i + 1;
                    System.out.println(indexNumber + ". " + textList[i]);
                }
            } else {
                System.out.println("added: " + userInput);
                textList[textCount] = userInput;
                textCount++;
            }
            System.out.println(horizontalLine);

        } while (!isBye);
    }

    public static void main(String[] args) {
        Greet();
        AddAndList();
    }
}
