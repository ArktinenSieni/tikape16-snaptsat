package tikape.database;

import java.sql.SQLException;
import java.util.List;
import tikape.database.collector.UserCollector;
import tikape.pojo.User;

public class UserDao implements Dao<User, Integer> {

    private Database database;

    public UserDao(Database database) {
        this.database = database;
    }

    @Override
    public User findOne(Integer key) throws SQLException {
        List<User> users = this.database.queryAndCollect("SELECT * FROM User WHERE id = ?", new UserCollector(), key);
        if (users.isEmpty()) {
            return null;
        }

        return users.get(0);
    }

    public User findByUsernameAndPassword(String username, String password) throws SQLException {
        List<User> users = this.database.queryAndCollect("SELECT * FROM User WHERE username = ? AND password = ?", new UserCollector(), username, password);
        
        if (users.isEmpty()) {
            return null;
        }
        
        return users.get(0);
    }

    @Override
    public void save(User user) throws SQLException {
        this.database.update("INSERT INTO User (name, username, password) VALUES (?, ?, ?)", user.getName(), user.getUsername(), user.getPassword());
    }

    @Override
    public List<User> findAll() throws SQLException {
        return this.database.queryAndCollect("SELECT * FROM User", new UserCollector());
    }

    @Override
    public void delete(Integer key) throws SQLException {
        this.database.update("DELETE FROM User WHERE id = ?", key);
    }

}
