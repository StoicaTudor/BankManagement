package repository;

import database.DatabaseConnectionFactory;
import model.User;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements GenericRepository<User> {

    public Optional<User> getUserByUsernameAndPassword(String username, String password) {
        return null;
    }

    private final Connection connection;

    public UserRepository() {
        this.connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
    }

    @Override
    public void add(User object) throws SQLException {

        PreparedStatement insertUserStatement = connection
                .prepareStatement("INSERT INTO `User` VALUES (null, ?)", Statement.RETURN_GENERATED_KEYS);
        insertUserStatement.setString(1, object.getUsername());
        insertUserStatement.executeUpdate();
    }

    @Override
    public void addWithId(User object) throws SQLException {
        PreparedStatement insertUserStatement = connection
                .prepareStatement("INSERT INTO `User` VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        insertUserStatement.setInt(1, object.getId());
        insertUserStatement.setString(2, object.getUsername());
        insertUserStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement insertUserStatement = connection
                .prepareStatement("DELETE FROM `User` WHERE `id`=?", Statement.RETURN_GENERATED_KEYS);
        insertUserStatement.setInt(1, id);
        insertUserStatement.executeUpdate();
    }

    @Override
    public void update(User object) throws SQLException {
        delete(object.getId());
        addWithId(object);
    }

    @Override
    public Optional<User> getByID(int id) throws SQLException {
        PreparedStatement insertUserStatement = connection
                .prepareStatement("SELECT * FROM `User` WHERE `id`=?", Statement.RETURN_GENERATED_KEYS);
        insertUserStatement.setInt(1, id);

        ResultSet resultSet = insertUserStatement.executeQuery();
        resultSet.next();

        return resultSetToObject(resultSet);
    }

    public Optional<User> getByName(String name) throws SQLException {
        PreparedStatement insertUserStatement = connection
                .prepareStatement("SELECT * FROM `User` WHERE `name`=?", Statement.RETURN_GENERATED_KEYS);
        insertUserStatement.setString(1, name);

        ResultSet resultSet = insertUserStatement.executeQuery();
        resultSet.next();

        return resultSetToObject(resultSet);
    }

    @Override
    public List<Optional<User>> getAll() throws SQLException {
        PreparedStatement insertUserStatement = connection
                .prepareStatement("SELECT * FROM `User`", Statement.RETURN_GENERATED_KEYS);

        ResultSet resultSet = insertUserStatement.executeQuery();

        List<Optional<User>> users = new ArrayList<>();

        while (resultSet.next()) {
            users.add(resultSetToObject(resultSet));
        }

        return users;
    }

    private Optional<User> resultSetToObject(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new User()
                        .setId(resultSet.getInt("id"))
                        .setUsername(resultSet.getString("name")));
    }
}
