package com.train.route.actions;

import com.train.route.Action;
import com.train.route.Route;
import com.train.route.StartTrainRouteSearch;
import com.train.route.Town;
import com.train.route.TrainNetwork;
import static com.train.route.util.DijkstraSearch.run;

/**
 * @author abhishek.ghosh
 */
public class ShortestPathAction extends Action {

    public String execute(TrainNetwork trainNetwork) {
        String[] parameters = getParameters();

        // second parameter contains the nodes specification
        if (parameters.length < 2) {
            throw new IllegalArgumentException(StartTrainRouteSearch.getProperty("invalid_action") + ": " + toString() +
                                               StartTrainRouteSearch.getProperty("missing_nodes_specification"));
        }

        String nodesSpec = parameters[1];
        Town[] towns = getNodes(trainNetwork, nodesSpec);

        if (towns.length != 2) {
            throw new IllegalArgumentException(StartTrainRouteSearch.getProperty("invalid_action") + ": " + toString() +
                                               StartTrainRouteSearch.getProperty("must_specify_source_and_target"));
        }

        Town start = towns[0];
        Town target = towns[1];

        int shortestDistance = Integer.MAX_VALUE;

        if (start.isEqual(target)) {
            // This is a bit of a special case. The algorithm finds the
            // shortest path between two nodes. When the start and end nodes are
            // the same, the distance will of course be 0. The problem requests
            // (implicitly) that in this case a different path be found instead
            // of just returning the obvious answer (no need to travel).
            for (Route route : start.getNeighbours()) {
                int distance = run(trainNetwork, route.getDestination(), target);
                if (distance != Integer.MAX_VALUE) {
                    // a path has been found from a neighbour of the start node
                    // to the start node. Total distance will of course include
                    // the distance from the start node to this neighbour.
                    distance += route.getDistance();
                    if (distance < shortestDistance) {
                        // a new shortest distance has been found
                        shortestDistance = distance;
                    }
                }
            }
        } else {
            shortestDistance = run(trainNetwork, towns[0], towns[1]);
        }

        if (shortestDistance == Integer.MAX_VALUE) {
            return StartTrainRouteSearch.getProperty("no_route");
        }
        return String.valueOf(shortestDistance);
    }
}
