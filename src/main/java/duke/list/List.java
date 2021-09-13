package duke.list;

import duke.exceptions.IllegalOperation;
import duke.messages.MessageBubble;
import duke.task.Task;

import java.util.ArrayList;

public class List {
    public static int MAX_LIST_ITEMS = 1000;
    private ArrayList<Task> items;

    public List() {
        // TODO: 2021/9/9 make linked list
        items = new ArrayList<>();
    }

    public void addItem(Task task) throws IllegalOperation {
        addItem(task, true);
    }

    public void addItem(Task task, boolean showMessage) throws IllegalOperation {
        if (items.size() >= MAX_LIST_ITEMS) {
            throw new IllegalOperation();
        }
        items.add(task);

        if (showMessage) {
            MessageBubble msg = new MessageBubble();
            msg.addMessage("Got it. I've added this task:");
            msg.addMessage(String.format(" %d:%s", items.size(), task.toString()));
            msg.addMessage("Now you have " + items.size() + " tasks in the list.");
            msg.printMessageBubble();
        }
    }

    public void removeItem(int index) {
        int adjustedIndex = index - 1;
        try {
            String removed_item_description = items.get(adjustedIndex).getDescription();
            items.remove(adjustedIndex);
            MessageBubble.printMessageBubble(String.format("Ok, I have deleted \"%s\" from your list",
                    removed_item_description));
        } catch (IndexOutOfBoundsException e) {
            MessageBubble.printMessageBubble("Oops! I cannot find item " + index + " in your list");
        }
    }

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

    public void printList() {
        // TODO: 2021/9/9 print empty list
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

    public String printListSimple() {
        String result = "";
        for (Task item : items) {
            result = result.concat(item.getSaveFormat() + "\n");
        }
        return result;
    }
}
