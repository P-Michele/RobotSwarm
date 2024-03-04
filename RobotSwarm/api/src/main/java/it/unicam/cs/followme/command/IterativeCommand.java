package it.unicam.cs.followme.command;

/**
 * Interface extending the Command interface, representing an iterative command that can contain a sequence of commands.
 */
public interface IterativeCommand extends Command {

    /**
     * Adds a command to the sequence of commands to be executed iteratively.
     *
     * @param command The command to be added to the sequence.
     */
    void addCommand(Command command);
}

