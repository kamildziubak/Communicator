package com.communicator.dao;

import com.communicator.module.Message;
import com.communicator.module.User;

import java.sql.*;
import java.util.GregorianCalendar;

public class MessageDao {
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/communicator", "root", "mjktm");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public MessageDao(String url, String user, String password)
    {
        try {
            connection= DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public MessageDao(Connection connection)
    {
        this.connection=connection;
    }

    public boolean messageExists(Integer id)
    {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(msg_id) FROM message WHERE msg_id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();

            if(result.getInt(1)==1) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Message getMessageById(Integer id)
    {
        try {
            if (messageExists(id))
            {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM message WHERE msg_id=?");
                statement.setInt(1, id);
                ResultSet result = statement.executeQuery();
                result.next();

                UserDao userDao = new UserDao(connection);

                Integer msg_id = result.getInt("msg_id");
                String text = result.getString("text");
                GregorianCalendar date = new GregorianCalendar();
                date.setGregorianChange(result.getDate("date"));
                User send_by = userDao.getUserByLogin(result.getString("send_by"));
                User send_to = userDao.getUserByLogin(result.getString("send_to"));
                Boolean isRead = false;

                if (result.getString("isRead") == "T")
                    isRead = true;

                return new Message(msg_id, text, date, send_by, send_to, isRead);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        throw new IllegalArgumentException("message not found");
    }

    public int InsertMessage(Message message)
    {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO message (text, send_by, send_to) VALUES (?, ?, ?)");
            statement.setString(1, message.getText());
            statement.setString(2, message.getSend_by().getLogin());
            statement.setString(3, message.getSend_to().getLogin());
            statement.executeUpdate();
            return 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public Message[] getMessagesBetweenUsers(User[] users)
    {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(msg_id) FROM(SELECT * FROM message WHERE send_by=? AND send_to=? UNION SELECT * FROM message WHERE send_to=? AND send_by=?) message");
            statement.setString(1, users[0].getLogin());
            statement.setString(2, users[1].getLogin());
            statement.setString(3, users[0].getLogin());
            statement.setString(4, users[1].getLogin());
            ResultSet result = statement.executeQuery();
            result.next();

            int length = result.getInt(1);
            Message[] messages = new Message[length];

            statement = connection.prepareStatement("SELECT msg_id FROM message WHERE send_by=? AND send_to=? UNION SELECT * FROM message WHERE send_to=? AND send_by=?");
            result=statement.executeQuery();

            int i=0;
            while(result.next())
                messages[i]= getMessageById(result.getInt(1));

            return messages;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new IllegalArgumentException("messages not found");
    }

    public int removeMessage(Integer id)
    {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM message WHERE msg_id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            return 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}