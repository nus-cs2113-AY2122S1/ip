package kitty.userinterface;

import kitty.KittyException;

import java.util.Scanner;

public class Ui {
    public static String userInput;
    public static String command;
    public static Scanner in = new Scanner(System.in);

    // Constants
    public static final String CAT_1 = " |\\__/,|   (`\\\n" +
                                        " |_ _  |.--.) )\n" +
                                        " ( T   )     /\n" +
                                        "(((^_(((/(((_/";
    public static final String CAT_2 = "  /\\_/\\  (\n" +
                                        " ( ^.^ ) _)\n" +
                                        "   \\\"/  (\n" +
                                        " ( | | )\n" +
                                        "(__d b__)";
    public static final String CAT_3 = "                       /)\n" +
                                        "              /\\___/\\ ((\n" +
                                        "              \\`@_@'/  ))\n" +
                                        "              {_:Y:.}_//\n" +
                                        "    ----------{_}^-'{_}----------";
    public static final String CAT_4 = "──────▄▀▄─────▄▀▄\n" +
                                        "─────▄█░░▀▀▀▀▀░░█▄\n" +
                                        "─▄▄──█░░░░░░░░░░░█──▄▄\n" +
                                        "█▄▄█─█░░▀░░┬░░▀░░█─█▄▄█\n";
    public static final String CAT_LOGO = "___$$$_____________$$$\n" +
                                            "__$___$___________$___$\n" +
                                            "_$_____$_________$_____$\n" +
                                            "_$_$$___$$$$$$$$$___$$_$\n" +
                                            "_$_$$$___$______$__$$$_$\n" +
                                            "_$_$__$__$______$_$__$_$\n" +
                                            "_$_$__$$$________$$__$_$\n" +
                                            "_$_$$$_____________$$$_$\n" +
                                            "_$_$_________________$_$\n" +
                                            "__$___________________$\n" +
                                            "__$___________________$\n" +
                                            "_$_____________________$\n" +
                                            "_$____$$_________$$____$\n" +
                                            "$____$_$$_______$_$$____$\n" +
                                            "$____$o_$_______$o_$____$\n" +
                                            "$_____$$___$$$___$$_____$\n" +
                                            "_$_______$__$__$_______$\n" +
                                            "__$_______$$_$$_______$\n" +
                                            "___$_________________$\n" +
                                            "____$$_____________$$\n" +
                                            "______$$$$$$$$$$$$$\n" +
                                            "________$_______$\n" +
                                            "_______$_________$\n" +
                                            "___$$$_$_________$_$$$\n" +
                                            "__$___$$___$$$___$$___$\n" +
                                            "__$____$___$_$___$____$\n" +
                                            "__$____$$$$$_$$$$$____$\n" +
                                            "__$____$___$_$___$____$\n" +
                                            "___$$$$$$$$___$$$$$$$$\n";
    public static final String CAT_EXIT = "      |\\      _,,,---,,_\n" +
                                        "ZZZzz /,`.-'`'    -.  ;-;;,_\n" +
                                        "     |,4-  ) )-,_. ,\\ (  `'-'\n" +
                                        "    '---''(_/--'  `-'\\_)";
    public static final String CAT_ERROR = "    /\\_/\\           ___\n" +
                                            "   = o_o =_______    \\ \\\n" +
                                            "    __^      __(  \\.__) )\n" +
                                            "(@)<_____>__(_____)____/";
    public static final String BAR_LINE = "==========================================================";

    // Methods
    public static void printCat1() {
        System.out.println(CAT_1);
    }

    public static void printCat2() {
        System.out.println(CAT_2);
    }

    public static void printCat3() {
        System.out.println(CAT_3);
    }

    public static void printCat4() {
        System.out.println(CAT_4);
    }

    public static void printCatLogo() {
        System.out.println(CAT_LOGO);
    }

    private static void printCatExit() {
        System.out.println(CAT_EXIT);
    }

    private static void printCatError() {
        System.out.println(CAT_ERROR);
    }

    public static void printBarLine() {
        System.out.println(BAR_LINE);
    }

    public static void greet() {
        printCatLogo();
        printBarLine();
        printBarLine();
        System.out.println("Hey there! I'm Kitty!");
        System.out.println("What can I do for you?");
        printBarLine();
        printBarLine();
        System.out.println("Here are some things that I can do:");
        printBarLine();
        System.out.println("Todo: todo *insert task here*");
        System.out.println("Deadline: deadline *insert task here* /by *insert deadline here*");
        System.out.println("Event: event *insert task here* /at *insert event date here*");
        printBarLine();
        printCat4();
        printBarLine();
    }

    public static void getUserInput() throws KittyException {
        try {
            System.out.print("You: ");
            userInput = in.nextLine();
            command = userInput.split(" ")[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KittyException("Invalid Input!");
        }
    }

    public static void exit() {
        System.out.println();
        System.out.println("Nap time!! Yawn...");
        printCatExit();
        printBarLine();
        System.exit(0);
    }

    public static void echo(String line) {
        System.out.println();
        System.out.println(line);
        printCat2();
        printBarLine();
    }

    public static void printErrorMessage() {
        System.out.println();
        System.out.println("Oops something went wrong! Please try again!");
        printCatError();
        printBarLine();
    }
}
