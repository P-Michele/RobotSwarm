package it.unicam.cs.followme;

/**
 * Interface representing a simulator for simulating the behavior of entities.
 */
public interface Simulator {

    /**
     * Simulates the behavior of entities for a specified time duration with a given time step.
     *
     * @param dt   The time step for each simulation step.
     * @param time The total simulation time.
     */
    void simulate(double dt, double time);

    /**
     * Checks whether the simulation has completed.
     *
     * @return True if the simulation has completed.
     */
    boolean hasDone();
}
