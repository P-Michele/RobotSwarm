package it.unicam.cs.followme.command;

import it.unicam.cs.followme.models.Entity;

import java.util.ArrayList;

/**
 * Represents a command that executes a sequence of commands forever.
 */
public class ForeverCommand implements IterativeCommand {

    private ArrayList<Command> commands;
    private int programCounter;

    /**
     * Constructs a ForeverCommand with an empty list of commands and initializes the program counter.
     */
    public ForeverCommand() {
        this.programCounter = 0;
        this.commands = new ArrayList<>();
    }

    /**
     * Executes the forever command by repeatedly executing the sequence of commands.
     *
     * @param robot The entity on which the command is to be executed.
     * @return false, as the command is designed to run indefinitely.
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
        }
        return false;
    }

    /**
     *
     * @return false, as the forever command runs indefinitely.
     */
    private boolean hasDone() {
        return false;
    }

    /**
     * Adds a command to the list of commands to be executed in the forever loop.
     *
     * @param command The command to be added to the sequence.
     */
    public void addCommand(Command command) {
        this.commands.add(command);
    }

    /**
     * Returns a string representation of the forever command.
     *
     * @return "FOREVER COMMAND".
     */
    @Override
    public String toString() {
        return "FOREVER COMMAND";
    }
}

