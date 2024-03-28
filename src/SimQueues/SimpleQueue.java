package SimQueues;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import SimQueues.Utils.Event;
import SimQueues.Utils.Utils;

/*
    Modeling of a simple queue
*/
public class SimpleQueue {

    private int queue = 0;
    private double globalTime = 0;
    private PriorityQueue<Event> scheduler;
    private HashMap<Integer, Float> states;
    private LinkedList<Float> randomNumbers;
    private int servers;
    private int k;
    private double[] arrivalInterval;
    private double[] serviceInterval;
    private int totalRandomNumbers;
    private int seed;
    private boolean lecture;

    /*
     * Constructor
     */
    public SimpleQueue(int servers, int k, double[] arrivalInterval, double[] serviceInterval,
                       double initialTime,
                       int totalRandomNumbers, int seed) {

        this.servers = servers;
        this.k = k;
        this.arrivalInterval = arrivalInterval;
        this.serviceInterval = serviceInterval;
        this.totalRandomNumbers = totalRandomNumbers;
        this.seed = seed;
        this.lecture = false;
        scheduler = new PriorityQueue<Event>();
        states = new HashMap<Integer, Float>();
        randomNumbers = new LinkedList<Float>();
        Utils.linearCongruentialMethod(totalRandomNumbers, seed, randomNumbers, lecture);
        generateHashMap();
        scheduler.add(new Event(initialTime, "Arrival"));
    }

    /*
     * Constructor to test a lecture example case
     */
    public SimpleQueue(int servers, int k, double[] arrivalInterval, double[] serviceInterval,
                       double initialTime,
                       int totalRandomNumbers) {

        this.servers = servers;
        this.k = k;
        this.arrivalInterval = arrivalInterval;
        this.serviceInterval = serviceInterval;
        this.totalRandomNumbers = totalRandomNumbers;
        this.lecture = true;
        scheduler = new PriorityQueue<Event>();
        states = new HashMap<Integer, Float>();
        randomNumbers = new LinkedList<Float>();
        Utils.linearCongruentialMethod(totalRandomNumbers, seed, randomNumbers, lecture);
        generateHashMap();
        scheduler.add(new Event(initialTime, "Arrival"));
    }

    /*
     * Method that executes the entire simulation algorithm
     * until the chosen number of pseudo-random numbers is reached.
     */
    public void start() {
        while (!randomNumbers.isEmpty()) {
            /*
             * Remove the next event from the scheduler and account for time
             */
            Event e = scheduler.poll();
            states.put(queue, (float) ((e.time - globalTime) + states.get(queue)));
            globalTime = e.time;
            if (e.name.equals("Arrival")) {
                arrival();
            } else {
                departure();
            }
        }
        Utils.printResult(servers, states, globalTime, k);
    }

    /*
     * Event handling (ARRIVAL)
     */
    public void arrival() {
        if (queue < k) {
            queue++;
            if (queue <= servers) {
                scheduler.add(new Event(globalTime + rand(serviceInterval[0], serviceInterval[1]),
                        "Departure"));
            }
        }
        scheduler.add(new Event(globalTime + rand(arrivalInterval[0], arrivalInterval[1]), "Arrival"));
    }

    /*
     * Event handling (DEPARTURE)
     */
    public void departure() {
        queue--;
        if (queue >= servers) {
            scheduler.add(new Event(globalTime + rand(serviceInterval[0], serviceInterval[1]), "Departure"));
        }
    }

    /*
     * Base transformation (obtaining a pseudo-random number)
     */
    public double rand(double a, double b) {
        return ((b - a) * randomNumbers.pop()) + a;
    }
        /*
         * Generates a hashmap according to the number of queue states
         */
        public void generateHashMap () {
            for (int i = 0; i <= k; i++) {
                states.put(i, (float) 0);
            }
        }

        /*
         * Returns the probability stored in each queue state
         */
        public double getProbability ( int i){
            return states.get(i);
        }

}
