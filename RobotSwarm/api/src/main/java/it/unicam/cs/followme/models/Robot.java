package it.unicam.cs.followme.models;

import it.unicam.cs.followme.command.Command;
import it.unicam.cs.followme.util.Direction;
import it.unicam.cs.followme.util.Point;
import it.unicam.cs.followme.utilities.ShapeData;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;


/**
 * Implementation of the Entity interface representing a robot in the environment.
 */
public class Robot implements Entity {

    private Point position;

    private String label;

    private Direction direction;

    private double speed;

    private Environment environment;

    public Queue<Command> commands;

    /**
     * Constructs a robot with a specified initial position.
     *
     * @param position The initial position of the robot.
     */
    public Robot(Point position) {
        this.position = position;
        this.label = "";
    }

    /**
     * Constructs a robot with a random initial position within a specified range.
     */
    public Robot() {
        double x = Math.random() * 200 - 100;
        double y = Math.random() * 200 - 100;
        this.position = new Point(x, y);
        this.label = "";
    }

    /**
     * Gets the current position of the robot.
     *
     * @return The position of the robot.
     */
    @Override
    public Point getPosition() {
        return this.position;
    }

    /**
     * Gets a list of neighboring entities within the specified distance.
     *
     * @param distance The maximum distance for an entity to be considered a neighbor.
     * @return A list of neighboring entities.
     */
    @Override
    public List<Entity> getNeighbors(double distance) {
        return environment.getEntities().stream()
                .filter(entity -> distance <= entity.getPosition().distance(this.getPosition()))
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of entities with a common signal with the robot.
     *
     * @return A list of entities with a common signal.
     */
    @Override
    public List<Entity> getCommonSignal() {
        return environment.getEntities().stream()
                .filter(entity -> entity.signal().equals(this.label))
                .collect(Collectors.toList());
    }

    /**
     * Checks if the robot is inside a shape specified by its shape data.
     *
     * @param shape The shape data defining the shape.
     * @return true if the robot is inside the specified shape, false otherwise.
     */
    @Override
    public boolean isInsideAShape(ShapeData shape) {
        return switch (shape.shape()) {
            case "CIRCLE" -> isInsideACircle(shape);
            case "RECTANGLE" -> isInsideARectangle(shape);
            default -> false;
        };
    }

    /**
     * Checks if the robot is inside a shape specified by its label.
     *
     * @param label The label associated with the shape.
     * @return true if the robot is inside the shape with the specified label, false otherwise.
     */
    @Override
    public boolean isInsideAShape(String label) {
        return environment.getShapes(label).stream()
                .anyMatch(this::isInsideAShape);
    }

    private boolean isInsideACircle(ShapeData shape) {
        double[] args = shape.args();
        Point position = new Point(args[0], args[1]);
        return args[2] >= position.distance(this.getPosition());
    }

    private boolean isInsideARectangle(ShapeData shape) {
        double[] args = shape.args();
        boolean insideX = this.getPosition().getX() >= args[0] && this.getPosition().getX() <= (args[0] + args[2]);
        boolean insideY = this.getPosition().getY() >= args[1] && this.getPosition().getY() <= (args[1] + args[3]);
        return insideX && insideY;
    }

    /**
     * Gets the current direction of the robot.
     *
     * @return The direction of the robot.
     */
    @Override
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the robot.
     *
     * @param direction The direction in which the robot should move.
     */
    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Updates the perception of the robot with the current environment.
     *
     * @param environment The environment in which the robot exists.
     */
    @Override
    public void perceive(Environment environment) {
        this.environment = environment;
    }

    /**
     * Sets the label of the robot.
     *
     * @param label The label to be assigned to the robot.
     */
    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Sets the speed of the robot.
     *
     * @param speed The speed at which the robot should move.
     */
    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }


    /**
     * Gets the current speed of the robot.
     *
     * @return The speed of the robot.
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Gets the label of the robot.
     *
     * @return The label of the robot.
     */
    @Override
    public String signal() {
        return this.label;
    }

    /**
     * Unsignals the robot, removing its label.
     */
    @Override
    public void unsignal() {
        this.label = "";
    }

    /**
     * Moves the robot based on its current speed and direction.
     */
    @Override
    public void move() {
        if (this.direction != null) {
            double deltaX = this.speed * direction.getDeltaX();
            double deltaY = this.speed * direction.getDeltaY();

            double newX = position.getX() + deltaX;
            double newY = position.getY() + deltaY;

            position = new Point(newX, newY);
        } else {
            System.out.println("Direction not set. Cannot move.");
        }
    }

    /**
     * Stops the movement of the robot.
     */
    @Override
    public void stop() {
        this.speed = 0;
    }

    /**
     * Adds a program (sequence of commands) to be executed by the robot.
     *
     * @param program The program to be added to the robot's execution queue.
     */
    @Override
    public void addProgram(Queue<Command> program) {
        this.commands = new LinkedList<>(program);
    }

    /**
     * Executes the next instruction in the robot's program.
     */
    @Override
    public void executeNextInstruction() {
        boolean result;
        if (!this.commands.isEmpty()) {
            result = this.commands.peek().execute(this);
            System.out.println(this.commands.peek().toString());
            if (result)
                this.commands.remove();
        }
    }

}

