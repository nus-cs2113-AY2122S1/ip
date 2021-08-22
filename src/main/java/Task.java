

public class Task {

    //variables
    private String description;
    private boolean done;
    private int index;

    //constructor
    public Task( String description , int index) {
        this.description = description;
        this.index = index;
        done = false;
    }

    //getters
    public String getDescription() {
        return description;
    }
    public boolean getDone() {
        return done;
    }
    public int getIndex() {
        return index;
    }

    //setters
    public void setDone() {
        this.done = true;
    }



}
