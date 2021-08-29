public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public String getTaskSymbol() {
        return "[T]";
    }

    public String toString() {
        return (getTaskSymbol() + getStatusSymbol() + " " + name);
    }
}
