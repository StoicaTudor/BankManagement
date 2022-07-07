package repository;

import database.DatabaseConnectionFactory;
import model.Account;
import model.Client;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository implements GenericRepository<Account> {

    private final Connection connection;
    private final ClientRepository clientRepository;

    public AccountRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
    }

    @Override
    public void add(Account object) throws SQLException {

        PreparedStatement insertAccountStatement = connection
                .prepareStatement("INSERT INTO `Account` VALUES (null, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        insertAccountStatement.setInt(1, object.getClient().getId());
        insertAccountStatement.setString(2, object.getIdentificationNumber());
        insertAccountStatement.setDouble(3, object.getBalance());
        insertAccountStatement.setString(4, object.getCurrency());
        insertAccountStatement.setString(5, object.getCreationDate().toString());
        insertAccountStatement.executeUpdate();
    }

    @Override
    public void addWithId(Account object) throws SQLException {
        PreparedStatement insertAccountStatement = connection
                .prepareStatement("INSERT INTO `Account` VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        insertAccountStatement.setInt(1, object.getId());
        insertAccountStatement.setInt(2, object.getClient().getId());
        insertAccountStatement.setString(3, object.getIdentificationNumber());
        insertAccountStatement.setDouble(4, object.getBalance());
        insertAccountStatement.setString(5, object.getCurrency());
        insertAccountStatement.setString(6, object.getCreationDate().toString());
        insertAccountStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement insertAccountStatement = connection
                .prepareStatement("DELETE FROM `Account` WHERE `id`=?", Statement.RETURN_GENERATED_KEYS);
        insertAccountStatement.setInt(1, id);
        insertAccountStatement.executeUpdate();
    }

    @Override
    public void update(Account object) throws SQLException {
        delete(object.getId());
        addWithId(object);
    }

    @Override
    public Optional<Account> getByID(int id) throws SQLException {
        PreparedStatement insertAccountStatement = connection
                .prepareStatement("SELECT * FROM `Account` WHERE `id`=?", Statement.RETURN_GENERATED_KEYS);
        insertAccountStatement.setInt(1, id);

        ResultSet resultSet = insertAccountStatement.executeQuery();
        resultSet.next();

        return resultSetToObject(resultSet);
    }

    @Override
    public List<Optional<Account>> getAll() throws SQLException {
        PreparedStatement insertAccountStatement = connection
                .prepareStatement("SELECT * FROM `Account`", Statement.RETURN_GENERATED_KEYS);

        ResultSet resultSet = insertAccountStatement.executeQuery();

        List<Optional<Account>> Accounts = new ArrayList<>();

        while (resultSet.next()) {
            Accounts.add(resultSetToObject(resultSet));
        }

        return Accounts;
    }

    public Optional<Account> getByIdentificationNumber(String identificationNumber) throws SQLException {
        PreparedStatement insertAccountStatement = connection
                .prepareStatement("SELECT * FROM `Account` WHERE `identification_number` = ?", Statement.RETURN_GENERATED_KEYS);
        insertAccountStatement.setString(1, identificationNumber);

        ResultSet resultSet = insertAccountStatement.executeQuery();
        resultSet.next();

        return resultSetToObject(resultSet);
    }

    private Optional<Account> resultSetToObject(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new Account()
                        .setId(resultSet.getInt("id"))
                        .setClient(clientRepository.getByID(Integer.parseInt(resultSet.getString("client_id"))).orElse(new Client()))
                        .setIdentificationNumber(resultSet.getString("identification_number"))
                        .setBalance(Float.parseFloat(resultSet.getString("sum")))
                        .setCurrency(resultSet.getString("currency"))
                        .setCreationDate(LocalDateTime.now()));
    }
}
