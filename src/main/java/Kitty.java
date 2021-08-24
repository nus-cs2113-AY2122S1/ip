import java.util.Scanner;

public class Kitty {
    public static boolean isConvoOver = false;
    public static Task[] task = new Task[100];
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

    public static void printList(Task[] task) {
        System.out.println();
        System.out.println("Here are the tasks you have!");
        for (int i = 0; i < totalTasksCount; i++) {
            System.out.print(i+1 + ".");
            if (task[i].isDone()) {
                System.out.print("[X] ");
            } else {
                System.out.print("[ ] ");
            }
            System.out.println(task[i].getTaskName());
        }
        System.out.println();
        System.out.println(" |\\__/,|   (`\\\n" +
                            " |_ _  |.--.) )\n" +
                            " ( T   )     /\n" +
                            "(((^_(((/(((_/");
        System.out.println("_______________________________");
    }

    public static void addToList(String line) {
        System.out.println();
        System.out.println("Added: " + line);
        System.out.println();
        System.out.println("  /\\_/\\  (\n" +
                            " ( ^.^ ) _)\n" +
                            "   \\\"/  (\n" +
                            " ( | | )\n" +
                            "(__d b__)");
        System.out.println("_______________________________");
        task[totalTasksCount] = new Task(line);
        totalTasksCount++;
    }

    public static boolean hasDone(String line) {
        return line.split(" ")[0].equals("done");
    }

    public static void markTask(String line) {
        int taskNum = Integer.parseInt(line.split(" ")[1]);
        task[taskNum-1].setDone();

        System.out.println();
        System.out.println("Good Job!! One more thing off your list!!");
        System.out.println("  [X] " + task[taskNum-1].getTaskName());
        System.out.println("                       /)\n" +
                "              /\\___/\\ ((\n" +
                "              \\`@_@'/  ))\n" +
                "              {_:Y:.}_//\n" +
                "    ----------{_}^-'{_}----------");
        System.out.println("_______________________________");
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
                printList(task);
                break;
            default:
                if (hasDone(line)) {
                    markTask(line);
                } else{
                    addToList(line);
                }
                break;
            }

        }
        exit();
    }
}
