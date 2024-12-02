package DAO;

import entitys.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO{

    public void delete_user(User us);
    public ResultSet get_name(String login);
    public boolean change_user(User us,String name, String lastname);

    public ResultSet authorise(String login, String password);
    public void signUp(String login,String password, String name, String lastname);

}
