package herrekt.taskmanager;

import java.util.List;

public interface Timetable {

    String getDescription();
    String toSave();
    void setDone();


    static void addTask(Task task) {
        System.out.println("Alright. I'll put it on the list.");
        Task.thingsToDo.add(task);
        System.out.println("  " + task.toString());
    }

    static String getTasks() {
        String toReturn = "Here are the tasks in your list:" + "\n";
        for (int i = 0; i < Task.thingsToDo.size(); i++) {
            if (i == Task.thingsToDo.size() - 1) {
                toReturn += (i + 1) + ". "
                        + Task.thingsToDo.get(i).toString();
                break;
            }
            toReturn += (i + 1) + ". "
                    + Task.thingsToDo.get(i).toString() + "\n";
        }
        return toReturn;
    }

    static List<Task> getThingsToDo() {
        return Task.thingsToDo;
    }

    static int getSize() {
        return Task.thingsToDo.size();
    }

    static void updateTasks(int taskNumber) {
        int index = taskNumber - 1;
        Task.finishTask(index);
    }

    static void deleteTasks(int taskNumber) {
        int index = taskNumber - 1;
        Task.removeTask(index);
    }
}
