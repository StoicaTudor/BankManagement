package service.reports.factory;

import model.User;
import service.reports.model.Type;

import java.time.LocalDateTime;

public interface IReportsGeneratorFactory {
    void generateReport(Type reportType, User user, LocalDateTime startDate, LocalDateTime endDate);
}
