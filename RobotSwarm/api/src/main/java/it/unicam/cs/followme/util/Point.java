package it.unicam.cs.followme.util;

/**
 * Represents a point in a two-dimensional space with x and y coordinates.
 */
public class Point {

    private double x;
    private double y;

    /**
     * Constructs a Point object with default coordinates (0, 0).
     */
    public Point() {
        this(0, 0);
    }

    /**
     * Constructs a Point object with specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of the point.
     *
     * @return The x-coordinate of the point.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the point.
     *
     * @param x The new x-coordinate value.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the point.
     *
     * @return The y-coordinate of the point.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the point.
     *
     * @param y The new y-coordinate value.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param otherPoint The other point to which the distance is calculated.
     * @return The distance between this point and the other point.
     */
    public double distance(Point otherPoint) {
        double dx = this.x - otherPoint.x;
        double dy = this.y - otherPoint.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns a string representation of the point in the format "(x, y)".
     *
     * @return A string representation of the point.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Checks if this point is equal to another.
     *
     * @param obj The object to compare for equality.
     * @return true if the points are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Point otherPoint = (Point) obj;

        return Double.compare(otherPoint.x, x) == 0 && Double.compare(otherPoint.y, y) == 0;
    }

}

