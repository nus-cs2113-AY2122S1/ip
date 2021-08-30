class otherCommand extends UserCommand {
    private String command;

    public otherCommand (String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute () {
        System.out.println(this.command);
    }
}
