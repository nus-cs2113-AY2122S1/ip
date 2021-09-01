public class ToDo extends Task{

    public ToDo(String description, boolean completed) {
        super(description, completed);
    }

    /**
     * Contains todo Icon [T].
     *
     * @return Icon in String format.
     */
    public String toDoIcon() {
        String completedIcon = "T";
        return  completedIcon;
    }

    /**
     * Output message when the todo task is added.
     */
    public void initialiseToDo(){
        System.out.println("______________________________\n");
        System.out.println("[" + toDoIcon() + "]"
                + "[ ]"
                + taskName
                + " has been added!\n");
    }

    /**
     * Override the toString method of ToDo class.
     *
     * @return String message in the right format.
     */
    @Override
    public String toString(){
        String s = "[" + toDoIcon() + "] " + "[" + super.completedTaskIcon() + "]" + super.taskName;
        return s;
    }
}