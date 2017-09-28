package com.train.route;

/**
 * @author abhishek.ghosh
 */
public class Action {
    private String[] parameters;

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    public String[] getParameters() {
        return parameters;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String s : parameters) {
            str.append(s + " ");
        }
        return str.toString();
    }

    public String execute(TrainNetwork g) {
        return StartTrainRouteSearch.getProperty("not_implemented");
    }

    protected Town[] getNodes(TrainNetwork trainNetwork, String s) {
        Town[] towns = new Town[s.length()];
        int i = 0;
        for (char c : s.toCharArray()) {
            String name = String.valueOf(c);
            towns[i++] = trainNetwork.getNode(name);
        }
        return towns;
    }
}
