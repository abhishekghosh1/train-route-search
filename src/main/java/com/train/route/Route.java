package com.train.route;

/**
 * @author abhishek.ghosh
 */
public class Route {
    private Town destination;
    private int distance;

    public Town getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }

    public Route() {
        destination = null;
        distance = 0;
    }

    public Route(Town n, int d) {
        destination = n;
        distance = d;
    }

    public String toString() {
        return destination.getName() + ": " + distance;
    }
}
