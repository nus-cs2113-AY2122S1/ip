import java.util.Scanner;
import java.util.Random;

public class Duke {
    public static final String CURR_VERSION = "Version 3.0";
    public static final int MAX_TASKS = 100;
    public static Scanner in = new Scanner(System.in);

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
    
    public static void printWelcomeMessage(){
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
     * Print the to-do list with frames
     *
     * @param tasks     the array of class Task instance which stores all the tasks added by the user
     * @param stopIndex the last index of the array that is not null
     */
    private static void printToDoList(Task[] tasks, int stopIndex, int maxLength) {
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
     * Print the ASCII art image of the robot and the description of the personality of the robot
     */
    public static void printPersonality() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        switch (randomNumber) {
        case 0:
            System.out.println(ProfilePicture.PICTURE_1);
            break;
        case 1:
            System.out.println(ProfilePicture.PICTURE_2);
            break;
        case 2:
            System.out.println(ProfilePicture.PICTURE_3);
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
        //print all the welcome screens
        printLogo();
        printWelcomeMessage();
        printVersionDescription();
        System.lineSeparator();
        System.out.println("Let's start:");
        Task[] tasks = new Task[MAX_TASKS];
        for (int i = 0; i < MAX_TASKS; i++) {
            tasks[i] = new Task();
        }
        readCommand(tasks);
    }

    /**
     * Continuously read the input command typed by the user, unless the exit command is sent
     *
     * @param tasks The array to store all the tasks required
     */
    private static void readCommand(Task[] tasks) {
        while (true) {
            String command = in.nextLine().trim();
            String[] words = command.split(" ");
            if (command.isEmpty()) {
                System.out.println("\t(Empty) <- will not save to the list");
            } else if (command.equals("view -p")) {
                printPersonality();
            } else if (command.toUpperCase().equals("EXIT") || command.toUpperCase().equals("BYE")) {
                showMessage("Bye! Hope to see you again :D");
                System.exit(0);
            } else {
                if (command.toUpperCase().equals("LIST") || command.toUpperCase().equals("LS")) {
                    printToDoList(tasks, Task.totalTask, Task.longestTaskLength);
                } else if (words[0].toUpperCase().equals("DONE")) {
                    try {
                        if (words.length == 1){
                            showMessage("Sorry, the input task number is missing, please try again! :(");
                        }
                        for (int i = 1; i < words.length; i++) {
                            int taskNumber = Integer.parseInt(words[i]); //check if the input character after the word "done" is integer value
                            if (taskNumber <= Task.totalTask && taskNumber > 0) {
                                showTaskDoneMessage(tasks, taskNumber);
                            } else {
                                showMessage("Sorry, the input task number is invalid, please try again! :(");
                            }
                        }
                    } catch (NumberFormatException ex) {
                        showMessage("Sorry, the input task number is invalid, please try again! :(");
                    }
                } else {
                    //add the task to the list
                    tasks[Task.totalTask].setTask(command);
                    showMessage(" ...\"" + command + "\" has been added to the list... ");
                }
            }
        }
    }

    /**
     * Show the message to indicate that the task is marked as done
     *
     * @param tasks The array which stores all the tasks
     * @param taskNumber The given task number to mark as done
     */
    private static void showTaskDoneMessage(Task[] tasks, int taskNumber) {
        if (!tasks[taskNumber - 1].getDone()) {
            tasks[taskNumber - 1].setDone();
            System.out.println("\tHooray! Task number " + taskNumber + " has been marked completed!");
            System.out.println("\t[✔] " + tasks[taskNumber - 1].getTask());
        } else {
            System.out.println("\tThe task number " + taskNumber + " - \"" + tasks[taskNumber - 1].getTask() + "\" has already been done!");
        }
    }

    /**
     * Show the formatted message string
     *
     * @param message The message to print
     */
    private static void showMessage(String message) {
        System.out.print("\t╔");
        for (int i = 0; i < message.length() + 4; i++) {
            System.out.print(" ");
        }
        System.out.println("╗");
        System.out.println("\t   " + message);
        System.out.print("\t╚");
        for (int i = 0; i < message.length() + 4; i++) {
            System.out.print(" ");
        }
        System.out.println("╝");
    }

    /**
     * Show the current version's functionality of the bot
     */
    private static void printVersionDescription() {
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("*" + CURR_VERSION + "                                                                                     *");
        System.out.println("*To know more about me, you can view my personality by typing the command \"view -p\"              *");
        System.out.println("*For now I am a note bot that can help you note down any tasks and create a to-do list for you :)*");
        System.out.println("*In addition, you can mark any task in the to-do list as done!                                   *");
        System.out.println("*You can type \"list\" or \"ls\" to list all the tasks that are waiting to do                        *");
        System.out.println("*You can type \"done i\" where i is the index of the task to mark the specific task as done        *");
        System.out.println("*You can type \"exit\" or \"bye\" to stop me and exit the program                                    *");
        System.out.println("--------------------------------------------------------------------------------------------------");
    }
}

