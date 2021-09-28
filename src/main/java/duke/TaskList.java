package duke;

import duke.task.Task;
import duke.task.ToDo;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    private static final ArrayList<Task> userLists = new ArrayList<>();
    private static int numOfTasks = 0;

    public static void taskHandler(String userCommand, String userInput, boolean isPrint) {
        switch (userCommand) {
        case "event":
            addEvent(userInput, isPrint);
            break;
        case "deadline":
            addDeadline(userInput, isPrint);
            break;
        default:
            addTodo(userInput, isPrint);
            break;
        }
    }

    public static void completeTask(int chosenIndex, boolean isPrint) {
        try {
            int doneIndex = chosenIndex - 1;
            String userDoneEntry;
            userLists.get(doneIndex).markAsDone();
            userDoneEntry = userLists.get(doneIndex).toString();
            if (isPrint) {
                UI.printDone(userDoneEntry);
            }
            Storage.saveFile(userLists, numOfTasks);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("\tCouldn't understand your instructions. Please enter a valid index");
        }
    }

    public static void deleteTask(String chosenIndex) {
        try {
            int deleteIndex = Integer.parseInt(chosenIndex) - 1;
            String chosenDeleteEntry = userLists.get(deleteIndex).toString();
            UI.printDelete(numOfTasks, chosenDeleteEntry);
            userLists.remove(deleteIndex);
            numOfTasks--;
            Storage.saveFile(userLists, numOfTasks);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("\tCouldn't understand your instructions. Please enter a valid index to delete");
        }
    }

    public static void addTodo(String userLineInput, boolean isPrint) {
        try {
            String todoDescription = userLineInput.substring(5);
            Task newToDo = new ToDo(todoDescription);
            userLists.add(numOfTasks, newToDo);
            numOfTasks++;
            if (isPrint) {
                UI.printAdd(numOfTasks, newToDo);
            }
            Storage.saveFile(userLists, numOfTasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("\tHey, please check your inputs again.");
        }
    }

    public static void addEvent(String userLineInput, boolean isPrint) {
        try {
            Task newEvent = Parser.getEventTask(userLineInput);
            userLists.add(numOfTasks, newEvent);
            numOfTasks++;
            if (isPrint) {
                UI.printAdd(numOfTasks, newEvent);
            }
            Storage.saveFile(userLists, numOfTasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("\tThere's something wrong with you input. Please try again.");
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }
    }


    public static void addDeadline(String userLineInput, boolean isPrint) {
        try {
            Task newDeadline = Parser.getDeadlineTask(userLineInput);
            userLists.add(numOfTasks, newDeadline);
            numOfTasks++;
            if (isPrint) {
                UI.printAdd(numOfTasks, newDeadline);
            }
            Storage.saveFile(userLists, numOfTasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("\tHey, please check your inputs again." +
                    System.lineSeparator() + "\tYou should add a /b before your event date");
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }
    }


    public static void getList() {
        if (numOfTasks == 0) {
            System.out.println("\tList is empty!");
        } else {
            System.out.println("\tHere's your list of tasks:");
            for (int i = 0; i < numOfTasks; i++) {
                System.out.println("\t" + (i + 1) + "." + userLists.get(i).toString());
                System.out.println(userLists.get(i).getStatusIcon() + "|");
            }
        }
    }

    public static String getDoneStatus(ArrayList<Task> userList, int numTask) {
        String currentStatusIcon = userList.get(numTask).getStatusIcon();
        if (currentStatusIcon.equals("[X] ")){
            return "1";
        }
        return "0";
    }

    public static void findEntry(String[] userInput) {
        String searchQuery = userInput[1];
        List<Task> matches =userLists.stream()
                .filter(t -> t.getDescription().contains(searchQuery))
                .collect(Collectors.toList());
        UI.printFoundEntries(matches);
    }
}
