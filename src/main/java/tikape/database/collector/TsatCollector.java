/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.database.collector;

import java.sql.ResultSet;
import java.sql.SQLException;
import tikape.database.Collector;
import tikape.pojo.Tsat;

/**
 *
 * @author mcraty
 */
public class TsatCollector implements Collector<Tsat>{

    @Override
    public Tsat collect(ResultSet rs) throws SQLException {
        return new Tsat(rs.getInt("id"), rs.getString("text"), rs.getString("username"));
    }
    
}
