package it.unicam.cs.followme.command;

import it.unicam.cs.followme.models.Entity;
import it.unicam.cs.followme.util.Direction;
import it.unicam.cs.followme.util.DirectionCalculator;
import it.unicam.cs.followme.util.Point;
import java.util.Random;

/**
 * Represents a command that instructs an entity to make a random move within a specified range of points at a given speed.
 */
public class RandomMoveCommand implements Command {

    private final Point point1;
    private final Point point2;
    private final double speed;

    /**
     * Constructs a RandomMoveCommand with two points defining the range and the specified speed.
     *
     * @param point1 The first point of the range.
     * @param point2 The second point of the range.
     * @param speed The speed at which the entity should make a random move within the range.
     */
    public RandomMoveCommand(Point point1, Point point2, double speed) {
        this.point1 = point1;
        this.point2 = point2;
        this.speed = speed;
    }

    /**
     * Executes the random move command, instructing the entity to make a random move within the specified range.
     *
     * @param robot The entity on which the command is to be executed.
     * @return true, indicating the command execution is complete.
     */
    @Override
    public boolean execute(Entity robot) {
        double x = this.randomGenerator(point1.getX(), point2.getX());
        double y = this.randomGenerator(point1.getY(), point2.getY());
        Point point = new Point(x, y);
        Direction direction = DirectionCalculator.calculateDirection(robot.getPosition(), point);
        robot.setSpeed(this.speed);
        robot.setDirection(direction);
        robot.move();
        return true;
    }

    /**
     * Generates a random double value within the specified range.
     *
     * @param first The lower bound of the range.
     * @param second The upper bound of the range.
     * @return A random double within the specified range.
     */
    public double randomGenerator(double first, double second) {
        Random rand = new Random();
        double min = Math.min(first, second);
        double max = Math.max(first, second);
        return min + (max - min) * rand.nextDouble();
    }

    /**
     * Returns a string representation of the random move command.
     *
     * @return "RANDOM MOVE COMMAND".
     */
    @Override
    public String toString() {
        return "RANDOM MOVE COMMAND";
    }
}

