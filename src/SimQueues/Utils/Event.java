package SimQueues.Utils;

/*
    Modeling of the event;
    It has a name (arrival or departure)
    and occurrence time;
*/
public class Event implements Comparable<Event> {
    public String name;
    public double time;

    public Event(double time, String name) {
        this.time = time;
        this.name = name;
    }

    public double getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Event ev) {
        return Double.compare(time, ev.time);
    }

    @Override
    public String toString() {
        return "Name: " + this.name + ", Time: " + this.time;
    }
}
