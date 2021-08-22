import java.util.Scanner;

public class Kitty {
    public static boolean isConvoOver = false;
    public static List[] list = new List[100];
    public static int totalTasksCount = 0;

    //Getters


    //Setters


    //Methods
    public static void printCat() {
        System.out.println( "──────▄▀▄─────▄▀▄\n" +
                            "─────▄█░░▀▀▀▀▀░░█▄\n" +
                            "─▄▄──█░░░░░░░░░░░█──▄▄\n" +
                            "█▄▄█─█░░▀░░┬░░▀░░█─█▄▄█\n");
    }

    public static void greet() {
        System.out.println("_______________________________");
        System.out.println("Hey there! I'm Kitty!");
        System.out.println("What can I do for you?");
        printCat();
        System.out.println("_______________________________");
    }

    public static void exit() {
        System.out.println();
        System.out.println("Nap time!! Yawn...");
        System.out.println("      |\\      _,,,---,,_\n" +
                                "ZZZzz /,`.-'`'    -.  ;-;;,_\n" +
                                "     |,4-  ) )-,_. ,\\ (  `'-'\n" +
                                "    '---''(_/--'  `-'\\_)");
        System.out.println("_______________________________");
    }

    public static void echo(String line) {
        System.out.println();
        System.out.println(line);
        System.out.println("  /\\_/\\  (\n" +
                            " ( ^.^ ) _)\n" +
                            "   \\\"/  (\n" +
                            " ( | | )\n" +
                            "(__d b__)");
        System.out.println("_______________________________");
    }

    public static void printList(List[] list) {
        System.out.println();
        for (int i = 0; i < totalTasksCount; i++) {
            System.out.println(i+1 + ". " + list[i].getTaskName());
        }
        System.out.println(" |\\__/,|   (`\\\n" +
                            " |_ _  |.--.) )\n" +
                            " ( T   )     /\n" +
                            "(((^_(((/(((_/");
        System.out.println("_______________________________");
    }

    public static void addToList(String line) {
        System.out.println();
        System.out.println("Added: " + line);
        System.out.println("  /\\_/\\  (\n" +
                            " ( ^.^ ) _)\n" +
                            "   \\\"/  (\n" +
                            " ( | | )\n" +
                            "(__d b__)");
        System.out.println("_______________________________");
        list[totalTasksCount] = new List(line);
        totalTasksCount++;
    }

    public static void main(String[] args) {
        String logo =
                "___$$$_____________$$$\n" +
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
        System.out.println(logo);

        // Conversation begins
        greet();
        while (!isConvoOver) {
            String line;
            Scanner in = new Scanner(System.in);

            System.out.print("You: ");
            line = in.nextLine();

            switch (line) {
            case "bye":
                isConvoOver = true;
                break;
            case "list":
                printList(list);
                break;
            default:
                addToList(line);
                break;
            }

        }
        exit();
    }
}
