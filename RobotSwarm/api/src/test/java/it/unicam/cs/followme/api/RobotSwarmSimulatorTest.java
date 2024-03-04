package it.unicam.cs.followme.api;

import it.unicam.cs.followme.RobotSwarmSimulator;
import it.unicam.cs.followme.command.*;
import it.unicam.cs.followme.models.*;
import it.unicam.cs.followme.util.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class RobotSwarmSimulatorTest {

    private RobotSwarmSimulator simulator;

    private List<Entity> entities;

    Environment environment;

    @BeforeEach
    public void setUp() {
        entities = new ArrayList<>();
        Entity robot1 = new Robot(new Point(4,1));
        Entity robot2 = new Robot(new Point(1,4));
        robot2.setLabel("LABEL");
        entities.add(robot1);
        entities.add(robot2);

        simulator = new RobotSwarmSimulator(entities);
        this.environment = new RobotEnvironment();
        environment.addEntity(robot1);
        environment.addEntity(robot2);
    }

    @Test
    public void testSimulate(){
        double dt = 1.0;
        double time = 6.0;
        this.setProgram();
        while(!simulator.hasDone())
            simulator.simulate(dt, time);
        assertEquals("NEWLABEL", this.entities.get(0).signal());
    }

    private void setProgram(){
        Queue<Command> program = new LinkedList<>() {
        };
        program.add(new MoveCommand(new Point() , 2));
        program.add(new FollowCommand("LABEL" , 5 ,2));

        RepeatCommand repeatCommand = new RepeatCommand(2);
        repeatCommand.addCommand(new SignalCommand("NEWLABEL"));
        program.add(repeatCommand);

        for (Entity entity: entities) {
            entity.addProgram(program);
            entity.perceive(this.environment);
        }
    }

    @Test
    public void testSimulate2(){
        double dt = 3.0;
        double time = 5.0;
        this.setProgram();

        while(!simulator.hasDone())
            simulator.simulate(dt, time);

        assertEquals("", this.entities.get(0).signal());
    }


}
