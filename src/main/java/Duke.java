import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    protected static boolean isOnline;

    private static void startDuke(){
        isOnline = true;
        System.out.println(Logo.logo +Logo.divider + "Hello! I'm Duke\n" + " What can I do for you?\n" + Logo.divider);
    }

    private static void endDuke(){
        System.out.println(Logo.divider + "Bye. Hope to see you again soon!\n" + Logo.divider + Logo.bye);
    }

    public static void main(String[] args) {
        startDuke();
        Scanner in = new Scanner(System.in);
        ArrayList <Task> list = new ArrayList<>();
        ListManager listManager = new ListManager(list);
        while(isOnline) {
            String userInput = in.nextLine().toLowerCase().trim();
            InputHandler inputManager = new InputHandler(userInput, listManager);
            inputManager.handleInput();
        }
        endDuke();
    }
}
