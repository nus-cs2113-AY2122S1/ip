public class Deadline extends Task {
    private String byDateTime;
    private String type;

    public Deadline(String description, String byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
        this.type = "D";
    }

    @Override
    public String getByDateTime() {
        return byDateTime;
    }

    @Override
    public String getType() {
        return type;
    }
}


