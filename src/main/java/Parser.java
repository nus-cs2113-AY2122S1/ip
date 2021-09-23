import exceptions.*;
import todo.Deadline;
import todo.Event;
import todo.Task;
import todo.ToDo;

import java.io.IOException;
import java.util.Scanner;

public class Parser extends Duke {

    public Parser(String filePath) {
        super(filePath);
    }

    protected static void readInput(String line) {
        Scanner in = new Scanner(System.in);
        while (!line.contains("bye") || in.hasNextLine()) {
            line = in.nextLine();
            assert line != null;
            String[] words = line.split(" ", 2);
            try{
                checkValidCommand(words);
            } catch (InvalidCommandError e) {
                printMessage("Invalid command. Try again!");
            } catch (MissingFieldError e) {
                printMessage("You are missing a field!");
            }

        }
    }

    protected static void checkValidCommand(String[] words) throws InvalidCommandError, MissingFieldError {
        String command = words[0];
        if(!commands.contains(command)){
            throw new InvalidCommandError();
        }
        executeCommand(words, command);
    }

    protected static void executeCommand(String[] words, String command) throws MissingFieldError {
        switch (command) {
            case "done": {
                int currentIndex = checkTaskIndex(words);
                setTaskDone(currentIndex);
                break;
            }
            case "list":
                TaskList.listAllTask();
                break;
            case "bye":
                Ui.showByeScreen();
                break;
            case "save":
                try {
                    Storage.saveTasks(filePath);
                } catch (IOException e) {
                    Duke.printMessage("Something went wrong!");
                }
                break;
            case "delete": {
                int currentIndex = checkTaskIndex(words);
                TaskList.removeTask(currentIndex);
                break;
            }
            case "find":
                String wordToFind = extractWord(words);
                findTask(wordToFind);
                break;
            default:  //is a valid task
                checkValidInput(words);
                break;
        }
    }

    private static void findTask(String wordToFind) {
        boolean taskExists = false;
        String currentTask;
        for(int i = 0; i < Duke.tasks.size(); i++) {
            currentTask = Duke.tasks.get(i).toString();
            if(currentTask.contains(wordToFind)) {
                if(!taskExists) {
                    System.out.println("Here are the matching tasks in your list");
                    taskExists = true;
                }
                System.out.println((i + 1) + ". " + currentTask);
                if(i + 1 == Duke.tasks.size()) {
                    printDivider();
                }
            }
        }

        if(!taskExists) {
            printMessage("There are no tasks containing such words!");
        }
    }

    private static String extractWord(String[] words) throws MissingFieldError {
        if (words.length <= 1) {
            throw new MissingFieldError();
        } else {
            return words[1];
        }
    }

    protected static void checkValidInput(String[] words) {
        try{
            splitInput(words);
        } catch (MissingDescriptionError e) {
            printMessage("Missing description!!!");
        } catch (MissingDateError e) {
            printMessage("Missing date!!");
        } catch (InvalidDescriptionError e) {
            printMessage("Description cannot be whitespace!!");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            printMessage("Something went wrong! Please try again!");
        } catch (EmptyDateError e) {
            printMessage("Date cannot be empty!");
        }
    }

    protected static void splitInput(String[] words)
            throws MissingDescriptionError, MissingDateError, InvalidDescriptionError, EmptyDateError {
        String task;
        String date = null;
        checkDescription(words);
        String[] taskDescription = splitString(words[1], "/");
        String type = words[0];
        if(taskDescription.length <= 1) {
            if(type.equals("todo")){ //todo command
                task = words[1];
            } else { //any other commands which requires dates
                throw new MissingDateError();
            }
        }
        else { //have action and date
            String[] inputDate = splitString(taskDescription[1], " ");
            if(TaskList.checkEmptyDate(inputDate)) {
                throw new EmptyDateError();
            }
            task = taskDescription[0].trim();
            date = inputDate[1];
        }
        executeCommand(task, date, type);
    }

    protected static void executeCommand(String task, String date,
                                         String type) {
        switch (type) {
            case "deadline":
                addDeadline(task, date);
                break;
            case "event":
                addEvent(task, date);
                break;
            case "todo":
                addToDo(task);
                break;
            default:  //invalid command
                Duke.printMessage("Invalid command, try again.");
                break;
        }
    }

    protected static void addToDo(String task) {
        Duke.tasks.add(new ToDo(task));
        TaskList.addSuccess();
    }

    protected static void addEvent(String task, String date) {
        Duke.tasks.add(new Event(task, date));
        TaskList.addSuccess();
    }

    protected static void addDeadline(String task, String date) {
        Duke.tasks.add(new Deadline(task, date));
        TaskList.addSuccess();
    }
    
    protected static void setTaskDone(int currentIndex) {
        tasks.get(currentIndex - 1).setDone(true);
        completeSuccess(currentIndex);
    }

    protected static int checkTaskIndex(String[] words) throws MissingFieldError {
        if(words.length < 2) {
            throw new MissingFieldError();
        } else {
            return Integer.parseInt(words[1]);
        }
    }
    
    protected static void checkDescription(String[] words) throws MissingDescriptionError, InvalidDescriptionError {
        if(words.length <= 1) {
            throw new MissingDescriptionError();
        } else if(checkInvalidDescription(words[1])) {
            throw new InvalidDescriptionError(); //check that description is not just whitespaces
        }
    }

    protected static String[] splitString(String word, String s) {
        return word.split(s, 2);
    }

    protected static String extractTask(Task currentTask) {
        String taskWithDate;
        String[] taskDescription;
        taskWithDate = currentTask.toString().split(" ", 2)[1];
        taskDescription = taskWithDate.split("\\(", 2);
        return taskDescription[0].trim();
    }

    protected static Boolean checkInvalidDescription(String word) {
        return word.trim().length() == 0;
    }

    protected static void completeSuccess(int index) {
        printMessage("Got it. I've marked this task as complete:");
        System.out.println(tasks.get(index - 1).toString());
        TaskList.getTasksLeft();
    }
}
