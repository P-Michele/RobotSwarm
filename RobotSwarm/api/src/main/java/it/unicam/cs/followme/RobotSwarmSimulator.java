package it.unicam.cs.followme;

import it.unicam.cs.followme.models.Entity;

import java.util.List;

/**
 * Simulator implementation for simulating the behavior of a robot swarm.
 */
public class RobotSwarmSimulator implements Simulator {

    private double time;

    private double dt;

    private double currentTime;

    private final List<Entity> entities;

    /**
     * Constructs a RobotSwarmSimulator with the specified list of entities.
     *
     * @param entities The list of entities in the robot swarm.
     */
    public RobotSwarmSimulator(List<Entity> entities) {
        this.dt = 0;
        this.currentTime = 0;
        this.time = 0;
        this.entities = entities;
    }

    /**
     * Simulates the behavior of the robot swarm for the specified time duration with the given time step.
     *
     * @param dt   The time step for each simulation step.
     * @param time The total simulation time.
     * @throws IllegalArgumentException If the provided time step or total time is non-positive.
     */
    @Override
    public void simulate(double dt, double time) {
        if (dt <= 0 || time < 0) {
            throw new IllegalArgumentException("The time entered for the simulation is invalid.");
        }

        this.dt = dt;
        this.time = time;

        if (!this.hasDone()) {
            this.entities.forEach(Entity::executeNextInstruction);
            this.currentTime += this.dt;
        }
    }


    /**
     * Checks if the simulation has completed based on the current time and total simulation time.
     *
     * @return True if the simulation has completed.
     */
    @Override
    public boolean hasDone() {
        return !(this.dt + this.currentTime <= time);
    }
}
