package com.train.route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author abhishek.ghosh
 */
public class TrainNetwork {

    Map<String, Town> nodeMap = new TreeMap<String, Town>();

    public Town[] getNodes() {
        return nodeMap.values().toArray(new Town[0]);
    }

    /**
     * Looks up a given node in this TrainNetwork by name. If it doesn't exist, a new node is created and inserted in the TrainNetwork.
     *
     * @param name townName
     * @return Town
     */
    public Town getNode(String name) {
        Town town = nodeMap.get(name);
        if (town == null) {
            town = new Town(name);
            nodeMap.put(name, town);
        }
        return town;
    }

    /**
     * reads a series of TrainNetwork edges (i.e. connections between nodes) from a given string containing a comma or whitespace separated list of connections of the
     * form XYZ, where X and Y correspond to a source and target nodes respectively and Z corresponds to the distance or weight between the two nodes. For
     * example "AB3".
     *
     * @param str
     */
    public void read(String str) {
        for (String s : str.split("[\\s,]")) {
            if (!s.isEmpty()) {
                // An edge spec must be at least 3 characters.. i.e.
                // AB3
                if (s.length() < 3) {
                    throw new IllegalArgumentException("Bad graph specification: " + s);
                }
                String sourceNodeName = s.substring(0, 1);
                String targetNodeName = s.substring(1, 2);

                int distance;

                try {
                    distance = Integer.parseInt(s.substring(2));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Bad graph specification: " + s +
                                                       ": " + s.substring(2) + " is not a valid integer");
                }
                Town source = getNode(sourceNodeName);
                Town target = getNode(targetNodeName);

                source.addEdge(new Route(target, distance));
            }
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<String, Town> entry : nodeMap.entrySet()) {
            s.append(entry.getKey() + ":\n");
            for (Route route : entry.getValue().getNeighbours()) {
                s.append("  -> " + route + "\n");
            }
        }
        return s.toString();
    }

    /**
     * Constructs a TrainNetwork based on a specification read from an InputStream The specification is a comma (or whitespace) separated list of connections of the
     * form XYZ, where X and Y correspond to a source and target nodes respectively and Z corresponds to the distance or weight between the two nodes. For
     * example "AB3".
     *
     * @param stream inputStream object
     * @throws IOException
     */
    public TrainNetwork(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String str;
        while ((str = reader.readLine()) != null) {
            read(str);
        }
    }

    public TrainNetwork(String str) {
        read(str);
    }
}
