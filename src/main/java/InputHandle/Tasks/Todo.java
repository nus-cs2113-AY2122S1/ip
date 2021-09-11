
package InputHandle.Tasks;

import java.io.Serializable;

public class Todo extends Task implements Serializable {

    public Todo (String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
