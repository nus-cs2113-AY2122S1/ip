import java.util.ArrayList;
import java.util.Comparator;

public class TimedTaskList {
    private static ArrayList<TimedTask> timedList = new ArrayList<>();

    /**
     * Sort task based on time/start time and remove Todo while sorting
     * @return List of Deadline and Event in ascending order of time
     */
    public static ArrayList<TimedTask> getSortedList (){
        for (Task task : TaskList.getList()){
            if (task instanceof TimedTask){
                timedList.add((TimedTask)task);
            }
        }
        timedList.sort(TimedTaskDateComparator);
        return timedList;
    }

    /**
     * Override Comparator to make sorting of deadline and event based on time possible
     */
    public static Comparator<TimedTask> TimedTaskDateComparator = new Comparator<TimedTask>() {
        @Override
        public int compare(TimedTask task1, TimedTask task2){
            return task1.getStartDate().compareTo(task2.getStartDate());
        }
    };

}
