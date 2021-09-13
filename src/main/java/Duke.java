import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Duke {

    private static final String FILEPATH = "C:\\Users\\User\\Documents\\NUS\\CS2113T\\IP\\ip\\data\\duke.txt";
    private static final int TODO_NAME_CONSTANT = 5;
    private static final int DEADLINE_NAME_CONSTANT = 9;
    private static final int DEADLINE_BY_CONSTANT = 5;
    private static final int EVENT_NAME_CONSTANT = 6;
    private static final int EVENT_AT_CONSTANT = 5;
    public static final String LINE = "____________________________________________________________";

    private static ArrayList<Task> list = new ArrayList<Task>();

    public static void initList() {
        try {
            File file = new File(FILEPATH);
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String input = s.nextLine();
                String[] inputWords = input.split(" / ");
                switch(inputWords[0]) {
                case "T":
                    list.add(new Todo(inputWords[2]));
                    if (inputWords[1].equals("1")) {
                        list.get(list.size()-1).markAsDone();
                    }
                    break;
                case "D":
                    list.add(new Deadline(inputWords[2], inputWords[3]));
                    if (inputWords[1].equals("1")) {
                        list.get(list.size()-1).markAsDone();
                    }
                    break;
                case "E":
                    list.add(new Event(inputWords[2], inputWords[3]));
                    if (inputWords[1].equals("1")) {
                        list.get(list.size()-1).markAsDone();
                    }
                    break;
                default:
                    throw new DukeException();
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println(LINE + "\nFile not found\n" + LINE);
        } catch (DukeException | ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE + "\nInput format within file is wrong!\n" + LINE);
        }
    }

    private static void appendToFile(String textToAppend) throws IOException {
        File file = new File(FILEPATH);
        if (file.length() != 0) {
            textToAppend = System.lineSeparator() + textToAppend;
        }
        FileWriter fw = new FileWriter(FILEPATH, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static void updateFile() {
        try {
            clearFile();
            for (Task task : list) {
                String textToAppend = "";
                if (task instanceof Todo) {
                    String isDone = task.isDone() ? "1" : "0";
                    textToAppend = "T / " + isDone + " / " + task.getName();
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    String isDone = deadline.isDone() ? "1" : "0";
                    textToAppend = "D / " + isDone + " / " + deadline.getName() + " / " + deadline.getDeadline();
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    String isDone = event.isDone() ? "1" : "0";
                    textToAppend = "E / " + isDone + " / " + event.getName() + " / " + event.getAt();
                }
                appendToFile(textToAppend);
            }
        } catch(IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void clearFile() throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        fw.write("");
        fw.close();
    }

    public static void printList() {
        System.out.println(LINE);
        if (list.size() == 0) {
            System.out.println("No items added!");
        }
        for (int i = 0; i < list.size(); i += 1) {
            System.out.println((i+1) + ". " + list.get(i));
        }   
        System.out.println(LINE);
    }

    public static void markTaskAsDone(String[] inputWords) {
        try {
            int taskIndex = Integer.parseInt(inputWords[1]) - 1;
            if (taskIndex < list.size() && taskIndex >= 0) {
                if (list.get(taskIndex).isDone()) {
                    System.out.println(LINE + "\nThat task is already done!\n" + LINE);
                } else {
                    list.get(taskIndex).markAsDone();
                    updateFile();
                    System.out.println(LINE + "\nNice! I've marked this task as done:");
                    System.out.println(list.get(taskIndex) + "\n" + LINE);
                }
            } else {
                System.out.println("That task does not exist!\n" + LINE);
            }
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE + "\nPlease enter a number after 'done'!\n" + LINE);
        }
    }

    public static void deleteTask(String[] inputWords) {
        try {
            int taskIndex = Integer.parseInt(inputWords[1]) - 1;
            if (taskIndex < list.size() && taskIndex >= 0) {
                System.out.format(LINE + "\nNoted. I've removed this task: \n   " + list.get(taskIndex) 
                + "\nNow you have %d tasks in the list.\n" + LINE + "\n", list.size()-1);
                list.remove(taskIndex);
                updateFile();
            } else {
                System.out.println("That task does not exist!\n" + LINE);
            }
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE + "\nPlease enter a number after 'done'!\n" + LINE);
        }
    }

    public static void printAddedTask() {
        System.out.println(LINE + "\nGot it. I've added this task:");
        System.out.println(list.size() + ". " + list.get(list.size() - 1));
        System.out.format("Now you have %d tasks in the list.\n" + LINE + "\n", list.size());
    }

    public static void addTodo(String input) {
        try {
            String taskName = input.substring(TODO_NAME_CONSTANT);
            list.add(new Todo(taskName));
            appendToFile("T / 0 / " + taskName);
            printAddedTask();
        } catch(StringIndexOutOfBoundsException e) {
            System.out.println(LINE + "\n☹ OOPS!!! The description of a todo cannot be empty.\n" + LINE);
        } catch(IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void addDeadline(String input) {
        try {
            if (!input.contains("/by")) {
                System.out.println(LINE + "\nIncorrect format for entering deadline!\n" + LINE);
                return;
            }
            int taskEndIndex = input.indexOf("/by") - 1;
            String taskName = input.substring(DEADLINE_NAME_CONSTANT, taskEndIndex);
            String deadline = input.substring(taskEndIndex + DEADLINE_BY_CONSTANT);
            list.add(new Deadline(taskName, deadline));
            appendToFile("D / 0 / " + taskName + " / " + deadline);
            printAddedTask();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LINE + "\n☹ OOPS!!! The description of a deadline cannot be empty.\n" + LINE);
        } catch(IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void addEvent(String input) {
        try {
            if (!input.contains("/at")) {
                System.out.println(LINE + "\nIncorrect format for entering event!\n" + LINE);
                return;
            }
            int taskEndIndex = input.indexOf("/at") - 1;
            String taskName = input.substring(EVENT_NAME_CONSTANT, taskEndIndex);
            String at = input.substring(taskEndIndex + EVENT_AT_CONSTANT);
            list.add(new Event(taskName, at));
            appendToFile("E / 0 / " + taskName + " / " + at);
            printAddedTask();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LINE + "\n☹ OOPS!!! The description of an event cannot be empty.\n" + LINE);
        } catch(IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void addTask(String input, String[] inputWords) {
        String taskType = inputWords[0].toLowerCase();
        try {
            switch(taskType) {
            case "todo":
                addTodo(input);
                break;
            case "deadline":
                addDeadline(input);
                break;
            case "event":
                addEvent(input);
                break;
            default:
                throw new DukeException();
            }
        } catch(DukeException e) {
            System.out.println(LINE + "\n☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE);
        }
    }

    public static void main(String[] args) { 
        System.out.println(LINE + "\n Hello! I'm Duke\n What can I do for you?\n" + LINE);
        boolean isCompleted = false;
        initList();
        while (!isCompleted) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] inputWords = input.split(" ");
            if (input.equalsIgnoreCase("bye")) {
                isCompleted = true;
                in.close();
                continue;
            }
            if (input.equalsIgnoreCase("list")){
                printList();
                continue;
            }
            if (inputWords[0].equalsIgnoreCase("done")) {
                markTaskAsDone(inputWords);
                continue;
            }
            if (inputWords[0].equalsIgnoreCase("delete")) {
                deleteTask(inputWords);
                continue;
            }
            addTask(input, inputWords);
        }
        System.out.println(LINE + "\n Bye. Hope to see you again soon!\n" + LINE);
    }
}
