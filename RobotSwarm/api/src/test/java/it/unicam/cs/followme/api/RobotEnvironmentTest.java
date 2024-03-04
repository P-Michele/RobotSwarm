package it.unicam.cs.followme.api;

import it.unicam.cs.followme.models.Robot;
import it.unicam.cs.followme.models.Entity;
import it.unicam.cs.followme.models.RobotEnvironment;
import it.unicam.cs.followme.utilities.ShapeData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class RobotEnvironmentTest {

    private RobotEnvironment environment;
    private Entity entity;
    private ShapeData shape;

    @BeforeEach
    public void setUp() {
        environment = new RobotEnvironment();
        entity = new Robot();
        shape = new ShapeData("label" , "RECTANGLE", new double[]{0, 0, 2, 2});
    }

    @Test
    public void testAddEntity() {
        environment.addEntity(entity);
        assertTrue(environment.isEntityInEnvironment(entity));
    }

    @Test
    public void testRemoveEntity() {
        environment.addEntity(entity);
        environment.removeEntity(entity);
        assertFalse(environment.isEntityInEnvironment(entity));
    }

    @Test
    public void testAddShape() {
        environment.addShape(shape);
        assertTrue(environment.isShapeInEnvironment(shape));
    }

    @Test
    public void testRemoveShape() {
        environment.addShape(shape);
        environment.removeShape(shape);
        assertFalse(environment.isShapeInEnvironment(shape));
    }

    @Test
    public void testGetEntities() {
        environment.addEntity(entity);
        List<Entity> entities = environment.getEntities();
        assertEquals(1, entities.size());
        assertTrue(entities.contains(entity));
    }

    @Test
    public void testGetShapes() {
        environment.addShape(shape);
        List<ShapeData> shapes = environment.getShapes();
        assertEquals(1, shapes.size());
        assertTrue(shapes.contains(shape));
    }

    @Test
    public void testGetShapesWithLabel() {
        environment.addShape(shape);
        List<ShapeData> shapesWithLabel = environment.getShapes("label");
        assertEquals(1, shapesWithLabel.size());
        assertTrue(shapesWithLabel.contains(shape));
    }

    @Test
    public void testClear() {
        environment.addEntity(entity);
        environment.addShape(shape);
        environment.clear();
        assertEquals(0, environment.getEntityCount());
        assertEquals(0, environment.getShapeCount());
    }
}
