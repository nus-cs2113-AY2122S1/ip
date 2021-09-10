package duke.list;

import duke.exceptions.IllegalOperation;
import duke.exceptions.ItemNotFound;
import duke.messages.MessageBubble;
import duke.task.Task;

import java.lang.reflect.InvocationTargetException;

public class List {
    public static int MAX_LIST_ITEMS = 1000;
    private Task[] items;
    private ListItem headItem = null;
    private ListItem tailItem = null;
    private int numOfListItems;

    public List() {
        // TODO: 2021/9/9 make linked list
        items = new Task[MAX_LIST_ITEMS];
        numOfListItems = 0;
    }

    public ListItem getItemAtIndex(int index) throws ItemNotFound {
        if (index > numOfListItems || index < 1) {
            throw new ItemNotFound();
        }
        ListItem temp = headItem;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.getNext();
        }
        return temp;
    }

    public void addItem(Task task) throws IllegalOperation {
        if (numOfListItems >= MAX_LIST_ITEMS) {
            throw new IllegalOperation();
        }

        ListItem newItem = new ListItem(task);
        if (headItem == null) {
            headItem = newItem;
            tailItem = newItem;
            numOfListItems = 1;
        } else {
            tailItem.setNext(newItem);
            tailItem = tailItem.getNext();
            numOfListItems++;
        }

        MessageBubble msg = new MessageBubble();
        msg.addMessage("Got it. I've added this task:");
        msg.addMessage(String.format(" %d:%s", numOfListItems, task.toString()));
        msg.addMessage("Now you have " + numOfListItems + " tasks in the list.");
        msg.printMessageBubble();
    }

    public void removeItem(int index) {
        try {
            if (numOfListItems == 0) {
                throw new ItemNotFound();
            }

            ListItem targetItem = getItemAtIndex(index);

            if (numOfListItems == 1) {
                headItem = null;
                tailItem = null;
            } else if (index == 1) {
                headItem = headItem.getNext();
            } else {
                ListItem parent = getItemAtIndex(index - 1);
                if (targetItem == tailItem) {
                    tailItem = parent;
                }
                parent.setNext(parent.getNext().getNext());
            }
            MessageBubble.printMessageBubble(String.format("Ok, I have deleted \"%s\" from your list",
                    targetItem.getItem().getDescription()));
            numOfListItems--;
        } catch (ItemNotFound e) {
            MessageBubble.printMessageBubble("Oops! I cannot find item " + index + " in your list");
        }
    }

    public void doneItem(int indexOfDoneItem) {
        try {
            ListItem temp = getItemAtIndex(indexOfDoneItem);
            temp.getItem().setDone(true);
            MessageBubble.printMessageBubble("Ok, I have marked \"" + temp.getItem().getDescription() + "\" as done.");
        } catch (ItemNotFound e) {
            MessageBubble.printMessageBubble("Oops! I cannot find item " + indexOfDoneItem + " in your list");
        } catch (IllegalOperation e) {
            MessageBubble.printMessageBubble("Oops! The item is already done");
        }
    }

    public void undoneItem(int indexOfDoneItem) {
        try {
            ListItem temp = getItemAtIndex(indexOfDoneItem);
            temp.getItem().setDone(false);
            MessageBubble.printMessageBubble("Ok, I have marked \"" + temp.getItem().getDescription() + "\" as not done.");
        } catch (ItemNotFound e) {
            MessageBubble.printMessageBubble("Oops! I cannot find item" + indexOfDoneItem + " in your list");
        } catch (IllegalOperation e) {
            MessageBubble.printMessageBubble("Oops! The item is already done");
        }
    }

    public void printList() {
        // TODO: 2021/9/9 print empty list 
        MessageBubble msg = new MessageBubble();
        msg.addMessage("Here are the tasks in your list:");
        ListItem temp = headItem;
        for (int i = 1; i <= numOfListItems; i++) {
            msg.addMessage(String.format(" %d:%s", i, temp.getItem().toString()));
            temp = temp.getNext();
        }
        msg.printMessageBubble();
    }
}
