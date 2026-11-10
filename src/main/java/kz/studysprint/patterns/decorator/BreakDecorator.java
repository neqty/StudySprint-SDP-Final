package kz.studysprint.patterns.decorator;

public class BreakDecorator implements SessionText {
    private final SessionText inner;
    public BreakDecorator(SessionText inner) { this.inner = inner; }
    @Override public String describe() { return inner.describe() + " + 5 min break"; }
    @Override public int totalMinutes() { return inner.totalMinutes() + 5; }
}
