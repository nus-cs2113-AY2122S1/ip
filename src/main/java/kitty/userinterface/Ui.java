package kitty.userinterface;

import kitty.Kitty;
import kitty.KittyException;
import kitty.task.Task;
import kitty.task.Todo;
import kitty.task.Deadline;
import kitty.task.Event;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

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
    public static final String INTRO_MESSAGE = CAT_LOGO + "\n"
                                                + BAR_LINE + "\n"
                                                + BAR_LINE + "\n"
                                                + "Hey there! I'm Kitty!\n"
                                                + "What can I do for you?\n"
                                                + BAR_LINE + "\n"
                                                + BAR_LINE;
    public static final String HELP_MESSAGE = "Here are some things that I can do:\n"
                                            + BAR_LINE + "\n"
                                            + "Todo: todo **INSERT TASK HERE**\n"
                                            + "Deadline: deadline **INSERT TASK HERE** /by **INSERT DEADLINE HERE**\n"
                                            + "Event: event **INSERT TASK HERE** /at **INSERT EVENT DATE HERE**\n"
                                            + BAR_LINE + "\n"
                                            + CAT_4;
    public static final String EXIT_MESSAGE = "\n" + "Nap time!! Yawn...\n"
                                            + CAT_EXIT + "\n"
                                            + BAR_LINE + "\n";
    public static final String ERROR_MESSAGE = "\nOops something went wrong! Please try again!\n"
                                            + CAT_ERROR;
    public static final String DATA_PATH = "src/main/java/kitty/userinterface/data.txt";

    // Methods
    public static void initData() throws KittyException{
        try {
            File f = new File(DATA_PATH);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String rawData = s.nextLine();
                String type = rawData.substring(0, rawData.indexOf("|"));
                String status = rawData.substring(rawData.indexOf("|") + 1, rawData.indexOf("|") + 2);
                String task = rawData.substring(rawData.indexOf("|") + 3);

                // Add Tasks
                switch (type) {
                case "T":
                    Kitty.tasks.add(new Todo(task));
                    if (status.equals("1")) {
                        Kitty.tasks.get(Kitty.tasks.size() - 1).setDone();
                    }
                    break;
                case "D":
                    String deadlineName = task.substring(0, task.indexOf("|"));
                    String deadlineDate = task.substring(task.indexOf("|") + 1);
                    Kitty.tasks.add(new Deadline(deadlineName, deadlineDate));
                    if (status.equals("1")) {
                        Kitty.tasks.get(Kitty.tasks.size() - 1).setDone();
                    }
                    break;
                case "E":
                    String eventName = task.substring(0, task.indexOf("|"));
                    String eventDate = task.substring(task.indexOf("|") + 1);
                    Kitty.tasks.add(new Event(eventName, eventDate));
                    if (status.equals("1")) {
                        Kitty.tasks.get(Kitty.tasks.size() - 1).setDone();
                    }
                    break;
                default:
                    throw new KittyException("Invalid Raw Data!");
                }
            }
        } catch (FileNotFoundException e) {
            throw new KittyException("File not found!");
        }
    }

    public static void greet() {
        System.out.println(INTRO_MESSAGE);
        System.out.println(HELP_MESSAGE);
    }

    public static void getUserInput() throws KittyException {
        try {
            System.out.println(BAR_LINE);
            System.out.print("You: ");
            userInput = in.nextLine();
            command = userInput.split(" ")[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KittyException("Invalid Input!");
        }
    }

    public static void printAddedTask() {
        System.out.println();
        System.out.println("Added: " + Kitty.tasks.get(Kitty.tasks.size() - 1));
        System.out.println();
        System.out.println(Ui.CAT_2);
    }

    public static void exit() {
        System.out.println(EXIT_MESSAGE);
        System.exit(0);
    }

    public static void printErrorMessage() {
        System.out.println(ERROR_MESSAGE);
    }
}
