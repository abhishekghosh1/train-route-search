package com.train.route;

import com.train.route.actions.DistanceAction;
import com.train.route.actions.ShortestPathAction;
import com.train.route.actions.TripsAction;
import junit.framework.TestCase;

/**
 * @author abhishek.ghosh
 */
public class ActionFactoryTest extends TestCase {

    private ActionFactory testFactory;

    public void testCreateAction() {
        testFactory = new ActionFactory();
        Action foo = testFactory.createAction("foo");
        assertTrue(foo == null);
        Action distance = testFactory.createAction("distance?");
        assertTrue(distance.getClass().equals(DistanceAction.class));
        Action trips = testFactory.createAction("trips?");
        assertTrue(trips.getClass().equals(TripsAction.class));
        Action shortest = testFactory.createAction("shortest?");
        assertTrue(shortest.getClass().equals(ShortestPathAction.class));
    }
}
