package Type;

public class Todo extends Task{
    public Todo(String description) {
        this.description = description;
    }
    public char getType() {
        return 'T';
    }

    @Override
    public String toString() {
        return Character.toString(this.getType()) + '|'
                + this.description.trim() + '|'
                + this.isDone();
    }
}
