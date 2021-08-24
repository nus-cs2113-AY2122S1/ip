package task;

import time.Time;

// Some funcitionalities
// Time related: (Use time class)
// 1. No start/ end time (todo)
// 2. Has end time (deadline)
// 3. Has start and end time (event)
//
// Time related (extension)
// 1. Tentative time & confirm (time class)
// 2. Snoozing ( postpone/reschedule) (time class)
// 3. Recurring task (time class, checked by task manager)
// 4. Time dependencies (todo + has start time)
// 4.1 Attribute, can be sorted
// 5. Do withim period (todo + has start/end time)
// 6. Fixed duration (todo + fixed duration)
// 7. Reminder (task manager)
// 8. Find free time (task manager)
// 9. View daily schedule (task manager)
// 10. Detect clash (task manager)
// 11. detect duplicate (task manager)
// 12. sort, by different orders (task manager)
// 12.1 each attribute must be comparable
// 13. Reminder
// 14. Find free time
// 15. Update (task manager)
// 16. Priorities ( attribute)
// 17. Archive (task manager)

public abstract class TaskBase {
    private TaskType taskType;
    private Time taskTime;
    private String taskContent;
    private boolean isDone;

    /**
     * Constructor of task that accepts tasktype, time and taskcontent
     **/
    public TaskBase(TaskType taskType, Time taskTime, String taskContent) {
        this.taskType = taskType;
        this.taskTime = taskTime;
        this.taskContent = taskContent;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    /**
     * Utility to print all info related to task for list command
     **/
    public void printInfo() {
        System.out.println(String.format("[%c] %s %s",
                    isDone ? 'X' : ' ',
                    TaskType.getTaskStrbyTaskType(taskType), taskContent));
        taskTime.printTimeInfo();
    }
}
