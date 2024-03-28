package SimQueues;

import java.util.LinkedList;
import java.util.PriorityQueue;
import SimQueues.Utils.Event;
import SimQueues.Utils.SimQueue;
import SimQueues.Utils.Utils;

public class TandemQueue {

	private static SimQueue queue1;
	private static SimQueue queue2;
	private static double globalTime = 0;
	private PriorityQueue<Event> scheduler;
	private LinkedList<Float> randomNumbers;
	private double[] arrivalInterval;
	private int numberOfRandoms;
	private int seed;

	public TandemQueue(SimQueue queue1, SimQueue queue2, double[] arrivalInterval, double initialTime, int n, int seed) {
		this.queue1 = queue1;
		this.queue2 = queue2;
		this.arrivalInterval = arrivalInterval;
		this.numberOfRandoms = n;
		this.seed = seed;
		scheduler = new PriorityQueue<Event>();
		randomNumbers = new LinkedList<Float>();
		Utils.linearCongruentialMethod(n, seed, randomNumbers, false);
		scheduler.add(new Event(initialTime, "Arrival"));
	}

	public void start() {
		while (!randomNumbers.isEmpty()) {
			Event event = scheduler.poll();
			queue1.count(queue1.getF(), (event.time - globalTime) + queue1.get(queue1.getF()));
			queue2.count(queue2.getF(), (event.time - globalTime) + queue2.get(queue2.getF()));
			globalTime = event.time;

			if (event.name.equals("Arrival")) {
				if (queue1.getF() < queue1.getK()) {
					queue1.increment();
					if (queue1.getF() <= queue1.getServers()) {
						scheduler.add(new Event(globalTime + rand(queue1.getStartA(), queue1.getEndA()), "Passage"));
					}
				}
				scheduler.add(new Event(globalTime + rand(arrivalInterval[0], arrivalInterval[1]), "Arrival"));
			}

			if (event.name.equals("Passage")) {
				queue1.decrement();
				if (queue1.getF() >= queue1.getServers()) {
					scheduler.add(new Event(globalTime + rand(queue1.getStartA(), queue1.getEndA()), "Passage"));
				}
				if (queue2.getF() < queue2.getK()) {
					queue2.increment();
					if (queue2.getF() <= queue2.getServers()) {
						scheduler.add(new Event(globalTime + rand(queue2.getStartA(), queue2.getEndA()), "Departure"));
					}
				}
			}

			if (event.name.equals("Departure")) {
				queue2.decrement();
				if (queue2.getF() >= queue2.getServers()) {
					scheduler.add(new Event(globalTime + rand(queue2.getStartA(), queue2.getEndA()), "Departure"));
				}
			}
		}
		printResults();
	}

	/*
	 * Base conversion (obtaining a pseudo-random number)
	 */
	public double rand(double a, double b) {
		return ((b - a) * randomNumbers.pop()) + a;
	}

	public static void printResults() {
		System.out.println("\nQueue 1");
		for (int i = 0; i <= queue1.getK(); i++) {
			System.out.println("State " + i + ": ");
			String probabilityString = String.format("        Probability: %.4f | ", (queue1.get(i) / globalTime) * 100);
			String timeString = String.format("Time: %.4f", queue1.get(i));
			System.out.println(probabilityString + timeString);
		}

		System.out.println("\nQueue 2");
		for (int i = 0; i <= queue2.getK(); i++) {
			System.out.println("State " + i + ": ");
			String probabilityString = String.format("        Probability: %.4f | ", (queue2.get(i) / globalTime) * 100);
			String timeString = String.format("Time: %.4f", queue2.get(i));
			System.out.println(probabilityString + timeString);
		}

		System.out.println("Total State: ");
		String totalString = String.format("        Probability: 100.00 | Global Time: %.4f", globalTime);
		System.out.println(totalString);
	}
}

