//possible to make this class an inner private class in TodoList
public class TodoListItem {
    private String item;
    private boolean isDone;

    public TodoListItem(String item) {
        this.item = item;
        this.isDone = false;
    }


    public void setDone() {
        this.isDone = true;
    }

    public boolean status() {
        return this.isDone;
    }


    public String getItem() {
        return this.item;
    }
}
