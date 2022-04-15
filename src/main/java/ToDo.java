/**
 * Class inheriting from Task that has a description attribute
 */
public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getSymbol() {
        return "T"; // mark ToDos with a "T"
    }
}
