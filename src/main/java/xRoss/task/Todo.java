package xRoss.task;

import xRoss.exception.EmptyStringException;

public class Todo extends Task {

    public Todo(String name) throws EmptyStringException {
        super(name);
    }

    public void printTask() {
        System.out.print("[T][");

        if (super.isDone()) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }

        System.out.println("] " + super.getName());
    }

    @Override
    public String toString() {
        return "T" + super.toString();
    }
}
