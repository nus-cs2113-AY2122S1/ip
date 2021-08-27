public class List {
    private int numberOfEntries = 0;
    private Task[] taskList;

    public List() {
        taskList = new Task[100];
    }

    public void printList() {
        for (int i = 0; i < numberOfEntries; i++) {
            System.out.println((i + 1) + "." + taskList[i].getStatusSymbol() + " " + taskList[i].getName());
        }
    }

    public void addEntry(String entryDescription) {
        taskList[numberOfEntries] = new Task(entryDescription);
        numberOfEntries++;
        System.out.println("Added " + entryDescription + " as entry " + numberOfEntries);
    }

    public int parseInputForEntryNumber(String input) {
        int entryNumber = Integer.parseInt(input.substring(5));
        return entryNumber;
    }

    public void doneEntry(int entryNumber) {
        taskList[entryNumber-1].setDone();
    }
}
