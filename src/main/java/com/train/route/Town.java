package com.train.route;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abhishek.ghosh
 */
public class Town {
    private List<Route> neighbours;
    private String name;

    public String getName() {
        return name;
    }

    public List<Route> getNeighbours() {
        return neighbours;
    }

    public Town(String name) {
        neighbours = new ArrayList<Route>();
        this.name = name;
    }

    public boolean isEqual(Town n) {
        return n.name == this.name;
    }

    public void addEdge(Route route) {
        neighbours.add(route);
    }
}
