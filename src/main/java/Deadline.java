public class Deadline extends Todo {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.type = 'D';
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + date + ")";
    }
}
