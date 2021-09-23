import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {

    private static final ArrayList<Task> tasks = new ArrayList<>();



    /**
     * Returns void. Function is responsible for adding different Tasks to the task list.
     *
     * @param query user raw data input
     * @throws NullPointerException if user keys in done [number] when there is no such task.
     */
    public static void addTask(String query) throws IndexOutOfBoundsException {
        Keyword keyword = Parser.getKeywordStatus(query);
        switch (keyword) {
        case DONE_TASK:
            try {
                int taskNumber = Parser.getTaskNum(query) - 1;
                Task referencedTask = tasks.get(taskNumber);
                referencedTask.markAsDone();
                Ui.printDone(referencedTask);
                System.out.println("Total unchecked items in your list: " + Parser.getNumOfUncompletedTasks(tasks));
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("There is no such task number...");
            }
            break;
        case TODO_TASK:
            tasks.add(new Todo(Parser.getQueryDescription(query)));
            System.out.println("Added a Todo Task: " + Parser.getQueryDescription(query));
            System.out.println("Total unchecked items in your list: " + Parser.getNumOfUncompletedTasks(tasks));
            break;
        case EVENT_TASK:
            try {
                if (Parser.getDate(query).equals("")) {
                    throw new InvalidInputsException.InvalidDateFormatting("Wrong format of date "
                            + "has been entered");
                }
                tasks.add(new Event(Parser.getQueryDescription(query), Parser.getDate(query)));
                System.out.println("Added an Event Task: " + Parser.getQueryDescription(query));
                System.out.println("Total unchecked items in your list: " + Parser.getNumOfUncompletedTasks(tasks));
            } catch (InvalidInputsException.InvalidDateFormatting exception) {
                exception.printStackTrace();
                System.out.println("Did you forget to add in the '/by' again?");
            }
            break;
        case DEADLINE_TASK:
            try {
                if (Parser.getDate(query).equals("")) {
                    throw new InvalidInputsException.InvalidDateFormatting("Wrong format of date "
                            + "has been entered");
                }
                tasks.add(new Deadline(Parser.getQueryDescription(query), Parser.getDate(query)));
                System.out.println("Added a Deadline Task: " + Parser.getQueryDescription(query));
                System.out.println("Total unchecked items in your list: " + Parser.getNumOfUncompletedTasks(tasks));
            } catch (InvalidInputsException.InvalidDateFormatting exception) {
                exception.printStackTrace();
                System.out.println("Did you forget to add in the '/by' again?");
            }
            break;
        case LIST_ITEMS:
            Ui.printList(tasks);
            break;
        case DELETE_KEYWORD:
            try {
                int taskNumber = Parser.getTaskNum(query) - 1;
                Task referencedTask = tasks.get(taskNumber);
                tasks.remove(taskNumber);
                Ui.printDeletedMessage(referencedTask);
                System.out.println("Total unchecked items in your list: " + Parser.getNumOfUncompletedTasks(tasks));
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("There is no such task number...");
            }
            break;
        case NO_KEYWORD:
            try {
                throw new InvalidInputsException.MissingKeyword("You have to input <todo>, <deadline> or"
                        + " <event> first!");
            } catch (InvalidInputsException.MissingKeyword exception) {
                exception.printStackTrace();
                System.out.println("Invalid keyword!!!");
            }
            break;
        case GOODBYE_KEYWORD:
            Ui.printGoodbyeMessage();
            break;
        }
    }

}
