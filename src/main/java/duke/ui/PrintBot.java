package duke.ui;

import duke.exception.EmptyListException;
import duke.exception.InvalidIndexException;
import duke.task.Task;

import java.util.ArrayList;

/*
 * This class prints the results of manipulation
 * from ActionBot to the CLI User Interface.
 */
public class PrintBot {
    static final String LINE = "--------------------------------";

    public static void print(String input) {
        System.out.println(input);
    }

    public void line() {
        print(LINE);
    }

    /*
     * Prints to the screen a welcome message immediately after
     * the user has keyed in the correct trigger sentence
     * (I solemnly swear that I am up to no good.).
     */

    public void launchTxt() {
        print("\n" +
                "                REPEAT AFTER ME:            \n" +
                " (       \"     )  I SOLEMNLY SWEAR THAT   \n" +
                "  ( _  *            I AM UP TO NO GOOD       \n" +
                "     * (     /      \\    ___                \n" +
                "        \"     \"        _/ /                 \n" +
                "       (   *  )    ___/   |                 \n" +
                "         )   \"     _ o)'-./__               \n" +
                "        *  _ )    (_, . $$$                 \n" +
                "        (  )   __ __ 7_ $$$$                \n" +
                "         ( :  { _)  '---  $\\                \n" +
                "    ______'___//__\\   ____, \\               \n" +
                "     )           ( \\_/ _____\\_              \n" +
                "   .'             \\   \\------''.            \n" +
                "   |='           '=|  |         )           \n" +
                "   |               |  |  .    _/            \n" +
                "    \\    (. ) ,   /  /__I_____\\             \n" +
                "     '._/_)_(\\__.'   (__,(__,_]             \n" +
                "    @---()_.'---@            ");
    }

    public void greet() {
        String logo =
                "88888888888888888888888888888888888888888888888888888888888888888888888\n" +
                "88.._|      | `-.  | `.  -_-_ _-_  _-  _- -_ -  .'|   |.'|     |  _..88\n" +
                "88   `-.._  |    |`!  |`.  -_ -__ -_ _- _-_-  .'  |.;'   |   _.!-'|  88\n" +
                "88      | `-!._  |  `;!  ;. _______________ ,'| .-' |   _!.i'     |  88\n" +
                "88..__  |     |`-!._ | `.| |_______________||.\"'|  _!.;'   |     _|..88\n" +
                "88   |``\"..__ |    |`\";.| i|_|MMMMMMMMMMM|_|'| _!-|   |   _|..-|'    88\n" +
                "88   |      |``--..|_ | `;!|l|MMoMMMMoMMM|1|.'j   |_..!-'|     |     88\n" +
                "88   |      |    |   |`-,!_|_|MMMMP'YMMMM|_||.!-;'  |    |     |     88\n" +
                "88___|______|____!.,.!,.!,!|d|MMMo * loMM|p|,!,.!.,.!..__|_____|_____88\n" +
                "88      |     |    |  |  | |_|THEMARAUDER|_|| |   |   |    |      |  88\n" +
                "88      |     |    |..!-;'i|r|MARAUDERMAP|r| |`-..|   |    |      |  88\n" +
                "88      |    _!.-j'  | _!,\"|_THE<>MARAUDE|_||!._|  `i-!.._ |      |  88\n" +
                "88     _!.-'|    | _.\"|  !;|1|MaRAUDER'SM|l|`.| `-._|    |``-.._  |  88\n" +
                "88..-i'     |  _.''|  !-| !|_|MMMoMMMMoMM|_|.|`-. | ``._ |     |``\"..88\n" +
                "88   |      |.|    |.|  !| |u|MoMMMMoMMMM|n||`. |`!   | `\".    |     88\n" +
                "88   |  _.-'  |  .'  |.' |/|_|MMMMoMMMMoM|_|! |`!  `,.|    |-._|     88\n" +
                "88  _!\"'|     !.'|  .'| .'|[@]MMMMMMMMMMM[@] \\|  `. | `._  |   `-._  88\n" +
                "88-'    |   .'   |.|  |/| /                 \\|`.  |`!    |.|      |`-88\n" +
                "88      |_.'|   .' | .' |/                   \\  \\ |  `.  | `._-Lee|  88\n" +
                "88     .'   | .'   |/|  /                     \\ |`!   |`.|    `.  |  88\n" +
                "88  _.'     !'|   .' | /                       \\|  `  |  `.    |`.|  88\n" +
                "88888888888888888888888888888 SICHENG.LU 888888888888888888888888888888\n" ;
        print(logo);
        print("           Messrs Moony, Wormtail, Padfoot, and Prongs           \n" +
                "           Purveyors of Aids to Magical Mischief-Makers\n" +
                "                     are proud to present\n" +
                "                   --THE MARAUDER'S MAP-- \n");
    }

    /*
     * Prints to screen a message when user types in "hello".
     */
    public void hello() {
        print("I see you are lost. \n" +
                "Read the charm beneath out loud, and I shall serve you.");
        print("- \"I solemnly swear that I am up to no good.\" ");
    }

    /*
     * Prints to screen a farewell message when user types in "bye".
     * The duke program exits after this method.
     */
    public void exit() {
        line();
        print("Mischief managed.");
        line();
    }

    /*
     * Prints to screen the task that has been added
     * and the updated amount of tasks in the list.
     */
    public void addTask(Task t, int taskCount) {
        print("|| Got it. I've added this task");
        print("|| \t" + t.toString());
        print("|| Now you have " + taskCount + " tasks in the list.");
    }

    /*
     * Prints to screen the task that has been marked
     * as Done just now. Shows the task's position in
     * the list as well.
     */
    public void markDone(int id, boolean isDone, Task t) throws InvalidIndexException, EmptyListException {
        if (isDone) {
            print("Nice!I've marked this task as done:");
            print(id + ". " + t.toString());
        }
    }

    /*
     * Tells the user if the input word exists inside the task list.
     * Print out the id and description of all results.
     */
    public void searchList(ArrayList<String> searchResult) {
        if (searchResult.isEmpty()) {
            print("No task found.");
        } else {
            print(" Here are the matching tasks in your list: ");
            for (String s : searchResult) {
                print(s);
            }
        }
    }

    public static void loadingData() {
        print("Please wait. Loading data ..... ");
    }

    public static void loadData(String data) {
        print(data);
    }

    /*
     * Prints the information of the task that
     * has just been deleted from the list.
     */
    public void delete(Task t, int taskCount) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("|| \t" + t.toString());
        System.out.println("|| Now you have " + taskCount + " in the list.");
    }

    /*
     * Prints four sentences, one by one if the user continues to
     * get the activation phrase wrong.
     */
    public void printFalseCharmMsg (int n) {
        if (n == 0) {
            System.out.println("Mr Moony presents his compliments to Professor Snape \n" +
                    "and begs him to keep his abnormally large nose \n" +
                    "out of other people's business. ");
        } else if (n == 1) {
            System.out.println("Mr Prongs agrees with Mr Moony \n" +
                    "and would like to add that Professor Snape is an ugly git.");
        } else if (n == 2) {
            System.out.println("Mr Padfoot would like to register \n" +
                    "his astonishment that an idiot like that ever became a Professor.");
        } else if (n == 3) {
            System.out.println("Mr Wormtail bids Professor Snape good day, \n" +
                    "and advises him to wash his hair, the slime-ball.\"");
        }
    }
}
