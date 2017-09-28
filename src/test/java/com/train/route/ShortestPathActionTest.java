package com.train.route;

import com.train.route.actions.ShortestPathAction;
import junit.framework.TestCase;

/**
 * @author abhishek.ghosh
 */
public class ShortestPathActionTest extends TestCase {

    public void testExecute() {
        TrainNetwork g1 = new TrainNetwork("AB2, BA3, BD5, BE7, DB11, EB13, CE17, EC19, DE23, ED29, CF31 " +
                                           "FC37, EF41, FE43, FA53, AF59, DA61, AD67, EA71, AE73");

        ShortestPathAction a1 = new ShortestPathAction();
        a1.setParameters(new String[]{"-", "BF"});
        assertEquals(a1.execute(g1), "48");

        try {
            ShortestPathAction a2 = new ShortestPathAction();
            a2.setParameters(new String[]{"-", "BFA"});
            a2.execute(g1);
            fail("Invalid Node Specification accepted");
        } catch (IllegalArgumentException e) {

        }

        try {
            ShortestPathAction a3 = new ShortestPathAction();
            a3.setParameters(new String[]{"-"});
            a3.execute(g1);
            fail("Missing Node Specification accepted");
        } catch (IllegalArgumentException e) {

        }
    }

}
