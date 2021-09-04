/**
 * This class handles the User Interface of Duke.
 *
 * @author richwill28
 */
public class Ui {
    public static final String LOGO =
            "     ,---.    ,---.   ____    .-./`)  ______          " + System.lineSeparator()
            + "     |    \\  /    | .'  __ `. \\ .-.')|    _ `''.    " + System.lineSeparator()
            + "     |  ,  \\/  ,  |/   '  \\  \\/ `-' \\| _ | ) _  \\" + System.lineSeparator()
            + "     |  |\\_   /|  ||___|  /  | `-'`\"`|( ''_'  ) |   " + System.lineSeparator()
            + "     |  _( )_/ |  |   _.-`   | .---. | . (_) `. |     " + System.lineSeparator()
            + "     | (_ o _) |  |.'   _    | |   | |(_    ._) '     " + System.lineSeparator()
            + "     |  (_,_)  |  ||  _( )_  | |   | |  (_.\\.' /     " + System.lineSeparator()
            + "     |  |      |  |\\ (_ o _) / |   | |       .'      " + System.lineSeparator()
            + "     '--'      '--' '.(_,_).'  '---' '-----'`         " + System.lineSeparator();

    public static final String LINE =
            "    ____________________________________________________________" + System.lineSeparator();

    public static final String PADDING = "     ";

    /** Displays logo and greets user */
    public static void printGreeting() {
        System.out.print(LINE);
        System.out.println(LOGO);
        System.out.println(PADDING + "Hello! I'm your personal maid. Call me Maid-chan!");
        System.out.println(PADDING + "What can I do for you?");
        System.out.println(LINE);
    }

    /** Says goodbye to user. */
    public static void printGoodbye() {
        System.out.print(LINE);
        System.out.println(PADDING + "Bye. Hope to see you again soon!");
        System.out.print(LINE);
    }
}
