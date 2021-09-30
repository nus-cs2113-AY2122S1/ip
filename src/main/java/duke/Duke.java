package duke;

import duke.task.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String dotsBreaker = "......................................................................";
        System.out.println("Hello from\n" + logo);
        System.out.println(dotsBreaker);
        System.out.println("Hi! I'm Duke.\n" + "How can I help make your life easier?");
        System.out.println(dotsBreaker);
        Scanner in = new Scanner(System.in);
        String lineIn = "";
        ArrayList<Task> listIn = new ArrayList<>();
        int totalNumber = 0;

        while (!lineIn.equals("bye")) {
            try {
                lineIn = in.nextLine();
                String[] lineInput = lineIn.split(" ");
                if (lineInput[0].equals("bye")) {
                    break;
                }
                if (lineInput[0].equals("list")) {
                    showTask(listIn, totalNumber);
                } else if (lineInput[0].equals("done")) {
                    try {
                        doneTask(listIn, lineInput);
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("OOPS!!! The index of the task that you entered does not exist:(\n" + dotsBreaker);
                    }
                } else if (lineInput[0].equals("event") || lineInput[0].equals("deadline") || lineInput[0].equals("todo")) {
                    try {
                        recordTask(listIn, lineIn, totalNumber, lineInput[0]);
                        totalNumber++;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The name of the task that you entered is empty:(\n" + dotsBreaker);
                    }
                } else if (lineInput[0].equals("delete")) {
                    try {
                        deleteTask(listIn, lineInput, totalNumber);
                        totalNumber--;
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("OOPS!!! The index of the task that you entered does not exist:(" + dotsBreaker);
                    }
                } else {
                    throw new DukeException("OOPS!!! Sorry, but I do not understand:(\n" + dotsBreaker);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Byebye! Have a wonderful day!");
        System.out.println(dotsBreaker);
    }

    private static void showTask(ArrayList listIn, int totalNumber) {
        String dotsBreaker = "......................................................................";
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < totalNumber; i++) {
            System.out.println((i + 1) + "." + listIn.get(i).toString());
        }
        System.out.println(dotsBreaker);
    }

    private static void doneTask(ArrayList listIn, String[] lineInput) {
        String dotsBreaker = "......................................................................";
        int inputIndex = Integer.parseInt(lineInput[1]) - 1;
        ((Task)listIn.get(inputIndex)).markAsDone();
        System.out.println("Wonderful! This task is now marked as done:");
        System.out.println(listIn.get(inputIndex).toString());
        System.out.println(dotsBreaker);
    }

    private static void recordTask(ArrayList listIn, String lineInput, int totalNumber, String firstInput) throws DukeException {
        String dotsBreaker = "......................................................................";
        if (firstInput.equals("event")) {
            int breakPoint = lineInput.indexOf("/");
            if (lineInput.length() < 9) {
                throw new DukeException("The description of the event is too short! Please enter again.\n" + dotsBreaker);
            }
            System.out.println("Got it. I've added this task:");
            String eventName = lineInput.substring(6, breakPoint);
            String eventTime = lineInput.substring(breakPoint + 3);
            listIn.add(totalNumber, new Event(eventName, eventTime));
        } else if (firstInput.equals("deadline")) {
            int breakPoint = lineInput.indexOf("/");
            if (lineInput.length() < 12) {
                throw new DukeException("The description of the deadline is too short! Please enter again.\n" + dotsBreaker);
            }
            System.out.println("Got it. I've added this task:");
            String deadlineName = lineInput.substring(9, breakPoint);
            String deadlineTime = lineInput.substring(breakPoint + 3);
            listIn.add(totalNumber, new Deadline(deadlineName, deadlineTime));
        } else {
            String todoName = lineInput.substring(5);
            System.out.println("Got it. I've added this task:");
            listIn.add(totalNumber, new ToDo(todoName));
        }
        System.out.println(listIn.get(totalNumber).toString());
        System.out.println("Now you have " + (totalNumber + 1) + " tasks in your list");
        System.out.println(dotsBreaker);
    }

    private static void deleteTask(ArrayList listIn, String[] lineInput, int totalNumber) {
        String dotsBreaker = "......................................................................";
        int inputIndex = Integer.parseInt(lineInput[1]) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(listIn.get(inputIndex).toString());
        System.out.println("Now you have " + (totalNumber - 1) + " tasks in the list.");
        System.out.println(dotsBreaker);
        listIn.remove(inputIndex);
    }
}