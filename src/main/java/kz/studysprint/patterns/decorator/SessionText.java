package kz.studysprint.patterns.decorator;

// DECORATOR: a schedule entry can gain breaks and tips independently.
public interface SessionText {
    String describe();
    int totalMinutes();
}
