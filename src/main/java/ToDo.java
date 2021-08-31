public class ToDo extends Task {
    private String type;

    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    public String getType() {
        return type;
    }
}
