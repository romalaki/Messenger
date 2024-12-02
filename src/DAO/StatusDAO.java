package DAO;

import entitys.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface StatusDAO {

    public void set_status(String stat, User us);
    public ResultSet get_status(String login);
}
