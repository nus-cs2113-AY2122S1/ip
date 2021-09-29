import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskLists {

    ArrayList<String> output;
    ArrayList<Integer> taskStatus;
    ArrayList<String> arrayInput;
    File file;
    ArrayList<String> taskType;
    int inputCount;
    String userCommand;
    UI ui = new UI();
    Parser parser = new Parser();

    public TaskLists(){}
    public TaskLists(ArrayList<String> Output, ArrayList<Integer> TaskStatus,
                     ArrayList<String> ArrayInput, File file1, ArrayList<String> TaskType,
                     int InputCount, String UserCommand) {
        ArrayList<String> output = new ArrayList<String> (Output);
        ArrayList<Integer> taskStatus = new ArrayList<Integer> (TaskStatus);
        ArrayList<String> arrayInput = new ArrayList<String> (ArrayInput);
        file = file1;
        ArrayList<String> taskType = new ArrayList<String> (TaskType);
        inputCount = InputCount;
        userCommand = UserCommand;
    }

    public ArrayList<String> getUpdateDelete(ArrayList<String> output,
                                              ArrayList<Integer> taskStatus,
                                              ArrayList<String> arrayInput, File file,
                                              ArrayList<String> taskType, int inputCount) {
        output = convertToArrayList(output, taskType, taskStatus, arrayInput, inputCount);
        saveTasks(file, output);
        return output;
    }

    public ArrayList<String> getUpdateEvent(ArrayList<String> output,
                                             ArrayList<Integer> taskStatus,
                                             ArrayList<String> arrayInput, File file,
                                             ArrayList<String> taskType, int inputCount, String e) {
        taskType.add(inputCount - 1, e);
        taskStatus.add(inputCount - 1, 0);
        output = convertToArrayList(output, taskType, taskStatus, arrayInput, inputCount);
        saveTasks(file, output);
        return output;
    }

    public ArrayList<String> getUpdateDeadline(ArrayList<String> output,
                                                ArrayList<Integer> taskStatus,
                                                ArrayList<String> arrayInput, File file,
                                                ArrayList<String> taskType, int inputCount) {
        taskType.add(inputCount - 1, "D");
        taskStatus.add(inputCount - 1, 0);
        output = convertToArrayList(output, taskType, taskStatus, arrayInput, inputCount);
        saveTasks(file, output);
        return output;
    }

    public ArrayList<String> getUpdateTodo(ArrayList<String> output,
                                            ArrayList<Integer> taskStatus,
                                            ArrayList<String> arrayInput, File file,
                                            ArrayList<String> taskType, int inputCount) {
        taskType.add(inputCount - 1, "T");
        taskStatus.add(inputCount - 1, 0);
        output = convertToArrayList(output, taskType, taskStatus, arrayInput, inputCount);
        saveTasks(file, output);
        return output;
    }

    public ArrayList<String> getUpdateDone(ArrayList<String> output,
                                            ArrayList<Integer> taskStatus,
                                            ArrayList<String> arrayInput, File file,
                                            String userCommand,
                                            ArrayList<String> taskType, int inputCount) {
        printDone(taskStatus, taskType, arrayInput, userCommand);
        output = convertToArrayList(output, taskType, taskStatus, arrayInput, inputCount);
        saveTasks(file, output);
        return output;
    }

    public boolean isValidTodo(String userCommand) {
        try {
            parser.checkTodo(userCommand);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            System.out.println(ui.LINE);
            return false;
        }
        return true;
    }

    public boolean isValidDeadline(String userCommand) {
        try {
            parser.checkDeadline(userCommand);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            System.out.println(ui.LINE);
            return false;
        }
        return true;
    }


    public boolean isValidEvent(String userCommand) {
        try {
            parser.checkEvent(userCommand);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
            System.out.println(ui.LINE);
            return false;
        }
        return true;
    }

    public boolean isValidDelete(String userCommand) {
        try {
            parser.checkDelete(userCommand);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The delete command is invalid.");
            System.out.println(ui.LINE);
            return false;
        }
        return true;
    }

    public int printDelete(ArrayList<String> taskType, ArrayList<Integer> taskStatus,
                           ArrayList<String> arrayInput, int inputCount, String userCommand) {
        int position = Integer.parseInt(userCommand.substring(7)) - 1;
        System.out.println("Noted. I've removed this task:");
        if (taskStatus.get(position) == 1) {
            System.out.println("  " + "[" + taskType.get(position) + "]"
                    + "[X] " + arrayInput.get(position));
        } else {
            System.out.println("  " + "[" + taskType.get(position) + "]"
                    + "[ ] " + arrayInput.get(position));
        }
        inputCount--;
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        System.out.println(ui.LINE);
        arrayInput.remove(position);
        taskStatus.remove(position);
        taskType.remove(position);
        return inputCount;
    }

    public void printInvalid() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public int printEvent(ArrayList<String> arrayInput, String userCommand, int inputCount) {
        String at;
        int index = userCommand.indexOf("-");
        at = userCommand.substring(index - 1);
        Event event = new Event(userCommand, at, index);
        inputCount++;
        System.out.println(ui.LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        System.out.println(ui.LINE);
        String taskName = event.description.substring(6, index - 10);
        String eventName = taskName + " (at: " + at + ")";
        arrayInput.add(inputCount - 1, eventName);
        return inputCount;
    }

    public int printDeadline(ArrayList<String> arrayInput, String userCommand, int inputCount) {
        String by;
        int index = userCommand.indexOf("by");
        by = userCommand.substring(index + 3);
        Deadline deadline = new Deadline(userCommand, by, index);
        inputCount++;
        System.out.println(ui.LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        System.out.println(ui.LINE);
        String taskName = deadline.description.substring(9, index - 2);
        String deadlineName = taskName + " (by: " + by + ")";
        arrayInput.add(inputCount - 1, deadlineName);
        return inputCount;
    }

    public int printTodo(ArrayList<String> arrayInput, String userCommand, int inputCount) {
        Todo todo = new Todo(userCommand);
        inputCount++;
        System.out.println(ui.LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        System.out.println(ui.LINE);
        String todoName = todo.description.substring(5);
        arrayInput.add(inputCount - 1, todoName);
        return inputCount;
    }

    public void printBye() {
        System.out.println(ui.LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(ui.LINE);
    }

    public void printDone(ArrayList<Integer> taskStatus, ArrayList<String> taskType,
                           ArrayList<String> arrayInput, String userCommand) {
        char taskID;
        int taskIDInt;
        int len = userCommand.length();
        taskID = userCommand.charAt(len - 1);
        taskIDInt = taskID - 49;
        taskStatus.set(taskIDInt, 1);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("	" + "[" + taskType.get(taskIDInt) + "]" +
                "[X] " + arrayInput.get(taskIDInt));
        System.out.println(ui.LINE);
    }

    public void printList(ArrayList<String> taskType, ArrayList<Integer> taskStatus,
                          ArrayList<String> arrayInput, int inputCount) {
        System.out.println(ui.LINE);
        System.out.println(ui.MESSAGE);
        for (int i = 1; i <= inputCount; i++) {
            if (taskStatus.get(i - 1) == 1) {
                System.out.println(i + ".[" + taskType.get(i - 1) + "]" + "[X] " + arrayInput.get(i - 1));
            } else {
                System.out.println(i + ".[" + taskType.get(i - 1) + "]" + "[ ] " + arrayInput.get(i - 1));
            }
        }
        System.out.println(ui.LINE);
    }

    public ArrayList<String> convertToArrayList(ArrayList<String> output, ArrayList<String> taskType,
                                                ArrayList<Integer> taskStatus,
                                                ArrayList<String> arrayInput, int inputCount) {
        String tempTask;
        output.clear();
        for (int i = 1; i <= inputCount; i++) {
            if (taskStatus.get(i - 1) == 1) {
                tempTask = i + ".[" + taskType.get(i - 1) + "]" + "[X] " + arrayInput.get(i - 1);
                output.add(i - 1, tempTask);
            } else {
                tempTask = i + ".[" + taskType.get(i - 1) + "]" + "[ ] " + arrayInput.get(i - 1);
                output.add(i - 1, tempTask);
            }
        }
        return output;
    }

    public void saveTasks(File file, ArrayList<String> output) {
        File directory = new File("./data/");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred, please try again!");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("./data/duke.txt");
            for (int i = 0; i < output.size(); i++) {
                myWriter.write(output.get(i));
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            System.out.println(ui.LINE);
        } catch (IOException e) {
            System.out.println("An error occurred, please try again!");
            e.printStackTrace();
        }
    }
}
