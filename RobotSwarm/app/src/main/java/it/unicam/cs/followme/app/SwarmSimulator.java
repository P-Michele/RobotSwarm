package it.unicam.cs.followme.app;

import it.unicam.cs.followme.Controller;
import it.unicam.cs.followme.RobotSwarmSimulator;
import it.unicam.cs.followme.Simulator;
import it.unicam.cs.followme.models.*;
import it.unicam.cs.followme.util.Point;
import it.unicam.cs.followme.utilities.FollowMeParserException;
import it.unicam.cs.followme.utilities.ShapeData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Controller class for the Swarm Simulator UI.
 */
public class SwarmSimulator {

    @FXML
    public TextField txtDt;

    @FXML
    public TextField txtTime;

    @FXML
    public Button btnDefaultRobot;

    @FXML
    public AnchorPane apArea;

    @FXML
    public TextField txtNRobots;

    @FXML
    public Button btnRandomRobots;

    @FXML
    public Button btnReset;

    // Constants for the field dimensions
    public final double FIELD_WIDTH = 500;
    public final double FIELD_HEIGHT = 500;

    // Center point of the field
    private final Point CENTER = new Point(FIELD_WIDTH/2, FIELD_HEIGHT/2);

    private Controller controller;

    private Simulator simulator;

    private double dt;

    private double time;

    /**
     * Load default robots into the simulation.
     *
     * @throws IOException If an I/O error occurs during parsing.
     */
    public void loadDefaultRobot() throws IOException {
        this.controller = new Controller();
        this.controller.parseEntity();
        this.simulator = new RobotSwarmSimulator(this.controller.getEntities());
        refresh();
        btnReset.setDisable(false);
        this.btnRandomRobots.setDisable(true);
        simulator = new RobotSwarmSimulator(this.controller.getEntities());
    }

    /**
     * Load default shapes into the simulation.
     *
     * @throws FollowMeParserException If an error occurs during shape parsing.
     * @throws IOException             If an I/O error occurs during parsing.
     */
    public void loadDefaultShapes() throws FollowMeParserException, IOException {
        if (this.controller != null) {
            this.controller.parseShape();
            refresh();
        }
    }

    /**
     * Load default program into the simulation.
     *
     * @throws FollowMeParserException If an error occurs during program parsing.
     * @throws IOException             If an I/O error occurs during parsing.
     */
    public void loadDefaultProgram() throws FollowMeParserException, IOException {
        if (this.controller != null) {
            this.controller.parseProgram();
            refresh();
        }
    }

    /**
     * Handle the event when generating random robots button is clicked.
     */
    public void onClickGenerateRandomRobots() {
        this.controller = new Controller();
        try {
            int numRobots = Integer.parseInt(txtNRobots.getText());
            for (int i = 0; i < numRobots; i++)
                this.controller.addEntity(new Robot());
            refresh();
            btnReset.setDisable(false);
        } catch (NumberFormatException e) {
            System.out.println("Inserisci un numero valido per il numero di robot.");
        }
        this.btnDefaultRobot.setDisable(true);
    }

    /**
     * Handle the event when the reset button is clicked.
     */
    public void onClickReset() {
        btnReset.setDisable(true);
        txtTime.setDisable(false);
        txtDt.setDisable(false);
        btnRandomRobots.setDisable(false);
        btnDefaultRobot.setDisable(false);
        apArea.getChildren().clear();
        this.controller = new Controller();
        this.simulator = null;
    }

    /**
     * Refresh the simulation display.
     */
    public void refresh() {
        apArea.getChildren().clear();
        drawShapes();
        drawRobots();
    }

    /**
     * Draw shapes in the simulation.
     */
    public void drawShapes() {
        for (ShapeData s : this.controller.getShapes()) {
            if (s.shape().equals("CIRCLE")) {
                drawCircle(s);
            } else {
                drawRectangle(s);
            }
        }
    }

    /**
     * Draw a circle shape in the simulation.
     *
     * @param s Shape data for the circle.
     */
    public void drawCircle(ShapeData s) {
        if (isInRange(s.args()[0], s.args()[1])) {
            double rightX = setX(s.args()[0]) + s.args()[2] / 4;
            double rightY = setY(s.args()[1]) + s.args()[2] / 4;
            Circle cc = new Circle(rightX, rightY, s.args()[2]);
            cc.setFill(Color.BLUE);
            apArea.getChildren().add(cc);
        }
    }

