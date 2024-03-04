package it.unicam.cs.followme.command;

import it.unicam.cs.followme.models.Entity;

/**
 * Represents a command that marks an iterative command as completed without executing any action.
 */
public class DoneCommand implements Command {

    private Command reference;

    /**
     * Constructs a DoneCommand with a reference to the command it marks as completed.
     *
     * @param command The command to be marked as completed.
     */
    public DoneCommand(Command command) {
        this.reference = command;
    }

    /**
     * Executes the done command, marking the referenced command as completed without performing any action.
     *
     * @param robot The entity on which the command is to be executed.
     * @return true, indicating the command execution is complete.
     */
    @Override
    public boolean execute(Entity robot) {
        return true;
    }

    /**
     * Returns a string representation of the done command.
     *
     * @return "DONE COMMAND".
     */
    @Override
    public String toString() {
        return "DONE COMMAND";
    }
}

