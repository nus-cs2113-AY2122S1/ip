public class TimedTask extends Task {
    private String dateTime;
    private final String preposition;

    public TimedTask(String userInput, String preposition, Types type) {
        //TODO: Is using constructor class to parse inputs good OOP?
        super(type);
        String[] inputs = userInput.split(String.format(" /%s ", preposition));
        this.setDescription(inputs[0]);
        this.dateTime = inputs[1];
        this.preposition = preposition;
    }

    @Override
    public String toString() {
        //Append preposition and datetime
        return String.format("%s (%s: %s)", super.toString(), preposition, dateTime);
    }

}
