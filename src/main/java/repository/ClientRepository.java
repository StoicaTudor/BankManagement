package repository;

import database.DatabaseConnectionFactory;
import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements GenericRepository<Client> {

    private final Connection connection;

    public ClientRepository() {
        this.connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
    }

    @Override
    public void add(Client object) throws SQLException {

        PreparedStatement insertClientStatement = connection
                .prepareStatement("INSERT INTO `Client` VALUES (null, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        insertClientStatement.setString(1, object.getName());
        insertClientStatement.setString(2, object.getIdentityCard());
        insertClientStatement.setString(3, object.getPersonalCode());
        insertClientStatement.setString(4, object.getAddress());
        insertClientStatement.executeUpdate();
    }

    @Override
    public void addWithId(Client object) throws SQLException {
        PreparedStatement insertClientStatement = connection
                .prepareStatement("INSERT INTO `Client` VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        insertClientStatement.setInt(1, object.getId());
        insertClientStatement.setString(2, object.getName());
        insertClientStatement.setString(3, object.getIdentityCard());
        insertClientStatement.setString(4, object.getPersonalCode());
        insertClientStatement.setString(5, object.getAddress());
        insertClientStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement insertClientStatement = connection
                .prepareStatement("DELETE FROM `Client` WHERE `id`=?", Statement.RETURN_GENERATED_KEYS);
        insertClientStatement.setInt(1, id);
        insertClientStatement.executeUpdate();
    }

    @Override
    public void update(Client object) throws SQLException {
        delete(object.getId());
        addWithId(object);
    }

    @Override
    public Optional<Client> getByID(int id) throws SQLException {
        PreparedStatement insertClientStatement = connection
                .prepareStatement("SELECT * FROM `Client` WHERE `id`=?", Statement.RETURN_GENERATED_KEYS);
        insertClientStatement.setInt(1, id);

        ResultSet resultSet = insertClientStatement.executeQuery();
        resultSet.next();

        return resultSetToObject(resultSet);
    }

    public Optional<Client> getByName(String name) throws SQLException {
        PreparedStatement insertClientStatement = connection
                .prepareStatement("SELECT * FROM `Client` WHERE `name`=?", Statement.RETURN_GENERATED_KEYS);
        insertClientStatement.setString(1, name);

        ResultSet resultSet = insertClientStatement.executeQuery();
        resultSet.next();

        return resultSetToObject(resultSet);
    }

    @Override
    public List<Optional<Client>> getAll() throws SQLException {
        PreparedStatement insertClientStatement = connection
                .prepareStatement("SELECT * FROM `Client`", Statement.RETURN_GENERATED_KEYS);

        ResultSet resultSet = insertClientStatement.executeQuery();

        List<Optional<Client>> clients = new ArrayList<>();

        while (resultSet.next()) {
            clients.add(resultSetToObject(resultSet));
        }

        return clients;
    }

    private Optional<Client> resultSetToObject(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new Client()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setIdentityCard(resultSet.getString("identity_card_number"))
                        .setPersonalCode(resultSet.getString("personal_numeric_code"))
                        .setAddress(resultSet.getString("address")));
    }
}
