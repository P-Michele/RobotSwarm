package it.unicam.cs.followme.command;

import it.unicam.cs.followme.models.Entity;

/**
 * Represents a command that unsignals an entity with a specified label.
 */
public class UnsignalCommand implements Command {

    private final String label;

    /**
     * Constructs an UnsignalCommand with the specified label.
     *
     * @param label The label associated with the entity to be unsignaled.
     */
    public UnsignalCommand(String label) {
        this.label = label;
    }

    /**
     * Executes the unsignal command, removing the specified label from the entity's signaling state.
     *
     * @param robot The entity on which the command is to be executed.
     * @return true, indicating the command execution is complete.
     */
    @Override
    public boolean execute(Entity robot) {
        if (this.label.equals(robot.signal()))
            robot.unsignal();
        return true;
    }

    /**
     * Returns a string representation of the unsignal command.
     *
     * @return "UNSIGNAL COMMAND".
     */
    @Override
    public String toString() {
        return "UNSIGNAL COMMAND";
    }
}

