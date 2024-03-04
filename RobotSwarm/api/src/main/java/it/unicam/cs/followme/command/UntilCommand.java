package it.unicam.cs.followme.command;

import it.unicam.cs.followme.models.Entity;

import java.util.ArrayList;

/**
 * Represents a command that iteratively executes a sequence of commands until a specific condition is met.
 */
public class UntilCommand implements IterativeCommand {

    private ArrayList<Command> commands;
    private int programCounter;
    private final String label;

    /**
     * Constructs an UntilCommand with the specified label for the condition.
     *
     * @param label The label representing the condition to be checked for termination.
     */
    public UntilCommand(String label) {
        this.commands = new ArrayList<>();
        this.programCounter = 0;
        this.label = label;
    }

    /**
     * Executes the until command by repeatedly executing the sequence of commands until the specified condition is met.
     *
     * @param robot The entity on which the command is to be executed.
     * @return true if the specified condition is met, false otherwise.
     */
    @Override
    public boolean execute(Entity robot) {
        boolean result;
        if (!this.hasDone(robot)) {
            if (this.commands.size() == this.programCounter)
                this.programCounter = 0;
            result = this.commands.get(this.programCounter).execute(robot);
            System.out.println(this.commands.get(this.programCounter).toString());
            if (result) {
                this.programCounter++;
            }
            return false;
        }
        return true;
    }

    /**
     * Checks if the until command has met the specified condition.
     *
     * @param robot The entity on which the command is being executed.
     * @return true if the specified condition is met, false otherwise.
     */
    public boolean hasDone(Entity robot) {
        return robot.isInsideAShape(this.label);
    }

    /**
     * Adds a command to the list of commands to be executed in the until loop.
     *
     * @param command The command to be added to the sequence.
     */
    public void addCommand(Command command) {
        this.commands.add(command);
    }

    /**
     * Returns a string representation of the until command.
     *
     * @return "UNTIL COMMAND".
     */
    @Override
    public String toString() {
        return "UNTIL COMMAND";
    }
}

