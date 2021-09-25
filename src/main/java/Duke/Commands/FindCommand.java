package Duke.Commands;

import Duke.BackEnd.DukeParser;
import Duke.Exception.DukeException;
import Duke.TaskTypes.Task;
import Duke.UI.UserInterface;

import java.util.ArrayList;

public class FindCommand {
    /**
     * Method to find if there is a matching keyword in the task list
     *
     * @param inWord the user instruction
     * @param taskList the current list of user's tasks
     */
    public static void findMatching (String inWord, ArrayList<Task> taskList) {
            try {
                String searchedWord = DukeParser.parseFindInstruction(inWord).toLowerCase();
                ArrayList<Task> tasksWithQuery = traverseCopy(taskList, searchedWord);
                printTasksWithQuery(searchedWord, tasksWithQuery);
            } catch (DukeException e) {
                DukeException.invalidFindException();
            }
    }

    /**
     * Execute the query result according to the search results obtained
     *
     * @param searchedWord the word that user queries
     * @param tasksWithQuery the current list of matching task and query
     */
    private static void printTasksWithQuery(String searchedWord, ArrayList<Task> tasksWithQuery) {
        if (tasksWithQuery.isEmpty()) {
            UserInterface.noMatchMessage();
        } else {
            UserInterface.printAllMatchingTasks(searchedWord, tasksWithQuery);
        }
    }

    /**
     * Method fills in a list of matching task and query
     *
     * @param taskList the current list of user's tasks
     * @param searchedWord the word that user queries
     * @return the newly created array list of matching task and queery
     */
    private static ArrayList<Task> traverseCopy (ArrayList<Task> taskList, String searchedWord) {
        ArrayList<Task> tasksWithQuery = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getDescription().toLowerCase().contains(searchedWord)) {
                tasksWithQuery.add(t);
            }
        }
        return tasksWithQuery;
    }
}
