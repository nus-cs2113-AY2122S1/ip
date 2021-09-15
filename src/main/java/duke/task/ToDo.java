package duke.task;

//Stores task without specific time
public class ToDo extends Task {
    public ToDo() {
        super();
    }

    public ToDo(String task) {
        super(task);
    }

    public String getClassType() {
        return "T";
    }

    public String getTime() {
        return "";
    }

    @Override
    public String toString() {
        return task;
    }
}
