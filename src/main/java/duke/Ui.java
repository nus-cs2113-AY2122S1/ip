package duke;

public class Ui {
    private static final String LS = System.lineSeparator();
    private static final String S_TAB = "     ";
    private static final String LINE = "    ─────────────────────────────────────────────────────────────────────────";
    private static final String RIMURU = "                                               *.\n"
            + "                     .                          *,,,,,,,,,\n"
            + "                   (           ..##%            **********,*,,,\n"
            + "               . #. . .........##%. ...... . ..///****************.\n"
            + "             ...#.......##/...................(/////*****************\n"
            + "           ..#%....%.........................((//////////////**********\n"
            + "         ..%..&..%%%.......................(((///////////////////*//*/***\n"
            + "       ...%......%#....%%&&...............(((///////////////////////////**\n"
            + "      #%*....%,......&&.,..&&..........,#((///////////////////////////////**\n"
            + "     ...%%#,.,,...&&#%&&,.......,.....#(((//////////////////////////////////*\n"
            + "    ......,,,,,&%,,,,,,....,.,,.,,,/#(((//(//////////////////////////////////\n"
            + "   ......,,,,,,&.&,,,,,,,.,,.,,,,##(((/((((///(//////////////////////////////%\n"
            + "   ,,,,,,,,,,,&,,,&,,,,,.,,,,,%#(((((((((((///(////////////////////////////&(/\n"
            + "   ,,,,,,,,,.,,,,,,%,,,,,,,%#((((((((((((((((((((//////////////////////(/&////\n"
            + "    ,,,,,,,,,,,,,,,,,,,%%#(((((((((((((((((((((((/(///////////////////@&(/////\n"
            + "      ,,,,,,,,,,,,%%###(((((((((#&@@@@@@@@@@@@@@&@&&&&%%%&#((&///////////////*\n"
            + "         /(((######((((((((((((((((((((((((((((((((((/((/(((/(///(//////////*\n"
            + "          ((((((((((((((((((((((((((((((((((((((((((((((////((/////////////*\n"
            + "           (((((((((((((((((((((((((((((((((((((((((((((((((/(/((/////////*\n"
            + "            /(((((((((((((((((((((((((((((((((((((((((((((((((/////////*/\n"
            + "              (/((((((((((((((((((((((((((((((((((((((((((((((/(/////*\n"
            + "                 (/((((((((((((((((((((((((((((((((((((((((((/////\n"
            + "                     ./(((((((((((((((((((((((((((((((((////*\n"
            + "                             *//(((((((((((((((((//*\n";

    private static final String MESSAGE_WELCOME = S_TAB + "Welcome to Jura Tempest!" + LS
            + S_TAB + "I'm Rimuru Tempest, pleased to make your acquaintance." + LS
            + S_TAB + "How can I help you today?";
    private static final String MESSAGE_GOODBYE = S_TAB + "Sayonara. Come visit Jura Tempest again soon!";

    private static final String ERROR_LOAD = S_TAB + "File not found. A new list will be started.";

    /**
     * Prints a divider line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints a welcome message.
     */
    public void showWelcome() {
        System.out.println(RIMURU);
        System.out.println(MESSAGE_WELCOME);
        showLine();
    }

    /**
     * Prints a goodbye message.
     */
    public void showGoodbye() {
        showLine();
        System.out.println(MESSAGE_GOODBYE);
        showLine();
    }

    /**
     * Prints an error message.
     *
     * @param errorMessage Error message (usually from DukeException)
     */
    public void showError(String errorMessage) {
        showLine();
        System.out.println(errorMessage);
        showLine();
    }

    /**
     * Prints a file load error message.
     */
    public void showLoadingError() {
        showLine();
        System.out.println(ERROR_LOAD);
        showLine();
    }

}
