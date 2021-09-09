package duke.list;

import duke.exceptions.IllegalOperation;
import duke.messages.MessageBubble;
import duke.task.Task;

public class List {
    public static int MAX_LIST_ITEMS = 100;
    private Task[] items;
    private int numOfListItems;

    public List() {
        // TODO: 2021/9/9 make linked list
        items = new Task[MAX_LIST_ITEMS];
        numOfListItems = 0;
    }

    public void addItem(Task task) throws IndexOutOfBoundsException{
        items[numOfListItems] = task;
        numOfListItems++;

        MessageBubble msg = new MessageBubble();
        msg.addMessage("Got it. I've added this task:");
        msg.addMessage(String.format(" %d:%s", numOfListItems, task.toString()));
        msg.addMessage("Now you have " + numOfListItems + " tasks in the list.");
        msg.printMessageBubble();
    }

    public void doneItem(int indexOfDoneItem) {
        int adjustedIndex = indexOfDoneItem - 1;
        try {
            items[adjustedIndex].setDone(true);
        } catch (NullPointerException e) {
            MessageBubble.printMessageBubble("Oops! I cannot find " + indexOfDoneItem + " in your list");
        } catch (IllegalOperation illegalOperation) {
            MessageBubble.printMessageBubble("Oops! The item is already done");
        }
    }

    public void undoneItem(int indexOfDoneItem) {
        int adjustedIndex = indexOfDoneItem - 1;
        try {
            items[adjustedIndex].setDone(false);
        } catch (NullPointerException e) {
            MessageBubble.printMessageBubble("Oops! I cannot find " + indexOfDoneItem + " in your list");
        } catch (IllegalOperation illegalOperation) {
            MessageBubble.printMessageBubble("Oops! The item is not done yet");
        }
    }

    public void printList() {
        // TODO: 2021/9/9 print empty list 
        MessageBubble msg = new MessageBubble();
        msg.addMessage("Here are the tasks in your list:");
        for (int i = 0; i < numOfListItems; i++) {
            msg.addMessage(String.format(" %d:%s", i + 1, items[i].toString()));
        }
        msg.printMessageBubble();
    }
}
