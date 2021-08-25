public class Task {
    private String description;
    private boolean status;

    public Task(String desc) {
        setDescription(desc);
        setStatus(false);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatus() {
        String result;
        if (status) {
            result = "X";
        } else {
            result = " ";
        }
        return result;
    }
}
