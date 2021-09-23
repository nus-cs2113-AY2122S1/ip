package shima.design;

import java.util.ArrayList;

public class Default {
    public static final String CURR_VERSION = "Version 7.0";

    /**
     * Displays all the welcome screens and designs when the program starts
     */
    public Default(){

    }

    /**
     * Print the logo and greeting message
     */
    public static void printLogo() {
        //print the logo when the program starts
        System.out.println("\t              ##*                                 ");
        System.out.println("\t   .      . .&####%                               ");
        System.out.println("\t    #%%%%((&%######%**.                           ");
        System.out.println("\t    %#############%#%&&%%%%%##(*.                 ");
        System.out.println("\t . .%%#######%%%%%##############%%%%#(.           ");
        System.out.println("\t/##&@%####&%####&#####%&&&&&&&&&%%%####&/  .      ");
        System.out.println("\t&######%%######%&%%%#################%%%%&#       ");
        System.out.println("\t(@####&#####%&%########################%###@(     ");
        System.out.println("\t,&%#%%%%##%%############%%%%%%%%%%%##########%.   ");
        System.out.println("\t*&#&%%&#%&########&%#(%#/((((((/****#&%&%#####%,  ");
        System.out.println("\t  ,&#%%%%#####%%#(%(((&#(&(((((((((/%#(((%##%%#%. ");
        System.out.println("\t  (%##%%####%%(((/%&((%&#%##((((((((&#(((%#(((&&, ");
        System.out.println("\t  (%#%&####%#((##(#(/###.%#.*(##(((%, /##((%#(#( .");
        System.out.println("\t  *&(&%###%#(((((#/@%%%&,. ...     &%%&%  (&###*  ");
        System.out.println("\t . (%%%###%(((((#/#####&, .. .... /&(#(&,.%(((#(  ");
        System.out.println("\t    *&&##%%(((((%*(%###%. .. .. . *%(##&, (%((#(  ");
        System.out.println("\t      (&#%%((((#%/ .**,. ..... . ...,,,. . (#(%(  ");
        System.out.println("\t        .(%(((((&/ .   .  ...... .. .. . .(%/#%,  ");
        System.out.println("\t          .(%(((#&*,,.           . .,*/*%#((#&*   ");
        System.out.println("\t              ,/(#(    .,,******,.     ####%*     ");
    }

    /**
     * Print the welcome message and the ASCII art when the program starts
     */
    public static void printWelcomeMessage() {
        System.out.println("   __          ________ _      _____ ____  __  __ ______    ");
        System.out.println("   \\ \\        / /  ____| |    / ____/ __ \\|  \\/  |  ____|  ");
        System.out.println("    \\ \\  /\\  / /| |__  | |   | |   | |  | | \\  / | |__     ");
        System.out.println("     \\ \\/  \\/ / |  __| | |   | |   | |  | | |\\/| |  __|    ");
        System.out.println("      \\  /\\  /  | |____| |___| |___| |__| | |  | | |____   ");
        System.out.println("     __\\/_ \\/   |______|______\\_____\\____/|_|  |_|______|_ ");
        System.out.println("    / ____| |  | |_   _|  \\/  |   /\\   |  __ \\|_   _| \\ | |");
        System.out.println("   | (___ | |__| | | | | \\  / |  /  \\  | |__) | | | |  \\| |");
        System.out.println("    \\___ \\|  __  | | | | |\\/| | / /\\ \\ |  _  /  | | | . ` |");
        System.out.println("    ____) | |  | |_| |_| |  | |/ ____ \\| | \\ \\ _| |_| |\\  |");
        System.out.println("   |_____/|_|  |_|_____|_|  |_/_/    \\_\\_|  \\_\\_____|_| \\_|");
        //greeting
        System.out.println("\nHi there! My name is Shima Rin and I am a chat robot that can help you do some wonderful jobs!");
    }

    /**
     * Show the current version's functionality of the bot
     */
    public static void printVersionDescription() {
        //Uses list to store all the version information
        ArrayList<String> versionDescriptions = new ArrayList<>();
        int maxDescriptionsLength = 0;
        versionDescriptions.add("* " + CURR_VERSION);
        versionDescriptions.add("* To know more about me, you can view my profile by typing the command \"view -p\"");
        versionDescriptions.add("* I can help you create a to-do list, you can perform tasks addition, deletion, and mark tasks as done");
        versionDescriptions.add("* In addition, I will memorise all the tasks that you entered, so that you can refer back when you revisit me :)");
        versionDescriptions.add("* You can type \"todo\" or \"deadline\" or \"event\" to create a task and I will help you save it automatically!");
        versionDescriptions.add("* You can type \"list\" or \"ls\" to list all the tasks that are waiting to do");
        versionDescriptions.add("* You can type \"done i\" where i is the index of the task to mark the specific task as done");
        versionDescriptions.add("* You can type \"delete i\" where i is the index of the task to delete the task whenever necessary");
        versionDescriptions.add("* You can type \"exit\" or \"bye\" to stop me and exit the program");
        versionDescriptions.add("* You can type \"help\" or \"view -h\" for more information about how to use me (YES the bot), ");
        //Finds the length of the longest description to align all '*' displayed
        for (String str : versionDescriptions) {
            if (str.length() > maxDescriptionsLength) {
                maxDescriptionsLength = str.length();
            }
        }
        //Draws the frame for the version description
        for (int i = 0; i < maxDescriptionsLength + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (String str : versionDescriptions) {
            //Uses format string to print the '*' at the correct position after each sentence is completed
            System.out.printf("%1$-" + (maxDescriptionsLength + 1) + "s", str);
            System.out.println("*");
        }
        for (int i = 0; i < maxDescriptionsLength + 2; i++) {
            System.out.print("-");
        }
    }

    /**
     * Shows the formatted message string
     *
     * @param message The message to print
     */
    public static void showMessage(String message) {
        System.out.print("\t@");
        for (int i = 0; i < message.length() + 4; i++) {
            System.out.print("-");
        }
        System.out.println("@");
        System.out.println("\t   " + message);
        System.out.print("\t@");
        for (int i = 0; i < message.length() + 4; i++) {
            System.out.print("-");
        }
        System.out.println("@");
    }
}
