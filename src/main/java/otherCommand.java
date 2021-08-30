class OtherCommand extends UserCommand {
    private String command;

    public OtherCommand (String command) {
        this.command = command;
    }

    @Override
    public void execute () {
        System.out.println("     " + this.command);
    }
}
