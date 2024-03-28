package SimQueues.Utils;

import java.util.HashMap;

public class SimQueue {
    private int f;
    private int servers;
    private int k;
    private double[] serviceInterval;
    private HashMap<Integer, Double> states;

    public SimQueue(int servers, int k, double[] serviceInterval) {
        this.f = 0;
        this.servers = servers;
        this.k = k;
        this.serviceInterval = serviceInterval;
        states = new HashMap<Integer, Double>();
        generateMap();
    }

    public int getF() {
        return f;
    }

    public int getServers() {
        return servers;
    }

    public int getK() {
        return k;
    }

    public double[] getServiceInterval() {
        return serviceInterval;
    }

    public double getStartA() {
        return serviceInterval[0];
    }

    public double getEndA() {
        return serviceInterval[1];
    }

    public void increment() {
        this.f++;
    }

    public void decrement() {
        this.f--;
    }

    public void count(int state, double time) {
        states.put(state, time);
    }

    public Double get(int state) {
        return states.get(state);
    }

    public void generateMap() {
        for (int i = 0; i <= k; i++) {
            states.put(i, 0.0);
        }
    }

    public void setPercentage(int state, double time, double totalTime) {
        states.replace(state, (time / totalTime) * 100);
    }

    public double getPercentage(int state) {
        return states.get(state);
    }

    @Override
    public String toString() {
        return "G/G/" + servers + "/" + k + " " + "Service interval: " + (int) serviceInterval[0] + " - "
                + (int) serviceInterval[1];
    }
}
