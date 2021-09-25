package Duke.Commands;

import Duke.BackEnd.DukeParser;
import Duke.Exception.DukeException;
import Duke.TaskTypes.Task;
import Duke.UI.UserInterface;

import java.util.ArrayList;

public class FindCommand {
    public static void findMatching (String inWord, ArrayList<Task> taskList) {
            try {
                String searchedWord = DukeParser.parseFindInstruction(inWord).toLowerCase();
                ArrayList<Task> tasksWithQuery = traverseCopy(taskList, searchedWord);
                printTasksWithQuery(searchedWord, tasksWithQuery);
            } catch (DukeException e) {
                DukeException.invalidFindException();
            }
    }

    private static void printTasksWithQuery(String searchedWord, ArrayList<Task> tasksWithQuery) {
        if (tasksWithQuery.isEmpty()) {
            UserInterface.noMatchMessage();
        } else {
            UserInterface.printAllMatchingTasks(searchedWord, tasksWithQuery);
        }
    }

    private static ArrayList<Task> traverseCopy(ArrayList<Task> taskList, String searchedWord) throws DukeException {
        ArrayList<Task> tasksWithQuery = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getDescription().toLowerCase().contains(searchedWord)) {
                tasksWithQuery.add(t);
            }
        }
        return tasksWithQuery;
    }
}