    /**
     * Draw a rectangle shape in the simulation.
     *
     * @param s Shape data for the rectangle.
     */
    public void drawRectangle(ShapeData s) {
        if (isInRange(s.args()[0], s.args()[1])) {
            double rightX = setX(s.args()[0]) - s.args()[2] / 2.5;
            double rightY = setY(s.args()[1]) - s.args()[3] / 3.5;
            Rectangle rt = new Rectangle(rightX, rightY, s.args()[2], s.args()[3]);
            rt.setFill(Color.RED);
            apArea.getChildren().add(rt);
        }
    }

    /**
     * Draw robots in the simulation.
     */
    public void drawRobots() {
        for (Entity e : this.controller.getEntities()) {
            if (isInRange(e.getPosition().getX(), e.getPosition().getY())) {
                ImageView imageView = new ImageView("/images/robot.png");
                imageView.setFitWidth(30);
                imageView.setPreserveRatio(true);
                imageView.setLayoutX(setX(e.getPosition().getX()));
                imageView.setLayoutY(setY(e.getPosition().getY()));
                apArea.getChildren().add(imageView);
            }
        }
    }

    /**
     * Load entities from a file into the simulation.
     */
    @FXML
    public void onLoadEntityFromFile() {
        this.controller = new Controller();
        File selectedFile = showFileChooser("Select Entities");

        if (selectedFile != null) {
            try {
                controller.parseEntityFromFile(selectedFile.getAbsolutePath());
                refresh();
                btnReset.setDisable(false);
                btnRandomRobots.setDisable(true);
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
        this.simulator = new RobotSwarmSimulator(this.controller.getEntities());
    }

    /**
     * Load shapes from a file into the simulation.
     */
    @FXML
    public void onLoadShapeFromFile() {
        if (this.controller != null) {
            File selectedFile = showFileChooser("Select Shapes");

            if (selectedFile != null) {
                try {
                    controller.parseShapeFromFile(selectedFile.getAbsolutePath());
                    refresh();
                } catch (FollowMeParserException | IOException e) {
                    System.out.println("Error");
                }
            }
        }
    }

    /**
     * Load program from a file into the simulation.
     */
    @FXML
    public void onLoadProgramFromFile() {
        if (this.controller != null) {
            File selectedFile = showFileChooser("Select Program");

            if (selectedFile != null) {
                try {
                    controller.parseProgramFromFile(selectedFile.getAbsolutePath());
                    refresh();
                } catch (FollowMeParserException | IOException e) {
                    System.out.println("Error");
                }
            }
        }
    }

    /**
     * Show a file chooser dialog.
     *
     * @param title The title of the file chooser dialog.
     * @return The selected file.
     */
    private File showFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt files", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );

        Stage stage = new Stage();
        return fileChooser.showOpenDialog(stage);
    }

    /**
     * Check if a given point is within the simulation field.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return True if the point is within the field, false otherwise.
     */
    private Boolean isInRange(double x, double y) {
        return x >= -FIELD_WIDTH/2 && x <= FIELD_WIDTH/2 && y >= -FIELD_HEIGHT/2 && y <= FIELD_HEIGHT/2;
    }

    /**
     * Calculate the y-coordinate in the simulation based on the provided y-coordinate.
     *
     * @param y The y-coordinate.
     * @return The adjusted y-coordinate for the simulation.
     */
    private double setY(double y) {
        return (CENTER.getY()) - (FIELD_HEIGHT - (FIELD_HEIGHT - (y % FIELD_HEIGHT)));
    }

    /**
     * Calculate the x-coordinate in the simulation based on the provided x-coordinate.
     *
     * @param x The x-coordinate.
     * @return The adjusted x-coordinate for the simulation.
     */
    private double setX(double x) {
        return ((CENTER.getX()) + FIELD_WIDTH - (FIELD_WIDTH - (x % FIELD_WIDTH)));
    }

    /**
     * Execute the simulation based on the provided time and time step.
     */
    @FXML
    public void onExecuteSimulation() {
        if (this.simulator != null) {
            try {
                txtTime.setDisable(true);
                txtDt.setDisable(true);
                this.dt = Double.parseDouble(txtDt.getText());
                this.time = Double.parseDouble(txtTime.getText());
                simulator.simulate(dt, time);
                refresh();
            } catch (NumberFormatException e) {
                System.out.println("Add dt and time.");
            }
        }
    }

}
