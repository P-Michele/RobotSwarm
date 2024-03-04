package it.unicam.cs.followme.models;

import it.unicam.cs.followme.utilities.ShapeData;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of the Environment interface representing an environment for robots.
 */
public class RobotEnvironment implements Environment {

    private List<ShapeData> shapes;
    private List<Entity> entities;

    /**
     * Constructs a RobotEnvironment with empty lists of shapes and entities.
     */
    public RobotEnvironment() {
        this.shapes = new ArrayList<>();
        this.entities = new ArrayList<>();
    }

    /**
     * Adds an entity to the environment.
     *
     * @param entity The entity to be added to the environment.
     */
    @Override
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Removes an entity from the environment.
     *
     * @param entity The entity to be removed from the environment.
     */
    @Override
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    /**
     * Adds a shape to the environment.
     *
     * @param shape The shape data representing the shape to be added.
     */
    @Override
    public void addShape(ShapeData shape) {
        shapes.add(shape);
    }

    /**
     * Removes a shape from the environment.
     *
     * @param shape The shape data representing the shape to be removed.
     */
    @Override
    public void removeShape(ShapeData shape) {
        shapes.remove(shape);
    }

    /**
     * Gets a list of all entities in the environment.
     *
     * @return A list of entities in the environment.
     */
    @Override
    public List<Entity> getEntities() {
        return new ArrayList<>(entities);
    }

    /**
     * Gets a list of all shapes in the environment.
     *
     * @return A list of shapes in the environment.
     */
    @Override
    public List<ShapeData> getShapes() {
        return new ArrayList<>(shapes);
    }

    /**
     * Gets a list of shapes in the environment with a specific label.
     *
     * @param label The label associated with the shapes to be retrieved.
     * @return A list of shapes in the environment with the specified label.
     */
    @Override
    public List<ShapeData> getShapes(String label) {
        return shapes.stream()
                .filter(shape -> Objects.equals(shape.label(), label))
                .collect(Collectors.toList());
    }

    /**
     * Checks if an entity is present in the environment.
     *
     * @param entity The entity to be checked for presence.
     * @return true if the entity is present in the environment, false otherwise.
     */
    @Override
    public boolean isEntityInEnvironment(Entity entity) {
        return entities.contains(entity);
    }

    /**
     * Checks if a shape is present in the environment.
     *
     * @param shape The shape data to be checked for presence.
     * @return true if the shape is present in the environment, false otherwise.
     */
    @Override
    public boolean isShapeInEnvironment(ShapeData shape) {
        return shapes.contains(shape);
    }

    /**
     * Clears the environment, removing all entities and shapes.
     */
    @Override
    public void clear() {
        entities.clear();
        shapes.clear();
    }

    /**
     * Gets the count of entities in the environment.
     *
     * @return The count of entities in the environment.
     */
    @Override
    public int getEntityCount() {
        return entities.size();
    }

    /**
     * Gets the count of shapes in the environment.
     *
     * @return The count of shapes in the environment.
     */
    @Override
    public int getShapeCount() {
        return shapes.size();
    }
}


