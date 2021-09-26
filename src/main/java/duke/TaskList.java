package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> commands;
    protected int positionCheck;
    protected int loadFlag;
    protected int taskNumber;


    public TaskList(ArrayList<String> loadedTasks) {
        this.commands = new ArrayList<>();
        this.positionCheck = 0;
        this.loadFlag = 0;
        this.taskNumber = 0;
        String[] taskFromFile;
        String[] taskInput;
        try {
            for (String task : loadedTasks) {
                taskFromFile = task.split(" \\| ");
                taskInput = (taskFromFile[0]).split(" ");
                switch (taskInput[0]) {
                case "deadline":
                    addDeadline(taskInput, taskInput.length);
                    break;
                case "event":
                    addEvent(taskInput, taskInput.length);
                    break;
                case "todo":
                    addTodo(taskInput, taskInput.length);
                default:
                }
                if (taskFromFile[1].equals("1")) {
                    markDone(taskNumber);
                }
                this.taskNumber += 1;
            }
            this.loadFlag = 1;
        } catch (DukeException e) {
            //Catching DukeException errors
        } catch (IOException e) {
            System.out.print("There is an error in your input master! Please check it out!");
        } catch (DateTimeParseException e) {
            System.out.println("Correct date format please!");
        }
    }
    public void addDeadline(String[] input, int length) throws DukeException, IOException, DateTimeParseException {
        String description;
        String by;
        for (int i = 1 ; i < length ; i++) {
            if ((input[i].equals("/by")) && (i != 1) && (i != (length-1))) {
                description = input[1];
                by = input[i+1];
                for (int j = 2 ; j < i ; j++) {
                    description += (" " + input[j]);
                }
                for (int k = i+2 ; k < length ; k++) {
                    by += (" " + input[k]);
                }
                commands.add(new Deadline(description,by));
                if (loadFlag == 1) {
                    System.out.println("Added to Galactic database:" );
                    System.out.println(commands.get(positionCheck));
                    Storage.saveNewTask(input);
                }
                positionCheck += 1;
                return;
            }
        }
        throw new DukeException(Ui.DEADLINE_ERROR);
    }

    public void addEvent(String[] input, int length) throws DukeException, IOException, DateTimeParseException {
        String description;
        String at;
        for (int i = 1 ; i < length ; i++) {
            if ((input[i].equals("/at")) && (i != 1) && (i != (length-1))) {
                description = input[1];
                at = input[i+1];
                for (int j = 2 ; j < i ; j++) {
                    description += (" " + input[j]);
                }
                for (int k = i+2 ; k < length ; k++) {
                    at += (" " + input[k]);
                }
                commands.add( new Event(description,at) );
                if (loadFlag == 1) {
                    System.out.println("Added to Galactic database:");
                    System.out.println(commands.get(positionCheck));
                    Storage.saveNewTask(input);
                }
                positionCheck += 1;
                return;
            }
        }
        throw new DukeException(Ui.EVENT_ERROR);
    }

    public void addTodo(String[] input, int length) throws DukeException, IOException {
        if (length == 1) {
            throw new DukeException(Ui.TODO_ERROR);
        } else {
            String description = input[1];
            for (int i = 2 ; i < length ; i++) {
                description += (" " + input[i]);
            }
            commands.add( new Todo(description) );
            if (loadFlag == 1) {
                System.out.println("Added to Galactic database:");
                System.out.println(commands.get(positionCheck));
                Storage.saveNewTask(input);
            }
            positionCheck += 1;
        }
    }

    public void printList() {
        System.out.println("Accessing archives...");
        int i = 1;
        for (Task num : commands) {
            System.out.println(i + ". " + num);
            i += 1;
        }
    }

    public void printListForFindingDate(String keyword) {

        System.out.println("Accessing archives...");
        System.out.println("Generating all the tasks that contain \"" + keyword + "\"...");
        int i = 1;
        LocalDate date = LocalDate.parse(keyword);
        for (Task num : commands) {
            if (num instanceof Event) {
                if (((Event) num).at.contains(keyword)) {
                    System.out.println(i + ". " + num);
                    i += 1;
                }
            } else if (num instanceof Deadline) {
                if (((Deadline) num).by.contains(keyword)) {
                    System.out.println(i + ". " + num);
                    i += 1;
                }
            }
        }
        if (i == 1) {
            System.out.println("There are no tasks that occur on \"" + keyword + "\" master. My apologies!");
        }
    }

    public void printListForFindingTask(String keyword) {
        System.out.println("Accessing archives...");
        System.out.println("Generating all the tasks that contain \"" + keyword + "\"...");
        int i = 1;
        for (Task num : commands) {
            if ((num.description).contains(keyword)) {
                System.out.println(i + ". " + num);
                i += 1;
            }
        }
        if (i == 1) {
            System.out.println("Unfortunately, there are no tasks that contain \"" + keyword + "\" master. My apologies!");
        }
    }

    public void markDone(int doneTaskNumber) throws IOException {
        (commands.get(doneTaskNumber)).markAsDone();
        if (loadFlag == 1) {
            System.out.println("The following task has been marked as done Master!");
            System.out.println((doneTaskNumber + 1) + ". " + commands.get(doneTaskNumber));
            Storage.saveAllTasks(commands);
        }
    }

    public void deleteTask(int doneTaskNumber) throws IOException {
        System.out.println("Taking one last look Master, at this Task. Removing the following from my memory");
        System.out.println((doneTaskNumber+1) + ". " + commands.get(doneTaskNumber));
        commands.remove(commands.get(doneTaskNumber));
        positionCheck -= 1;
        System.out.println("Goodbye Task, may the force be with you. You have " + positionCheck + " task(s) left Master");
        Storage.saveAllTasks(commands);
    }

}
