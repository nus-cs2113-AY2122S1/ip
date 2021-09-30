package duke.task;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
        this.type = 'T';
    }

    public ToDo(String name, Boolean isDone) {
        super(name,isDone);
        this.type = 'T';
    }

}
