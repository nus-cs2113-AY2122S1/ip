public class Deadline extends Task{

    protected String deadline;

    public Deadline(String description) {
        super(description.substring(description.indexOf("deadline") + 8, description.indexOf("/")).trim());
        deadline = description.substring(description.indexOf("/")).replace("/by", "").trim();
    }

    @Override
    public String getTaskType() {
        return ("D");
    }

    @Override
    public String getDeadline(){

        return (deadline);
    }
}
