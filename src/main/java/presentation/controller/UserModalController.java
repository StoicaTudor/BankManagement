package presentation.controller;

import model.User;
import presentation.view.UserModal;
import service.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserModalController {

    private final UserModal userModal;
    private final UserService userService;

    public UserModalController(UserModal userModal, UserService userService) {
        this.userModal = userModal;
        this.userService = userService;

        this.addActionListeners();

        this.userModal.setVisible(true);
    }

    private void addActionListeners() {
        this.userModal.setActionListenerToSubmitButton(new SubmitButtonListener());
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int id = 0;

            try {
                id = Integer.parseInt(userModal.getId());
            } catch (NumberFormatException ex) {

            }

            String name = userModal.getName();

            User user = new User()
                    .setId(id)
                    .setUsername(name);

            switch (userModal.getModalType()) {
                case CREATE -> userService.createUser(user);
                case UPDATE -> userService.updateUser(user);
            }
        }
    }

    public UserModal getUserModal() {
        return this.userModal;
    }
}
