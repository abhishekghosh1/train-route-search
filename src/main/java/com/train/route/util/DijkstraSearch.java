package com.train.route.util;

import com.train.route.Route;
import com.train.route.Town;
import com.train.route.TrainNetwork;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * This class use Dijkstra algorithm to calculate shorted possible route
 * @author abhishek.ghosh
 */
public class DijkstraSearch {

    /**
     * Performs a complete run of the Dijkstra algorithm to calculate the shortest possible route between a start and target node
     *
     * @param trainNetwork TrainNetwork
     * @param start startPoint
     * @param target endPoint
     * @return shortest distance
     */
    public static int run(TrainNetwork trainNetwork, Town start, Town target) {
        Set<Town> unsettled = new HashSet<Town>();
        Set<Town> settled = new HashSet<Town>();

        Town[] towns = trainNetwork.getNodes();
        Map<String, Integer> distance = new TreeMap<String, Integer>();

        // Start by setting the distances from all nodes to the start node to
        // infinity
        for (Town town : towns) {
            distance.put(town.getName(), Integer.MAX_VALUE);
        }

        // move start node to the list of unvisited nodes and set the
        // distance to 0 (i.e. the distance from the start node to itself)
        unsettled.add(start);
        distance.put(start.getName(), 0);

        // while the list of unvisited nodes is not empty...
        while (!unsettled.isEmpty()) {

            // find the node in the unvisited list with the
            // shortest known distance to the source so far (i.e. the
            // "nearest" node
            Town nearest = null;
            int minDistance = Integer.MAX_VALUE;
            for (Town n : unsettled) {
                Integer dist = distance.get(n.getName());
                if (dist < minDistance) {
                    nearest = n;
                    minDistance = dist;
                }
            }

            // move the nearest node to the list of visited nodes
            unsettled.remove(nearest);
            settled.add(nearest);

            // for all neighbours of the current node...
            for (Route route : nearest.getNeighbours()) {
                // which haven't been visited...
                if (!settled.contains(route.getDestination())) {
                    // find the ones for which we find a better (shorter) path
                    // to the source than the one we had previously calculated for it...
                    int newDistance = distance.get(nearest.getName()) + route.getDistance();
                    Integer dist = distance.get(route.getDestination().getName());
                    if (newDistance < dist) {
                        // save the new shortest distance for this neighbour
                        // and move it to the list of unvisited nodes.
                        distance.put(route.getDestination().getName(), newDistance);
                        unsettled.add(route.getDestination());
                    }
                }
            }

            // if the target node has already been visited, we can stop searching
            if (settled.contains(target)) {
                break;
            }
        }
        // returns the shortest distance recorded from the target node to the start node
        return distance.get(target.getName());
    }
}
