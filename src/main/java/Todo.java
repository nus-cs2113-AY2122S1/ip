public class Todo extends Task{
    public Todo(String name) {
        super(name);
    }
    /**
     * Show the todo list.
     */

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}