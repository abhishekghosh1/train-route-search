package com.train.route;

import com.train.route.actions.DistanceAction;
import com.train.route.actions.ShortestPathAction;
import com.train.route.actions.TripsAction;
import java.util.HashMap;
import java.util.Map;

/**
 * A small Factory Pattern to instantiate Actions based on a command string
 *
 * @author abhishek.ghosh
 */
public class ActionFactory {
    private Map<String, Class<?>> actionsMap;

    public Action createAction(String s) {
        Class<?> c = actionsMap.get(s);
        Action action = null;

        if (c != null) {
            try {
                action = (Action) c.newInstance();
            } catch (Exception e) {
                System.err.println(StartTrainRouteSearch.getProperty("internal_error") + " " + s + " action: " + e.getMessage());
            }
        }

        return action;
    }

    public ActionFactory() {
        actionsMap = new HashMap<String, Class<?>>();

        actionsMap.put("distance?", DistanceAction.class);
        actionsMap.put("trips?", TripsAction.class);
        actionsMap.put("shortest?", ShortestPathAction.class);
    }
}
