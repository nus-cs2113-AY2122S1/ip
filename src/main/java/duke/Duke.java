package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import duke.task.*;
import duke.command.TaskParser;

public class Duke {

    static ArrayList<Task> taskList = new ArrayList<>();


    private static boolean handleOneInputLine(String line) {
        String[] splitted = line.split("\\s+");
        switch (splitted[0]) {
            case "bye":
		return true;
            case "list":
                for (int i = 1; i <= taskList.size(); i += 1) {
                    System.out.println(i + ": " + taskList.get(i - 1));
                }
		break;
            case "done": {
                Task target = taskList.get(Integer.parseInt(splitted[1]) - 1);
                target.setDoneStatus(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(target);
		break;
	    }
	    case "delete": {
                Task target = taskList.remove(Integer.parseInt(splitted[1]) - 1);
		System.out.println("Noted. I've removed this task:");
		System.out.println(target);
		break;
	    }
            default:
		Task newTask = null;
		try {
		    newTask = TaskParser.parseTask(splitted);
		} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
		    throw new ArrayIndexOutOfBoundsException("OOPS! Some required arguments are missing.");
		}
                taskList.add(newTask);
                System.out.println("added: " + newTask);
        }
	return false;
    }

    private static void initialise() {
        String greeting = "Hello! I'm Duke";
        String assist = "What can I do for you?";
        System.out.println(greeting);
        System.out.println(assist);
    }

    private static void finalise() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    public static void main(String[] args) {
        initialise();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
	    try {
                if (handleOneInputLine(sc.nextLine())) {
                    finalise();
		    return;
                }
            } catch (Exception e) {
		System.out.println(e.getMessage());
	    }
	}
        finalise();
    }
}
