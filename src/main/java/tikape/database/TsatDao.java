/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.database;

import java.sql.SQLException;
import java.util.List;
import tikape.database.collector.TsatCollector;
import tikape.pojo.Tsat;

/**
 *
 * @author mcraty
 */
public class TsatDao implements Dao<Tsat, Integer> {
    
    private Database database;
    
    public TsatDao(Database database) {
        this.database = database;
    }

    @Override
    public Tsat findOne(Integer key) throws SQLException {
        List<Tsat> messages = this.database.queryAndCollect("SELECT * FROM Tsat WHERE id = ?"
                , new TsatCollector(), key);
        
        return null;
    }

    @Override
    public void save(Tsat tsat) throws SQLException {
        //this.database.update("INSERT INTO User (name, username, password) VALUES (?, ?, ?)", user.getName(), user.getUsername(), user.getPassword());
        this.database.update("INSERT INTO Tsat (text, username) VALUES (?, ?)", tsat.getText(), tsat.getUsername());        
    }

    @Override
    public List<Tsat> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Tsat> findlastTenMessages() throws SQLException{
        List<Tsat> messages = this.database.queryAndCollect("SELECT * FROM Tsat ORDER BY id DESC LIMIT 10", new TsatCollector());
        
        return messages;
    }
}
