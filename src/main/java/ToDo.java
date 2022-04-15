public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, String isDone) {
        super(description);
        if (isDone.equals("1")) {
            this.isDone = true;
        }
    }

    @Override
    public void printTask() {
        System.out.println("[T]" + super.getStatusIcon() + super.getDescription());
    }

    @Override
    public String getTextString() {
        return ("T" + "|" + super.getStatusValue() + "|" + super.getDescription() + "\n");
    }

}
