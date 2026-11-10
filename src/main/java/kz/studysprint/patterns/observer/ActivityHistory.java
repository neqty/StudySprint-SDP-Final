package kz.studysprint.patterns.observer;

import java.util.*;
public class ActivityHistory implements PlanObserver {
    private final List<String> messages = new ArrayList<>();
    @Override public void onPlanChanged(String message) {
        messages.add(message);
        if (messages.size() > 30) messages.remove(0);
    }
    public List<String> getMessages() { return new ArrayList<>(messages); }
}
