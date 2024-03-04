package it.unicam.cs.followme;

import it.unicam.cs.followme.models.*;
import it.unicam.cs.followme.util.Point;
import it.unicam.cs.followme.utilities.FollowMeParser;
import it.unicam.cs.followme.utilities.FollowMeParserException;
import it.unicam.cs.followme.utilities.ShapeData;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * Controller class responsible for managing entities, shapes, and parsing programs.
 */
public class Controller {

    private final FollowMeParser parser;
    private final Environment environment;

    /**
     * Constructs a Controller with a default RobotEnvironment and a FollowMeParser with a ParserHandler.
     */
    public Controller() {
        this.environment = new RobotEnvironment();
        this.parser = new FollowMeParser(new ParserHandler(this.environment));
    }

    /**
     * Parses entity information from a default file and adds entities to the environment.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void parseEntity() throws IOException {
        List<String> lines = Files.readAllLines(new File(Objects.requireNonNull(getClass().getClassLoader().getResource("files/robots.txt")).
                getFile()).toPath());
        for (String line : lines) {
            String[] elements = line.trim().toUpperCase().split(" ");
            this.environment.addEntity(new Robot(new Point(Double.parseDouble(elements[0]), Double.parseDouble(elements[1]))));
        }
    }

    /**
     * Parses shape information from a default file and adds shapes to the environment.
     *
     * @throws FollowMeParserException If a parsing error occurs.
     * @throws IOException              If an I/O error occurs.
     */
    public void parseShape() throws FollowMeParserException, IOException {
        List<ShapeData> shapes = this.parser.parseEnvironment(new File(Objects.requireNonNull(getClass().getClassLoader().
                getResource("files/shapes.txt")).getFile()));
        for (ShapeData shape : shapes) {
            this.environment.addShape(shape);
        }
    }

    /**
     * Parses robot program information from a default file.
     *
     * @throws FollowMeParserException If a parsing error occurs.
     * @throws IOException              If an I/O error occurs.
     */
    public void parseProgram() throws FollowMeParserException, IOException {
        this.parser.parseRobotProgram(new File(Objects.requireNonNull(getClass().getClassLoader().
                getResource("files/program.txt")).getFile()));
    }

    /**
     * Parses entity information from a specified file and adds entities to the environment.
     *
     * @param filePath The path of the file containing entity information.
     * @throws IOException If an I/O error occurs.
     */
    public void parseEntityFromFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));
        for (String line : lines) {
            String[] elements = line.trim().toUpperCase().split(" ");
            this.environment.addEntity(new Robot(new Point(Double.parseDouble(elements[0]), Double.parseDouble(elements[1]))));
        }
    }

    /**
     * Parses shape information from a specified file and adds shapes to the environment.
     *
     * @param filePath The path of the file containing shape information.
     * @throws FollowMeParserException If a parsing error occurs.
     * @throws IOException              If an I/O error occurs.
     */
    public void parseShapeFromFile(String filePath) throws FollowMeParserException, IOException {
        List<ShapeData> shapes = this.parser.parseEnvironment(new File(filePath));
        for (ShapeData shape : shapes) {
            this.environment.addShape(shape);
        }
    }

    /**
     * Parses robot program information from a specified file.
     *
     * @param filePath The path of the file containing robot program information.
     * @throws FollowMeParserException If a parsing error occurs.
     * @throws IOException              If an I/O error occurs.
     */
    public void parseProgramFromFile(String filePath) throws FollowMeParserException, IOException {
        this.parser.parseRobotProgram(new File(filePath));
    }

    /**
     * Adds an entity to the environment.
     *
     * @param entity The entity to be added.
     */
    public void addEntity(Entity entity) {
        this.environment.addEntity(entity);
    }

    /**
     * Adds a shape to the environment.
     *
     * @param shape The shape to be added.
     */
    public void addShape(ShapeData shape) {
        this.environment.addShape(shape);
    }

    /**
     * Retrieves a list of entities in the environment.
     *
     * @return A list of entities in the environment.
     */
    public List<Entity> getEntities() {
        return this.environment.getEntities();
    }

    /**
     * Retrieves a list of shapes in the environment.
     *
     * @return A list of shapes in the environment.
     */
    public List<ShapeData> getShapes() {
        return this.environment.getShapes();
    }
}

