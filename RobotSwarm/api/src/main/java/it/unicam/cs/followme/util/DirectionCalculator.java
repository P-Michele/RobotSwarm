package it.unicam.cs.followme.util;

/**
 * Interface for a DirectionCalculator that provides a method to calculate the direction
 * from a current position to a target position.
 */
public interface DirectionCalculator {

    /**
     * Calculates the direction from a current position to a target position.
     *
     * @param currentPosition The current position.
     * @param targetPosition  The target position.
     * @return The calculated Direction object representing the direction from current to target.
     */
    static Direction calculateDirection(Point currentPosition, Point targetPosition) {
        double deltaX = targetPosition.getX() - currentPosition.getX();
        double deltaY = targetPosition.getY() - currentPosition.getY();

        // Calculate the angle between the two points
        double angle = Math.atan2(deltaY, deltaX);

        // Normalize the angle between -PI and PI and convert to normalized delta x and delta y
        double normalizedDeltaX = Math.cos(angle);
        double normalizedDeltaY = Math.sin(angle);

        return new Direction(normalizedDeltaX, normalizedDeltaY);
    }
}

