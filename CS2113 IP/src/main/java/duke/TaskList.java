package duke;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(String filePath) throws IOException, DukeException {
        tasks = new ArrayList<Task>();
        setUpDuke(filePath, tasks);
    }

    public Task get(int indexNumber) {
        return tasks.get(indexNumber);
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task newTask) {
        tasks.add(newTask);
    }

    public void remove(int indexNumber) {
        tasks.remove(indexNumber);
    }

    private static boolean isIncomplete(String[] lineData) {
        return lineData.length < 3;
    }

    private static void setUpDuke(String filePath, ArrayList<Task> tasks) throws IOException, DukeException {
        File storedData = new File(filePath);
        Scanner sc = new Scanner(storedData);
        final int TASK_TYPE_INDEX = 0;
        final int DONE_INDEX = 1;
        final int DESCRIPTION_INDEX = 2;

        while (sc.hasNext()) {
            boolean isDone;
            Task newTask;
            String userInput;
            String lineDataString = sc.nextLine();
            String[] lineData = lineDataString.trim().split(" \\| ");
            if (isIncomplete(lineData)) {
                throw new DukeException();
            }
            String taskTypeString = lineData[TASK_TYPE_INDEX];
            String isDoneString = lineData[DONE_INDEX];
            String description = lineData[DESCRIPTION_INDEX];
            isDone = isDoneString.equals("X");

            switch (taskTypeString) {
            case ("T"):
                userInput = String.format("todo %s", description.trim());
                newTask = new Todo(userInput, Task.taskCount);
                tasks.add(Task.taskCount, newTask);
                break;
            case ("E"):
                userInput = String.format("event %s", description.trim());
                newTask = new Event(userInput, Task.taskCount);
                tasks.add(Task.taskCount, newTask);
                break;
            case ("D"):
                userInput = String.format("deadline %s", description.trim());
                newTask = new Deadline(userInput, Task.taskCount);
                tasks.add(Task.taskCount, newTask);
                break;
            }
            if (isDone) {
                tasks.get(Task.taskCount).markAsDone();
            }
            Task.taskCount++;
        }
    }
}
