package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO implements DAO{
    public Connection conn;

    @Override
    public Connection getConn() {
        String url = "jdbc:mysql://localhost:3306/person";
        String user = "root";
        String password = "eveeve103";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn= DriverManager.getConnection(url,user,password);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return conn;
    }
}
