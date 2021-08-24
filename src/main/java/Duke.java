import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greeting the User
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        //Echo commands entered by the user
        String inWord;
        Scanner scan = new Scanner(System.in);
        System.out.println("");
        inWord = scan.nextLine();

        String exitString = "bye";

        while (!inWord.equals(exitString)) {
            System.out.println("____________________________________________________________");
            System.out.println(" " + inWord);
            System.out.println("____________________________________________________________");
            System.out.println("");
            inWord = scan.nextLine();
        }

        //Exits when user types "bye"
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}