package it.unicam.cs.followme.api;

import it.unicam.cs.followme.command.Command;
import it.unicam.cs.followme.command.MoveCommand;
import it.unicam.cs.followme.models.Robot;
import it.unicam.cs.followme.util.Direction;
import it.unicam.cs.followme.util.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.util.Queue;

public class RobotTest {

    @Test
    public void testGetPosition() {
        Point initialPosition = new Point(1.0, 2.0);
        Robot robot = new Robot(initialPosition);
        assertEquals(initialPosition, robot.getPosition());
    }

    @Test
    public void testSetDirection() {
        Robot robot = new Robot();
        Direction direction = new Direction(1, 0);
        robot.setDirection(direction);
        assertEquals(direction, robot.getDirection());
    }

    @Test
    public void testMove() {
        Robot robot = new Robot(new Point(1.0, 2.0));
        robot.setDirection(new Direction(1, 1));
        robot.setSpeed(2.0);
        robot.move();
        Point newPosition = new Point(3.0, 4.0);
        assertTrue(newPosition.equals(robot.getPosition()));
    }

    @Test
    public void testAddProgram() {
        Robot robot = new Robot();
        Queue<Command> program = new LinkedList<>();
        program.add(new MoveCommand(new Point() , 1));
        robot.addProgram(program);
        robot.executeNextInstruction();
        assertTrue(robot.commands.isEmpty());
    }

}
