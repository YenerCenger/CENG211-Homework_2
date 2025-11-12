package output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import model.Applicant;
import model.Application;
import model.MeritBasedApplication;
import model.NeedBasedApplication;
import model.ResearchGrantApplication;

public class ResultPrinter {

    public void printAll(Map<Integer, Applicant> applicants) {
        if (applicants == null || applicants.isEmpty()) {
            System.out.println("No applications to evaluate.");
            return;
        }

        System.out.println("================ Scholarship Results ================");

        List<Integer> sortedIds = new ArrayList<>(applicants.keySet());
        Collections.sort(sortedIds);

        for (Integer id : sortedIds) {
            Applicant applicant = applicants.get(id);
            Application application = buildApplication(applicant);
            if (application == null) {
                System.out.printf("Unknown scholarship category for applicant %d.%n%n", id);
                continue;
            }

            application.evaluate();
            printApplication(application);
            System.out.println("------------------------------------------------------");
            System.out.println();
        }
    }

    private Application buildApplication(Applicant applicant) {
        if (applicant == null) {
            return null;
        }

        String id = String.valueOf(applicant.getApplicantID());
        if (id.startsWith("11")) {
            return new MeritBasedApplication(applicant);
        }
        if (id.startsWith("22")) {
            return new NeedBasedApplication(applicant);
        }
        if (id.startsWith("33")) {
            return new ResearchGrantApplication(applicant);
        }
        return null;
    }

    private void printApplication(Application application) {
        System.out.printf("Applicant ID   : %s%n", application.getApplicantID());
        System.out.printf("Applicant Name : %s%n", application.getApplicantName());
        System.out.printf("Scholarship    : %s%n", resolveScholarshipName(application));
        String status = application.getStatus() != null ? application.getStatus() : "-";
        System.out.printf("Status         : %s%n", status);

        String awardType = application.getType() != null ? application.getType() : "-";
        System.out.printf("Award Type     : %s%n", awardType);

        String duration = formatDuration(application.getDuration());
        System.out.printf("Duration       : %s%n", duration);

        String reason = application.getReason();
        if ("Rejected".equalsIgnoreCase(status) && reason != null && !reason.isBlank()) {
            System.out.printf("Reason         : %s%n", reason);
        }
    }

    private String resolveScholarshipName(Application application) {
        if (application instanceof MeritBasedApplication) {
            return "Merit-Based Scholarship";
        }
        if (application instanceof NeedBasedApplication) {
            return "Need-Based Scholarship";
        }
        if (application instanceof ResearchGrantApplication) {
            return "Research Grant";
        }
        return "Unknown";
    }

    private String formatDuration(int durationInMonths) {
        if (durationInMonths <= 0) {
            return "-";
        }
        if (durationInMonths % 12 == 0) {
            int years = durationInMonths / 12;
            return years == 1 ? "1 year" : years + " years";
        }
        return durationInMonths + " months";
    }
}
