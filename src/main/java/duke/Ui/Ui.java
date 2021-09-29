package duke.Ui;

import duke.ArtBot.Logo;
import java.util.Scanner;

/**
 * Handles Interaction between user and program
 * Contains function that greets user when program start
 * Contains function that bid farewell to user when program ends
 * Contains function that tell user what went wrong when an error is encountered
 * Contains function that print a line for readability
 */
public class Ui {

    private static final String MESSAGE_HI = "Hello! I'm Duke\n" + "What can I do for you?\n" + "!help for Command List\n";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!\n";

    /**
     * Called at start of program
     * Greet user with message
     * Handles message that is printed to user
     */
    public void Greetings(){
        System.out.println(Logo.logo + Logo.divider + MESSAGE_HI + Logo.dividerWithoutNewLine);
    }

    /**
     * Called when program exit
     * Print a farewell message to user
     * Handles message to be printed to user
     */
    public void Farewell(){
        System.out.println(MESSAGE_BYE + Logo.bye);
    }

    /**
     * Called before input is process and after
     * Draw a line before and after response of input
     * Improve readability of program
     */
    public void showLine(){
        System.out.println(Logo.dividerWithoutNewLine);
    }

    /**
     * Read in input from user
     * Remove Spaces and convert to lower case
     *
     * @return input from user in String
     */
    public String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine().toLowerCase().trim();
    }

    /**
     * Called when error encountered
     * Print error message to user
     *
     * @param error Error Message to print
     */
    public void showError(String error){
        System.out.println(error);
    }
}

