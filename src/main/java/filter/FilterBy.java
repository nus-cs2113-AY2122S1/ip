package filter;

import parser.DateParser;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;

import java.util.ArrayList;
import java.util.Date;

public class FilterBy {
  /*  public String Date(Date date) {
        if (date==null){
            return "Invalid date! Ensure that it is in dd/MM/YYYY";
        }
        ArrayList<Task> filteredTasks = new ArrayList<>();
        String dateWithoutTime = DateParser.dateWithoutTime(date);
        for (Task task : TaskList.tasks) {
            if (task instanceof Event){
                Event event = (Event) task;
                if (DateParser.dateWithoutTime(event.getAt()).equals(dateWithoutTime)){
                    filteredTasks.add(event);
                }
            }else if(task instanceof Deadline){
                Deadline deadline = (Deadline) task;
                if (DateParser.dateWithoutTime(deadline.getDate()).equals(dateWithoutTime)){
                    filteredTasks.add(deadline);
                }
            }
        }
        return TaskList.listFilteredTasks(filteredTasks);
    }*/
}
