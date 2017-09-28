package com.train.route.util;

import com.train.route.TrainNetwork;
import com.train.route.util.DijkstraSearch;
import junit.framework.TestCase;

/**
 * @author abhishek.ghosh
 */
public class DijkstraSearchTest extends TestCase {

    public void testRun() {
        TrainNetwork g1 = new TrainNetwork("AB5, AC9, BC2, BD7, CD4");
        assertTrue(DijkstraSearch.run(g1, g1.getNode("A"), g1.getNode("D")) == 11);

        TrainNetwork g2 = new TrainNetwork("AB5, AB1, BC2, CA1, CD4, BD6");
        assertTrue(DijkstraSearch.run(g2, g2.getNode("A"), g2.getNode("D")) == 7);
        assertTrue(DijkstraSearch.run(g2, g2.getNode("A"), g2.getNode("F")) == Integer.MAX_VALUE);
        assertTrue(DijkstraSearch.run(g2, g2.getNode("F"), g2.getNode("F")) == 0);
        assertFalse(DijkstraSearch.run(g2, g2.getNode("D"), g2.getNode("A")) == 7);

        TrainNetwork g3 = new TrainNetwork("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
        assertTrue(DijkstraSearch.run(g3, g3.getNode("A"), g3.getNode("C")) == 9);
        assertTrue(DijkstraSearch.run(g3, g3.getNode("B"), g3.getNode("B")) == 0);
    }

}
