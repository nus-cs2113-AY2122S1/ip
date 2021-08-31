public class Todo extends Task{

    public Todo (String description) {
        super(description);
        System.out.println("  " + this.toString());
    }

    @Override
    public String toString() {
        String str = "[T]" + super.toString();
        return str;
    }
}
