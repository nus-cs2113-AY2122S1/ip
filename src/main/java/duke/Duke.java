package duke;

import duke.exception.*;
import duke.task.*;
import duke.util.Alarm;

import java.util.Scanner;

import static duke.ui.UI.*;
import static duke.ui.ErrorReport.*;
import static duke.util.CommandAction.*;

public class Duke {
    static final int COMMAND = 0;
    static final int DESCRIPTION = 0;
    static final int TIME = 1;

    //public static Task[] tasks = new Task[100];

    public static String[] getDetails(String taskInput, String keyword) {
        String[] details = taskInput.split(keyword,2);

        details[TIME] = details[TIME].replace(keyword, "");

        return details;
    }

    public static void identifyInput (String[] userInput) {
        String taskType = userInput[COMMAND];

        line();
        switch (taskType) {
        case "hello":
            hello();
            break;
        case"todo":
            try {
                String taskInput = userInput[1];
                Todo t = new Todo(taskInput);
                add(t);
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_TODO);
            }
            break;
        case "deadline":
            try {
                String taskInput = userInput[1];

                try {
                    String[] details1 = getDetails(taskInput, "/by");
                    Deadline d = new Deadline(details1[DESCRIPTION], details1[TIME]);
                    add(d);
                } catch (ArrayIndexOutOfBoundsException e) {
                    alarm(Alarm.NO_DDL_KEYWORD);
                }

            }  catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_DEADLINE);
            }
            break;
        case "event":
            try {
                String taskInput = userInput[1];

                try {
                    String[] details2 = getDetails(taskInput, "/at");
                    Event e = new Event(details2[DESCRIPTION], details2[TIME]);
                    add(e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    alarm(Alarm.NO_EVENT_KEYWORD);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_EVENT);
            }
            break;
        case "list":
            try {
                printList();
            } catch (EmptyListException e) {
                alarm(Alarm.EMPTY_LIST);
            }
            break;
        case "done":
            try {
                String taskInput = userInput[1];
                markDone(taskInput);
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_INDEX);
            } catch (InvalidIndexException e) {
                alarm(Alarm.OUT_OF_RANGE);
            } catch (NumberFormatException e) {
                alarm(Alarm.INVALID_ID);
            } catch (EmptyListException e) {
                alarm(Alarm.EMPTY_LIST);
            }
            break;
        case "delete":
            try {
                String taskInput = userInput[1];
                delete(taskInput);
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_INDEX);
            } catch (InvalidIndexException e) {
                alarm(Alarm.OUT_OF_RANGE);
            } catch (NumberFormatException e) {
                alarm(Alarm.INVALID_ID);
            } catch (EmptyListException e) {
                alarm(Alarm.EMPTY_LIST);
            }
            break;
        default:
            alarm(Alarm.INVALID_COMMAND);
            break;
        }
        line();
    }

    public static void main(String[] args) {
        greet();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.startsWith("bye")) {
            String cleanInput = input.trim();
            String[] userInput = cleanInput.split(" ",2);

            identifyInput(userInput);
            input = in.nextLine();
        }
        exit();
    }
}
