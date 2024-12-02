package DAO;

import entitys.User;

import java.sql.*;

public class UserRealization extends BaseDAO implements UserDAO {

    @Override
    public void signUp(String login,String password, String name, String lastname) {
        try{
            String insert = "INSERT INTO person.info VALUES (?,?,?,?);";
            PreparedStatement ps = getConn().prepareStatement(insert);

            ps.setString(1,login);
            ps.setString(2,password);
            ps.setString(3,name);
            ps.setString(4,lastname);
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        try {
            String insert_stat = "INSERT INTO person.status (user_login, status, last_changed) VALUES (?,?,?);";
            PreparedStatement ps = getConn().prepareStatement(insert_stat);

            ps.setString(1,login);
            ps.setString(2,"Offline");
            ps.setString(3, "Not today");

            ps.executeUpdate();
        }catch (SQLException e){
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
    public ResultSet authorise(String login, String password){
        ResultSet rs = null;
        try {
            String select = "SELECT * FROM person.info where login = ? and password = ?";
            PreparedStatement ps = getConn().prepareStatement(select);

            ps.setString(1,login);
            ps.setString(2,password);
            rs = ps.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            return rs;
        }
    }

    @Override
    public ResultSet get_name(String login){
        ResultSet rs = null;

        try {
            String select = "SELECT * FROM person.info WHERE login = ?";
            PreparedStatement ps = getConn().prepareStatement(select);

            ps.setString(1,login);
            rs = ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            return rs;
        }
    }

    @Override
    public boolean change_user(User us,String name, String lastname){
        if(name.isBlank() || lastname.isBlank())
            return false;
        try {
            String select = "UPDATE info SET name = ?, surname = ? WHERE login = ?;";
            PreparedStatement ps = getConn().prepareStatement(select);

            ps.setString(1,name);
            ps.setString(2,lastname);
            ps.setString(3,us.login);
            ps.executeUpdate();
        }catch (SQLException e){
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
    public void delete_user(User us){
        try {
            String select = "DELETE FROM person.status WHERE user_login = ?;";
            PreparedStatement ps = getConn().prepareStatement(select);

            ps.setString(1,us.login);
            ps.executeUpdate();

            select = "DELETE FROM person.message WHERE sender = ?;";
            ps = getConn().prepareStatement(select);

            ps.setString(1,us.login);
            ps.executeUpdate();

            select = "DELETE FROM person.message WHERE getter = ?;";
            ps = getConn().prepareStatement(select);

            ps.setString(1,us.login);
            ps.executeUpdate();

            select = "DELETE FROM person.info WHERE login = ?;";
            ps = getConn().prepareStatement(select);

            ps.setString(1,us.login);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            try {
                conn.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean check_reg(String login,String password, String name, String lastname){
        name=name.trim();
        login=login.trim();
        password=password.trim();
        lastname=lastname.trim();

        if(!login.isBlank() && !password.isBlank() && !name.isBlank() && !lastname.isBlank()){
            UserRealization us = new UserRealization();

            try {
                ResultSet rs = us.get_name(login);
                if(!rs.next())
                    return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
