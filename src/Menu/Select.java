package Menu;

import SimQueues.SimpleQueue;
import SimQueues.TandemQueue;
import SimQueues.Utils.SimQueue;
import SimQueues.Utils.Utils;

public class Select {

    public static void ExampleClass() {
        /*
         * CLASS EXAMPLE:
         * G/G/1/3, arrivals between 1..2, service time between 3..6
         * Random numbers -> 7 | They are | ([0.3276, 0.8851, 0.1643, 0.5542, 0.6813,
         * 0.7221, 0.9881])
         * Initial time -> 2
         * No average of executions
         */
        double[] arrivalInterval = { 1, 2 };
        double[] serviceInterval = { 3, 6 };
        SimpleQueue classExample = new SimpleQueue(1, 3, arrivalInterval, serviceInterval, 2.0, 7);
        classExample.start();
    }

    public static void PartOne() {
        /*
         * G/G/1/5, arrivals between 2..4, service time between 3..5
         * G/G/2/5, arrivals between 2..4, service time between 3..5
         * Random numbers -> 100,000
         * Initial time -> 3
         * Average of 5 executions
         */
        double[] arrivalInterval = { 2, 4 };
        double[] serviceInterval = { 3, 5 };
        SimpleQueue execution1 = new SimpleQueue(1, 5, arrivalInterval, serviceInterval, 3.0, 100000, 897328);
        SimpleQueue execution2 = new SimpleQueue(1, 5, arrivalInterval, serviceInterval, 3.0, 100000, 4382919);
        SimpleQueue execution3 = new SimpleQueue(1, 5, arrivalInterval, serviceInterval, 3.0, 100000, 293922105);
        SimpleQueue execution4 = new SimpleQueue(1, 5, arrivalInterval, serviceInterval, 3.0, 100000, 1395839);
        SimpleQueue execution5 = new SimpleQueue(1, 5, arrivalInterval, serviceInterval, 3.0, 100000, 999941);

        execution1.start();
        execution2.start();
        execution3.start();
        execution4.start();
        execution5.start();

        Utils.calculateAverageOfFiveExecutions("G/G/1/5", 5, execution1, execution2, execution3, execution4, execution5);
    }

    public static void PartTwo() {
        /*
         * G/G/2/5, arrivals between 2..4, service time between 3..5
         * Random numbers -> 100,000
         * Initial time -> 3
         * Average of 5 executions
         */
        double[] arrivalInterval = { 2, 4 };
        double[] serviceInterval = { 3, 5 };

        SimpleQueue execution1 = new SimpleQueue(2, 5, arrivalInterval, serviceInterval, 3.0, 100000, 129038218);
        SimpleQueue execution2 = new SimpleQueue(2, 5, arrivalInterval, serviceInterval, 3.0, 100000, 48278392);
        SimpleQueue execution3 = new SimpleQueue(2, 5, arrivalInterval, serviceInterval, 3.0,100000, 293922105);
                SimpleQueue execution4 = new SimpleQueue(2, 5, arrivalInterval, serviceInterval, 3.0, 100000, 9228282);
        SimpleQueue execution5 = new SimpleQueue(2, 5, arrivalInterval, serviceInterval, 3.0, 100000, 759595);

        execution1.start();
        execution2.start();
        execution3.start();
        execution4.start();
        execution5.start();

        Utils.calculateAverageOfFiveExecutions("G/G/2/5", 5, execution1, execution2, execution3, execution4, execution5);
    }

    public static void PartThree() {
        /*
         * SIMULATOR WITH 2 TANDEM QUEUES
         * Queue 1 - G/G/2/3, arrivals between 2..3, service time between 2..5
         * Queue 2 - G/G/1/3, service time between 3..5
         * Note that Queue 2 does not have arrivals from outside the system.
         * Queue 2 receives 100% of the customers passing through Queue 1, meaning
         * Queues 1 and 2 are in tandem where customers arrive from outside the system at Queue 1 and
         * subsequently move to Queue 2,
         * leaving the system after being served at Queue 2.
         * For the simulation, consider initially empty queues and the first customer
         * arriving at time 2.5.
         * The result of your simulation should be the average of 5 executions
         * (using different pseudo-random number seeds, of course)
         * with 100,000 random numbers used for each of these simulations. In other words,
         * when using the 100,000th random number, your simulation should end.
         */
        double[] arrivalInterval = { 2, 3 };
        double[] serviceInterval1 = { 2, 5 };
        double[] serviceInterval2 = { 3, 5 };

        SimQueue queue1 = new SimQueue(2, 3, serviceInterval1);
        SimQueue queue2 = new SimQueue(1, 3, serviceInterval2);

        TandemQueue tandemQueues = new TandemQueue(queue1, queue2, arrivalInterval, 2.5, 100000, 129038218);

        tandemQueues.start();
    }
}
