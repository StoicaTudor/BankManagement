package repository;

import model.Action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericRepository<Type> {

    void add(Type object) throws SQLException;

    void addWithId(Type object) throws SQLException;

    void delete(int id) throws SQLException;

    void update(Type object) throws SQLException;

    Optional<Type> getByID(int id) throws SQLException;

    List<Optional<Type>> getAll() throws SQLException;
}
