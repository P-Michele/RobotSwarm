package it.unicam.cs.followme.command;

import it.unicam.cs.followme.models.Entity;

/**
 * Represents a command that signals an entity with a specified label.
 */
public class SignalCommand implements Command {

    private final String label;

    /**
     * Constructs a SignalCommand with the specified label.
     *
     * @param label The label to be assigned to the entity when signaling.
     */
    public SignalCommand(String label) {
        this.label = label;
    }

    /**
     * Executes the signal command, assigning the specified label to the entity and signaling.
     *
     * @param robot The entity on which the command is to be executed.
     * @return true, indicating the command execution is complete.
     */
    @Override
    public boolean execute(Entity robot) {
        robot.setLabel(label);
        robot.signal();
        return true;
    }

    /**
     * Returns a string representation of the signal command.
     *
     * @return "SIGNAL COMMAND".
     */
    @Override
    public String toString() {
        return "SIGNAL COMMAND";
    }
}

