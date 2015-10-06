package tikape.database.collector;

import java.sql.ResultSet;
import java.sql.SQLException;
import tikape.database.Collector;
import tikape.pojo.User;

public class UserCollector implements Collector<User> {

    @Override
    public User collect(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"));
    }

}
