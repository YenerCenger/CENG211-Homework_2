package app;

import output.ResultPrinter;
import service.ApplicationFactory;

/**
 * Entry point for the scholarship evaluation application.
 */
public class Main {

    private static final String CSV_PATH = "Files/ScholarshipApplications.csv";

    public static void main(String[] args) {
        ScholarshipEvaluationApp application = new ScholarshipEvaluationApp(
                new ApplicationFactory(CSV_PATH),
                new ResultPrinter()
        );
        application.run();
    }

    private static final class ScholarshipEvaluationApp {
        private final ApplicationFactory factory;
        private final ResultPrinter printer;

        private ScholarshipEvaluationApp(ApplicationFactory factory, ResultPrinter printer) {
            this.factory = factory;
            this.printer = printer;
        }

        private void run() {
            factory.loadData();
            printer.printAll(factory.getApplicants());
        }
    }
}