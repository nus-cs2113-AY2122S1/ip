package shima.design;

import java.util.ArrayList;

public class HelpMenu {

    /**
     * Prints the help menu to the console
     */
    public static void printHelpMenu() {
        ArrayList<String> helpLists = createHelpMenu();
        System.out.println("************************************************************************************************************************************************************************");
        System.out.println("Help Menu " + Default.CURR_VERSION + "\n");
        for (int i = 0; i < helpLists.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + helpLists.get(i));
        }
        System.out.println("************************************************************************************************************************************************************************");
    }

    /**
     * Creates the help menu descriptions by adding the strings into the array list
     *
     * @return Returns an array list of string with all the help menu descriptions
     */
    private static ArrayList<String> createHelpMenu() {
        ArrayList<String> helpLists = new ArrayList<>();
        //To-do command
        helpLists.add("To add a new to-do task, use the command \"todo\" with syntax:\n\t\ttodo [YOUR_TASK_DESCRIPTION]\n\t\t" +
                "eg. todo read book\t<-- will add the task \"read book\" to your to-do list\n");
        //Deadline command
        helpLists.add("To add a new task specified with deadline, use the command \"deadline\" with syntax:\n\t\tdeadline [YOUR_TASK_DESCRIPTION] /[DEADLINE]\n\t\t" +
                "eg. deadline submit assignment /by Friday 6pm\t<-- will add the task \"submit assignment\" with deadline \"Friday 6pm\n");
        //Event command
        helpLists.add("To add an event specified with start time and end time, use the command \"event\" with syntax:\n\t\tevent [YOUR_TASK_DESCRIPTION] /[START_TIME]-[END_TIME]" +
                "\n\t\teg. event attend tutorial /at Friday 2 - 4pm\t<-- will add the event \"attend tutorial\" with the event day Friday and start time 2pm and end time 4pm\n");
        //List command
        helpLists.add("To print the to-do list, use the command \"list\" or \"ls\", it will then show you the to-do list\n");
        //Done command
        helpLists.add("To mark the task as done, use the command \"done\" with syntax:\n\t\tdone [TASK_INDEX]\n\t\teg. done 1\t<-- will mark the 1st task as completed\n\t" +
                "\t\tdone 1 2 3\t<-- will mark the 1st, 2nd and 3rd tasks as completed\n");
        //Delete command
        helpLists.add("To delete task, use the command \"delete\" with syntax:\n\t\tdelete [TASK_INDEX]\n\t\teg. delete 1\t<--will delete the 1st task\n\t" +
                "\t\tdelete 1 2 3\t<-- will delete the tasks with index 1, 2 and 3\n\t\t\tdelete all\t<-- will delete all the tasks in the list\n");
        //Exit command
        helpLists.add("To exit the program, use the command \"exit\" or \"bye\"\n");
        return helpLists;
    }
}
