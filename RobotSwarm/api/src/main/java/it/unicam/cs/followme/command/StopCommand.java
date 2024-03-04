package it.unicam.cs.followme.command;

import it.unicam.cs.followme.models.Entity;

/**
 * Represents a command that instructs an entity to stop its current movement.
 */
public class StopCommand implements Command {

    /**
     * Executes the stop command, instructing the entity to stop its current movement.
     *
     * @param robot The entity on which the command is to be executed.
     * @return true, indicating the command execution is complete.
     */
    @Override
    public boolean execute(Entity robot) {
        robot.stop();
        return true;
    }

    /**
     * Returns a string representation of the stop command.
     *
     * @return "STOP COMMAND".
     */
    @Override
    public String toString() {
        return "STOP COMMAND";
    }
}

