package it.unicam.cs.followme.command;

import it.unicam.cs.followme.models.Entity;

/**
 * Represents a command that continue performing a move for a set duration.
 */
public class ContinueCommand implements Command {

    private int seconds;
    private int interactions;

    /**
     * Constructs a ContinueCommand with the specified duration in seconds.
     *
     * @param seconds The duration for which the command should be executed.
     * @throws IllegalArgumentException if the specified duration is negative.
     */
    public ContinueCommand(int seconds) {
        if (seconds < 0)
            throw new IllegalArgumentException("Seconds cannot be negative");
        this.seconds = seconds;
        this.interactions = 0;
    }

    /**
     * Executes the continue command on the given entity.
     *
     * @param robot The entity on which the command is to be executed.
     * @return true or false based on the command execution.
     */
    @Override
    public boolean execute(Entity robot) {
        if (!hasDone()) {
            robot.move();
            this.interactions++;
            return false;
        }
        return true;
    }

    /**
     * Checks if the command has been executed for the specified duration.
     *
     * @return true if the duration has been reached, false otherwise.
     */
    private boolean hasDone() {
        if (this.interactions == this.seconds) {
            this.interactions = 0;
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the continue command.
     *
     * @return "CONTINUE COMMAND".
     */
    @Override
    public String toString() {
        return "CONTINUE COMMAND";
    }
}
