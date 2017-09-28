package com.train.route;

import com.train.route.actions.TripsAction;
import junit.framework.TestCase;

/**
 * @author abhishek.ghosh
 */
public class TripsActionTest extends TestCase {

    public void testExecute() {
        TrainNetwork g1 = new TrainNetwork("AB3, BC3, CD3, DA3");
        TripsAction a1 = new TripsAction();
        a1.setParameters(new String[]{"-", "<101", "stops", "AA"});
        assertEquals(a1.execute(g1), "25");

        try {
            TripsAction a2 = new TripsAction();
            a2.setParameters(new String[]{"-", "<101", "stops"});
            a2.execute(g1);
            fail("invalid action accepted");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        TripsAction a3 = new TripsAction();
        a3.setParameters(new String[]{"-", "=6", "distance", "BD"});
        assertEquals(a3.execute(g1), "1");
    }

}
