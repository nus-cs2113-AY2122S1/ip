import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Prints all program responses to the terminal.
 */

public class Ui {
    private static String logo = "                       ./((((((((((((((/.                    \n" +
            "                  /(((/.  .,*////*,.   *((((                \n" +
            "               (#(*  (.       ,((*.     #. ,#(#             \n" +
            "            .((/ .*    (*     ,   ./((((,  ,, *((,          \n" +
            "           ((/ /   .(        (##        (((,  ( *((.        \n" +
            "         .(( ..  .,         #####         .((,  , #(*       \n" +
            "        .(( ,   #          ####( ,,         /((  / ((,      \n" +
            "        ((  ,  (         ,####* /##(         *(( .. ((      \n" +
            "        (( /  ..        (####,  #####         #(. # ((.     \n" +
            "        /              %####     #####        #(.    #      \n" +
            "        ,  *   ,                  /####,      ((  (  /      \n" +
            "         (  *  /    ,###################(    ((* ,  ,       \n" +
            "         .   ,  *  /######################  ((* ..  ,       \n" +
            "           ,  #   (                       (((  (   .        \n" +
            "            (   (   ,/                 (((/  *.  #          \n" +
            "              (   *,    ,(*.  ,/((#((((,  ./   #            \n" +
            "                 #    (*              ,(    (.              \n" +
            "                    ,#                  (*                  \n" +
            "                           .,.   ,.                         \n" +
            "                                                            \n" +
            "                                                            \n" +
            "      ,/,     ,//      //////*    //      */. */.    */////*\n" +
            "      /@*    (@#@&            @@  .@&    %@*  #@*  &@       \n" +
            "      /@*   #@* .@@    @@@@@@@@*   .@@  @@,   #@*  .@@@@@@@.\n" +
            "      @@.  %@,    @@   @@   #@(      @@&@.    #@*         @&\n" +
            " */**.    */.      /*  **    ./*      */      ,*.  ,******  ";

    private static void showLine() {
        System.out.println("\t____________________________________________________________\n");
    }

    private static String addTaskSalutation(String item) {
        Random rand = new Random();
        String out = "";
        switch(rand.nextInt(3)) {
            case 0:
                out = "Right away sir. I have added " + item + " to your list of tasks.\n";
                break;
            case 1:
                out = "Understood sir. " + item + " has been added to your list of tasks.\n";
                break;
            case 2:
                out = "Sir, I can confirm that " + item + " has been added to your list of tasks.\n";
                break;
            default:
                break;
        }
        return out;
    }
    /**
     * Reads and returns user input
     * @return user input
     */
    public static String readCommand() {
        Scanner sc= new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }
    public static void greet() {
        System.out.println("\n" + logo);
        showLine();
        System.out.println("\tHello Sir,\n" + "\tWhat can I do for you?\n"); // greet
        showLine();
    }
    /**
     * Prints all existing tasks in Duke
     * @param tasks List of Existing tasks
     */
    public void printList(Tasks tasks){
        showLine();
        System.out.println("List so far: ");
        tasks.print();
        showLine();
    }
    /**
     * Prints tasks that have descriptions containing the input keyword
     * @param tasks List of Existing tasks
     * @param keyword User input keyword to search for corresponding tasks containing the keyword
     */
    public void printMatching(Tasks tasks, String keyword){
        showLine();
        System.out.println("Here are the matching tasks in your list: ");
        int num = 1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                System.out.println(num + ". " + tasks.get(i));
                num++;
            }
        }
        showLine();
    }
    public static void exit() {
        System.out.println("Goodbye Sir. I shall be at your service again whenever you require.\n"); //exit message
        showLine();
    }

    public void delete(Task task, Tasks tasks){
        showLine();
        if (task.complete) {
            System.out.println("\tSir, as per your request, I have deleted the task:\n\t" + task + "\n\tNow you have " + (tasks.size() - 1) + " tasks in the list, " + tasks.tasksIncomplete() + " incomplete tasks.");
        } else {
            System.out.println("\tSir, as per your request, I have deleted the task:\n\t" + task + "\n\tNow you have " + (tasks.size() - 1) + " tasks in the list, " + (tasks.tasksIncomplete() - 1)+ " incomplete tasks.");
        }

        showLine();
    }

    public void done(Task task, Tasks tasks){
        showLine();
        System.out.println("\tAs you wish sir. I have marked this task as done:\n\t" + task + "\n\tNow you have " + tasks.size() + " tasks in the list, " + tasks.tasksIncomplete() + " incomplete tasks.");
        showLine();
    }

    public void add(Task task, Tasks tasks){
        showLine();
        System.out.println("\t" + addTaskSalutation(task.getDescription()) + "\n\tNow you have " + tasks.size() + " tasks in the list, " + tasks.tasksIncomplete() + " incomplete tasks.");
        showLine();
    }
}
