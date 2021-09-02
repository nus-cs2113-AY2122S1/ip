public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    public String getSymbol() {
        return "T"; // mark ToDos with a "T"
    }
}
