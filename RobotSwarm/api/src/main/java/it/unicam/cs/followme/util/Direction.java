package it.unicam.cs.followme.util;

/**
 * Represents a directional vector.
 */
public class Direction {

    private double deltaX;
    private double deltaY;

    /**
     * Constructs a Direction object with specified deltaX and deltaY components.
     *
     * @param deltaX The x-component of the direction vector.
     * @param deltaY The y-component of the direction vector.
     */
    public Direction(double deltaX, double deltaY) {
        setDeltaX(deltaX);
        setDeltaY(deltaY);
    }

    /**
     * Gets the x-component of the direction vector.
     *
     * @return The x-component of the direction vector.
     */
    public double getDeltaX() {
        return deltaX;
    }

    /**
     * Gets the y-component of the direction vector.
     *
     * @return The y-component of the direction vector.
     */
    public double getDeltaY() {
        return deltaY;
    }

    /**
     * Sets the x-component of the direction vector.
     *
     * @param deltaX The new x-component value.
     * @throws IllegalArgumentException if deltaX is not within the range [-1, 1].
     */
    public void setDeltaX(double deltaX) {
        if (deltaX >= -1 && deltaX <= 1) {
            this.deltaX = deltaX;
        } else {
            throw new IllegalArgumentException("deltaX must be within the range [-1, 1]");
        }
    }

    /**
     * Sets the y-component of the direction vector.
     *
     * @param deltaY The new y-component value.
     * @throws IllegalArgumentException if deltaY is not within the range [-1, 1].
     */
    public void setDeltaY(double deltaY) {
        if (deltaY >= -1 && deltaY <= 1) {
            this.deltaY = deltaY;
        } else {
            throw new IllegalArgumentException("deltaY must be within the range [-1, 1]");
        }
    }

}

