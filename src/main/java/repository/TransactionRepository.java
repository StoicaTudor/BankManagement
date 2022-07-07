package repository;

import model.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TransactionRepository implements GenericRepository<Transaction>{

    @Override
    public void add(Transaction object) throws SQLException {

    }

    @Override
    public void addWithId(Transaction object) throws SQLException {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Transaction object) {

    }

    @Override
    public Optional<Transaction> getByID(int id) {
        return Optional.empty();
    }

    @Override
    public List<Optional<Transaction>> getAll() {
        return null;
    }
}
