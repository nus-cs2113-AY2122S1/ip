package shima.command;

import shima.design.Profile;


public class ViewPersonalityCommand extends Command {
    protected Profile profile;

    public ViewPersonalityCommand() {
        profile = new Profile();
    }

    /**
     * Runs the command to print the profile of Shima bot
     */
    @Override
    public void runCommand() {
        profile.printPersonality();
    }
}
