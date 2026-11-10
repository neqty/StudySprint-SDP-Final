package kz.studysprint.patterns.bridge;

import java.util.List;
public class PlainTextFormat implements ReportFormat {
    @Override public String write(String heading, List<String> lines) {
        return heading + "\n" + "=".repeat(heading.length()) + "\n\n" + String.join("\n", lines) + "\n";
    }
}
