package repository;

import database.DatabaseConnectionFactory;
import model.Action;
import model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActionRepository implements GenericRepository<Action> {

    private final Connection connection;
    private final UserRepository userRepository;

    public ActionRepository(UserRepository userRepository) {
        this.connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
        this.userRepository = userRepository;
    }

    public List<Optional<Action>> getAllBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int userID) throws SQLException {

        List<Optional<Action>> actions = new ArrayList<>();

        PreparedStatement insertClientStatement = connection
                .prepareStatement("SELECT * FROM `Action` WHERE `user_id`=? AND `date` BETWEEN ?,?", Statement.RETURN_GENERATED_KEYS);
        insertClientStatement.setInt(1, userID);
        insertClientStatement.setString(2, startDate.toString());
        insertClientStatement.setString(3, endDate.toString());

        ResultSet resultSet = insertClientStatement.executeQuery();

        while (resultSet.next()) {
            actions.add(resultSetToObject(resultSet));
        }

        return actions;
    }

    @Override
    public void add(Action object) throws SQLException {

    }

    @Override
    public void addWithId(Action object) throws SQLException {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Action object) {

    }

    @Override
    public Optional<Action> getByID(int id) {
        return Optional.empty();
    }

    @Override
    public List<Optional<Action>> getAll() {
        return null;
    }

    private Optional<Action> resultSetToObject(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new Action()
                        .setId(resultSet.getInt("id"))
                        .setUser(userRepository.getByID(resultSet.getInt("user_id")).orElse(new User()))
                        .setName(resultSet.getString("action"))
                        .setDetails(resultSet.getString("action_details"))
                        .setDate(LocalDateTime.parse(resultSet.getString("date"))));
    }
}
