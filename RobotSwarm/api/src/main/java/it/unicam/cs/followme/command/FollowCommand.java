package it.unicam.cs.followme.command;

import it.unicam.cs.followme.models.Entity;
import it.unicam.cs.followme.util.Direction;
import it.unicam.cs.followme.util.DirectionCalculator;
import it.unicam.cs.followme.util.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a command that instructs an entity to follow a specific direction based on common signal.
 */
public class FollowCommand implements Command {

    private double dist;
    private String label;
    private double speed;

    /**
     * Constructs a FollowCommand with the specified label, distance, and speed.
     *
     * @param label The label associated with the command.
     * @param dist The distance within which neighbors and common signals are considered.
     * @param speed The speed at which the entity should follow the direction.
     */
    public FollowCommand(String label, double dist, double speed) {
        this.dist = dist;
        this.label = label;
        this.speed = speed;
    }

    /**
     * Executes the follow command, instructing the entity to follow a specific direction.
     *
     * @param robot The entity on which the command is to be executed.
     * @return true, indicating the command execution is complete.
     */
    @Override
    public boolean execute(Entity robot) {
        List<Entity> neighbours = robot.getNeighbors(this.dist);
        List<Entity> commonSignal = robot.getCommonSignal();

        robot.setSpeed(this.speed);
        robot.setDirection(this.randomMove(robot));

        if (!neighbours.isEmpty() && !commonSignal.isEmpty()) {
            List<Entity> intersection = new ArrayList<>(neighbours);
            intersection.retainAll(commonSignal);

            if (!intersection.isEmpty()) {
                robot.setDirection(this.follow(intersection, robot));
            }
        }
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
     * Calculates the direction for the entity to follow based on the average position of entities in the intersection.
     *
     * @param intersection The list of entities that are both neighbors and in the common signal.
     * @param robot The entity on which the command is being executed.
     * @return The calculated direction for the entity to follow.
     */
    private Direction follow(List<Entity> intersection, Entity robot) {
        Point mediumPoint = new Point(0, 0);
        for (Entity entity : intersection) {
            mediumPoint.setX(mediumPoint.getX() + entity.getPosition().getX());
            mediumPoint.setY(mediumPoint.getY() + entity.getPosition().getY());
        }
        mediumPoint.setX(mediumPoint.getX() / intersection.size());
        mediumPoint.setY(mediumPoint.getY() / intersection.size());
        return DirectionCalculator.calculateDirection(robot.getPosition(), mediumPoint);
    }

    /**
     * Generates a random move for the entity within the specified distance range.
     *
     * @param robot The entity on which the command is being executed.
     * @return The calculated direction for the entity to follow.
     */
    private Direction randomMove(Entity robot) {
        Point rand = new Point(this.randomGenerator(-this.dist, this.dist),
                this.randomGenerator(-this.dist, this.dist));
        return DirectionCalculator.calculateDirection(robot.getPosition(), rand);
    }

    /**
     * Returns a string representation of the follow command.
     *
     * @return "FOLLOW COMMAND".
     */
    @Override
    public String toString() {
        return "FOLLOW COMMAND";
    }
}

