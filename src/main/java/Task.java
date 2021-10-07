public class Task {
    protected String description;

    //Constructor of Task object
    public Task(String description) {
        this.description = description;
    }

    //Getter of description var
    public String getDescription() {
        return description;
    }

    //toString method
    public String toString() {
        return "[ ] " + description.substring(5);
    }
}