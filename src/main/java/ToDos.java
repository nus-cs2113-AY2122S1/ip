public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public static Task parse(String taskInfo){
        return new ToDos(taskInfo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
