public class Task {
    private String descr; //descr as an abbreviation for description
    private boolean isDone;
    private static int listLength = 0;

    public Task(String descr) {
        this.descr = descr;
        this.isDone = false;
        listLength++;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //icon "[X]" as done and "[ ]" as not done
    }

    public String getDescr() {
        return this.descr;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static int getListLength() {
        return listLength;
    }
}
