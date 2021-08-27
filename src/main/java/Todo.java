public class Todo extends Task{

    public Todo(String name) {
        super(name);
        this.typeOfTask = "[T]";
    }

    public String printTask() {
         return this.getTypeOfTask() + this.getDoneStatusAsSymbol() + " " + this.getName();
    }


}
