package DAO;

import entitys.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageRealization extends BaseDAO implements MessageDAO{

    @Override
    public ResultSet get_sented(String sender){
        ResultSet rs = null;
        try {

            String select = "SELECT * FROM person.message WHERE sender = ? ORDER BY id DESC";
            PreparedStatement ps = getConn().prepareStatement(select);
            ps.setString(1,sender);
            rs = ps.executeQuery();

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public ResultSet get_owned(String getter){
        ResultSet rs = null;
        try {

            String select = "SELECT * FROM person.message WHERE getter = ? ORDER BY id DESC";
            PreparedStatement ps = getConn().prepareStatement(select);
            ps.setString(1,getter);
            rs = ps.executeQuery();

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public boolean send_message(User sender, String getter, String message){
        UserRealization u = new UserRealization();
        ResultSet rs = u.get_name(getter);

        try {
            if(!rs.next())
                return false;

            String insert = "INSERT INTO person.message(sender,getter,text) VALUES (?,?,?);";
            PreparedStatement ps = getConn().prepareStatement(insert);
            ps.setString(1,sender.login);
            ps.setString(2,getter);
            ps.setString(3,message);

            ps.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            try {
                conn.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean change_message(int id, String getter, String message){
        UserRealization u = new UserRealization();
        ResultSet rs = u.get_name(getter);

        try {
            if(!rs.next())
                return false;

            String update = "UPDATE message SET getter = ?, text = ? WHERE id = ?";
            PreparedStatement ps = getConn().prepareStatement(update);
            ps.setString(1,getter);
            ps.setString(2,message);
            ps.setInt(3,id);

            ps.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ResultSet get_message(int id){
        ResultSet rs = null;
        try {
            String select = "SELECT * FROM person.message WHERE id = ?";
            PreparedStatement ps = getConn().prepareStatement(select);
            ps.setInt(1,id);
            rs = ps.executeQuery();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
