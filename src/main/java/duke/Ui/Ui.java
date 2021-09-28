package duke.Ui;

import duke.ArtBot.Logo;
import java.util.Scanner;

/**
 * Handles the Interaction between the user and the program
 * Contains the function that greets the user when program is started
 * Contains the function that bid farewell to the user when the program ends
 * Contains the function that tell the user what went wrong when an error is encountered
 * Contains the function that print a line for readability
 */
public class Ui {

    private static final String MESSAGE_HI = "Hello! I'm Duke\n" + "What can I do for you?\n" + "!help for Command List\n";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!\n";

    /**
     * Called right at the start of the program
     * Greet the user with a message
     * Handles the message that is printed to the user
     */
    public void Greetings(){
        System.out.println(Logo.logo + Logo.divider + MESSAGE_HI + Logo.dividerWithoutNewLine);
    }

    /**
     * Called when the program exit
     * Print a farewell message to the user
     * Handles the message to be printed to the user
     */
    public void Farewell(){
        System.out.println(MESSAGE_BYE + Logo.bye);
    }

    /**
     * Called before the input is process and after
     * Draw a line before and after the response to an input
     * Improve readability when using the program
     */
    public void showLine(){
        System.out.println(Logo.dividerWithoutNewLine);
    }

    /**
     * A function to read in input from user
     *
     * @return input from user in String
     */
    public String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine().toLowerCase().trim();
    }

    /**
     * Called when an error is encountered
     * Print error message to the user
     *
     * @param error Error Message in String to print to user
     */
    public void showError(String error){
        System.out.println(error);
    }
}

