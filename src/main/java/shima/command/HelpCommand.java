package shima.command;

import shima.design.UserInterface;
import java.util.ArrayList;

public class HelpCommand extends Command {

    public static final String LINE_SEPARATOR = "************************************************************************************************************************************************************************";
    public static final String NEXT_LINE_INDENT = "\n\t\t";
    protected ArrayList<String> helpLists;


    public HelpCommand(){
        this.helpLists = createHelpMenu();
    }

    public void runCommand() {
        printHelpMenu();
    }

    /**
     * Prints the help menu to the console
     */
    public void printHelpMenu() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Help Menu " + UserInterface.CURR_VERSION + "\n");
        System.out.println("Welcome user!");
        System.out.println("In this version, I am equipped with several functions, including PROFILE, HELP, TODO, DEADLINE, EVENT, LIST, DONE, DELETE, DATE, FIND and EXIT");
        System.out.println("I am able to understand your command in any case, meaning that you are free to enter command with any case :D");
        for (int i = 0; i < helpLists.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + helpLists.get(i));
        }
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Creates the help menu descriptions by adding the strings into the array list
     *
     * @return Returns an array list of string with all the help menu descriptions
     */
    private ArrayList<String> createHelpMenu() {
        ArrayList<String> helpLists = new ArrayList<>();
        //Profile command
        helpLists.add("To view my cute profile XD, use the command \"profile\"" + System.lineSeparator());
        //Help command
        helpLists.add("To view the help menu, use the command \"help\"" + System.lineSeparator());
        //To-do command
        helpLists.add("To add a new to-do task, use the command \"todo\" with syntax:" + NEXT_LINE_INDENT + "todo [YOUR_TASK_DESCRIPTION]" + NEXT_LINE_INDENT +
                "eg. todo read book\t<-- will add the task \"read book\" to your to-do list" + System.lineSeparator());
        //Deadline command
        helpLists.add("To add a new task specified with deadline, use the command \"deadline\" with syntax:" + NEXT_LINE_INDENT + "deadline [YOUR_TASK_DESCRIPTION] /[END_DATE (yyyy-MM-dd)] [END_TIME in UTC (HH:mm)]" + NEXT_LINE_INDENT +
                "eg. deadline submit assignment /by 2021-10-1 16:00\t<-- will add the task \"submit assignment\" with deadline \"1 Oct 2021 4.00 pm" + System.lineSeparator());
        //Event command
        helpLists.add("To add an event specified with start time and end time, use the command \"event\" with syntax:" + NEXT_LINE_INDENT + "event [YOUR_TASK_DESCRIPTION] /[START_TIME]-[END_TIME]" +
                NEXT_LINE_INDENT + "eg. event attend tutorial /at Friday 2 - 4pm\t<-- will add the event \"attend tutorial\" with the event day Friday and start time 2pm and end time 4pm" + System.lineSeparator());
        //List command
        helpLists.add("To print the to-do list, use the command \"list\", it will then show you the to-do list created by me :P" + System.lineSeparator());
        //Done command
        helpLists.add("To mark the task as done, use the command \"done\" with syntax:" + NEXT_LINE_INDENT + "done [TASK_INDEX]" + NEXT_LINE_INDENT + "eg. done 1\t<-- will mark the 1st task as completed\n\t" +
                "\t\tdone 1 2 3\t<-- will mark the 1st, 2nd and 3rd tasks as completed" + System.lineSeparator());
        //Delete command
        helpLists.add("To delete task, use the command \"delete\" with syntax:" + NEXT_LINE_INDENT + "delete [TASK_INDEX]" + NEXT_LINE_INDENT + "eg. delete 1\t<--will delete the 1st task\n\t" +
                "\t\tdelete 1 2 3\t<-- will delete the tasks with index 1, 2 and 3" + NEXT_LINE_INDENT + "\tdelete all\t<-- will delete all the tasks in the list" + System.lineSeparator());
        //Date command
        helpLists.add("To print only the task with a specific deadline, use the command \"date\" with syntax:" + NEXT_LINE_INDENT + "date [SPECIFIC_DATE (yyyy-MM-dd)]" + NEXT_LINE_INDENT +
                "eg. date 2021-10-1\t<-- will display all the tasks with deadline date 2021-10-1"+ System.lineSeparator());
        //Find command
        helpLists.add("To check if there exist tasks that contain a specific keyword, use the command \"find\" with syntax" + NEXT_LINE_INDENT + "find [KEYWORD]" + NEXT_LINE_INDENT +
                "eg. find assignment\t<-- will display all the tasks that contain the keyword \"assignment\"" + System.lineSeparator());
        //Exit command
        helpLists.add("To exit the program, use the command \"exit\" or \"bye\"" + System.lineSeparator());
        return helpLists;
    }
}
