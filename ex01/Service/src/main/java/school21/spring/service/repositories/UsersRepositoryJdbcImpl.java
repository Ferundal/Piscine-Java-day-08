package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private DataSource dataSource;

    public UsersRepositoryJdbcImpl (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        final String QUERY_TEMPLATE = "SELECT * FROM service.users WHERE identifier = ";
        Connection connection = null;
        User tempUser = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            String query = QUERY_TEMPLATE + id;
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();

            tempUser = new User(resultSet.getLong(1), resultSet.getString(2));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tempUser;
    }

    @Override
    public List<User> findAll() {
        ArrayList<User> result = new ArrayList<>();
        final String QUERY_TEMPLATE = "SELECT * FROM service.users";
        Connection connection = null;
        User tempUser = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(QUERY_TEMPLATE);
            while (resultSet.next()) {
                tempUser = new User(resultSet.getLong(1), resultSet.getString(2));
                result.add(tempUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;

    }

    @Override
    public void save(User entity) {
        final String QUERY_TEMPLATE = "INSERT INTO service.users (email) VALUES (?) RETURNING *";
        PreparedStatement query;
        try {
            Connection connection = dataSource.getConnection();
            query = connection.prepareStatement(QUERY_TEMPLATE);

            try {
                query.setString(1, entity.getEmail());
            } catch (NullPointerException nullPointerException) {
                throw new RuntimeException();
            }
        } catch (java.sql.SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
        ResultSet resultSet;
        try {
            resultSet = query.executeQuery();
        } catch (java.sql.SQLException sqlException) {
            throw new RuntimeException();
        }
        try {
            resultSet.next();
            entity.setIdentifier(resultSet.getLong("identifier"));
        } catch (java.sql.SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public void update(User entity) {
        final String QUERY_TEMPLATE = "UPDATE service.users SET email = ? WHERE identifier = ? RETURNING *";
        PreparedStatement query;
        try {
            Connection connection = dataSource.getConnection();
            query = connection.prepareStatement(QUERY_TEMPLATE);

            try {
                query.setString(1, entity.getEmail());
                query.setLong(2, entity.getIdentifier());
                query.executeQuery();
            } catch (NullPointerException nullPointerException) {
                throw new RuntimeException();
            }
        } catch (java.sql.SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public void delete(Long id) {
        final String QUERY_TEMPLATE = "DELETE FROM service.users WHERE identifier = ? RETURNING *";
        Connection connection = null;
        User tempUser = null;
        Optional<User> optionalMessage;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            PreparedStatement query = connection.prepareStatement(QUERY_TEMPLATE);
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        final String QUERY_TEMPLATE = "SELECT * FROM service.users WHERE email = ?";
        Connection connection = null;
        User tempUser = null;
        Optional<User> optionalMessage;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            PreparedStatement query = connection.prepareStatement(QUERY_TEMPLATE);
            query.setString(1, email);
            ResultSet resultSet = query.executeQuery();
            resultSet.next();
            tempUser = new User(resultSet.getLong(1), resultSet.getString(2));
            optionalMessage = Optional.of(tempUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalMessage;
    }
}
