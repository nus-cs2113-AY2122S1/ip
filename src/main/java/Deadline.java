public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        category = "D";
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        String categoryIcon = this.getCategoryIcon();
        int spaceIndex = by.indexOf(" ");
        String preposition = by.substring(0, spaceIndex);
        String details = by.substring(spaceIndex + 1);
        return categoryIcon + statusIcon + " " + description + " (" + preposition + ": " + details + ")";
    }

}