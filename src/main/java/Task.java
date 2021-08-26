public class Task {
    private String name;
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public String getStatusSymbol() {
        return ("[" + (isDone ? "X" : " ") + "]");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
        System.out.println(name + " done, well done.");
        System.out.println(getStatusSymbol() + " " + name);
    }
}
