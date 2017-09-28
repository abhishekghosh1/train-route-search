package com.train.route;

import junit.framework.TestCase;

/**
 * @author abhishek.ghosh
 */
public class TrainNetworkSpecTest extends TestCase {

    public void testGraphString() {
        try {
            TrainNetwork g1 = new TrainNetwork("AB5, DFA, DB9 FA1");
            fail("Invalid DFA spec accepted");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            TrainNetwork g2 = new TrainNetwork("AB5, DF3, DB , BA9");
            fail("Incomplete DB spec accepted");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        TrainNetwork g3 = new TrainNetwork("AB1 BD8, \n FZ5");
        assertTrue(g3.getNodes().length == 5);
    }

}
