package it.unicam.cs.followme.command;

import it.unicam.cs.followme.models.Entity;
import it.unicam.cs.followme.util.DirectionCalculator;
import it.unicam.cs.followme.util.Point;

/**
 * Represents a command that instructs an entity to move towards a specific point at a given speed.
 */
public class MoveCommand implements Command {

    private final Point point;
    private final double speed;

    /**
     * Constructs a MoveCommand with the specified destination point and speed.
     *
     * @param point The destination point towards which the entity should move.
     * @param speed The speed at which the entity should move.
     */
    public MoveCommand(Point point, double speed) {
        this.point = point;
        this.speed = speed;
    }

    /**
     * Executes the move command, instructing the entity to move towards the specified point at the given speed.
     *
     * @param robot The entity on which the command is to be executed.
     * @return true, indicating the command execution is complete.
     */
    @Override
    public boolean execute(Entity robot) {
        robot.setSpeed(this.speed);
        robot.setDirection(DirectionCalculator.calculateDirection(robot.getPosition(), this.point));
        return true;
    }

    /**
     * Returns a string representation of the move command.
     *
     * @return "MOVE COMMAND".
     */
    @Override
    public String toString() {
        return "MOVE COMMAND";
    }
}

