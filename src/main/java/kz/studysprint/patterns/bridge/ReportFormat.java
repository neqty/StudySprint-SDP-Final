package kz.studysprint.patterns.bridge;

import java.util.List;
// BRIDGE implementation side: choose output format independently of report type.
public interface ReportFormat { String write(String heading, List<String> lines); }
