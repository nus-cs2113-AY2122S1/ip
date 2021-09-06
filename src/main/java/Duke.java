import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String MESSAGE_HI = "Hello! I'm Duke\n" + "What can I do for you?\n" + "!help for Command List\n";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!\n";

    protected static boolean isOnline;

    private static void startDuke(){
        isOnline = true;
        System.out.println(Logo.logo +Logo.divider + MESSAGE_HI + Logo.divider);
    }

    private static void endDuke(){
        System.out.println(Logo.divider + MESSAGE_BYE + Logo.divider + Logo.bye);
    }

    public static void main(String[] args) {
        startDuke();
        Scanner in = new Scanner(System.in);
        ArrayList <Task> list = new ArrayList<>();
        ListManager listManager = new ListManager(list);
        while(isOnline) {
            String userInput = in.nextLine().toLowerCase().trim();
            if(userInput.startsWith("!")){
                CommandManager commandManager = new CommandManager(userInput);
                commandManager.handleCommand();
            }else {
                InputHandler inputManager = new InputHandler(userInput, listManager);
                inputManager.handleInput();
            }
        }
        endDuke();
    }
}
