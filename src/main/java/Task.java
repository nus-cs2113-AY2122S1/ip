import java.awt.image.SinglePixelPackedSampleModel;

public class Task {
    protected String name;
    protected boolean done;

    public Task(String input){
        this.name = input;
        this.done = false;
    }
    public void task_done(){
        this.done = true;
    }
    public String getStatus(){
        return (done ? "X" : " ");
    }

}
