public class Event extends Task {

    protected String on;

    public Event(String description, String on) {
        super(description);
        this.on = on;
        category = "E";
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        String categoryIcon = this.getCategoryIcon();
        int spaceIndex = on.indexOf(" ");
        String preposition = on.substring(0, spaceIndex);
        String details = on.substring(spaceIndex + 1);
        return categoryIcon + statusIcon + " " + description + " (" + preposition + ": " + details + ")";
    }

}