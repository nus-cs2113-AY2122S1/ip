package shima.command;

import shima.task.Deadline;

import java.util.ArrayList;

/**
 * This class is used to find deadlines that match with the user input deadline
 */
public class ListDateCommand extends Command {
    public static final String DATE_NOT_FOUND_MSG = "\tI do not find any task with the deadline given...";
    public static final String DISPLAY_MSG = "\tHere are the tasks that I found with the deadline you given:";
    protected ArrayList<Deadline> resultList;

    public ListDateCommand(ArrayList<Deadline> resultList) {
        this.resultList = resultList;
    }

    /**
     * Prints out all the deadlines from the resultList that match the user input date
     */
    public void runCommand() {
        if (resultList.isEmpty()) {
            System.out.println(DATE_NOT_FOUND_MSG);
        }
        System.out.println(DISPLAY_MSG);
        int count = 0;
        for (Deadline d : resultList) {
            System.out.println("\t\t" + (count + 1) + ". " + resultList.get(count++));
        }
        System.out.println("\tThere are " + count + " tasks matched with the deadline");
    }
}
