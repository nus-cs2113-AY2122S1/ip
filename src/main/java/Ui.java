/**
 * This class handles the User Interface of Duke.
 *
 * @author richwill28
 */
public abstract class Ui {
    private static final String LOGO =
            "     ,---.    ,---.   ____    .-./`)  ______          " + System.lineSeparator()
            + "     |    \\  /    | .'  __ `. \\ .-.')|    _ `''.    " + System.lineSeparator()
            + "     |  ,  \\/  ,  |/   '  \\  \\/ `-' \\| _ | ) _  \\" + System.lineSeparator()
            + "     |  |\\_   /|  ||___|  /  | `-'`\"`|( ''_'  ) |   " + System.lineSeparator()
            + "     |  _( )_/ |  |   _.-`   | .---. | . (_) `. |     " + System.lineSeparator()
            + "     | (_ o _) |  |.'   _    | |   | |(_    ._) '     " + System.lineSeparator()
            + "     |  (_,_)  |  ||  _( )_  | |   | |  (_.\\.' /     " + System.lineSeparator()
            + "     |  |      |  |\\ (_ o _) / |   | |       .'      " + System.lineSeparator()
            + "     '--'      '--' '.(_,_).'  '---' '-----'`         " + System.lineSeparator();

    private static final String LINE =
            "    ____________________________________________________________" + System.lineSeparator();

    private static final String PADDING = "     ";

    public String getLogo() {
        return LOGO;
    }

    public String getLine() {
        return LINE;
    }

    public String getPadding() {
        return PADDING;
    }

    /**
     * Displays logo and greets user.
     */
    public static void greet() {
        System.out.print(LINE);
        System.out.println(LOGO);
        System.out.println(PADDING + "Hello! I'm your personal maid. Call me Maid-chan!");
        System.out.println(PADDING + "What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Prints error message.
     */
    public static void error() {
        System.out.print(LINE);
        System.out.println(PADDING + "Sorry.. I don't understand your command.");
        System.out.println(LINE);
    }

    /**
     * Says goodbye to user.
     */
    public static void bye() {
        System.out.print(LINE);
        System.out.println(PADDING + "Bye. Hope to see you again soon!");
        System.out.print(LINE);
    }
}
