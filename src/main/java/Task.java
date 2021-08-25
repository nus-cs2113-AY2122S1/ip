public class Task {
    private String description;
    private boolean isDone = false;
    private int howLong = 0;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, int howLong) {
        this.description = description;
        this.howLong = howLong;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getHowLong() {
        return howLong;
    }

    public void setHowLong(int howLong) {
        this.howLong = howLong;
    }
}
