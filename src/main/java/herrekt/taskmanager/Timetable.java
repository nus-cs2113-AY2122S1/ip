package herrekt.taskmanager;

import java.util.List;

public interface Timetable {

    static void addTask(Timetable task) {
        System.out.println("Alright. I'll put it on the list.");
        Task.thingsToDo.add((Task) task);
        System.out.println("  " + task.toString());
    }

    static void getTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.thingsToDo.size(); i++) {
            System.out.println((i + 1) + ". "
                    + Task.thingsToDo.get(i).toString());
        }
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
