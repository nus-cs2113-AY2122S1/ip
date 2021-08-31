public class ToDo extends Task{

    public ToDo(String description, int noOfTask) {
        super(description, noOfTask);
    }

    public String toString() {
        return ("[T]" + super.toString());
    }
}
