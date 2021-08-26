public class Task {
    String name;
    boolean isDone=false;
    public Task(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }
    public boolean getIsDone(){
        return isDone;
    }
    public void setIsDone(){
        isDone=true;
    }
}
