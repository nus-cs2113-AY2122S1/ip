package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasklist.task.Task;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Command {
    private static final int CMD_NOT_FOUND = 0;
    private static final int CMD_TODO = 1;
    private static final int CMD_EVENT = 2;
    private static final int CMD_DEADLINE = 3;
    private static final int CMD_LIST = 4;
    private static final int CMD_DONE = 5;
    private static final int CMD_DELETE = 6;
    private static final int CMD_SHOW_DATE = 7;
    private static final int CMD_FIND = 8;
    private static final int CMD_TERMINATE = 0;
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final int INDEX_NUM_DONE = 5;
    private static final int INDEX_NUM_DELETE = 7;
    private static final String BY = "/by";
    private static final String AT = "/at";
    private static final String SHOW_DATE = "show date";
    private static final String FIND = "find";

    protected int command;
    protected boolean isExit = false;
    protected String userInput;

    public Command() {
        this.command = CMD_NOT_FOUND;
    }
    public void setCommand(int command) {
        this.command = command;
    }
    public void setUserInput(String userInput) {
        this.userInput = userInput;

    }
    public String getUserInput() {
        return this.userInput;
    }
    public int getCommand() {
        return this.command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskCount = tasks.getTaskCount();
        String[] userInputs;
        switch (command) {
            case CMD_TODO:
                tasks.addTodo(storage.items, userInput.replace(TODO, "").trim());
                tasks.loadTaskCount(storage.items);
                ui.addTaskMessage(tasks, storage.items.get(taskCount));
                break;
            case CMD_EVENT:
                userInputs = userInput.split(AT);
                tasks.addEvent(storage.items, userInputs[0].trim().replace(EVENT, "").trim(), userInputs[1].trim());
                tasks.loadTaskCount(storage.items);
                ui.addTaskMessage(tasks, storage.items.get(taskCount));
                break;
            case CMD_DEADLINE:
                userInputs = userInput.split(BY);
                tasks.addDeadline(storage.items, userInputs[0].trim().replace(DEADLINE, "").trim(), userInputs[1].trim());
                tasks.loadTaskCount(storage.items);
                ui.addTaskMessage(tasks, storage.items.get(taskCount));
                break;
            case CMD_LIST:
                int j = 1;
                System.out.println(Ui.border);
                System.out.println("Here are the task in your list:");
                for (Task item : storage.items) {
                    if (item != null) {
                        System.out.print(j + ".");
                        System.out.println(item);
                        j++;
                    }
                }
                break;
            case CMD_SHOW_DATE:
                int index = 1;
                LocalDate deadline = LocalDate.parse(getUserInput().replace(SHOW_DATE, "").trim(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                System.out.println(Ui.border);
                System.out.println("Here are the all the task in your list to be done by:" + deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
                for (Task item : storage.items) {
                    if (item.getDeadline().isEqual(deadline)) {
                        System.out.print(index + ".");
                        System.out.println(item);
                        index++;
                    }
                }
                break;
            case CMD_FIND:
                index = 1;
                String key = userInput.replace(FIND, "").trim();
                System.out.println(Ui.border);
                System.out.println("Here are the all the task in your list with keyword: " + key);
                for (Task item : storage.items) {
                    if (item.getDescription().contains(key)) {
                        System.out.print(index + ".");
                        System.out.println(item);
                        index++;
                    }
                }
                break;
            case CMD_DONE:
                int dividerPosition = userInput.indexOf(" ") + 1;
                int endPosition = userInput.length();
                if (endPosition > INDEX_NUM_DONE) {
                    String num = userInput.substring(dividerPosition, endPosition);
                    int taskNum = Integer.parseInt(num) - 1;
                    storage.items.get(taskNum).markDone();
                    ui.showLine();
                    System.out.println("Nice! task is done " + '\n');
                    ui.showLine();
                }
                break;
            case CMD_DELETE:
                dividerPosition = userInput.indexOf(" ") + 1;
                endPosition = userInput.length();
                if (endPosition > INDEX_NUM_DELETE) {
                    String num = userInput.substring(dividerPosition, endPosition);
                    int taskNum = Integer.parseInt(num) - 1;
                    tasks.removeItem(ui, storage.items, taskNum);
                }
                break;
            case CMD_TERMINATE:
                isExit = true;
                break;
        }
    }
    public boolean isExit() {
        return this.isExit;
    }
}
