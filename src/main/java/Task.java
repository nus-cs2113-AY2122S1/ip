public class Task {
    protected String content;
    protected boolean Done;

    public Task(String content) {
        this.content = content;
        this.Done = false;
    }

    public void complete(){
        this.Done = true;
    }

    public String getInformation() {
        return (Done ? "X" : " ");
    }

}
