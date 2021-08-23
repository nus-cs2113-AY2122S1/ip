public class ListItem {
    private String toDoItem;
    private boolean isDone;

    public ListItem(String toDoItem) {
        this.toDoItem = toDoItem;
        this.isDone = false;
    }

    public String getToDoItem() {
        return toDoItem;
    }

    public void setToDoItem(String toDoItem) {
        this.toDoItem = toDoItem;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }
}
