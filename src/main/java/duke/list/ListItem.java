package duke.list;

import duke.task.Task;

public class ListItem {
    private Task item;
    private ListItem next;

    public ListItem(Task item) {
        setItem(item);
    }

    public Task getItem() {
        return this.item;
    }

    public void setItem(Task item) {
        this.item = item;
    }

    public ListItem getNext() {
        return this.next;
    }

    public void setNext(ListItem next) {
        this.next = next;
    }
}
