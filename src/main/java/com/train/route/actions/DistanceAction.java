package com.train.route.actions;

import com.train.route.Action;
import com.train.route.Route;
import com.train.route.StartTrainRouteSearch;
import com.train.route.Town;
import com.train.route.TrainNetwork;

/**
 * @author abhishek.ghosh
 */
public class DistanceAction extends Action {
	public String execute(TrainNetwork trainNetwork) {
		String[] parameters = getParameters();

		// second parameter is the nodes specification
		if (parameters.length < 2) {
			throw new IllegalArgumentException(StartTrainRouteSearch.getProperty("invalid_action") + ": " + toString() +
					StartTrainRouteSearch.getProperty("missing_nodes_specification"));
		}
		
		String nodesSpec = parameters[1];
		Town[] towns = getNodes(trainNetwork, nodesSpec);
				
		int distance = 0;
		
		// initial "last node" is the start node...
		Town lastTown = towns[0];
		
		// iterate from the second node onwards...
		for (int i = 1; i < towns.length; ++i) {
			Town town = towns[i];
			boolean routeFound = false;
			// find out if there is indeed a path from
			// the previous node visited to this one...
			for (Route neighbour: lastTown.getNeighbours()) {
				if (neighbour.getDestination().isEqual(town)) {
					//found a path
					distance += neighbour.getDistance();
					routeFound = true;
					break;
				}
			}
			if (!routeFound) {
				// route doesn't exist. Distance is infinity
				distance = Integer.MAX_VALUE;
				break;
			}
			lastTown = town;
		}
		
		if (distance == Integer.MAX_VALUE) {
			return StartTrainRouteSearch.getProperty("no_route");
		}
		return String.valueOf(distance);
	}
}
