public class Event extends ToDo{
    protected String period;

    public Event(){
        super();
        period = "";
    }

    public Event(String task, String period){
        super(task);
        this.period = period;
    }

    public String getClassType(){
        return "E";
    }

    @Override
    public String toString(){
        return task + " (at: " + period + ")";
    }
}
