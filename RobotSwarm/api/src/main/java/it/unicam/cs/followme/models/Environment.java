package it.unicam.cs.followme.models;

import it.unicam.cs.followme.utilities.ShapeData;

import java.util.List;

/**
 * Interface representing the environment in which entities exist.
 */
public interface Environment {

    /**
     * Adds an entity to the environment.
     *
     * @param entity The entity to be added to the environment.
     */
    void addEntity(Entity entity);

    /**
     * Removes an entity from the environment.
     *
     * @param entity The entity to be removed from the environment.
     */
    void removeEntity(Entity entity);

    /**
     * Adds a shape to the environment.
     *
     * @param shape The shape data representing the shape to be added.
     */
    void addShape(ShapeData shape);

    /**
     * Removes a shape from the environment.
     *
     * @param shape The shape data representing the shape to be removed.
     */
    void removeShape(ShapeData shape);

    /**
     * Gets a list of all entities in the environment.
     *
     * @return A list of entities in the environment.
     */
    List<Entity> getEntities();

    /**
     * Gets a list of all shapes in the environment.
     *
     * @return A list of shapes in the environment.
     */
    List<ShapeData> getShapes();

    /**
     * Gets a list of shapes in the environment with a specific label.
     *
     * @param label The label associated with the shapes to be retrieved.
     * @return A list of shapes in the environment with the specified label.
     */
    List<ShapeData> getShapes(String label);

    /**
     * Checks if an entity is present in the environment.
     *
     * @param entity The entity to be checked for presence.
     * @return true if the entity is present in the environment, false otherwise.
     */
    boolean isEntityInEnvironment(Entity entity);

    /**
     * Checks if a shape is present in the environment.
     *
     * @param shape The shape data to be checked for presence.
     * @return true if the shape is present in the environment, false otherwise.
     */
    boolean isShapeInEnvironment(ShapeData shape);

    /**
     * Clears the environment, removing all entities and shapes.
     */
    void clear();

    /**
     * Gets the count of entities in the environment.
     *
     * @return The count of entities in the environment.
     */
    int getEntityCount();

    /**
     * Gets the count of shapes in the environment.
     *
     * @return The count of shapes in the environment.
     */
    int getShapeCount();
}
