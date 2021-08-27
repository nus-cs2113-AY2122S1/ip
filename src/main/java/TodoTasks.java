public class TodoTasks extends Tasks {

    public TodoTasks(String input){
        super(input);
    }

    @Override
    public String getName() {
        return "[T][" + super.mark() + "] " + super.name;
    }

}
