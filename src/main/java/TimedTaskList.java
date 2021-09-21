import java.util.ArrayList;
import java.util.Comparator;

public class TimedTaskList {
    private static ArrayList<TimedTask> timedList = new ArrayList<>();

    public static ArrayList<TimedTask> getSortedList (){
        for (Task task : TaskList.getList()){
            if (task instanceof TimedTask){
                timedList.add((TimedTask)task);
            }
        }
        timedList.sort(TimedTaskDateComparator);
        return timedList;
    }

    public static Comparator<TimedTask> TimedTaskDateComparator = new Comparator<TimedTask>() {
        @Override
        public int compare(TimedTask task1, TimedTask task2){
            return task1.getStartDate().compareTo(task2.getStartDate());
        }
    };

}
