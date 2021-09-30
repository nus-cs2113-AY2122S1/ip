public class Task {
    protected String description;

    public Task(String description) {
        this.description = description;
    }   //Constructor of Task object

    public String getDescription() {
        return description;
    }   //Getter of description var

    public String toString() {
        return "[ ] " + description.substring(5);
    }   //toString method
}