import java.util.*;

public class Duke {

    static ArrayList<String> inputs = new ArrayList<String>();

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();

        while(true) {
            String userInput = getUserInput();
            if(userInput.equals("bye"))
                break;
            else if(userInput.equals("list"))
                listInputs();
            else {
                inputs.add(userInput);
                System.out.println("added: " + userInput);
            }
            System.out.println();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void listInputs() {
        Iterator<String> it = inputs.iterator();
        int i = 1;

        while(it.hasNext()) {
            System.out.printf("%d. %s\n", i++, it.next());
        }
    }

    public static String getUserInput() {
        System.out.print("duke:$ ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}