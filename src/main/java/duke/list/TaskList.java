package duke.list;

import duke.exceptions.IllegalOperation;
import duke.ui.MessageBubble;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Locale;

public class TaskList {
    public static int MAX_LIST_ITEMS = 1000;
    private final ArrayList<Task> items = new ArrayList<>();

    /**
     * Convenience constructor
     */
    public TaskList() {
    }

    /**
     * Add the specified Task to the end of the TaskList.
     * A message will be print out to show the result.
     *
     * @param task the task to be added
     * @throws IllegalOperation if the number of tasks >= MAX_LIST_ITEMS
     */
    public void addItem(Task task) throws IllegalOperation {
        addItemWithoutPrintResult(task);

        MessageBubble msg = new MessageBubble();
        msg.addMessage("Got it. I've added this task:");
        msg.addMessage(String.format(" %d:%s", items.size(), task.toString()));
        msg.addMessage("Now you have " + items.size() + " tasks in the list.");
        msg.printMessageBubble();
    }

    /**
     * Add the specified Task to the end of the TaskList.
     * A message will be print out to show the result if printResult == true.
     *
     * @param task the task to be added
     * @throws IllegalOperation if the number of tasks >= MAX_LIST_ITEMS
     */
    public void addItemWithoutPrintResult(Task task) throws IllegalOperation {
        if (items.size() >= MAX_LIST_ITEMS) {
            throw new IllegalOperation();
        }
        items.add(task);
    }

    /**
     * Remove the Task at the specified index in the TaskList.
     *
     * @param index index of the Task to be removed
     */
    public void removeItem(int index) {
        int adjustedIndex = index - 1;
        try {
            String removedItemDescription = items.get(adjustedIndex).getDescription();
            items.remove(adjustedIndex);
            MessageBubble.printMessageBubble(String.format("Ok, I have deleted \"%s\" from your list",
                    removedItemDescription));
        } catch (IndexOutOfBoundsException e) {
            MessageBubble.printMessageBubble("Oops! I cannot find item " + index + " in your list");
        }
    }

    /**
     * Mark the Task at the specified index in the TaskList as done.
     * e.g. status = true
     *
     * @param indexOfDoneItem index of the Task to be marked as done
     */
    public void doneItem(int indexOfDoneItem) {
        int adjustedIndex = indexOfDoneItem - 1;
        try {
            items.get(adjustedIndex).setDone(true);
            MessageBubble.printMessageBubble("Ok, I have marked \"" +
                    items.get(adjustedIndex).getDescription() + "\" as done.");
        } catch (IndexOutOfBoundsException e) {
            MessageBubble.printMessageBubble("Oops! I cannot find item " +
                    adjustedIndex + " in your list");
        } catch (IllegalOperation e) {
            MessageBubble.printMessageBubble("Oops! The item is already done");
        }
    }

    /**
     * Mark the Task at the specified index in the TaskList as not done.
     * e.g. status = false
     *
     * @param indexOfDoneItem index of the Task to be marked as not done
     */
    public void undoneItem(int indexOfDoneItem) {
        int adjustedIndex = indexOfDoneItem - 1;
        try {
            items.get(adjustedIndex).setDone(false);
            MessageBubble.printMessageBubble("Ok, I have marked \"" +
                    items.get(adjustedIndex).getDescription() + "\" as not done.");
        } catch (IndexOutOfBoundsException e) {
            MessageBubble.printMessageBubble("Oops! I cannot find item " +
                    adjustedIndex + " in your list");
        } catch (IllegalOperation e) {
            MessageBubble.printMessageBubble("Oops! The item is not done yet");
        }
    }

    /**
     * Print all Tasks in the TaskList in the chronological order.
     * For each task, its category, status and description will be displayed in a new line.
     */
    public void searchItem(String keyword) {
        MessageBubble msg = new MessageBubble("Here are the matching tasks in your list:");
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                msg.addMessage(String.format(" %d:%s", i + 1, items.get(i)));
            }
        }
        if (msg.getMessagesCount() == 1) {
            MessageBubble.printMessageBubble("No result found.");
        } else {
            msg.printMessageBubble();
        }
    }

    public void printList() {
        if (items.isEmpty()) {
            MessageBubble.printMessageBubble("There is nothing on your list");
        } else {
            MessageBubble msg = new MessageBubble();
            msg.addMessage("Here are the tasks in your list:");
            for (int i = 0; i < items.size(); i++) {
                msg.addMessage(String.format(" %d:%s", i + 1, items.get(i)));
            }
            msg.printMessageBubble();
        }
    }

    /**
     * Returns the entire TaskList as a String that can be saved to a txt file, which can be later read and restored to the original TaskList.
     *
     * @return String to be saved as txt file
     */
    public String printListSaveFormat() {
        String result = "";
        for (Task item : items) {
            result = result.concat(item.getSaveFormat() + "\n");
        }
        return result;
    }
}
