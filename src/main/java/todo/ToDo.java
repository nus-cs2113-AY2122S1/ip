package todo;

public class ToDo extends Task {


    public ToDo(String description) {
        super(description);
        super.setType("t");
    }

    public String toString() {
        return "[T]" + super.toString();
    }

}
