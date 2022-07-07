package service.reports.factory;

import model.User;
import service.ActionService;
import service.reports.TXTReportGenerator;
import service.reports.model.Type;

import java.time.LocalDateTime;

public class ReportsGeneratorFactory implements IReportsGeneratorFactory {

    private final ActionService actionService;

    public ReportsGeneratorFactory(ActionService actionService) {
        this.actionService = actionService;
    }

    @Override
    public void generateReport(Type reportType, User user, LocalDateTime startDate, LocalDateTime endDate) {

        switch (reportType) {
            case TXT:
                new TXTReportGenerator(this.actionService)
                        .generateReport(startDate, endDate, user);
                break;

            default:
                System.out.println("Unknown Report Type");
        }
    }
}
