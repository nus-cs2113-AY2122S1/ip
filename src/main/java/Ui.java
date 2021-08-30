class Ui {
    /** Decorative logo */
    public static final String LOGO =
            "     ,---.    ,---.   ____    .-./`)  ______           \n" +
            "     |    \\  /    | .'  __ `. \\ .-.')|    _ `''.     \n" +
            "     |  ,  \\/  ,  |/   '  \\  \\/ `-' \\| _ | ) _  \\ \n" +
            "     |  |\\_   /|  ||___|  /  | `-'`\"`|( ''_'  ) |    \n" +
            "     |  _( )_/ |  |   _.-`   | .---. | . (_) `. |      \n" +
            "     | (_ o _) |  |.'   _    | |   | |(_    ._) '      \n" +
            "     |  (_,_)  |  ||  _( )_  | |   | |  (_.\\.' /      \n" +
            "     |  |      |  |\\ (_ o _) / |   | |       .'       \n" +
            "     '--'      '--' '.(_,_).'  '---' '-----'`          \n";

    /** Decorative line */
    public static final String LINE =
            "    ____________________________________________________________\n";

    /** Decorative padding */
    public static final String PADDING = "     ";

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
