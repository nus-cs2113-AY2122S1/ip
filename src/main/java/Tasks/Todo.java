package Tasks;

<<<<<<< HEAD:src/main/java/Tasks/Todo.java
import java.time.LocalDateTime;


=======
>>>>>>> 6c45e62dbd0025b1449708253f2ba2b9181540f6:src/main/java/Todo.java
public class Todo extends Tasks {
    protected String type = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.done + "] " + this.description;
    }

    public String toOutput(){
        return "T_"+ this.done+"_"+this.description;
    }
}
