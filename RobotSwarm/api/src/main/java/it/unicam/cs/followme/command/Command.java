package it.unicam.cs.followme.command;

import it.unicam.cs.followme.models.Entity;

/**
 * Interface defining a command to be executed by an entity.
 */
public interface Command {

    /**
     * Executes the command on the specified entity.
     *
     * @param robot The entity on which the command is to be executed.
     * @return true if the command execution is successful, false otherwise.
     */
    boolean execute(Entity robot);

    /**
     * Returns a string representation of the command.
     *
     * @return A string representation of the command.
     */
    String toString();
}

