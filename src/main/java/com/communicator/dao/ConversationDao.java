package com.communicator.dao;

import com.communicator.module.Conversation;
import com.communicator.module.Message;
import com.communicator.module.User;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("conversation")
public class ConversationDao {
    private Connection connection;
    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/communicator", "root", "mjktm");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ConversationDao(Connection connection) {
        this.connection = connection;
    }

    public ConversationDao()
    {
        try {
            this.connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/communicator", "root", "mjktm");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Conversation[] getAllConversationsOfTheUser(String login)
    {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(user) FROM(SELECT send_to AS user FROM message WHERE send_by=? UNION SELECT send_by AS user FROM message WHERE send_to=? GROUP BY user) AS m;");
            statement.setString(1, login);
            statement.setString(2, login);
            ResultSet result = statement.executeQuery();
            result.next();
            int numberOfConversations = result.getInt(1);

            statement = connection.prepareStatement("SELECT send_to AS user FROM message WHERE send_by=? UNION SELECT send_by AS user FROM message WHERE send_to=? GROUP BY user;");
            statement.setString(1, login);
            statement.setString(2, login);

            Conversation[] conversations = new Conversation[numberOfConversations];

            result=statement.executeQuery();

            int i=0;
            while(result.next())
            {
                String[] users = new String[2];
                users[0] = login;
                users[1] = result.getString("user");
                conversations[i] = new Conversation(users);
                i++;
            }

            return conversations;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new IllegalArgumentException("Conversations not found");
    }
}
