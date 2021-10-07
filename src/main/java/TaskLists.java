import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskLists {

    ArrayList<String> output;
    ArrayList<Integer> taskStatus;
    ArrayList<String> taskName;
    File file;
    ArrayList<String> taskType;
    int inputCount;
    String userCommand;
    UI ui = new UI();
    Parser parser = new Parser();

    //Default constructor of the TaskLists object
    public TaskLists() {
    }

    //Constructor of TaskLists object
    public TaskLists(ArrayList<String> Output, ArrayList<Integer> TaskStatus,
                     ArrayList<String> TaskName, File file1, ArrayList<String> TaskType,
                     int InputCount, String UserCommand) {
        ArrayList<String> output = new ArrayList<String>(Output);
        ArrayList<Integer> taskStatus = new ArrayList<Integer>(TaskStatus);
        ArrayList<String> taskName = new ArrayList<String>(TaskName);
        file = file1;
        ArrayList<String> taskType = new ArrayList<String>(TaskType);
        inputCount = InputCount;
        userCommand = UserCommand;
    }

    //update arrayLists after user input a 'delete' command
    public ArrayList<String> getUpdateDelete(ArrayList<String> output,
                                             ArrayList<Integer> taskStatus,
                                             ArrayList<String> taskName, File file,
                                             ArrayList<String> taskType, int inputCount) {
        output = convertToArrayList(output, taskType, taskStatus, taskName, inputCount);
        saveTasks(file, output);
        return output;
    }

    //update arrayLists after user input a 'event' to the list
    public ArrayList<String> getUpdateEvent(ArrayList<String> output,
                                            ArrayList<Integer> taskStatus,
                                            ArrayList<String> taskName, File file,
                                            ArrayList<String> taskType, int inputCount) {
        taskType.add("E");
        taskStatus.add(0);
        output = convertToArrayList(output, taskType, taskStatus, taskName, inputCount);
        saveTasks(file, output);
        return output;
    }

    //update arrayLists after user input a 'deadline' to the list
    public ArrayList<String> getUpdateDeadline(ArrayList<String> output,
                                               ArrayList<Integer> taskStatus,
                                               ArrayList<String> taskName, File file,
                                               ArrayList<String> taskType, int inputCount) {
        taskType.add("D");
        taskStatus.add(0);
        output = convertToArrayList(output, taskType, taskStatus, taskName, inputCount);
        saveTasks(file, output);
        return output;
    }

    //update arrayLists after user input a 'todo' to the list
    public ArrayList<String> getUpdateTodo(ArrayList<String> output,
                                           ArrayList<Integer> taskStatus,
                                           ArrayList<String> taskName, File file,
                                           ArrayList<String> taskType, int inputCount) {
        taskType.add("T");
        taskStatus.add(0);
        output = convertToArrayList(output, taskType, taskStatus, taskName, inputCount);
        saveTasks(file, output);
        return output;
    }

    //update arrayLists after user input a 'done' command
    public ArrayList<String> getUpdateDone(ArrayList<String> output,
                                           ArrayList<Integer> taskStatus,
                                           ArrayList<String> taskName, File file,
                                           String userCommand,
                                           ArrayList<String> taskType, int inputCount) {
        printDone(taskStatus, taskType, taskName, userCommand);
        output = convertToArrayList(output, taskType, taskStatus, taskName, inputCount);
        saveTasks(file, output);
        return output;
    }

    //check if the ’todo‘ userInput is valid and remind user if the command is invalid
    public boolean isValidTodo(String userCommand) {
        try {
            parser.checkTodo(userCommand);
        } catch (DukeException e) {
            System.out.println(ui.LINE);
            System.out.println("OOPS!!! The description of a todo task is invalid.");
            System.out.println(ui.LINE);
            return false;
        }
        return true;
    }

    //check if the 'deadline' userInput is valid and remind user if the command is invalid
    public boolean isValidDeadline(String userCommand) {
        try {
            parser.checkDeadline(userCommand);
        } catch (DukeException e) {
            System.out.println(ui.LINE);
            System.out.println("OOPS!!! The description of a deadline task is invalid.");
            System.out.println(ui.LINE);
            return false;
        }
        return true;
    }


    //check if the 'event' userInput is valid and remind user if the command is invalid
    public boolean isValidEvent(String userCommand) {
        try {
            parser.checkEvent(userCommand);
        } catch (DukeException e) {
            System.out.println(ui.LINE);
            System.out.println("OOPS!!! The description of a event task is invalid.");
            System.out.println(ui.LINE);
            return false;
        }
        return true;
    }

    //check if the 'delete' command is valid and remind user if the command is invalid
    public boolean isValidDelete(String userCommand, int inputCount) {
        try {
            parser.checkDelete(userCommand);
        } catch (DukeException e) {
            System.out.println(ui.LINE);
            System.out.println("OOPS!!! The 'delete' command is invalid.");
            System.out.println(ui.LINE);
            return false;
        }

        if (Integer.parseInt(userCommand.substring(7)) > inputCount) {
            System.out.println(ui.LINE);
            System.out.println("OOPS!!! The 'delete' command is out of bound.");
            System.out.println(ui.LINE);
            return false;
        }
        return true;
    }

    //check if the 'done' command is valid and remind user if the command is invalid
    public boolean isValidDone(String userCommand, int inputCount) {
        try {
            parser.checkDone(userCommand);
        } catch (DukeException e) {
            System.out.println(ui.LINE);
            System.out.println("OOPS!!! The 'done' command is invalid.");
            System.out.println(ui.LINE);
            return false;
        }
        if (Integer.parseInt(userCommand.substring(5)) > inputCount) {
            System.out.println(ui.LINE);
            System.out.println("OOPS!!! The 'done' command is out of bound.");
            System.out.println(ui.LINE);
            return false;
        }
        return true;
    }

    //check if the 'find' command is valid and remind user if the command is invalid
    public boolean isValidFind(String userCommand) {
        try {
            parser.checkFind(userCommand);
        } catch (DukeException e) {
            System.out.println(ui.LINE);
            System.out.println("OOPS!!! The 'find' command is invalid.");
            System.out.println(ui.LINE);
            return false;
        }
        return true;
    }

    //print the result of the 'delete' command
    public int printDelete(ArrayList<String> taskType, ArrayList<Integer> taskStatus,
                           ArrayList<String> taskName, int inputCount, String userCommand) {
        int position = Integer.parseInt(userCommand.substring(7)) - 1;
        System.out.println(ui.LINE);
        System.out.println("Noted. I've removed this task:");
        if (taskStatus.get(position) == 1) {
            System.out.println("  " + "[" + taskType.get(position) + "]"
                    + "[X] " + taskName.get(position));
        } else {
            System.out.println("  " + "[" + taskType.get(position) + "]"
                    + "[ ] " + taskName.get(position));
        }
        inputCount--;
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        System.out.println(ui.LINE);
        taskName.remove(position);
        taskStatus.remove(position);
        taskType.remove(position);
        return inputCount;
    }

    //print the result of a meaningless input
    public void printInvalid() {
        System.out.println(ui.LINE);
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    //print the result of adding an 'event' task
    public int printEvent(ArrayList<String> taskName, String userCommand, int inputCount) {
        String at;
        int index = userCommand.indexOf("/");
        at = userCommand.substring(index + 1);
        Event event = new Event(userCommand, at, index);
        inputCount++;
        System.out.println(ui.LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        System.out.println(ui.LINE);
        String task = event.description.substring(6, index - 1);
        String eventName = task + " (at: " + at.substring(3) + ")";
        taskName.add(eventName);
        return inputCount;
    }

    //print the result of adding a 'deadline' task
    public int printDeadline(ArrayList<String> taskName, String userCommand, int inputCount) {
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
        String task = deadline.description.substring(9, index - 2);
        String deadlineName = task + " (by: " + by + ")";
        taskName.add(deadlineName);
        return inputCount;
    }

    //print the result of adding a 'todo' task
    public int printTodo(ArrayList<String> taskName, String userCommand, int inputCount) {
        Todo todo = new Todo(userCommand);
        inputCount++;
        System.out.println(ui.LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        System.out.println(ui.LINE);
        String todoName = todo.description.substring(5);
        taskName.add(todoName);
        return inputCount;
    }

    //print the result of terminating command
    public void printBye() {
        System.out.println(ui.LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(ui.LINE);
    }

    //print the result of 'done' command
    public void printDone(ArrayList<Integer> taskStatus, ArrayList<String> taskType,
                          ArrayList<String> taskName, String userCommand) {
        char taskID;
        int taskIDInt;
        int len = userCommand.length();
        taskID = userCommand.charAt(len - 1);
        taskIDInt = taskID - 49;
        taskStatus.set(taskIDInt, 1);
        System.out.println(ui.LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("	" + "[" + taskType.get(taskIDInt) + "]" +
                "[X] " + taskName.get(taskIDInt));
        System.out.println(ui.LINE);
    }

    //print the result of 'find' command
    public void printFind(ArrayList<String> output, String userCommand, int inputCount) {
        System.out.println(ui.LINE);
        System.out.println("Here are the matching tasks in your list:");
        int taskNumber = 1;
        String tempOutput;
        for (int i = 0; i < inputCount; i++) {
            if (output.get(i).contains(userCommand.substring(5))) {
                tempOutput = taskNumber + output.get(i).substring(1);
                System.out.println(tempOutput);
                taskNumber++;
            }
        }
        if (taskNumber == 1) {
            System.out.println("Sorry! No matching tasks found.");
        }
        System.out.println(ui.LINE);
    }

    //print the result of 'list' command by printing all the tasks in the current list
    public void printList(ArrayList<String> taskType, ArrayList<Integer> taskStatus,
                          ArrayList<String> taskName, int inputCount) {
        System.out.println(ui.LINE);
        System.out.println(ui.MESSAGE);
        if (inputCount == 0) {
            System.out.println("The list is empty!");
            return;
        }
        for (int i = 1; i <= inputCount; i++) {
            if (taskStatus.get(i - 1) == 1) {
                System.out.println(i + ".[" + taskType.get(i - 1) + "]" + "[X] " + taskName.get(i - 1));
            } else {
                System.out.println(i + ".[" + taskType.get(i - 1) + "]" + "[ ] " + taskName.get(i - 1));
            }
        }
        System.out.println(ui.LINE);
    }

    //update the 'output' ArrayList with corresponding taskNames
    public ArrayList<String> convertToArrayList(ArrayList<String> output, ArrayList<String> taskType,
                                                ArrayList<Integer> taskStatus,
                                                ArrayList<String> taskName, int inputCount) {
        String tempTask;
        output.clear();
        for (int i = 1; i <= inputCount; i++) {
            if (taskStatus.get(i - 1) == 1) {
                tempTask = i + ".[" + taskType.get(i - 1) + "]" + "[X] " + taskName.get(i - 1);
                output.add(tempTask);
            } else {
                tempTask = i + ".[" + taskType.get(i - 1) + "]" + "[ ] " + taskName.get(i - 1);
                output.add(tempTask);
            }
        }
        return output;
    }

    //print the result of 'list' command if the current list is empty
    public void printEmptyList() {
        System.out.println(ui.LINE);
        System.out.println("Empty list!");
        System.out.println(ui.LINE);
    }

    //perform the "save" function to save the current list to the output file
    public void saveTasks(File file, ArrayList<String> output) {
        File directory = new File("./data/");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
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
        } catch (IOException e) {
            System.out.println("An error occurred, please try again!");
            e.printStackTrace();
        }
    }
}
