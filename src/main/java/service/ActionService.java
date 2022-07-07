package service;

import model.Action;
import model.User;
import repository.ActionRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ActionService {

    private final ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public List<Action> getActionsBetweenDates(LocalDateTime startDate, LocalDateTime endDate, User user) {
        try {
            return this.actionRepository
                    .getAllBetweenDates(startDate, endDate, user.getId())
                    .stream()
                    .map(action -> action.orElse(new Action()))
                    .toList();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
