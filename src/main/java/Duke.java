import java.util.Scanner;

public class Duke {
    private static final String DIVISIONLINE = "    ____________________________________________________________\n";
    private static final String GREETINGS = "     Hello! I'm Duke\n" + "     What can I do for you?\n";
    private static final String BYE = "     Bye. Hope to see you again soon!\n";


    public static void main (String[] args) {

        User newUser = new User();
        newUser.serviceBegin();
        newUser.serviceEnd();

    }
}


