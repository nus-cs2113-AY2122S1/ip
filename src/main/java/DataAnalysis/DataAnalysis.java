package DataAnalysis;

import Storage.Storage;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import Ui.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

public class DataAnalysis {

    public void listTasks(UserInterface ui, ArrayList<Task> taskList) {
        ui.printOutput("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("    " + (i + 1) + ". [" + taskList.get(i).getTaskType() + "]" + "[" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).getDescription());
        }
        ui.printOutput("");
    }

    public void processInput(UserInterface ui, Storage storage, ArrayList<Task> taskList) {
        int descriptionIndex = 0;
        int taskCount = taskList.size();
        String line = ui.getUserInput();
        String[] splicedLine;
        String commandWord;

        String task;
        String taskDate;
        while (!line.equals("bye")) {

            splicedLine = line.split(" ");
            commandWord = splicedLine[0];

            if ((commandWord.equals("done") || commandWord.equals("todo") || commandWord.equals("deadline") || commandWord.equals("event")) && splicedLine.length == 1) {
                ui.printOutput("Here are the tasks in your list:\n");

            } else if (line.equals("list")) {
                if (taskCount == 0) {
                    ui.printOutput("You don't have any items on your list! Add something :)\n");
                } else {
                    ui.printOutput("Here are the tasks in your list:\n");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("    " + (i + 1) + ". [" + taskList.get(i).getTaskType() + "]" + "[" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).getDescription());
                    }
                    ui.printOutput("");
                }
            } else if (commandWord.equals("done")) {
                descriptionIndex = Integer.parseInt(splicedLine[1]);
                line = taskList.get(descriptionIndex - 1).getDescription();
                ui.printOutput("Nice! I\'ve marked this task as done:\n" + "   [" + taskList.get(descriptionIndex - 1).getTaskType() + "][X] " + line + "\n");

                taskList.get(descriptionIndex - 1).markAsDone();
            } else if (commandWord.equals("todo")) {
                task = line.substring(line.indexOf(' '));
                taskDate = "";
                taskList.add(new Todo(task, false));
                ui.printOutput(taskList.get(taskCount).toString() + "Now you have " + ++taskCount + " tasks on the list.\n");

            } else if (commandWord.equals("deadline")) {
                task = line.substring(line.indexOf(' ') + 1, line.indexOf('/') - 1);
                taskDate = line.substring(line.indexOf('/') + 4);
                taskList.add(new Deadline(task, false, taskDate));
                ui.printOutput(taskList.get(taskCount).toString() + "Now you have " + ++taskCount + " tasks on the list.\n");

            } else if (commandWord.equals("event")) {
                task = line.substring(line.indexOf(' ') + 1, line.indexOf('/') - 1);
                taskDate = line.substring(line.indexOf('/') + 4);
                taskList.add(new Event(task, false, taskDate));
                ui.printOutput(taskList.get(taskCount).toString() + "Now you have " + ++taskCount + " tasks on the list.\n");

            } else if (commandWord.equals("delete")) {
                descriptionIndex = Integer.parseInt(splicedLine[1]);
                line = taskList.get(descriptionIndex - 1).getDescription();
                ui.printOutput("Noted. I\'ve removed this task:\n" + "   [" + taskList.get(descriptionIndex - 1).getTaskType() +
                        "][" + taskList.get(descriptionIndex - 1).getStatusIcon() + "] " + line + "Now you have " + --taskCount + " tasks on the list.\n");
                taskList.remove(taskList.get(descriptionIndex - 1));

            } else if (commandWord.equals("find")) {
                String wordToFind = " " + splicedLine[1] + " ";
                String description;
                int j = 0;
                ui.printOutput("Here are the matching tasks in your list:\n");
                for (int i = 0; i < taskCount; i++) {
                    description = taskList.get(i).getDescription();
                    if (description.contains(wordToFind)) {
                        System.out.println((j + 1) + ". [" + taskList.get(i).getTaskType() + "]" + "[" + taskList.get(i).getStatusIcon() + "] " + description);
                        j++;
                    }
                }
                if (j == 0) {
                    ui.printOutput("Oops! There are no matches :(\n");
                }
                ui.printOutput("");
            } else {
                ui.printOutput("Oops! I don't know what that means :-(\n");
            }
            try {
                storage.saveTasks(taskList);
            } catch (IOException e) {
                ui.printError("Something went wrong: " + e.getMessage());
            }
            line = ui.getUserInput();

        }
    }
}
