package service.reports;

import model.User;

import java.time.LocalDateTime;

public interface IReportGenerator {
    void generateReport(LocalDateTime startDate, LocalDateTime endDate, User user);
}
