package shima.task;

//Stores task without specific time
public class ToDo extends Task {
    public ToDo() {
        super();
    }

    public ToDo(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getClassType() {
        return TaskType.T.toString();
    }

    public String getTime() {
        return "";
    }

    @Override
    public String toString() {
        return task;
    }
}
