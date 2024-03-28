package SimQueues.Utils;

import java.util.HashMap;
import java.util.LinkedList;
import SimQueues.SimpleQueue;

public class Utils {

    public static void calculateAverageOfFiveExecutions(String name, int k, SimpleQueue queue1, SimpleQueue queue2, SimpleQueue queue3,
                                                        SimpleQueue queue4, SimpleQueue queue5) {
        System.out.println("\nAverage - " + name);
        double calc = 0;

        for (int i = 0; i <= k; i++) {
            calc = queue1.getProbability(i) +
                    queue2.getProbability(i) +
                    queue3.getProbability(i) +
                    queue4.getProbability(i) +
                    queue5.getProbability(i);
            System.out.println("State " + i + ": " + ((calc) / k) + " %");
        }
        System.out.println("Total State: 100%");
    }

    public static void printResult(int servers, HashMap<Integer, Float> states, double totalTime, int k) {
        System.out.println("\nG/G/" + servers + "/" + k);
        for (int i = 0; i <= k; i++) {
            System.out.println("State " + i + ": ");
            String string = String.format("        Probability: %.4f | ", (states.get(i) / totalTime) * 100);
            String stringTwo = String.format("Time: %.4f", states.get(i));
            System.out.println(string + stringTwo);

            states.replace(i, (float) ((states.get(i) / totalTime) * 100));
        }
        System.out.println("Total State: ");
        String stringThree = String.format("        Probability: 100.00 | Time: %.4f", totalTime);
        System.out.println(stringThree);
    }

    public static void linearCongruentialMethod(int totalRandomNumbers, int seed, LinkedList<Float> randomNumbers,
                                                boolean lecture) {
        if (lecture) {
            randomNumbers.add((float) 0.3276);
            randomNumbers.add((float) 0.8851);
            randomNumbers.add((float) 0.1643);
            randomNumbers.add((float) 0.5542);
            randomNumbers.add((float) 0.6813);
            randomNumbers.add((float) 0.7221);
            randomNumbers.add((float) 0.9881);
        } else {
            float aux = seed;
            for (int i = 0; i < totalRandomNumbers; i++) {
                aux = (float) (((aux * (totalRandomNumbers - i)) + 74) % Math.pow(2, 32));
                randomNumbers.add((float) (aux / Math.pow(2, 32)));
            }
        }
    }
}
