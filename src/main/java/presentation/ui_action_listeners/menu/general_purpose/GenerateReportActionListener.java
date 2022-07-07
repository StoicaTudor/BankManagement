package presentation.ui_action_listeners.menu.general_purpose;

import model.User;
import presentation.view.Menu;
import service.UserService;
import service.reports.factory.IReportsGeneratorFactory;
import service.reports.model.Type;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class GenerateReportActionListener implements ActionListener {

    private final Menu menuView;
    private final IReportsGeneratorFactory reportsGeneratorFactory;
    private final UserService userService;

    public GenerateReportActionListener(
            Menu menuView,
            IReportsGeneratorFactory reportsGeneratorFactory,
            UserService userService) {
        this.menuView = menuView;
        this.reportsGeneratorFactory = reportsGeneratorFactory;
        this.userService = userService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        User user = null;

        try {
            int userId = this.menuView.getSelectedUserId();
            user = userService.getUser(userId);
        } catch (Exception ex) {
            ex.printStackTrace();
            this.menuView.alert("An error has occured");
        }

        LocalDateTime startDate = LocalDateTime.parse(this.menuView.getStartDate());
        LocalDateTime endDate = LocalDateTime.parse(this.menuView.getEndDate());

        reportsGeneratorFactory.generateReport(Type.TXT, user, startDate, endDate);
    }
}
