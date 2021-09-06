public class Todo extends Task {
    /**
     * Constructor for Todo subclass.
     *
     * @param todoDescription Name of task.
     */
    public Todo(String todoDescription) throws DukeException{
        super(todoDescription.substring(5), "todo");
        if (super.description.equals("") | super.description.equals(" ")) {
            throw new DukeException();
        }
    }
}
