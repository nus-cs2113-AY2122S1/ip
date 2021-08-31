public class ToDo extends Task{

    public ToDo(String description, boolean completed) {
        super(description, completed);
    }

    public String toDoIcon() {
        String completedIcon = "T";
        return  completedIcon;
    }

    public void initialiseToDo(){
        System.out.println("______________________________\n");
        System.out.println("[" + toDoIcon() + "]"
                + "[ ]"
                + taskName
                + "has been added!\n");
    }
}
