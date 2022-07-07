package service.reports;

import model.User;
import service.ActionService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class TXTReportGenerator implements IReportGenerator {

    private final ActionService actionService;

    public TXTReportGenerator(ActionService actionService) {
        this.actionService = actionService;
    }

    @Override
    public void generateReport(LocalDateTime startDate, LocalDateTime endDate, User user) {

        StringBuilder reportContent = new StringBuilder("Report from " + startDate + " to " + endDate + " for " + user.getId() + "\n");

        actionService.getActionsBetweenDates(startDate, endDate, user).forEach(action ->
                reportContent
                        .append(action)
                        .append(" -> ")
                        .append(action.getName())
                        .append(" at ")
                        .append(action.getDate())
                        .append("\n"));

        createFile("/home/citadin/Desktop", "report.txt", reportContent.toString());
    }

    private void createFile(String fileDirectoryPath, String fileName, String fileContent) {

        String fullFilePath = fileDirectoryPath + File.separator + fileName;

        try {
            new File(fullFilePath).createNewFile();
        } catch (IOException e) {
            System.out.println(fullFilePath + " could not have been created");
            e.printStackTrace();
        }

        writeInFile(fullFilePath, fileContent);
    }

    private void writeInFile(String filePath, String fileContent) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(fileContent);
            myWriter.close();
        } catch (IOException e) {
            System.out.println(filePath + " could not write in it");
            e.printStackTrace();
        }
    }
}
