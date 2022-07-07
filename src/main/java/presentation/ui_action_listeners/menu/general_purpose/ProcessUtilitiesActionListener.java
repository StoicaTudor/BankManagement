package presentation.ui_action_listeners.menu.general_purpose;

import model.Account;
import service.bills.IBillsProcessor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessUtilitiesActionListener implements ActionListener {

    private final presentation.view.Menu menuView;
    private final IBillsProcessor billsProcessor;

    public ProcessUtilitiesActionListener(presentation.view.Menu menuView, IBillsProcessor billsProcessor) {
        this.menuView = menuView;
        this.billsProcessor = billsProcessor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        float sum = Float.parseFloat(this.menuView.getProcessUtilitiesSum());

        int accountId = 0;
        try {
            accountId = this.menuView.getSelectedAccountId();
        } catch (Exception ex) {
            this.menuView.alert(ex.getMessage());
        }

        billsProcessor.processBills(sum, accountId);
    }
}
