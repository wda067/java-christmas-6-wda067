package christmas.service;

public class EventService {

    public int calculateXmasDiscount(int day) {
        return -1 * (1000 + (day - 1) * 100);
    }
}
