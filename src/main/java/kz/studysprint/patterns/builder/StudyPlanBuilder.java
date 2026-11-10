package kz.studysprint.patterns.builder;

import kz.studysprint.model.StudyPlan;
import java.util.UUID;

// BUILDER: optional settings do not make the StudyPlan constructor long in the UI.
public class StudyPlanBuilder {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String strategy = "Priority first";
    private boolean breaksEnabled = true;
    private boolean tipsEnabled = true;

    public StudyPlanBuilder id(String value) { id = value; return this; }
    public StudyPlanBuilder name(String value) { name = value; return this; }
    public StudyPlanBuilder strategy(String value) { strategy = value; return this; }
    public StudyPlanBuilder breaks(boolean value) { breaksEnabled = value; return this; }
    public StudyPlanBuilder tips(boolean value) { tipsEnabled = value; return this; }
    public StudyPlan build() { return new StudyPlan(id, name, strategy, breaksEnabled, tipsEnabled); }
}
