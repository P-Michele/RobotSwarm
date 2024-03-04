package it.unicam.cs.followme.models;

import it.unicam.cs.followme.command.Command;
import it.unicam.cs.followme.util.Direction;
import it.unicam.cs.followme.util.Point;
import it.unicam.cs.followme.utilities.ShapeData;

import java.util.List;
import java.util.Queue;

/**
 * Interface representing an entity in the environment.
 */
public interface Entity {

    /**
     * Perceives the environment, updating the entity's perception.
     *
     * @param environment The environment in which the entity exists.
     */
    void perceive(Environment environment);

    /**
     * Sets the label of the entity.
     *
     * @param label The label to be assigned to the entity.
     */
    void setLabel(String label);

    /**
     * Sets the speed of the entity.
     *
     * @param speed The speed at which the entity should move.
     */
    void setSpeed(double speed);

    /**
     * Sets the direction of the entity.
     *
     * @param dir The direction in which the entity should move.
     */
    void setDirection(Direction dir);

    /**
     * Gets the speed of the entity.
     *
     * @return The speed of the entity.
     */
    double getSpeed();

    /**
     * Gets the direction of the entity.
     *
     * @return The direction in which the entity is moving.
     */
    Direction getDirection();

    /**
     * Stops the current action of the entity.
     */
    void stop();

    /**
     * Signals the entity and returns its label.
     *
     * @return The label of the entity.
     */
    String signal();

    /**
     * Unsignals the entity, removing its label.
     */
    void unsignal();

    /**
     * Moves the entity according to its speed and direction.
     */
    void move();

    /**
     * Gets the current position of the entity.
     *
     * @return The position of the entity.
     */
    Point getPosition();

    /**
     * Gets a list of neighbors within the specified distance from the entity.
     *
     * @param distance The maximum distance for a neighbor to be considered.
     * @return A list of entities that are neighbors to the current entity.
     */
    List<Entity> getNeighbors(double distance);

    /**
     * Gets a list of entities that share a common signal with the current entity.
     *
     * @return A list of entities with a common signal.
     */
    List<Entity> getCommonSignal();

    /**
     * Checks if the entity is inside a shape specified by its shape data.
     *
     * @param shape The shape data defining the shape.
     * @return true if the entity is inside the specified shape, false otherwise.
     */
    boolean isInsideAShape(ShapeData shape);

    /**
     * Checks if the entity is inside a shape specified by its label.
     *
     * @param label The label associated with the shape.
     * @return true if the entity is inside the shape with the specified label, false otherwise.
     */
    boolean isInsideAShape(String label);

    /**
     * Adds a program (sequence of commands) to be executed by the entity.
     *
     * @param program The program to be added to the entity's execution queue.
     */
    void addProgram(Queue<Command> program);

    /**
     * Executes the next instruction in the entity's program.
     */
    void executeNextInstruction();
}

