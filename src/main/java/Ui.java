/**
 * Contains methods that help to print out strings more easily
 */
public class Ui {

    /**
     * prints a string with a tab prepended to it and a newline appended to it
     *
     * @param str string to print
     */
    public static void printlnTab(String str) {
        System.out.println("\t" + str);
    }

    /**
     * prints a text divider to separate blocks of text in the program
     */
    public static void printDivider() {
        System.out.println("\t__________________________________________________________________________________________");
        System.out.println();
    }

    /**
     * Prints when program is just started by the user
     */
    public static void welcomeMessage() {
        String logo = "\t                ▄▄▄█▄▄▄▄▄▄▄▄▄▄▄██▄▄▄                                                     \n"
                + "\t          ▄▄████████████████▄███████▄▄▄▄▄                                                \n"
                + "\t       ▄▄▄████████▄████████▄█████████████▄▄▄                                             \n"
                + "\t    ▄▄▄██████████████████████████████████████▄                                           \n"
                + "\t   █████████████████████████████████████▄██████▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄                         \n"
                + "\t ▄████████▄██████████████▄████████████████████▄▄████████████████▄▄▄▄                     \n"
                + "\t   ▀▀▀▀▀▀▀█████████████████████▄████████████▄███████████████████████▄▄▄                  \n"
                + "\t             ▀██████████▄█████████████▄████▄███████████████████████████▄▄           ▄▄   \n"
                + "\t                ▀▀▄▄▄█████████████████████▄██████████████████████████████▄▄      ▄▄█▄█   \n"
                + "\t                     ▀▀▀███████▄▄▄▄▄▄▄█████████████████████████████████████▄▄  ▄▄█████   \n"
                + "\t                                        ▀▄███████████████████▄████▄▄▄▄▄▄▄█▄▄▄▄▄▄▄█▄██    \n"
                + "\t                                         ██████████▄▄█████▄███████▄████████▄██████▄▄█    \n"
                + "\t                                         ███████████▄▄██████████▄██▄█████▄████▄▄▄████    \n"
                + "\t                                         ▀▄██████████████████████▄▄█████▄█▄██▄▄█████▄    \n"
                + "\t                                          ████████████▄▄██████████▄▄██▄▄█▄█▄▄██▄▄███▄    \n"
                + "\t                                           ▀▄████████████████▄▄▄█▄███▄▄████▄▄██▄▄▄▄▄▄▄   \n"
                + "\t                                            ▀▄██████▄▄███████▄▄▄▄▄██▄█▄▄█████▄▄▄▄█████▄▄ \n"
                + "\t                                              ████▄▄████▄▄▀▀▄█▄█▀ ▀▄▄▄▄▀   ▀▄▄▄▄██▄▄▄▄▄██\n"
                + "\t                                               █▄▄██▄▀▀▀     ▀▀▀▀   ▀▄▄▄       ▀▀▀▀▀     \n"
                + "\t                                              ▄▄██▄▄▄▄              ▄███                 \n"
                + "\t                                              ██▄▀▄▀▄               ▄█▀█                 \n"
                + "\t                                              █▀█▀▀                   ▀▀                 \n"
                + "\t                                              ▀  ▀                                       \n";


        printDivider();
        System.out.println(logo);
        printlnTab("Hello! I'm Ricky the Task Racoon!\n\tHow can I help you?");
        printDivider();
    }
}