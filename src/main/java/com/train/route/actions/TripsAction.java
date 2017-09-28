package com.train.route.actions;

import com.train.route.Action;
import com.train.route.Condition;
import com.train.route.Path;
import com.train.route.StartTrainRouteSearch;
import com.train.route.Town;
import com.train.route.TrainNetwork;
import com.train.route.util.PathFinder;

/**
 * @author abhishek.ghosh
 */
public class TripsAction extends Action {
	
	public String execute(TrainNetwork trainNetwork) {
		String[] parameters = getParameters();

		// second & third parameter specify the condition
		// i.e. "<5 stops"
		// fourth parameter is the nodes specification
		if (parameters.length < 4) {
			throw new IllegalArgumentException(StartTrainRouteSearch.getProperty("invalid_action") + ": " + toString() +
					StartTrainRouteSearch.getProperty("incomplete_action"));
		}
		
		String condition = parameters[1];
		String entity    = parameters[2];
		String nodesSpec = parameters[3];
		
		String operator  = condition.substring(0,1);
		int value        = Integer.parseInt(condition.substring(1));
		
		Town[] towns = getNodes(trainNetwork, nodesSpec);
		
		if (towns.length != 2) {
			throw new IllegalArgumentException(StartTrainRouteSearch.getProperty("invalid_action") + ": " + toString() +
					StartTrainRouteSearch.getProperty("must_specify_source_and_target"));
		}
		
		Town start = towns[0];
		Town target = towns[1];
		Condition filterCond = new Condition(operator, value, entity);
		Condition runCond = filterCond;
		
		// when the filtering condition is to find all paths that match
		// a given value, the test runs so long as the number of hops
		// of the distance is still less than the value we are searching for
		if (operator.equals("=")) {
			runCond = new Condition("<", value + 1, entity);
		}
		
		Path[] paths = new PathFinder(runCond, filterCond, false).findAllPaths(trainNetwork, start, target);
		
		return String.valueOf(paths.length);
	}
}
