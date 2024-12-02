package DAO;

import entitys.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MessageDAO {

    public ResultSet get_sented(String sender);
    public ResultSet get_owned(String getter);

    public ResultSet get_message(int id);

    public boolean change_message(int id, String getter, String message) ;
    public boolean send_message(User sender, String getter, String message);

}
