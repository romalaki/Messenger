package DAO;

import entitys.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusRealization extends BaseDAO implements StatusDAO{

    @Override
    public void set_status(String stat, User us) {
        String select = "UPDATE Status SET status = ?, last_changed = 'now' WHERE user_login = ?;";
        try {
            PreparedStatement ps = getConn().prepareStatement(select);
            ps.setString(1,stat);
            ps.setString(2,us.login);
            ps.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                conn.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ResultSet get_status(String login){
        String select = "SELECT * FROM person.status where user_login = ?";
        try {
            PreparedStatement ps = getConn().prepareStatement(select);
            ps.setString(1,login);
            ResultSet rs = ps.executeQuery();
            return rs;
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
