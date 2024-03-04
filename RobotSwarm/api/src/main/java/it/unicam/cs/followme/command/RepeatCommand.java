package it.unicam.cs.followme.command;

import it.unicam.cs.followme.models.Entity;

import java.util.ArrayList;

/**
 * Represents a command that executes a sequence of commands a specified number of times.
 */
public class RepeatCommand implements IterativeCommand {

    private final int interaction;
    private int counter;
    private ArrayList<Command> commands;
    private int programCounter;

    /**
     * Constructs a RepeatCommand with the specified number of interactions.
     *
     * @param n The number of times the sequence of commands should be repeated.
     */
    public RepeatCommand(int n) {
        this.commands = new ArrayList<>();
        this.programCounter = 0;
        this.counter = 0;
        this.interaction = n;
    }

    /**
     * Executes the repeat command by repeatedly executing the sequence of commands for the specified number of interactions.
     *
     * @param robot The entity on which the command is to be executed.
     * @return true if the specified number of interactions is reached, false otherwise.
     */
    @Override
    public boolean execute(Entity robot) {
        boolean result;
        if (!this.hasDone()) {
            if (this.commands.size() == this.programCounter)
                this.programCounter = 0;

            result = this.commands.get(this.programCounter).execute(robot);
            System.out.println(this.commands.get(this.programCounter).toString());
            if (result) {
                this.programCounter++;
            }
            this.counter++;
            return false;
        }
        return true;
    }

    /**
     * Checks if the repeat command has completed the specified number of interactions.
     *
     * @return true if the specified number of interactions is reached, false otherwise.
     */
    private boolean hasDone() {
        return counter == interaction;
    }

    /**
     * Adds a command to the list of commands to be executed in the repeat loop.
     *
     * @param command The command to be added to the sequence.
     */
    public void addCommand(Command command) {
        this.commands.add(command);
    }

    /**
     * Returns a string representation of the repeat command.
     *
     * @return "REPEAT COMMAND".
     */
    @Override
    public String toString() {
        return "REPEAT COMMAND";
    }
}

