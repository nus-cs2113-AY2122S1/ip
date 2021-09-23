package shima.command;

import shima.design.Profile;


public class ViewPersonalityCommand extends Command {
    protected Profile profile;

    public ViewPersonalityCommand() {
        profile = new Profile();
    }

    @Override
    public void runCommand() {
        profile.printPersonality();
    }
}
