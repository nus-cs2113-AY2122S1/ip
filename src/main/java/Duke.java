import com.sun.source.tree.WhileLoopTree;
import java.util.Scanner;

public class Duke {

    private static final String LINE = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";


    /**
     * Prints given string in the middle of 2 horizontal lines.
     *
     * @param message String to be printed
     */
    public static void printMessage(String message){
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public static void EchoUserInput(){

    }
    public static void main(String[] args) {
        String welcomeMessage = "Hello! I'm Gaben.\n"
                + "Steam sales is coming! Prepare your wallet.\n"
                + "Anyways, what can I do for you today?";
        String exitMessage = "Thank you for using Gaben.\n"
                + "Remember to keep your wallet stacked before using me again!";

        printMessage(welcomeMessage);

        Scanner in = new Scanner(System.in);
        while (true){
            String userInput = in.nextLine();
            if(userInput.equalsIgnoreCase("bye")){
                printMessage(exitMessage);
                break;
            }
            else{
                printMessage(userInput);
            }
        }

    }
}
