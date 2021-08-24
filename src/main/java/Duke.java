import java.util.Scanner;

import java.io.FileReader;
import java.io.IOException;

public class Duke {
	
	public static void printParrotText(){
        try {
            // Note that the text file must be placed at the base java directory!
            FileReader readParrot = new FileReader("ParrotText.txt");
            int singleCharacters;

            while ((singleCharacters = readParrot.read()) != -1) {
                // Print each character individually.
                System.out.print((char) singleCharacters);
            }
            readParrot.close();
        } catch (IOException except) {
            except.printStackTrace();
        }

        System.out.println("");
	}
	
    public static void main(String[] args) {
        String userInputString;
        Scanner userInput = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke\n" +
                " What can I do for you? c(＞ω＜)ゞ");
        System.out.println("I'm currently in parrot mode! ");

        printParrotText();

        while (true) {
            userInputString = userInput.nextLine();

            if (userInputString.equals("bye")) {
                break;
            }

            System.out.print(" ");
            System.out.println(userInputString);
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!ヾ(´￢｀)ﾉ");
        System.out.println("____________________________________________________________");
    }
}
