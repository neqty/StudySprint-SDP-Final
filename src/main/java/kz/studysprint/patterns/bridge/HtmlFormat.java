package kz.studysprint.patterns.bridge;

import java.util.List;
public class HtmlFormat implements ReportFormat {
    private String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
    @Override public String write(String heading, List<String> lines) {
        StringBuilder html = new StringBuilder("<!doctype html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>");
        html.append(escape(heading)).append("</title><style>body{font:16px Arial;max-width:850px;margin:40px auto;color:#182840;line-height:1.6}h1{color:#123766}li{margin:7px 0}</style></head><body><h1>");
        html.append(escape(heading)).append("</h1><ul>");
        for (String line : lines) html.append("<li>").append(escape(line)).append("</li>");
        return html.append("</ul></body></html>").toString();
    }
}
