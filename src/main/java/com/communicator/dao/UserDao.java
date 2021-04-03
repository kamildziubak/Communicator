package com.communicator.dao;
import com.communicator.module.User;

import javax.xml.transform.Result;
import java.sql.*;

public class UserDao {
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/communicator", "root", "mjktm");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public UserDao(String url, String user, String password)
    {
        try {
            connection=DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public UserDao(Connection connection)
    {
        connection=connection;
    }

    public boolean userExists(String login)
    {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(login) FROM user WHERE login=?");
            statement.setString(1, login);
            ResultSet result=statement.executeQuery();
            result.next();
            if(result.getInt(1)==1) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public User getUserByLogin(String login)
    {
        try {
            if (userExists(login)){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE login=?");
            statement.setString(1,login);
            ResultSet result=statement.executeQuery();
            result.next();
            User user = new User(result.getString("login"), result.getString("password"), result.getString("name"));
            return user;}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new IllegalArgumentException("User not found");
    }

    public User[] getAllUsers()
    {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(login) FROM user");
            result.next();
            User[] users = new User[result.getInt(1)];

            result = statement.executeQuery("SELECT * FROM user");
            int i=0;
            while(result.next()){
                users[i]=new User(result.getString("login"), result.getString("password"), result.getString("name"));
                i++;
            }
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new IllegalArgumentException("Method failed to return an user");
    }

    public int registerNewUser(User user)
    {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?)");
            if(userExists(user.getLogin())) throw new IllegalArgumentException("User exists");
            else
            {
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getName());
                statement.executeUpdate();
                return 1;
            }
        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return 0;
    }

    public int deleteUser(String login)
    {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE login=?");
            if(userExists(login))
            {
                statement.setString(1, login);
                statement.executeUpdate();
                return 1;
            }
        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return 0;
    }
}
