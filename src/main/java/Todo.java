public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }


    public String getDataStorageString() {
        return 'T' + super.getDataStorageString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
