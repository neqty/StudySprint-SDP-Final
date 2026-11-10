package kz.studysprint.patterns.observer;

// OBSERVER: changes notify the GUI and the activity history.
public interface PlanObserver { void onPlanChanged(String message); }
