public class Event extends Task{
    private String at;

    public Event(String name, String date) {
        super(name);
        this.at = date;
    }

    public String getDate() {
        return at;
    }

    public void setDate(String date) {
        this.at = date;
    }

    @Override
    public String getTaskSymbol() {
        return "[E]";
    }

    public String toString() {
        return (getTaskSymbol() + getStatusSymbol() + " " + name + "(at: " + at + ")");
    }
}
