import org.jetbrains.annotations.NotNull;

public class ToDo extends Task {
    public static final String MARK_TO_DO = "[T]";
    public ToDo(String todo){
        super(todo);
    }

    @Override
    public String getTask(){
        if(isDone){
            return MARK_TO_DO + MARK_AS_DONE + " " + task;
        }
        else{
            return MARK_TO_DO + MARK_AS_NOT_DONE + " " + task;
        }
    }

}
