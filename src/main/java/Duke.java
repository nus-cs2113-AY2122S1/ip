import java.util.Scanner;
import java.util.Random;

public class Duke {
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
        System.out.println("\nKonichiwa! Hi there! My name is Shima Rin and I am a chat robot that can help you do some wonderful jobs!");
    }

    /**
     * Print the formatted to-do list whenever the user type 'list' or 'ls'
     *
     * @param tasks     the array of class Task instance which stores all the tasks added by the user
     * @param stopIndex the last index of the array that is not null
     */
    public static void printList(Task[] tasks, int stopIndex) {
        int maxLength = 0;
        for (int i = 0; i < stopIndex; i++) {
            if (tasks[i].getTask().length() > maxLength) {
                maxLength = tasks[i].getTask().length();
            }
        }
        //print the to-do list
        System.out.print("\t╭");
        if (maxLength < "My to-do list: ".length()) {
            maxLength = "My to-do list".length();
        }
        for (int i = 0; i < maxLength + "| [ ] 100. ".length(); i++) {
            System.out.print("-");
        }
        System.out.println("╮");
        System.out.print("\t|My to-do list: ");
        for (int i = 0; i < maxLength + "| [ ] 100. ".length() - "|My to-do list: ".length() + 1; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
        for (int i = 0; i < stopIndex; i++) {
            if (tasks[i].getDone()) {
                System.out.print("\t| [X] " + (i + 1) + ". " + tasks[i].getTask());
            } else {
                System.out.print("\t| [ ] " + (i + 1) + ". " + tasks[i].getTask());
            }
            for (int j = 0; j < maxLength + "| [ ] 100. ".length() - ("| [ ] " + (i + 1) + ". " + tasks[i].getTask()).length() + 1; j++) {
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.print("\t╰");
        for (int i = 0; i < maxLength + "| [ ] 100. ".length(); i++) {
            System.out.print("-");
        }
        System.out.println("╯");
    }

    /**
     * Print the "selfie" image of the robot which is randomly generated and the description of the personality of the robot
     */
    public static void printPersonality() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        switch (randomNumber) {
        case 0:
            System.out.println(ProfilePicture.picture1);
            break;
        case 1:
            System.out.println(ProfilePicture.picture2);
            break;
        case 2:
            System.out.println(ProfilePicture.picture3);
            break;
        }
        System.out.println("**                                                                                                                   **");
        System.out.println("**       Greeting!                                                                                                   **");
        System.out.println("**      -My name is Shima Rin, I am a Japanese anime character that comes from the anime \"Yuru Camp\"                 **");
        System.out.println("**      -I love camping, especially during the winter season!                                                        **");
        System.out.println("**      -I also love food (especially hot pot!) and hot spring! :P                                                   **");
        System.out.println("**      -By the way, I am also a dog-lover!                                                                          **");
        System.out.println("**                                                                                                                   **");
        System.out.println("***********************************************************************************************************************");
    }

    public static void main(String[] args) {
        printLogo();
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("|To know more about me, you can view my personality by typing the command \"view -p\"              |");
        System.out.println("|For now I am a note bot that can help you note down any tasks and create a to-do list for you :)|");
        System.out.println("|In addition, you can mark any task in the to-do list as done!                                   |");
        System.out.println("|You can type \"list\" or \"ls\" to list all the tasks that are waiting to do                        |");
        System.out.println("|You can type \"done i\" where i is the index of the task to mark the specific task as done        |");
        System.out.println("|You can type \"exit\" or \"bye\" to stop me and exit the program                                    |");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("\nLet's start:");

        Scanner in = new Scanner(System.in);
        String cmd = "START";
        Task[] tasks = new Task[100];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task();
        }
        int totalTask = 0;

        //continue reading input if the cmd is not exit or bye
        while (!cmd.toUpperCase().equals("EXIT") && !cmd.toUpperCase().equals("BYE")) {
            cmd = in.nextLine().trim();
            String[] words = cmd.split(" ");

            if (cmd.equals("") || cmd.trim().equals("")) {
                System.out.println("\t(Empty) <- will not save to the list");
            } else if (cmd.equals("view -p")) {
                printPersonality();
            } else if (!cmd.toUpperCase().equals("EXIT") && !cmd.toUpperCase().equals("BYE")) {
                if (cmd.toUpperCase().equals("LIST") || cmd.toUpperCase().equals("LS")) {
                    //list all the saved tasks
                    printList(tasks, totalTask);
                } else if (words[0].toUpperCase().equals("DONE")) {
                    try {
                        for (int i = 1; i < words.length; i++) {
                            int taskNumber = Integer.parseInt(words[i]);
                            if (taskNumber <= totalTask && taskNumber > 0) {
                                if (!tasks[taskNumber - 1].getDone()) {
                                    tasks[taskNumber - 1].setDone();
                                    System.out.println("\tHooray! Task number " + taskNumber + " has been marked completed!");
                                    System.out.println("\t[✔] " + tasks[taskNumber - 1].getTask());
                                } else {
                                    System.out.println("\tThe task number " + taskNumber + " - \"" + tasks[taskNumber - 1].getTask() + "\" has already been done!");
                                }
                            } else {
                                System.out.print("\t╔");
                                for (int j = 0; j < " Sorry, the input task number is invalid, please try again! :( ".length() + 1; j++) {
                                    System.out.print(" ");
                                }
                                System.out.println("╗");
                                System.out.println("\t   Sorry, the input task number is invalid, please try again! :(");
                                System.out.print("\t╚");
                                for (int j = 0; j < " Sorry, the input task number is invalid, please try again! :( ".length() + 1; j++) {
                                    System.out.print(" ");
                                }
                                System.out.println("╝");
                            }
                        }
                    } catch (NumberFormatException ex) {
                        System.out.print("\t╔");
                        for (int i = 0; i < " Sorry, the input task number is invalid, please try again! :( ".length() + 1; i++) {
                            System.out.print(" ");
                        }
                        System.out.println("╗");
                        System.out.println("\t   Sorry, the input task number is invalid, please try again! :(");
                        System.out.print("\t╚");
                        for (int i = 0; i < " Sorry, the input task number is invalid, please try again! :( ".length() + 1; i++) {
                            System.out.print(" ");
                        }
                        System.out.println("╝");
                    }
                } else {
                    //add the task to the list
                    tasks[totalTask++].setTask(cmd.trim());
                    System.out.print("\t╔");
                    for (int i = 0; i < cmd.length() + " \"...has been added to the list...\" ".length() + 1; i++) {
                        System.out.print(" ");
                    }
                    System.out.println("╗");
                    System.out.println("\t" + "  ...\"" + cmd.trim() + "\" has been added to the list...");
                    System.out.print("\t╚");
                    for (int i = 0; i < cmd.length() + " \"...has been added to the list...\" ".length() + 1; i++) {
                        System.out.print(" ");
                    }
                    System.out.println("╝");
                }
            }
        }
        if (cmd.toUpperCase().equals("EXIT") || cmd.toUpperCase().equals("BYE")) {
            System.out.println("Bye! Hope to see you again :D");
        }
    }
}
