package com.train.route;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * StartTrainRouteSearch Program starts from here. This is the main class which controls the entire Route Search Algorithm.
 *
 * @author abhishek.ghosh
 */
public class StartTrainRouteSearch {
    private ResourceBundle properties;
    private static StartTrainRouteSearch instance;

    private StartTrainRouteSearch() {
        try {
            properties = ResourceBundle.getBundle("com.train.route.train-route");
        } catch (MissingResourceException e) {
            System.err.println("Could not find train-route.properties file");
            System.exit(1);
        }
    }

    public static StartTrainRouteSearch getInstance() {
        if (instance == null) {
            instance = new StartTrainRouteSearch();
        }
        return instance;
    }

    public static String getProperty(String name) {
        String s;
        try {
            s = getInstance().properties.getString(name);
        } catch (MissingResourceException e) {
            System.err.println("WARNING: missing data: " + name);
            return "";
        }
        return s;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(getProperty("usage"));
            System.exit(1);
        }

        String routeInputFilename = args[0];
        String outPutFormatFilename = args[1];

        try {
            TrainNetwork trainNetwork = new TrainNetwork(getFileInputStream(routeInputFilename));
            Actions actions = new Actions(getFileInputStream(outPutFormatFilename));

            System.out.println(actions.execute(trainNetwork));
        } catch (IOException e) {
            System.err.println("FATAL: " + e.getMessage());
            System.exit(1);
        }
    }

    private static FileInputStream getFileInputStream(String name) {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(name);
        } catch (FileNotFoundException e) {
            System.err.println(name + ": File not found");
            System.exit(1);
        }
        return stream;
    }
}
