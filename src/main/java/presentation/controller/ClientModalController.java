package presentation.controller;

import model.Client;
import presentation.view.ClientModal;
import service.ClientService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientModalController {

    private final ClientModal clientModal;
    private final ClientService clientService;

    public ClientModalController(ClientModal clientModal, ClientService clientService) {
        this.clientModal = clientModal;
        this.clientService = clientService;

        this.addActionListeners();

        this.clientModal.setVisible(true);
    }

    private void addActionListeners() {
        this.clientModal.setActionListenerToSubmitButton(new SubmitButtonListener());
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int id = 0;

            try {
                id = Integer.parseInt(clientModal.getId());
            } catch (NumberFormatException ex) {

            }

            String name = clientModal.getName();
            String identityCardNumber = clientModal.getIdentityCardNumber();
            String personalNumericCode = clientModal.getPersonalNumericCode();
            String address = clientModal.getAddress();

            Client client = new Client()
                    .setId(id)
                    .setName(name)
                    .setIdentityCard(identityCardNumber)
                    .setPersonalCode(personalNumericCode)
                    .setAddress(address);

            switch (clientModal.getModalType()) {
                case CREATE -> clientService.createClient(client);
                case UPDATE -> clientService.updateClient(client);
            }
        }
    }

    public ClientModal getClientModal() {
        return this.clientModal;
    }
}
